import java.util.Scanner;

class Shapes {

    Shapes() {
        System.out.println("Default Constructor");
    }

    // Constructor Overloading
    Shapes(int r) {
        System.out.println("Area of Circle: " + (3.14 * r * r));
    }

    Shapes(int l, int b) {
        System.out.println("Area of Rectangle: " + (l * b));
    }

    // Method Overloading
    void area(int side) {
        System.out.println("Area of Square: " + (side * side));
    }

    void area(double r) {
        System.out.println("Area of Circle: " + (3.14 * r * r));
    }
}

public class Java3_1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Shapes s = new Shapes();

        // Taking input from user
        System.out.print("Enter radius for circle: ");
        int r = sc.nextInt();

        System.out.print("Enter length and breadth for rectangle: ");
        int l = sc.nextInt();
        int b = sc.nextInt();

        System.out.print("Enter side for square: ");
        int side = sc.nextInt();

        System.out.print("Enter radius (double) for circle: ");
        double rd = sc.nextDouble();

        // Calling constructors
        Shapes s1 = new Shapes(r);
        Shapes s2 = new Shapes(l, b);

        // Calling overloaded methods
        s.area(side);
        s.area(rd);

        sc.close();
    }
}