public class Employee {
    String name;
    String department;
    double salary;
    int age;

    Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    double getSalary() {
        return salary;
    }
}
