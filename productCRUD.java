package collegeWork.jdbcExperiment6;
import java.sql.*;
import java.util.Scanner;

public class productCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/college";
    static final String USER = "root";
    static final String PASS = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            con.setAutoCommit(false); // enable manual transaction control
            int choice;

            do {
                System.out.println("\n=== PRODUCT MENU ===");
                System.out.println("1. Insert Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1 -> insertProduct(con, sc);
                    case 2 -> viewProducts(con);
                    case 3 -> updateProduct(con, sc);
                    case 4 -> deleteProduct(con, sc);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }

            } while (choice != 5);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void insertProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();

            PreparedStatement ps = con.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setInt(4, qty);
            ps.executeUpdate();
            con.commit();
            System.out.println("✅ Product inserted successfully!");
        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
    }

    static void viewProducts(Connection con) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Product");
            System.out.println("ProductID\tName\tPrice\tQty");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDouble(3) + "\t" + rs.getInt(4));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    static void updateProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Product ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter New Name: ");
            String name = sc.nextLine();
            System.out.print("Enter New Price: ");
            double price = sc.nextDouble();
            System.out.print("Enter New Quantity: ");
            int qty = sc.nextInt();

            PreparedStatement ps = con.prepareStatement("UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?");
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, qty);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product updated successfully!");
            } else {
                System.out.println("❌ Product not found!");
            }
        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
    }

    static void deleteProduct(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Product ID to Delete: ");
            int id = sc.nextInt();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE ProductID=?");
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                con.commit();
                System.out.println("✅ Product deleted successfully!");
            } else {
                System.out.println("❌ Product not found!");
            }
        } catch (Exception e) {
            try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
    }
}
