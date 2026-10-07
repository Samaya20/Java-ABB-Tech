public class Bicycle implements Transport {

    private final double ratePerKm;
    private final double speed;

    public Bicycle() {
        ratePerKm = 0.1;
        speed = 15.0;
    }

    public Bicycle(double ratePerKm, double speed) {
        this.ratePerKm = ratePerKm;
        this.speed = speed;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * ratePerKm;
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return distance * ratePerKm;
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }

    @Override
    public String getTransportInfo() {
        return "Bicycle: Eco-friendly manual transport.";
    }
}
