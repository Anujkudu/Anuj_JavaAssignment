import java.util.Scanner;

class Hillstations {

    void famousfood() {
        System.out.println("Famous Food of Hillstation");
    }

    void famousfor() {
        System.out.println("Famous for scenic beauty");
    }
}

// Subclass 1
class Manali extends Hillstations {
    void famousfood() {
        System.out.println("Manali Famous Food: Maggi");
    }

    void famousfor() {
        System.out.println("Manali Famous For: Snow and Mountains");
    }
}

// Subclass 2
class Ooty extends Hillstations {
    void famousfood() {
        System.out.println("Ooty Famous Food: Chocolates");
    }

    void famousfor() {
        System.out.println("Ooty Famous For: Tea Gardens");
    }
}

// Subclass 3
class Shimla extends Hillstations {
    void famousfood() {
        System.out.println("Shimla Famous Food: Chole Bhature");
    }

    void famousfor() {
        System.out.println("Shimla Famous For: Mall Road");
    }
}

public class Java3_2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hillstations h;

        System.out.println("Choose Hill Station:");
        System.out.println("1. Manali");
        System.out.println("2. Ooty");
        System.out.println("3. Shimla");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        // Runtime Polymorphism
        switch (choice) {
            case 1:
                h = new Manali();
                break;
            case 2:
                h = new Ooty();
                break;
            case 3:
                h = new Shimla();
                break;
            default:
                System.out.println("Invalid Choice");
                sc.close();
                return;
        }

        h.famousfood();
        h.famousfor();

        sc.close();
    }
}