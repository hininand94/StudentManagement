package DBconnection;

import java.sql.*;
import java.util.Scanner;

public class AddStudent {
    public static void add(Scanner sc) {
        try (Connection con = DBconnection.getConnection()) {
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Age:");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Course: ");
            String course = sc.nextLine();

            String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, course);
            pst.executeUpdate();

            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Age must be numeric!");
        }
    }

    // TEMPORARY - remove once Main.java is ready
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        add(sc);
    }
}