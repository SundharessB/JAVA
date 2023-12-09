import java.util.*;

class Employee
{
    int employeeId;
    String employeeName, designation;
    double bonus;

    Employee(int empid, String empname, String des)
    {
        employeeId = empid;
        employeeName = empname;
        designation = des;
    }

    public void calculateBonus()
    {
        bonus = 0.10;
        System.out.println("Bonus percentage for all Employees is 10%");

    }
}

class HourlyEmployee extends Employee
{
    double hourlyRate;
    int hoursWorked;
    double bonusearnings;
    
    HourlyEmployee(int empid, String empname, String des, double hrate, int hwork)
    {
        super(empid, empname, des);
        hourlyRate = hrate;
        hoursWorked = hwork;
    }

    public void display()
    {
        System.out.println("Details of the Hourly Employee");
        System.out.println("Name: "+employeeName);
        System.out.println("Employee Id: "+employeeId);
        System.out.println("Designation: "+designation);
        System.out.println("Weekly Earnings: "+(hourlyRate*hoursWorked));
    }

    
    public void calculateBonus()
    {
        bonus = 0.5;
        bonusearnings = bonus * hourlyRate* hoursWorked*4;
        System.out.println("Bonus Earnings: "+bonusearnings);
    }

    public void annualdisplay()
    {
        System.out.println("Annual Earnings: "+((52*hourlyRate*hoursWorked)+bonusearnings));
    }


}



class SalariedEmployee extends Employee
{
    double MonthlySalary, bonusearnings;

    SalariedEmployee(int empid, String empname, String des, double sal)
    {
        super(empid, empname, des);
        MonthlySalary = sal;
    }

    public void display()
    {
        System.out.println("Details of the Salaried Employee");
        System.out.println("Name: "+employeeName);
        System.out.println("Employee Id: "+employeeId);
        System.out.println("Designation: "+designation);
        System.out.println("Weekly Earnings: "+(MonthlySalary/4));
    }

     public void calculateBonus()
    {
        
        bonus = 0.10;
        bonusearnings = bonus * MonthlySalary;
        System.out.println("Bonus Earnings: "+bonusearnings);
    }

     public void annualdisplay()
    {
        System.out.println("Annual Earnings: "+((12*MonthlySalary)+bonusearnings));
    }

}

class ExecutiveEmployee extends SalariedEmployee
{
    double bonusPercentage;
    ExecutiveEmployee(int empid, String empname, String des, double sal, double bpercent)
    {
        super(empid, empname, des, sal);
        bonusPercentage = bpercent;
    }
    public void display()
    {
        System.out.println("Details of the Salaried Employee");
        System.out.println("Name: "+employeeName);
        System.out.println("Employee Id: "+employeeId);
        System.out.println("Designation: "+designation);
        System.out.println("Weekly Earnings: "+(MonthlySalary/4));
    }

      public void calculateBonus()
    {
        System.out.println("Bonus for Executive Employees");
        double bonusearnings = (bonusPercentage/100) * MonthlySalary;
        System.out.println("Bonus Earnings: "+bonusearnings);
        super.annualdisplay();
    }

    

}
public class Payroll
{

    public static void main(String[] args)
    {
        String name, des;
        int id, hours, choice;
        double hrate, salary, bonuspercent;
        Scanner sc = new Scanner(System.in);
         
        System.out.println("Enter the name of the Employee: ");  
        name = sc.nextLine();
        System.out.println("Enter the Employee Id of the Employee: ");  
        id = sc.nextInt();
        System.out.println("Enter the designation of the Employee: ");  
        des = sc.next();
        System.out.println("Choose whether the Employee is:\n1.Hourly\n2.Salaried");  
        choice = sc.nextInt();
        switch(choice)
        {
            case 1: 
            {
                System.out.println("Enter the Hourly Rate: ");
                hrate = sc.nextDouble();
                if(hrate>500)
                {

                    System.out.println("Hourly Rate cannot exceed 500.");
                    System.out.println("Enter the Hourly Rate: ");
                    hrate = sc.nextDouble();

                }

                else
                {
                System.out.println("Enter the hours worked per week: ");
                hours = sc.nextInt();
                if(hours>=168)
                {
                    System.out.println("There are only 168 hours in a week, enter the number of hours properly!");
                    System.out.println("Enter the correct hours worked per week: ");
                    hours = sc.nextInt();
                }

                else
                {
                    HourlyEmployee h = new HourlyEmployee(id, name, des, hrate, hours);
                    h.display();
                    h.calculateBonus();

                }

            }

            }

            break;

            case 2:
            {
                int ch;
                System.out.println("Choose if your Salaried Employee is:\n1.Non-Executive\n2.Executive");
                ch = sc.nextInt();

                if(ch==1)
                {
                    System.out.println("Enter the Monthly Salary ");
                    salary = sc.nextDouble();
                    if(salary<20000 || salary>250000)
                    {
                        System.out.println("Salary has to be in the range 20000-250000.");
                        System.out.println("Enter the Monthly Salary: ");
                        salary = sc.nextDouble();
                    }
                    else
                    {
                        SalariedEmployee s = new SalariedEmployee(id, name, des, salary);
                        s.display();
                        s.calculateBonus();
                    }
                    
                }
                 else if(ch==2)
                 {
                    System.out.println("Enter the Monthly Salary ");
                    salary = sc.nextDouble();
                     if(salary<20000 || salary>250000)
                    {
                        System.out.println("Salary has to be in the range 20000-250000.");
                        System.out.println("Enter the Monthly Salary: ");
                        salary = sc.nextDouble();
                    }
                    else{
                      System.out.println("Enter the bonus percentage for Executive Employees: ");
                      bonuspercent = sc.nextDouble();
                      ExecutiveEmployee e = new ExecutiveEmployee(id, name, des, salary, bonuspercent);
                      e.display();
                      e.calculateBonus();
                    }
                 }

                 else
                 {
                    System.out.println("Please enter  a valid choice!");
                 }

                }

                break;

                default:
                
                    System.out.println("Enter a valid choice!");
                
        }

        sc.close(); 
    }

}
