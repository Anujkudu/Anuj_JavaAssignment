// Base Class
class Employee {
    String name;
    double salary;

    // Constructor
    Employee(String n, double s) {
        name = n;
        salary = s;
    }

    // Method to display salary
    void displaySalary() {
        System.out.println("Employee Name: " + name);
        System.out.println("Salary: " + salary);
    }
}

// Derived Class 1
class FullTimeEmployee extends Employee {

    FullTimeEmployee(String n, double s) {
        super(n, s);
    }

    void calculateSalary() {
        System.out.println("\n--- Full Time Employee ---");
        System.out.println("Salary before hike: " + salary);
        salary = salary + (salary * 0.50);   // 50% hike
        System.out.println("Salary after 50% hike: " + salary);
    }
}

// Derived Class 2
class InternEmployee extends Employee {

    InternEmployee(String n, double s) {
        super(n, s);
    }

    void calculateSalary() {
        System.out.println("\n--- Intern Employee ---");
        System.out.println("Salary before hike: " + salary);
        salary = salary + (salary * 0.25);   // 25% hike
        System.out.println("Salary after 25% hike: " + salary);
    }
}

// Main Class
public class Anuj3 {
    public static void main(String[] args) {

        FullTimeEmployee f1 = new FullTimeEmployee("Anuj", 20000);
        f1.displaySalary();
        f1.calculateSalary();

        InternEmployee i1 = new InternEmployee("Rahul", 10000);
        i1.displaySalary();
        i1.calculateSalary();
    }
}
