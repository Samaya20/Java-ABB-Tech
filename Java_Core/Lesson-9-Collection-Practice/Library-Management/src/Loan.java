public class Loan {
    String loanId;
    BookCopy bookCopy;
    Member member;
    int borrowDay, dueDay;
    boolean returned;

    public Loan(String loanId, BookCopy bookCopy, Member member, int borrowDay, int dueDay) {
        this.loanId = loanId;
        this.bookCopy = bookCopy;
        this.member = member;
        this.borrowDay = borrowDay;
        this.dueDay = dueDay;
        this.returned = false;
    }
}