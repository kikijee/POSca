import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public User login(String user, String password, Boolean admin) {
    	try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "SELECT * FROM posdatabase.users WHERE users.Username = "+"\""+user+"\""+";";
			Statement st = con.createStatement();	// statement object for String to SQL
			ResultSet rs = st.executeQuery(query);	// result set containing the rows collected from query
			// incorrect username and password check
			System.out.println(query);
			if(rs.next()) {
				String u = rs.getString("username");
				String p = rs.getString("password");
				Boolean a = rs.getBoolean("admin");
				if(password.equals(p)) {
					return new User(u,p,a);
				}
			
				else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password","ERROR",JOptionPane.INFORMATION_MESSAGE);
					return null;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid Username or Password","ERROR",JOptionPane.INFORMATION_MESSAGE);
				return null;
			}
			st.close();		// statement object close
			con.close();	// connection close
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
