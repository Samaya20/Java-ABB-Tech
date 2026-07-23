import java.util.*;

public class Bus {
    private final List<Passenger> passengers = new ArrayList<>();
    private final int capacity = 5;

    public void dropPassengers(Random random) {
        if (passengers.isEmpty()) return;

        int leaveCount = random.nextInt(Math.min(2, passengers.size())) + 1;
        for (int i = 0; i < leaveCount && !passengers.isEmpty(); i++) {
            Passenger removed = passengers.remove(random.nextInt(passengers.size()));
            System.out.println(removed.name + " avtobusdan düsdü.");
        }
    }

    public void boardPassengers(Queue<Passenger> stationQueue) {
        Queue<Passenger> normalQueue = new LinkedList<>();

        while (!stationQueue.isEmpty() && passengers.size() < capacity) {
            Passenger p = stationQueue.poll();
            if (p.isPriority) {
                passengers.add(p);
                System.out.println(p + " avtobusa mindi.");
            } else {
                normalQueue.add(p);
            }
        }

        while (!normalQueue.isEmpty() && passengers.size() < capacity) {
            Passenger p = normalQueue.poll();
            passengers.add(p);
            System.out.println(p + " avtobusa mindi.");
        }

        stationQueue.addAll(normalQueue);
    }

    public void printBusStatus() {
        System.out.println("Avtobusdaki sernisinler: " + passengers);
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}