public class BookCopy {
    String copyId;
    Book book;
    String branchId;
    CopyStatus status;

    public BookCopy(String copyId, Book book, String branchId, CopyStatus status) {
        this.copyId = copyId;
        this.book = book;
        this.branchId = branchId;
        this.status = status;
    }
}
