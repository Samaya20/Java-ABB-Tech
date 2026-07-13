public class Bus implements Transport {

    private final double ratePerKm;
    private final double speed;

    public Bus() {
        ratePerKm = 0.5;
        speed = 40.0;
    }

    public Bus(double ratePerKm, double speed) {
        this.ratePerKm = ratePerKm;
        this.speed = speed;
    }

    @Override
    public double calculateFare(double distance) {
        return distance * ratePerKm;
    }

    @Override
    public double calculateFare(double distance, int passengers) {
        return (distance * ratePerKm) + (passengers * 0.2);
    }

    @Override
    public double calculateTime(double distance) {
        return distance / speed;
    }

    @Override
    public String getTransportInfo() {
        return "Bus: Large capacity vehicle for city transport.";
    }
}
