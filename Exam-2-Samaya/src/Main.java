import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws Exception {

        List<Employee> employees = Files.lines(Paths.get("data/employees.txt"))
                .map(x -> x.split(","))
                .map(x -> new Employee(x[0], x[1],
                        Double.parseDouble(x[2]),
                        Integer.parseInt(x[3])))
                .toList();

        var departments = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        var average = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        var highSalary = employees.stream()
                .filter(e -> e.getSalary() > 2000)
                .toList();

        var sorted = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .toList();

        var max = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        var names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(", "));

        var count = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()));

        Files.writeString(
                Paths.get("data/salary_report.txt"),
                "Orta maas: " + average +
                        "\n2000-den cox: " + highSalary +
                        "\nSiralama: " + sorted +
                        "\nEn yüksek maas: " + max +
                        "\nAdlar: " + names +
                        "\nŞöbe sayi: " + count
        );
    }
}
