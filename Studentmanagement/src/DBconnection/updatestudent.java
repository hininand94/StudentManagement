package DBconnection;

import java.sql.*;
import java.util.Scanner;

public class updatestudent {
    public static void update(Scanner sc) {
        try (Connection con = DBconnection.getConnection()) {
            System.out.print("Student ID to update: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("New Name: ");
            String name = sc.nextLine();
            System.out.print("New Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("New Course: ");
            String course = sc.nextLine();

            String sql = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, course);
            pst.setInt(4, id);

            int rows = pst.executeUpdate();
            System.out.println(rows > 0 ? "Student updated!" : "Student ID not found!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Numeric value expected!");
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        update(sc);
    }
}