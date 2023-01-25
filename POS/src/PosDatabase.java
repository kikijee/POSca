import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class PosDatabase {
	private String myDriver;
    private String myUrl;
    private String myUsername;
    private String myPassword;
    
    public PosDatabase(){
    	myDriver = "com.mysql.cj.jdbc.Driver";
        myUrl = "jdbc:mysql://localhost:3306/posdatabase";
        myUsername = "root";
        myPassword = "root";
    }
    
    public User login(String user, String password) {
    	try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "SELECT * FROM posdatabase.users WHERE users.Username = "+"\""+user+"\""+";";
			Statement st = con.createStatement();	// statement object for String to SQL
			ResultSet rs = st.executeQuery(query);	// result set containing the rows collected from query
			// incorrect username and password check
			if(rs.next()) {
				String u = rs.getString("username");
				String p = rs.getString("password");
				Boolean a = rs.getBoolean("admin");
				if(password.equals(p)) {
					st.close();		// statement object close
					con.close();	// connection close
					return new User(u,p,a);
				}
			
				else {
					st.close();		// statement object close
					con.close();	// connection close
					JOptionPane.showMessageDialog(null, "Invalid Username or Password","ERROR",JOptionPane.INFORMATION_MESSAGE);
					return null;
				}
			}
			else {
				st.close();		// statement object close
				con.close();	// connection close
				JOptionPane.showMessageDialog(null, "Invalid Username or Password","ERROR",JOptionPane.INFORMATION_MESSAGE);
				return null;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
    }
    
    // HAS NOT BEEN TESTED YET
    public boolean add_user(String user, String password, boolean admin) {
    	try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "INSERT into users values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);	// statement object for String to SQL
			ResultSet rs = ps.executeQuery(query);	// result set containing the rows collected from query
			ps.setString(1,user);
			ps.setString(2, query);
			ps.setBoolean(3, admin);
			int num = ps.executeUpdate();
			if(num!=1) {ps.close();con.close();return false;}	// this should not happen
			ps.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.out.println("1 row inserted into USERS");
    	return true;
    }
    
    // HAS NOT BEEN TESTED YET
    public boolean change_privilege(String username, boolean status) {
    	try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "UPDATE users SET admin=? WHERE username=?";
			PreparedStatement ps = con.prepareStatement(query);	// statement object for String to SQL
			ps.setBoolean(1, status);
			ps.setString(2, username);
			ResultSet rs = ps.executeQuery(query);	// result set containing the rows collected from query
			int num = ps.executeUpdate();
			if(num == 0) {ps.close();con.close();return false;}	// user does not exist
			ps.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.out.println("1 row updated into USERS");
    	return true;
    }
    
    // HAS NOT BEEN TESTED YET
    public boolean delete_user(String username) {
    	try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "DELETE FROM users WHERE username=?";
			PreparedStatement ps = con.prepareStatement(query);	// statement object for String to SQL
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery(query);	// result set containing the rows collected from query
			int num = ps.executeUpdate();
			if(num == 0) {ps.close();con.close();return false;}	// invalid username
			ps.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	System.out.println("1 row deleted from USERS");
    	return true;
    }
}
