public class DepartmentSummary {

    private long employeeCount;
    private double averageSalary;
    private String highestPaidEmployee;

    public DepartmentSummary(long employeeCount,
                             double averageSalary,
                             String highestPaidEmployee) {
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
        this.highestPaidEmployee = highestPaidEmployee;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public String getHighestPaidEmployee() {
        return highestPaidEmployee;
    }
}