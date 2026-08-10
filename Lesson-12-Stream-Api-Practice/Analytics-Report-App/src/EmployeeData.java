import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeData {

    public static List<Employee> createEmployees() {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(
                "Ali", "Engineering", 7000, 28,
                Arrays.asList("Java", "SQL", "Docker"),
                LocalDate.of(2020, 5, 10), "Kamran"
        ));

        employees.add(new Employee(
                "Veli", "Engineering", 9500, 35,
                Arrays.asList("Java", "AWS", "Docker", "SQL"),
                LocalDate.of(2018, 3, 15), "Kamran"
        ));

        employees.add(new Employee(
                "Aygun", "Engineering", 12000, 41,
                Arrays.asList("Java", "AWS", "Python"),
                LocalDate.of(2016, 7, 20), null
        ));

        employees.add(new Employee(
                "Murad", "Engineering", 6000, 26,
                Arrays.asList("Java", "React"),
                LocalDate.of(2022, 1, 12), "Aygun"
        ));

        employees.add(new Employee(
                "Elvin", "Engineering", 8000, 31,
                Arrays.asList("Python", "SQL", "Docker"),
                LocalDate.of(2019, 9, 5), "Aygun"
        ));

        employees.add(new Employee(
                "Nigar", "Sales", 5000, 29,
                Arrays.asList("Sales", "Negotiation", "Excel"),
                LocalDate.of(2021, 2, 18), "Rashad"
        ));

        employees.add(new Employee(
                "Rashad", "Sales", 9000, 44,
                Arrays.asList("Sales", "Negotiation", "Excel"),
                LocalDate.of(2017, 4, 10), null
        ));

        employees.add(new Employee(
                "Leyla", "Sales", 6500, 33,
                Arrays.asList("Sales", "Excel"),
                LocalDate.of(2019, 6, 22), "Rashad"
        ));

        employees.add(new Employee(
                "Orxan", "Sales", 5500, 27,
                Arrays.asList("Sales", "Negotiation"),
                LocalDate.of(2022, 8, 1), "Rashad"
        ));

        employees.add(new Employee(
                "Gunel", "Sales", 7200, 37,
                Arrays.asList("Sales", "Excel", "Negotiation"),
                LocalDate.of(2018, 11, 12), "Rashad"
        ));

        employees.add(new Employee(
                "Samir", "HR", 4500, 30,
                Arrays.asList("Excel", "Negotiation"),
                LocalDate.of(2021, 5, 14), "Sevda"
        ));

        employees.add(new Employee(
                "Sevda", "HR", 7500, 42,
                Arrays.asList("Excel", "Negotiation", "SQL"),
                LocalDate.of(2016, 2, 20), null
        ));

        employees.add(new Employee(
                "Aysel", "HR", 4800, 25,
                Arrays.asList("Excel", "Sales"),
                LocalDate.of(2023, 1, 10), "Sevda"
        ));

        employees.add(new Employee(
                "Tural", "HR", 5200, 34,
                Arrays.asList("Excel", "SQL"),
                LocalDate.of(2020, 10, 3), "Sevda"
        ));

        employees.add(new Employee(
                "Zehra", "HR", 6000, 39,
                Arrays.asList("Negotiation", "Excel"),
                LocalDate.of(2017, 12, 8), "Sevda"
        ));

        employees.add(new Employee(
                "Emin", "Marketing", 5500, 28,
                Arrays.asList("Sales", "Excel", "React"),
                LocalDate.of(2021, 3, 17), "Ilham"
        ));

        employees.add(new Employee(
                "Ilham", "Marketing", 8500, 45,
                Arrays.asList("Sales", "Negotiation", "Excel"),
                LocalDate.of(2015, 6, 2), null
        ));

        employees.add(new Employee(
                "Lala", "Marketing", 6200, 32,
                Arrays.asList("React", "Excel"),
                LocalDate.of(2019, 2, 14), "Ilham"
        ));

        employees.add(new Employee(
                "Farid", "Marketing", 5800, 27,
                Arrays.asList("React", "Sales"),
                LocalDate.of(2022, 5, 9), "Ilham"
        ));

        employees.add(new Employee(
                "Narmin", "Marketing", 7000, 36,
                Arrays.asList("Sales", "Excel", "React"),
                LocalDate.of(2018, 8, 21), "Ilham"
        ));

        employees.add(new Employee(
                "Kamran", "Finance", 10000, 48,
                Arrays.asList("Excel", "SQL"),
                LocalDate.of(2014, 4, 3), null
        ));

        employees.add(new Employee(
                "Amina", "Finance", 6500, 31,
                Arrays.asList("Excel", "SQL", "Python"),
                LocalDate.of(2019, 7, 15), "Kamran"
        ));

        employees.add(new Employee(
                "Rauf", "Finance", 7200, 40,
                Arrays.asList("Excel", "SQL"),
                LocalDate.of(2017, 1, 25), "Kamran"
        ));

        employees.add(new Employee(
                "Sona", "Finance", 5800, 29,
                Arrays.asList("Excel", "Python"),
                LocalDate.of(2021, 9, 11), "Kamran"
        ));

        employees.add(new Employee(
                "Javid", "Finance", 8300, 52,
                Arrays.asList("Excel", "SQL", "AWS"),
                LocalDate.of(2015, 10, 6), "Kamran"
        ));

        return employees;
    }


    public static List<Employee> createBigList(int count) {

        List<Employee> employees = new ArrayList<>();

        String[] names = {"Ali", "Veli", "Aygun", "Murad", "Elvin", "Nigar", "Rashad", "Leyla", "Orxan", "Gunel"};

        String[] departments = {"Engineering", "Sales", "HR", "Marketing", "Finance"};

        String[] skills = {"Java", "Python", "SQL", "AWS", "React", "Docker", "Excel", "Sales", "Negotiation"};

        for (int i = 0; i < count; i++) {

            String name = names[i % names.length] + i;

            String department =
                    departments[i % departments.length];

            double salary =
                    3000 + (i % 16) * 1000;

            int age =
                    22 + (i % 39);

            List<String> employeeSkills = Arrays.asList(
                    skills[i % skills.length],
                    skills[(i + 1) % skills.length],
                    skills[(i + 2) % skills.length]
            );

            LocalDate hireDate =
                    LocalDate.of(
                            2014 + (i % 11),
                            (i % 12) + 1,
                            (i % 28) + 1
                    );

            String managerName;

            if (i % 10 == 0) {
                managerName = null;
            } else {
                managerName =
                        names[(i + 1) % names.length];
            }

            employees.add(
                    new Employee(name, department, salary, age, employeeSkills, hireDate, managerName)
            );
        }

        return employees;
    }
}