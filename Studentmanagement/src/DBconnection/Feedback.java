package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Feedback {

    public static void main(String[] args) {

        int studentId = Utility.readInt("Enter Student ID: ");

        submitFeedback(studentId);
    }

    public static void submitFeedback(int studentId) {

        Utility.printLine();
        System.out.println("SUBMIT FEEDBACK");
        Utility.printLine();

        String feedbackText = Utility.readString("Enter your feedback: ");
        int rating = Utility.readInt("Rating (1-5): ");

        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        String query = "INSERT INTO feedback " +
                "(student_id, feedback_text, rating, feedback_date) " +
                "VALUES (?, ?, ?, CURDATE())";

        Connection con = DBconnection.getConnection();

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, studentId);
            ps.setString(2, feedbackText);
            ps.setInt(3, rating);

            int rows = ps.executeUpdate();

            System.out.println(
                rows > 0
                ? "Thank you! Feedback submitted."
                : "Failed to submit feedback."
            );

        } catch (SQLException e) {
            System.out.println("Error submitting feedback: " + e.getMessage());
        }
    }
}