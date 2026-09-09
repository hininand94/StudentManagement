package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Viewfeedback {

    // Main method for separate execution
    public static void main(String[] args) {
        viewAllFeedback();
    }

    public static void viewAllFeedback() {

        Utility.printLine();
        System.out.println("ALL STUDENT FEEDBACK");
        Utility.printLine();

        String query =
                "SELECT s.name, f.feedback_text, f.rating, f.feedback_date " +
                "FROM feedback f " +
                "JOIN students s ON f.student_id = s.id " +
                "ORDER BY f.feedback_date DESC";

        Connection con = DBconnection.getConnection();

        if (con == null) {
            System.out.println("Database connection failed.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                    rs.getString("name") +
                    " (Rating: " +
                    rs.getInt("rating") +
                    "/5) [" +
                    rs.getDate("feedback_date") +
                    "]"
                );

                System.out.println(
                    "  \"" +
                    rs.getString("feedback_text") +
                    "\""
                );

                Utility.printLine();
            }

            if (!found) {
                System.out.println("No feedback submitted yet.");
            }

            rs.close();

        } catch (SQLException e) {

            System.out.println(
                "Error fetching feedback: " +
                e.getMessage()
            );
        }
    }
}