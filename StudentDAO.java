package collegeWork.jdbcExperiment6.StudentMVC.dao;


import collegeWork.jdbcExperiment6.StudentMVC.model.Student;

import java.sql.*;

import java.util.*;

public class StudentDAO {
    private Connection con;

    public StudentDAO() throws Exception {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "your_password");
    }

    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO Student VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, s.getStudentID());
        ps.setString(2, s.getName());
        ps.setString(3, s.getDepartment());
        ps.setDouble(4, s.getMarks());
        ps.executeUpdate();
        System.out.println("✅ Student added successfully!");
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Student");
        while (rs.next()) {
            list.add(new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
        }
        return list;
    }

    public void updateStudent(Student s) throws SQLException {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getName());
        ps.setString(2, s.getDepartment());
        ps.setDouble(3, s.getMarks());
        ps.setInt(4, s.getStudentID());
        ps.executeUpdate();
        System.out.println("✅ Student updated successfully!");
    }

    public void deleteStudent(int id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM Student WHERE StudentID=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("✅ Student deleted successfully!");
    }
}
