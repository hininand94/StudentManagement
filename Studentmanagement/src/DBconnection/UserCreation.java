package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCreation {

    // Main method - allows separate execution
    public static void main(String[] args) {
        createUser();
    }

    public static void createUser() {

        Utility.printLine();
        System.out.println("CREATE NEW USER");
        Utility.printLine();

        String username = Utility.readString("Username: ");
        String password = Utility.readString("Password: ");
        String role = Utility.readString("Role (ADMIN / STUDENT): ").toUpperCase();

        Connection con = DBconnection.getConnection();

        if (con == null) {
            System.out.println("Database connection failed.");
            return;
        }

        String checkQuery = "SELECT id FROM users WHERE username = ?";
        String insertQuery =
                "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try {
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setString(1, username);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                System.out.println("Username already exists.");

                rs.close();
                checkPs.close();
                con.close();
                return;
            }

            rs.close();
            checkPs.close();

            PreparedStatement ps = con.prepareStatement(insertQuery);

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("User created successfully.");
            } else {
                System.out.println("Failed to create user.");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }
}