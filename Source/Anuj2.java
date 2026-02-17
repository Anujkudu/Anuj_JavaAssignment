class Student {
    int rollNo;
    String name;
    double marks;

    // Constructor
    Student(int r, String n, double m) {
        rollNo = r;
        name = n;
        marks = m;
    }

    // Method
    void displayInfo() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
    }
}

public class Anuj2 {
    public static void main(String[] args) {

        // Creating object
        Student s1 = new Student(101, "Anuj", 85.5);

        // Calling method
        s1.displayInfo();
    }
}
