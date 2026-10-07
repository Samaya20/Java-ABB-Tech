import java.util.Scanner;

public void main() {
    Library library = new Library();
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
        System.out.println("\ncommands: ADD, SHOW, SEARCH, CHECKOUT, RETURN, EXIT");
        String command = scanner.nextLine().trim().toUpperCase();

        switch (command) {
            case "ADD" -> {
                System.out.print("type (book/magazine): ");
                String type = scanner.nextLine();
                System.out.print("title: ");
                String title = scanner.nextLine();
                if (type.equalsIgnoreCase("book"))
                    library.addItem(new Book(title, 1, "authorName", 200));
                else
                    library.addItem(new Magazine(title, 2, 5));
            }
            case "SHOW" -> library.showAllItems();
            case "SEARCH" -> {
                System.out.print("keyword: ");
                library.searchByTitle(scanner.nextLine());
            }
            case "CHECKOUT" -> {
                System.out.print("id: ");
                library.checkOutItem(Integer.parseInt(scanner.nextLine()));
            }
            case "RETURN" -> {
                System.out.print("id: ");
                library.returnItem(Integer.parseInt(scanner.nextLine()));
            }
            case "EXIT" -> {
                System.out.println("goodbye!");
                running = false;
            }
            default -> System.out.println("invalid command, try again.");
        }
    }
}
