package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewEventList {

    public static void main(String[] args) {
        viewEvents();
    }

    public static void viewEvents() {

        Utility.printLine();
        System.out.println("UPCOMING EVENTS");
        Utility.printLine();

        String query =
                "SELECT id, event_name, event_date, description " +
                "FROM events ORDER BY event_date";

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
                        "ID: " + rs.getInt("id")
                        + " | " + rs.getString("event_name")
                        + " | Date: " + rs.getString("event_date")
                        + " | " + rs.getString("description")
                );
            }

            if (!found) {
                System.out.println("No events scheduled yet.");
            }

            rs.close();

        } catch (SQLException e) {
            System.out.println(
                    "Error fetching events: " + e.getMessage()
            );
        }
    }
}