public class Reservation {
    Member member;
    Book book;
    int reservationDay, priorityScore;

    public Reservation(Member member, Book book, int reservationDay, int priorityScore) {
        this.member = member;
        this.book = book;
        this.reservationDay = reservationDay;
        this.priorityScore = priorityScore;
    }
}