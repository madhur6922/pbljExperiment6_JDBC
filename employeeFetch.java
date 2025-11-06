package collegeWork.jdbcExperiment6;

import java.sql.*;

public class employeeFetch {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/college";
        String user = "root";
        String pass = "your_password";

        try {
            // 1. Load and register driver (optional in newer Java)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection
            Connection con = DriverManager.getConnection(url, user, pass);

            // 3. Create Statement
            Statement st = con.createStatement();

            // 4. Execute query
            ResultSet rs = st.executeQuery("SELECT * FROM Employee");

            // 5. Display results
            System.out.println("EmpID\tName\tSalary");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + "\t" + rs.getString("Name") + "\t" + rs.getDouble("Salary"));
            }

            // 6. Close
            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
