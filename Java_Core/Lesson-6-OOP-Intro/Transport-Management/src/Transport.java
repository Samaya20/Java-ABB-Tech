public interface Transport {
    double calculateFare(double distance);
    double calculateFare(double distance, int passengers);
    double calculateTime(double distance);
    String getTransportInfo();
}
