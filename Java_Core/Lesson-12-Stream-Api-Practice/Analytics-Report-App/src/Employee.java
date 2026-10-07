import java.time.LocalDate;
import java.util.List;

public class Employee {

    private String name;
    private String department;
    private double salary;
    private int age;
    private List<String> skills;
    private LocalDate hireDate;
    private String managerName;

    public Employee(String name, String department, double salary,
                    int age, List<String> skills,
                    LocalDate hireDate, String managerName) {

        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.skills = skills;
        this.hireDate = hireDate;
        this.managerName = managerName;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public List<String> getSkills() {
        return skills;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getManagerName() {
        return managerName;
    }
}