void main() {
    Queue<Passenger> stop1 = new LinkedList<>();
    Queue<Passenger> stop2 = new LinkedList<>();
    Queue<Passenger> stop3 = new LinkedList<>();

    //menim taskımda prioritet sadece sizedir müellim, torpil keçirem size :)
    stop1.add(new Passenger("Ruslan müellim", true));


    stop1.add(new Passenger("Nermin", false));
    stop1.add(new Passenger("Aylin", false));

    stop2.add(new Passenger("Orxan", false));
    stop2.add(new Passenger("Samir", false));
    stop2.add(new Passenger("Fazil", false));

    stop3.add(new Passenger("Semaye", false));
    stop3.add(new Passenger("Leyla", false));
    stop3.add(new Passenger("Vaqif", false));

    Bus bus = new Bus();
    Random random = new Random();

    // Dayanacaq 1
    System.out.println("Dayanacaq 1-e catdiq.");
    bus.dropPassengers(random);
    bus.boardPassengers(stop1);
    bus.printBusStatus();
    System.out.println("Dayanacaq 1-de qalanlar: " + new ArrayList<>(stop1));

    // Dayanacaq 2
    System.out.println("\nDayanacaq 2-ye catdiq.");
    bus.dropPassengers(random);
    bus.boardPassengers(stop2);
    bus.printBusStatus();
    System.out.println("Dayanacaq 2-de qalanlar: " + new ArrayList<>(stop2));

    // Dayanacaq 3
    System.out.println("\nDayanacaq 3-e catdiq.");
    bus.dropPassengers(random);
    bus.boardPassengers(stop3);
    bus.printBusStatus();
    System.out.println("Dayanacaq 3-de qalanlar: " + new ArrayList<>(stop3));

    // Son dayanacaq
    System.out.println("\n Sonuncu dayanacaga catdiq.");
    System.out.println("Avtobusdaki son sernisinler: " + bus.getPassengers());
}
