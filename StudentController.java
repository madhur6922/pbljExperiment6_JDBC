package collegeWork.jdbcExperiment6.StudentMVC.view;


import collegeWork.jdbcExperiment6.StudentMVC.dao.StudentDAO;
import collegeWork.jdbcExperiment6.StudentMVC.model.Student;

import java.util.*;

public class StudentController {
    public static void main(String[] args) {
        try {
            StudentDAO dao = new StudentDAO();
            Scanner sc = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n=== STUDENT MENU ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("ID: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Dept: ");
                        String dept = sc.nextLine();
                        System.out.print("Marks: ");
                        double marks = sc.nextDouble();
                        dao.addStudent(new Student(id, name, dept, marks));
                    }
                    case 2 -> {
                        List<Student> list = dao.getAllStudents();
                        System.out.println("ID\tName\tDept\tMarks");
                        for (Student s : list) System.out.println(s);
                    }
                    case 3 -> {
                        System.out.print("Enter ID to Update: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("New Name: ");
                        String name = sc.nextLine();
                        System.out.print("New Dept: ");
                        String dept = sc.nextLine();
                        System.out.print("New Marks: ");
                        double marks = sc.nextDouble();
                        dao.updateStudent(new Student(id, name, dept, marks));
                    }
                    case 4 -> {
                        System.out.print("Enter ID to Delete: ");
                        int id = sc.nextInt();
                        dao.deleteStudent(id);
                    }
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }

            } while (choice != 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
