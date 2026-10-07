public class Book {
    private String title;
    private String author;
    private String isbn;
    protected int totalCopies;
    protected int availableCopies;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        totalCopies = 1;
        availableCopies = 1;
    }

    {
        System.out.println("New book created");
    }

    public void borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Sorry, no copies available.");
        }
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("All copies are already in the library.");
        }
    }

    public void printInfo() {
        System.out.println("-- Book info --");
        System.out.println("Title : " + this.title);
        System.out.println("Author : " + this.author);
        System.out.println("ISBN : " + this.isbn);
        System.out.println("Total Copies : " + this.totalCopies);
        System.out.println("Available Copies : " + this.availableCopies);
        System.out.println("---------");
    }

    public static void libraryRules() {
        System.out.println("Max 3 books can be borrowed per person.");
    }

    public final void bookType() {
        System.out.println("This is a regular book.");
    }
}
