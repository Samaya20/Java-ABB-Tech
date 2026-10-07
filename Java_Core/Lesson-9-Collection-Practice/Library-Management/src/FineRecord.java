public class FineRecord {
    Member member;
    double amount;
    String reason;
    int day;

    public FineRecord(Member member, double amount, String reason, int day) {
        this.member = member;
        this.amount = amount;
        this.reason = reason;
        this.day = day;
    }
}