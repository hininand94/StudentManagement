package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Skillentry {

    public static void main(String[] args) {

        int studentId = Utility.readInt("Enter Student ID: ");

        System.out.println("1. Add Skill");
        System.out.println("2. View Skills");

        int choice = Utility.readInt("Enter your choice: ");

        if (choice == 1) {
            addSkill(studentId);
        } 
        else if (choice == 2) {
            viewSkills(studentId);
        } 
        else {
            System.out.println("Invalid choice.");
        }
    }

    public static void addSkill(int studentId) {
        Utility.printLine();
        System.out.println("ADD SKILL");
        Utility.printLine();

        String skillName = Utility.readString("Skill Name: ");
        String proficiency = Utility.readString(
            "Proficiency (Beginner/Intermediate/Advanced): "
        );

        String query = "INSERT INTO skills (student_id, skill_name, proficiency) VALUES (?, ?, ?)";
        Connection con = DBconnection.getConnection();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ps.setString(2, skillName);
            ps.setString(3, proficiency);

            int rows = ps.executeUpdate();

            System.out.println(
                rows > 0 ? "Skill added successfully." : "Failed to add skill."
            );

        } catch (SQLException e) {
            System.out.println("Error adding skill: " + e.getMessage());
        }
    }

    public static void viewSkills(int studentId) {
        Utility.printLine();
        System.out.println("YOUR SKILLS");
        Utility.printLine();

        String query = "SELECT skill_name, proficiency FROM skills WHERE student_id = ?";
        Connection con = DBconnection.getConnection();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getString("skill_name") + " - " +
                    rs.getString("proficiency")
                );
            }

            if (!found) {
                System.out.println("No skills added yet.");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching skills: " + e.getMessage());
        }
    }
}