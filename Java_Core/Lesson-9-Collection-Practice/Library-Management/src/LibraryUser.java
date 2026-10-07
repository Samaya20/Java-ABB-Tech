public abstract class LibraryUser {
    protected String id;
    protected String name;
    protected String email;

    public LibraryUser(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public abstract double getDiscountRate();
}
