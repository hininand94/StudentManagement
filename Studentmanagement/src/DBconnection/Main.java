package DBconnection;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println();
            System.out.println("==============================");
            System.out.println("     STUDENT MANAGEMENT");
            System.out.println("==============================");
            System.out.println("1. User Login");
            System.out.println("2. Create User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            switch (choice) {

                case 1:

                    int userId = UserLogin.login(sc);

                    if (userId != -1) {

                        String role = UserLogin.getRole(userId);

                        if (role.equalsIgnoreCase("ADMIN")) {

                            adminMenu(sc);

                        } else if (role.equalsIgnoreCase("STUDENT")) {

                            studentMenu(sc, userId);

                        } else {

                            System.out.println("Invalid role!");
                        }
                    }

                    break;

                case 2:

                    UserCreation.createUser();
                    break;

                case 3:

                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:

                    System.out.println("Invalid choice!");
            }
        }
    }


    // ================= ADMIN MENU =================

    public static void adminMenu(Scanner sc) {

        while (true) {

            System.out.println();
            System.out.println("========== ADMIN MENU ==========");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. View Students");
            System.out.println("4. Create Event");
            System.out.println("5. View Events");
            System.out.println("6. View Student Feedback");
            System.out.println("7. Logout");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    AddStudent.add(sc);
                    break;

                case 2:
                    updatestudent.update(sc);
                    break;

                case 3:
                    Viewstudents.view();
                    break;

                case 4:
                    CreateEven.addEvent();
                    break;

                case 5:
                    ViewEventList.viewEvents();
                    break;

                case 6:
                    Viewfeedback.viewAllFeedback();
                    break;

                case 7:
                    System.out.println("Admin logged out.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // ================= STUDENT MENU =================

    public static void studentMenu(Scanner sc, int userId) {

        System.out.println();
        System.out.println("========== STUDENT LOGIN ==========");
        System.out.println("Logged in User ID: " + userId);

        System.out.print("Enter Student ID: ");

        int studentId;

        try {
            studentId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Student ID!");
            return;
        }

        while (true) {

            System.out.println();
            System.out.println("========== STUDENT MENU ==========");
            System.out.println("1. View Events");
            System.out.println("2. Add Skill");
            System.out.println("3. View Skills");
            System.out.println("4. Submit Feedback");
            System.out.println("5. Logout");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    ViewEventList.viewEvents();
                    break;

                case 2:
                    Skillentry.addSkill(studentId);
                    break;

                case 3:
                    Skillentry.viewSkills(studentId);
                    break;

                case 4:
                    Feedback.submitFeedback(studentId);
                    break;

                case 5:
                    System.out.println("Student logged out.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}