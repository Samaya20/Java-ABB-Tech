import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportGenerator {

    public String generateReport(List<Employee> employees) {
        return generateReport(employees, false);
    }

    public String generateReport(List<Employee> employees, boolean parallel) {

        Stream<Employee> stream1 =
                parallel ? employees.parallelStream() : employees.stream();

        DoubleSummaryStatistics salaryStats = stream1
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();

        long employeeCount = employees.size();

        Optional<Employee> oldest = (parallel
                ? employees.parallelStream()
                : employees.stream())
                .max(Comparator.comparingInt(Employee::getAge));

        Optional<Employee> youngest = (parallel
                ? employees.parallelStream()
                : employees.stream())
                .min(Comparator.comparingInt(Employee::getAge));

        Map<String, DepartmentSummary> departments =
                (parallel ? employees.parallelStream() : employees.stream())
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> {

                                            long count = list.size();

                                            double average = list.stream()
                                                    .mapToDouble(Employee::getSalary)
                                                    .average()
                                                    .orElse(0);

                                            String highest = list.stream()
                                                    .max(Comparator.comparingDouble(Employee::getSalary))
                                                    .map(Employee::getName)
                                                    .orElse("");

                                            return new DepartmentSummary(count, average, highest);
                                        }
                                )
                        ));

        String highestSalaryDepartment =
                (parallel ? employees.parallelStream() : employees.stream())
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.summingDouble(Employee::getSalary)
                        ))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("");

        Map<String, Long> skillCounts =
                (parallel ? employees.parallelStream() : employees.stream())
                        .flatMap(employee -> employee.getSkills().stream())
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        ));

        List<String> popularSkills =
                skillCounts.entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(3)
                        .map(entry -> entry.getKey() + " - " + entry.getValue())
                        .collect(Collectors.toList());


        Map<String, Set<String>> departmentSkills =
                (parallel ? employees.parallelStream() : employees.stream())
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.flatMapping(
                                        employee -> employee.getSkills().stream(),
                                        Collectors.toSet()
                                )
                        ));

        List<String> departmentNames = new ArrayList<>(departmentSkills.keySet());

        String departmentsWithoutCommonSkills = "";

        Optional<String> department1 = departmentNames.stream()
                .flatMap(first ->
                        departmentNames.stream()
                                .filter(second -> !first.equals(second))
                                .filter(second -> {
                                    Set<String> firstSkills =
                                            departmentSkills.get(first);

                                    Set<String> secondSkills =
                                            departmentSkills.get(second);

                                    return Collections.disjoint(
                                            firstSkills,
                                            secondSkills
                                    );
                                })
                                .map(second -> first + " - " + second)
                ).findFirst();

        if (department1.isPresent()) {
            departmentsWithoutCommonSkills = department1.get();
        }

        Map<String, Double> experienceSalary =
                (parallel ? employees.parallelStream() : employees.stream())
                        .collect(Collectors.groupingBy(
                                employee -> {

                                    int years = Period.between(
                                            employee.getHireDate(),
                                            LocalDate.now()
                                    ).getYears();

                                    if (years <= 2) {
                                        return "0-2 il";
                                    }

                                    if (years <= 5) {
                                        return "3-5 il";
                                    }

                                    return "5+ il";
                                },
                                Collectors.averagingDouble(Employee::getSalary)
                        ));

        Map<String, Long> managerCounts =
                (parallel ? employees.parallelStream() : employees.stream())
                        .filter(employee -> employee.getManagerName() != null)
                        .collect(Collectors.groupingBy(
                                Employee::getManagerName,
                                Collectors.counting()
                        ));

        long managerCount = managerCounts.size();

        Map<String, Double> minMax =
                (parallel ? employees.parallelStream() : employees.stream())
                        .collect(Collectors.teeing(
                                Collectors.minBy(
                                        Comparator.comparingDouble(Employee::getSalary)
                                ),
                                Collectors.maxBy(
                                        Comparator.comparingDouble(Employee::getSalary)
                                ),
                                (min, max) -> {

                                    Map<String, Double> result = new HashMap<>();

                                    result.put("min", min.map(Employee::getSalary).orElse(0.0));

                                    result.put("max", max.map(Employee::getSalary).orElse(0.0));

                                    return result;
                                }
                        ));

        Map<String, String> namesByDepartment =
                (parallel ? employees.parallelStream() : employees.stream())
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.mapping(
                                        Employee::getName,
                                        NameCollector.create()
                                )
                        ));

        StringBuilder report = new StringBuilder();

        report.append("A - Umumi statistika\n");
        report.append("Isci sayi: " + employeeCount + "\n");
        report.append("Umumi maas xerc: " + salaryStats.getSum() + "\n");
        report.append("Orta maas: " + salaryStats.getAverage() + "\n");

        if (oldest.isPresent()) {
            report.append("En yasli: " + oldest.get().getName() + ", " + oldest.get().getAge() + "\n");
        }

        if (youngest.isPresent()) {
            report.append("En genc: " + youngest.get().getName() + ", " + youngest.get().getAge() + "\n");
        }

        report.append("\nB - Departament analizi\n");

        departments.forEach((department, summary) -> {
            report.append(
                    department + ": " + summary.getEmployeeCount() +
                    " isci, orta maas = " + summary.getAverageSalary() +
                    ", en yuksek maasli = " + summary.getHighestPaidEmployee() + "\n");
        });

        report.append("En cox maas xerc eden departament: " + highestSalaryDepartment + "\n");

        report.append("\nC - Skill analizi\n");

        report.append("Butun skilller:\n");

        skillCounts.forEach((skill, count) -> {
            report.append(skill + " - " + count + "\n");
        });

        report.append("En populyar 3 skill:\n");

        popularSkills.forEach(skill -> {
            report.append(skill + "\n");
        });

        report.append("Ortaq skill-i olmayan departamentler: " + departmentsWithoutCommonSkills + "\n");

        report.append("\nD - Yas / staj analizi\n");

        experienceSalary.forEach((group, average) -> {
            report.append(group + ": orta maas = " + average + "\n");
        });

        report.append("\nE - Rehberlik strukturu\n");

        report.append("Rehberi olan isci sayi: " + managerCount + "\n");

        managerCounts.forEach((manager, count) -> {
            report.append(manager + " - " + count + " tabeliyinde isci\n");
        });

        report.append("\nF - Minimum ve maksimum maas\n");

        report.append("Minimum maas: " + minMax.get("min") + "\n");

        report.append("Maksimum maas: " + minMax.get("max") + "\n");

        report.append("\nG - Departament uzre isci adlari\n");
        namesByDepartment.forEach((department, names) -> {
            report.append(department + ": " + names + "\n");
        });

        return report.toString();
    }
}