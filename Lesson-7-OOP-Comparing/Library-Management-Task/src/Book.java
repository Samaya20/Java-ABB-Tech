class Book extends Item {
    private String author;
    private int pages;

    public Book(String title, int id, String author, int pages) {
        super(title, id);
        this.author = author;
        this.pages = pages;
    }

    @Override
    public void displayInfo() {
        System.out.println(getBaseInfo() + ", type: book, author: " + author + ", pages: " + pages);
    }
}