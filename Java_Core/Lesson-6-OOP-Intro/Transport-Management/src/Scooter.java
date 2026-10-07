public class Scooter implements Transport {

    private final double ratePerKm;
    private final double speed;

    public Scooter() {
        ratePerKm = 0.3;
        speed = 25.0;
    }

    public Scooter(double ratePerKm, double speed) {
        this.ratePerKm = ratePerKm;
        this.speed = speed;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * ratePerKm + 1.0;
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return (distance * ratePerKm) + 1.0;
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }

    @Override
    public String getTransportInfo() {
        return "Scooter: Fast urban electric transport.";
    }
}
