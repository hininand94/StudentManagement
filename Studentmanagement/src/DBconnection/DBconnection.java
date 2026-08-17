package DBconnection;

import java.sql.*;

public class DBconnection {

    static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "Nandhini@24";

    public static Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database!");
        } catch (SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
        return con;
    }

    public static void initializeTables() {
        String createStudents = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50) NOT NULL, " +
                "age INT, " +
                "course VARCHAR(50))";

        String createCourses = "CREATE TABLE IF NOT EXISTS courses (" +
                "course_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "course_name VARCHAR(50) NOT NULL, " +
                "duration VARCHAR(20))";

        String createMarks = "CREATE TABLE IF NOT EXISTS marks (" +
                "marks_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "student_id INT, " +
                "subject VARCHAR(50), " +
                "marks_obtained INT, " +
                "grade VARCHAR(5), " +
                "FOREIGN KEY (student_id) REFERENCES students(id))";

        String createAdmin = "CREATE TABLE IF NOT EXISTS admin (" +
                "admin_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "username VARCHAR(30) UNIQUE NOT NULL, " +
                "password VARCHAR(50) NOT NULL)";

        // Run each one separately so one failure doesn't block the rest
        createTableSafely(createStudents, "students");
        createTableSafely(createCourses, "courses");
        createTableSafely(createMarks, "marks");
        createTableSafely(createAdmin, "admin");

        // After attempting all, print what actually exists in the database
        listTables();
    }

    private static void createTableSafely(String sql, String tableName) {
        try (Connection con = getConnection();
             Statement st = con.createStatement()) {
            st.execute(sql);
            System.out.println("Table '" + tableName + "' checked/created successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create table '" + tableName + "': " + e.getMessage());
        }
    }

    // NEW: prints all tables currently in the database
    public static void listTables() {
        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SHOW TABLES")) {

            System.out.println("\n--- Tables currently in database ---");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(rs.getString(1));
            }
            if (!found) {
                System.out.println("No tables found!");
            }
            System.out.println("-------------------------------------");

        } catch (SQLException e) {
            System.out.println("Could not list tables: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initializeTables();
    }
}