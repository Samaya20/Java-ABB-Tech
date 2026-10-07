public class Notification {
    NotificationType type;
    String message;
    int day;

    public Notification(NotificationType type, String message, int day) {
        this.type = type;
        this.message = message;
        this.day = day;
    }
}