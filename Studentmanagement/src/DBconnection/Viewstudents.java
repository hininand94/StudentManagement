package DBconnection;

import java.sql.*;

public class Viewstudents {
    public static void view() {
        try (Connection con = DBconnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM students")) {

            System.out.println("\nID\tName\tAge\tCourse");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getInt("age") + "\t" +
                        rs.getString("course"));
            }
            if (!found) System.out.println("No students found!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    	view();
    }
}