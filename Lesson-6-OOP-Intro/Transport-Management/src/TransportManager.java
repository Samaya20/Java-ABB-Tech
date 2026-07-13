public class TransportManager {
    public static Transport getTransport(TransportType type) {
        return switch (type) {
            case TAXI -> new Taxi();
            case BUS -> new Bus();
            case BICYCLE -> new Bicycle();
            case SCOOTER -> new Scooter();
            default -> null;
        };
    }
}
