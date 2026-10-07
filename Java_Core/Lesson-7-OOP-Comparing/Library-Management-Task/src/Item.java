public abstract class Item {
    protected String title;
    protected int id;
    protected boolean isAvailable;

    public Item(String title, int id) {
        this.title = title;
        this.id = id;
        this.isAvailable = true;
    }

    public abstract void displayInfo();

    public void checkOut() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("item " + id + " checked out.");
        } else {
            System.out.println("item already unavailable.");
        }
    }

    public void returnItem() {
        isAvailable = true;
        System.out.println("item " + id + " returned.");
    }

    public String getTitle() { return title; }
    public int getId() { return id; }
    protected String getBaseInfo() {
        return "id: " + id + ", title: " + title + ", available: " + isAvailable;
    }
}