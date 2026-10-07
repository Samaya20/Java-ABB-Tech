public class Taxi implements Transport {

    private final double ratePerKm;
    private final double speed;

    public Taxi() {
        ratePerKm = 2.0;
        speed = 60.0;
    }

    public Taxi(double ratePerKm, double speed) {
        this.ratePerKm = ratePerKm;
        this.speed = speed;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * ratePerKm + 5.0;
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return (distance * ratePerKm) + 5.0 + (passengers * 1.0);
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }

    @Override
    public String getTransportInfo() {
        return "Taxi: Private comfortable transportation.";
    }
}
