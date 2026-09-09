package DBconnection;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateEven {

    public static void main(String[] args) {
        addEvent();
    }

    public static void addEvent() {

        Utility.printLine();
        System.out.println("ADD NEW EVENT");
        Utility.printLine();

        String name = Utility.readString("Event Name: ");
        String date = Utility.readString("Event Date (YYYY-MM-DD): ");
        String description = Utility.readString("Description: ");

        String query =
                "INSERT INTO events (event_name, event_date, description) " +
                "VALUES (?, ?, ?)";

        Connection con = DBconnection.getConnection();

        if (con == null) {
            System.out.println("Database connection failed.");
            return;
        }

        try (PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, description);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Event added successfully.");
            } else {
                System.out.println("Failed to add event.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding event: " + e.getMessage());
        }
    }
}