import java.util.List;

public class User {
    private String name;
    private int age;
    private List<BorrowRecord> borrowHistory;

    public User(String name, int age, List<BorrowRecord> borrowHistory) {
        this.name = name;
        this.age = age;
        this.borrowHistory = borrowHistory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }

    public void setBorrowHistory(List<BorrowRecord> borrowHistory) {
        this.borrowHistory = borrowHistory;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", age=" + age + ", borrowHistory=" + borrowHistory + '}';
    }
}
