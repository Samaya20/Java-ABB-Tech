void main() {
    TransportType userChoice = TransportType.BUS;
    double distance = 15.0;
    int passengers = 2;

    Transport transport = TransportManager.getTransport(userChoice);
    System.out.println("Transport Info: \n" + transport.getTransportInfo());
    System.out.println("Fare for " + passengers + " passenger(s): " +
            transport.calculateFare(distance, passengers));
    System.out.println("Estimated time: " +
            transport.calculateTime(distance) + " hours.");
}
