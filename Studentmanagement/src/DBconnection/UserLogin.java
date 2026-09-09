package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserLogin {

    public static int login(Scanner sc) {

        System.out.println("===== USER LOGIN =====");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        String sql = "SELECT id, role FROM users WHERE username=? AND password=?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int userId = rs.getInt("id");

                System.out.println("Login successful!");
                System.out.println("Role: " + rs.getString("role"));

                return userId;

            } else {

                System.out.println("Invalid username/password!");
                return -1;
            }

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
            return -1;
        }
    }


    // GET ROLE OF LOGGED-IN USER

    public static String getRole(int userId) {

        String role = "";

        String sql = "SELECT role FROM users WHERE id=?";

        try (Connection con = DBconnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                role = rs.getString("role");
            }

        } catch (SQLException e) {

            System.out.println("Error getting role: " + e.getMessage());
        }

        return role;
    }
}