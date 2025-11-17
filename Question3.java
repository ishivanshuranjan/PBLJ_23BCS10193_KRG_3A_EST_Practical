import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Question3 {
    
	public static void main(String[] args) {
		final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
		final String DB_USER = "root"; 
		final String DB_PASSWORD = "pass@123"; 

		final String INSERT_SQL = "INSERT INTO employee (name, position, salary) VALUES (?, ?, ?)";
		final String SELECT_SQL = "SELECT id, name, position, salary FROM employee ORDER BY name";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("MySQL JDBC Driver not found. Add it to classpath.");
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			try (Statement stmt = conn.createStatement()) {
				stmt.executeUpdate(INSERT_SQL);
			}

			try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
				Object[][] samples = {
					{"Alice", "Developer", 75000.0},
					{"Bob", "Manager", 88000.0},
					{"Charlie", "Tester", 60000.0}
				};
				for (Object[] emp : samples) {
					ps.setString(1, (String) emp[0]);
					ps.setString(2, (String) emp[1]);
					ps.setDouble(3, (Double) emp[2]);
					ps.executeUpdate();
				}
			}
			try (PreparedStatement ps = conn.prepareStatement(SELECT_SQL);
				ResultSet rs = ps.executeQuery()) {
				System.out.println("ID\tName\t\tPosition\tSalary");
				System.out.println("-------------------------------------------------");
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String position = rs.getString("position");
					double salary = rs.getDouble("salary");
					System.out.printf("%d\t%s\t\t%s\t\t%.2f%n", id, name, position, salary);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
