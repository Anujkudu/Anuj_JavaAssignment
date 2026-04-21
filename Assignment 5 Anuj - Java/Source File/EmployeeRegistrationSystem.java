
import java.sql.*;
import java.util.*;

class Employee {
    private int id;
    private String name, email, department;
    private double salary;

    public Employee() {}

    public Employee(String name, String email, String department, double salary) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public int    getId()           { return id; }
    public void   setId(int id)     { this.id = id; }
    public String getName()         { return name; }
    public void   setName(String n) { this.name = n; }
    public String getEmail()        { return email; }
    public void   setEmail(String e){ this.email = e; }
    public String getDepartment()   { return department; }
    public void   setDepartment(String d) { this.department = d; }
    public double getSalary()       { return salary; }
    public void   setSalary(double s){ this.salary = s; }

    @Override
    public String toString() {
        return String.format("ID:%-4d Name:%-20s Email:%-25s Dept:%-15s Salary:%.2f",
                id, name, email, department, salary);
    }
}

class DBUtil {
    private static final String URL  =
        "jdbc:mysql://localhost:3306/employeedb?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "anuj";   

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found. Is the connector JAR on the classpath?", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

class EmployeeDAO {

    public boolean insert(Employee e) throws SQLException {
        String sql = "INSERT INTO employees (name, email, department, salary) VALUES (?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getDepartment());
            ps.setDouble(4, e.getSalary());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Employee> getAll() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection c = DBUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = new Employee(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getDouble("salary"));
                e.setId(rs.getInt("id"));
                list.add(e);
            }
        }
        return list;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}

class Main {

    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Registration System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Delete Employee");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Name       : "); String name  = sc.nextLine();
                        System.out.print("Email      : "); String email = sc.nextLine();
                        System.out.print("Department : "); String dept  = sc.nextLine();
                        System.out.print("Salary     : "); double sal   = sc.nextDouble(); sc.nextLine();
                        boolean added = dao.insert(new Employee(name, email, dept, sal));
                        System.out.println(added ? "Employee added successfully!" : "Failed to add.");
                        break;

                    case 2:
                        List<Employee> list = dao.getAll();
                        if (list.isEmpty()) {
                            System.out.println("No employees found.");
                            break;
                        }
                        System.out.println("\n" + "-".repeat(85));
                        list.forEach(System.out::println);
                        System.out.println("-".repeat(85));
                        break;

                    case 3:
                        System.out.print("Enter Employee ID to delete: ");
                        int id = sc.nextInt(); sc.nextLine();
                        boolean deleted = dao.delete(id);
                        System.out.println(deleted ? "Deleted successfully!" : "ID not found.");
                        break;

                    case 4:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please enter 1–4.");
                }
            } catch (SQLException ex) {
                System.out.println("DB Error: " + ex.getMessage());
            }
        }
    }
}
