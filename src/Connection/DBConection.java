package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConection {
	public Connection conectar() throws SQLException {
		try {
			Class.forName ("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "hito3";
		String user = "root";
		String pwd = "campusfp";
		Connection con = DriverManager.getConnection(url+dbName, user, pwd);
		return con;
	}
}