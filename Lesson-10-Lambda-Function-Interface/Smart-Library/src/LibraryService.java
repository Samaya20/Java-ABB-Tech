import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {

    private List<Book> books;
    private List<User> users;

    public LibraryService(List<Book> books, List<User> users) {
        this.books = books;
        this.users = users;
    }

    public void sortBooks() {

        books.sort(
                Comparator.comparing(Book::getRating)
                        .reversed()
                        .thenComparing(Book::getYear)
                        .thenComparing(Book::getTitle)
        );

        System.out.println("Sorted Books:");

        books.forEach(System.out::println);
    }

    public void analyzeLibrary() {

        System.out.println("\nLibrary Analysis:");

        double average =
                books.stream()
                        .mapToDouble(Book::getRating)
                        .average()
                        .orElse(0);

        System.out.println("Average Rating: " + average);

        List<Book> availableBooks =
                books.stream()
                        .filter(book -> book.getYear() > 2000)
                        .filter(Book::isAvailable)
                        .collect(Collectors.toList());

        System.out.println("Available after 2000:");
        availableBooks.forEach(System.out::println);

        Map<String, Long> borrowedBooks =
                users.stream()
                        .flatMap(user -> user.getBorrowHistory().stream())
                        .collect(Collectors.groupingBy(
                                record -> record.getBook().getTitle(),
                                Collectors.counting()
                        ));

        borrowedBooks.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry ->
                        System.out.println("Most borrowed book: " + entry.getKey() + " (" + entry.getValue() + " times)")
                );

        Map<String, List<Book>> currentReading =
                users.stream()
                        .collect(Collectors.toMap(
                                User::getName,
                                user -> user.getBorrowHistory()
                                        .stream()
                                        .filter(record -> record.getReturnedDate() == null)
                                        .map(BorrowRecord::getBook)
                                        .collect(Collectors.toList())
                        ));

        System.out.println("\nCurrently Reading:");

        currentReading.forEach((user, books) -> {
            System.out.println(user + " -> " + books);
        });


        Map<String, List<Book>> grouped =
                books.stream()
                        .collect(Collectors.groupingBy(
                                Book::getAuthor,
                                Collectors.filtering(
                                        book -> book.getYear() > 1950,
                                        Collectors.toList()
                                )
                        ));

        System.out.println("\nBooks Grouped By Author:");

        grouped.forEach((author, list) -> {
            if (!list.isEmpty()) {
                System.out.println(author + " -> " + list);
            }
        });

    }

    public Optional<Book> findRecommendedBookForUser(User user) {
        if (user.getBorrowHistory() == null ||
                user.getBorrowHistory().isEmpty()) {
            return Optional.empty();
        }

        Optional<String> favoriteAuthor =
                user.getBorrowHistory().stream()
                        .collect(Collectors.groupingBy(
                                record -> record.getBook().getAuthor(),
                                Collectors.counting()
                        ))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey);

        if (!favoriteAuthor.isPresent()) {
            return Optional.empty();
        }

        return books.stream()
                .filter(Book::isAvailable)
                .filter(book -> book.getAuthor().equals(favoriteAuthor.get()))
                .max(Comparator.comparing(Book::getRating));

    }

    public void uniqueAuthorsRead() {
        Set<String> authors =
                users.stream()
                        .flatMap(user -> user.getBorrowHistory().stream())
                        .map(record -> record.getBook().getAuthor())
                        .collect(Collectors.toSet());

        System.out.println("\nAuthors read by users:");
        System.out.println(authors);
    }

    public Optional<User> findTopReaderOfMonth(int month, int year) {
        return users.stream().max(Comparator.comparingLong(user ->
                user.getBorrowHistory()
                        .stream()
                        .filter(record -> record.getBorrowedDate().getMonthValue() == month
                                && record.getBorrowedDate().getYear() == year).count()
        ));

    }

}