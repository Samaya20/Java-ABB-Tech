import java.util.List;

void main(String[] args) {

    List<Employee> employees = EmployeeData.createEmployees();

    ReportGenerator generator = new ReportGenerator();

    String report = generator.generateReport(employees);

    System.out.println(report);

    System.out.println("-- Performans Testi --");

    List<Employee> bigList = EmployeeData.createBigList(500000);

    long start = System.currentTimeMillis();

    generator.generateReport(bigList, false);

    long end = System.currentTimeMillis();

    System.out.println(
            "Stream vaxti: " +
                    (end - start) +
                    " ms"
    );


    start = System.currentTimeMillis();

    generator.generateReport(bigList, true);

    end = System.currentTimeMillis();

    System.out.println(
            "ParallelStream vaxti: " +
                    (end - start) +
                    " ms"
    );
}