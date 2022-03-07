package Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class UsersDB {
	private Connection con;
	
	public UsersDB(){
		
		try {
			this.con = new DBConection().conectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User> getAll(){
		List<User> usuarios = new ArrayList<User>();
		try {
			
			Statement stmt; 
			ResultSet rs;
			stmt = this.con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM usuarios");
			while(rs.next()) {
				usuarios.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("clave")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return usuarios;
	}
	
	public String update(String username,String password) {
		String msg = "";
		try {
			PreparedStatement pstmt;
			String sql = "update usuarios set clave=? where username=?";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			
			
			int row = pstmt.executeUpdate();
			if(row>0) {
				msg = "Actualizado con éxito";
			}else {
				msg = "Error al actualizarse";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return msg;
	}
	
	public String register(String username,String password) {
		String msg = "";
		try {
			PreparedStatement pstmt;
			String sql = "insert into usuarios(username, clave) values(?,?)";
			pstmt = this.con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			int row = pstmt.executeUpdate();
			if(row>0) {
				msg = "Registrado con éxito";
			}else {
				msg = "Error al registrarse";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return msg;
	}

}
