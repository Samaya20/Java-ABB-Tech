public class Passenger {
    String name;
    boolean isPriority;

    public Passenger(String name, boolean isPriority) {
        this.name = name;
        this.isPriority = isPriority;
    }

    @Override
    public String toString() {
        return isPriority ? name + "(PRİORİTET)" : name;
    }
}
