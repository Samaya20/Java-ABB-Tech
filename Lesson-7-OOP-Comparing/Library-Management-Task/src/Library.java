public class Library {
    private Item[] items = new Item[10];
    private int count = 0;

    public void addItem(Item item) {
        if (count >= 10) {
            System.out.println("library is full!");
            return;
        }
        if (item.getTitle().trim().length() == 0) {
            System.out.println("invalid title! cannot add.");
            return;
        }
        items[count++] = item;
    }

    public void showAllItems() {
        for (int i = 0; i < count; i++) items[i].displayInfo();
    }

    public void checkOutItem(int id) {
        for (int i = 0; i < count; i++) {
            if (items[i].getId() == id) { items[i].checkOut(); return; }
        }
        System.out.println("item not found.");
    }

    public void returnItem(int id) {
        for (int i = 0; i < count; i++) {
            if (items[i].getId() == id) { items[i].returnItem(); return; }
        }
        System.out.println("item not found.");
    }

    public void searchByTitle(String keyword) {
        String search = keyword.trim().toLowerCase();
        for (int i = 0; i < count; i++) {
            if (items[i].getTitle().toLowerCase().contains(search)) {
                items[i].displayInfo();
            }
        }
    }
}