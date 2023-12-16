//Base Class
abstract class Employee
{
    int employeeId;
    String employeeName;
    String designation;

    // Class Constructor
    public Employee(int employeeId, String employeeName, String designation)
    {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.designation = designation;
    }

    // methods to get and set the data
    public int getEmployeeId() {return employeeId;}
    public void setEmployeeId(int employeeId) {this.employeeId = employeeId;}
    public String getEmployeeName() {return employeeName;}
    public void setEmployeeName(String employeeName) {this.employeeName = employeeName;}
    public String getDesignation() {return designation;}
    public void setDesignation(String designation) {this.designation = designation;}

    // methods too implement in dervied classes and override
    //
    public abstract double calculateWeeklySalary();
    public abstract double calculateBonus();
    public abstract double calculateAnnualEarnings();
}

class HourlyEmployee extends Employee
{
    private double hourlyRate;
    private int hoursWorked;

    // Class Constructor
    public HourlyEmployee(int employeeId, String employeeName, String designation,double hourlyRate, int hoursWorked)
    {
        super(employeeId,employeeName,designation);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {return hourlyRate;}
    public void setHourlyRate(double hourlyRate) {this.hourlyRate = hourlyRate;}
    public int getHoursWorked() {return hoursWorked;}
    public void setHoursWorked(int hoursWorked) {this.hoursWorked = hoursWorked;}

    // Override methods
    @Override
    public double calculateWeeklySalary()
    {
        return hourlyRate * hoursWorked;
    }

    @Override
    public double calculateBonus()
    {
        return 0.1 * calculateAnnualEarnings(); // 10% of annual earnings
    }

    @Override
    public double calculateAnnualEarnings()
    {
        return 52 * calculateWeeklySalary();
    }
}

class SalariedEmployee extends Employee
{
    private double monthlySalary;

    public SalariedEmployee(int employeeId, String employeeName, String designation,double montlySalary)
    {
        super(employeeId, employeeName, designation);
        this.monthlySalary = montlySalary;
    }

    // methods to get and set the data
    public double getMonthlySalary() { return monthlySalary; }
    public void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }

    // Override methods
    @Override
    public double calculateWeeklySalary()
    {
        return monthlySalary / 4;
    }

    @Override
    public double calculateBonus()
    {
        return 0.1 * calculateAnnualEarnings(); // 10% of annual earnings
    }

    @Override
    public double calculateAnnualEarnings()
    {
        return 12 * monthlySalary;
    }
}

// Derived Class: ExecutiveEmployee
class ExecutiveEmployee extends SalariedEmployee
{
    private double bonusPercentage;

    // Class Constructor
    public ExecutiveEmployee(int employeeId, String employeeName, String designation, double monthlySalary, double bonusPercentage)
    {
        super(employeeId, employeeName, designation, monthlySalary);
        this.bonusPercentage = bonusPercentage;
    }

    // methods for value assignment and retrieval
    public double getBonusPercentage() {return bonusPercentage;}
    public void setBonusPercentage(double bonusPercentage) {this.bonusPercentage = bonusPercentage;}

    // Override calculateBonus method
    @Override
    public double calculateBonus()
    {
        return calculateAnnualEarnings() * bonusPercentage / 100;
    }
}

// Class to manage all employees
class PayrollSystem {
    private Employee[] employees;
    private int employeeCount;

    // Class Constructor
    public PayrollSystem(int maxSize) {
        employees = new Employee[maxSize];
        employeeCount = 0;
    }

    // Method to add an employee
    public void addEmployee(Employee employee) {
        if (employeeCount < employees.length) {
            employees[employeeCount++] = employee;
        } else {
            System.out.println("Payroll is full. Cannot add more employees.");
        }
    }

    // Method to calculate total payroll
    public double calculateTotalPayroll() {
        double totalPayroll = 0;
        for (int i = 0; i < employeeCount; i++) {
            totalPayroll += employees[i].calculateAnnualEarnings();
        }
        return totalPayroll;
    }

    // Method to display all employee details
    public void displayEmployeeDetails() {
        for (int i = 0; i < employeeCount; i++) {
            System.out.println("Employee ID: " + employees[i].getEmployeeId());
            System.out.println("Employee Name: " + employees[i].getEmployeeName());
            System.out.println("Designation: " + employees[i].getDesignation());
            System.out.println("Weekly Salary: " + employees[i].calculateWeeklySalary());
            System.out.println("Annual Earnings: " + employees[i].calculateAnnualEarnings());
            System.out.println("Bonus: " + employees[i].calculateBonus());
            System.out.println("-----------------------------");
        }
    }
}

class Employee_Main{
    public static void main(String[] args) {
        // Create instances of employees
        HourlyEmployee hourlyEmployee = new HourlyEmployee(1, "Lewis Hamilton", "Teaching Assistant", 15.0, 40);
        SalariedEmployee salariedEmployee = new SalariedEmployee(2, "Max Verstappen", "Head of Department", 5000.0);
        ExecutiveEmployee executiveEmployee = new ExecutiveEmployee(3, "Marc Marquez", "Assistant Lecutrer", 10000.0, 15.0);

        // Create PayrollSystem instance
        PayrollSystem payrollSystem = new PayrollSystem(3);

        // Add employees to the payroll system
        payrollSystem.addEmployee(hourlyEmployee);
        payrollSystem.addEmployee(salariedEmployee);
        payrollSystem.addEmployee(executiveEmployee);

        // Display employee details and total payroll
        payrollSystem.displayEmployeeDetails();
        System.out.println("Total Payroll: $" + payrollSystem.calculateTotalPayroll());
    }
}

