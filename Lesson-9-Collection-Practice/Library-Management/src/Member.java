import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Member extends LibraryUser implements Finable, Notifiable {
    MembershipType type;
    Queue<Notification> notifications = new LinkedList<>();

    public Member(String id, String name, MembershipType type, String email) {
        super(id, name, email);
        this.type = type;
    }

    @Override
    public double getDiscountRate() {
        return type == MembershipType.PREMIUM ? 0.2 : (type == MembershipType.STUDENT ? 0.1 : 0.0);
    }

    @Override
    public double calculateFine(int daysLate) {
        double rate = (type == MembershipType.STUDENT) ? 0.3 : (type == MembershipType.PREMIUM ? 0.2 : 0.5);
        return daysLate * rate;
    }

    @Override
    public void receiveNotification(Notification n) {
        notifications.offer(n);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
