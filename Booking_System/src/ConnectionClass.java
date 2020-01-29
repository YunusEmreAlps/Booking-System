import javax.swing.JOptionPane; // API
import java.sql.*; // API 

public class ConnectionClass {
	public static Connection dbconnect() {
		
		try { 
			
			// db connection 
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:yunus","system","Yunus1453");
			System.out.println(" - Database connection is success.");
			return conn; 
		} 
		catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
}