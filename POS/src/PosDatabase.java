
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class PosDatabase {
	private String myDriver;
    private String myUrl;
    private String myUsername;
    private String myPassword;
    private transient InputStream imag;
    
    public PosDatabase(){
    	myDriver = "com.mysql.cj.jdbc.Driver";
        myUrl = "jdbc:mysql://localhost:3306/posdatabase";
        myUsername = "root";
        myPassword = "root";
    }
    
        //=====================ITEM TABLE QUERY FUNCTIONALITY====================
    public Items checkItemprice(Object item, Items it) {
    	try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			HashMap<JButton, Thuple<String,Float,Integer>> hmValues = it.returnHashmap();
			Iterator hmIterator = hmValues.entrySet().iterator();
			Statement st = con.createStatement();	// statement object for String to SQL
			//iterate through hashmap to get ID and price of item
			while (hmIterator.hasNext()) {
				Map.Entry mapElement = (Map.Entry)hmIterator.next();
				Object priceVal = (mapElement.getValue());
				Thuple<String,Float,Integer> myObj = (Thuple<String,Float,Integer>)priceVal;
				priceVal = myObj.y;
				Object item_ID = myObj.z;
				Object objName = myObj.x;
				 System.out.println(item_ID);
				String query = "SELECT * FROM posdatabase.items WHERE items.ItemID = "+"\""+item_ID+"\""+";";
				ResultSet rs = st.executeQuery(query);	// result set containing the rows collected from query
				 if(rs.next()) {
					 Integer itemID = rs.getInt("ItemID");
					 Float itemPrice = rs.getFloat("Price");
					 System.out.println(itemID);
					 if(itemPrice.equals(priceVal)) {
						 System.out.println("PRICE SAME");
					 
				 } else{
					 System.out.println("PRICE CHANGE");
					 myObj.y = itemPrice;
					 System.out.println(myObj);
				 	}
				 }
			}
			 st.close();		// statement object close
			 con.close();	// connection close
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
    }
    
    //===============USER TABLE QUERY FUNCTIONALITY===============
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
					rs.close();
					return new User(u,p,a);
				}
			
				else {
					st.close();		// statement object close
					con.close();	// connection close
					rs.close();
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
			ps.setString(1,user);
			ps.setString(2, query);
			ps.setBoolean(3, admin);
			//ps.executeQuery(query);	// result set containing the rows collected from query
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
			//ps.executeQuery(query);	// result set containing the rows collected from query
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
   //============================================================
    
   //===============ORDER TABLE QUERY FUNCTIONALITY===============
   public boolean validate_id(Order order) {
	   try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			Statement st = con.createStatement();	// statement object for String to SQL
			int id = order.return_id();
			while(true) {
				String query = "SELECT * FROM orders WHERE orderid = "+String.valueOf(id)+";"; 
				ResultSet rs = st.executeQuery(query);
				if(!rs.next()) {order.set_id(id); Order.set_id_counter(id); break;}	
				else {id++;}
			}
			st.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   return true;
   }
    
   public boolean add_order(int id, Float subtotal, Float total, String time, String server, boolean paid, int numItems, ArrayList<Thuple<String,Integer,Float>>items) {
	   byte data[] = null;
	   try {
		   	// object serialization
		   	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream(baos);
	        oos.writeObject(items);
	        oos.flush();
	        oos.close();
	        baos.close();
	        data = baos.toByteArray();
	        
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "INSERT into orders values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);	// statement object for String to SQL
			ps.setInt(1, id);
			ps.setFloat(2, subtotal);
			ps.setFloat(3, total);
			ps.setString(4, time);
			ps.setString(5, server);
			ps.setBoolean(6, paid);
			ps.setString(7, null); //null
			ps.setNull(8, java.sql.Types.NULL);	//null what... an enum of sorts?
			ps.setInt(9, numItems);
			ps.setObject(10, data);
			int num = ps.executeUpdate();
			if(num==0) {ps.close();con.close();return false;}	// this should not happen
			ps.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	System.out.println("1 row inserted into ORDERS");
   	return true;
   }
   
   public boolean pay_cash(float amount,int orderId,float change) {
	   try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "UPDATE orders SET paid=?, paytype=?, orders.change=? WHERE orderid=?"; // NOTE: change is a reserved keyword in mysql
			PreparedStatement ps = con.prepareStatement(query);	// statement object for String to SQL
			ps.setBoolean(1,true);
			ps.setString(2, "CASH");
			ps.setFloat(3, change);
			ps.setInt(4, orderId);
			int num = ps.executeUpdate();
			if(num == 0) {ps.close();con.close();return false;}	// user does not exist
			ps.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	System.out.println("1 row updated into ORDERS");
   	return true;
   }
   
   public boolean pay_card(int orderId) {
	   try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "UPDATE orders SET paid=?, paytype=?, orders.change=? WHERE orderid=?"; // NOTE: change is a reserved keyword in mysql
			PreparedStatement ps = con.prepareStatement(query);	// statement object for String to SQL
			ps.setBoolean(1,true);
			ps.setString(2, "CARD");
			ps.setFloat(3, 0.00f);
			ps.setInt(4, orderId);
			int num = ps.executeUpdate();
			if(num == 0) {ps.close();con.close();return false;}	// user does not exist
			ps.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	System.out.println("1 row updated into ORDERS");
   	return true;
   }
   
   public boolean void_order(int orderId, String time, String server, String desc) {
	   try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "DELETE FROM orders WHERE orderid="+String.valueOf(orderId)+";"; // NOTE: change is a reserved keyword in mysql
			Statement st = con.createStatement();	// statement object for String to SQL
			int num = st.executeUpdate(query);
			if(num == 0) {st.close();con.close();return false;}	// user does not exist
			st.close();
	    	con.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   System.out.println("1 row deleted from ORDERS");
	   return true;
   }
   
   public int get_max_id() {
	   try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "SELECT MAX(orders.orderid) FROM orders";
			Statement st = con.createStatement();	// statement object for String to SQL
			ResultSet rs = st.executeQuery(query);	// result set containing the rows collected from query
			// incorrect username and password check
			if(rs.next()) {
				int val = rs.getInt("MAX(orders.orderid)");
				st.close();		// statement object close
				con.close();	// connection close
				rs.close();
				return val;
			}
			else {
				st.close();		// statement object close
				con.close();	// connection close
				rs.close();
				return -1;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   return -1;
   }
   
   public boolean redraw_orders(ArrayList<Order> orders) {
	   orders.clear();
	   try {
			Class.forName(myDriver);
			Connection con = DriverManager.getConnection(myUrl,myUsername,myPassword);
			String query = "SELECT * FROM posdatabase.orders ORDER by orderid ASC;";
			Statement st = con.createStatement();	// statement object for String to SQL
			ResultSet rs = st.executeQuery(query);	// result set containing the rows collected from query
			// incorrect username and password check
			if(rs.next()) {
				do {
					ArrayList<Thuple<String,Integer,Float>>items;
					byte [] bytes = rs.getBlob("items").getBytes(1l, (int)rs.getBlob("items").length());
					ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
				    ObjectInput in = new ObjectInputStream(bis);
				    items = (ArrayList<Thuple<String,Integer,Float>>) in.readObject();
					orders.add(new Order(rs.getInt("orderid"),rs.getFloat("subtotal"),rs.getFloat("total"),rs.getString("time"),rs.getString("server"),rs.getBoolean("paid"),rs.getString("paytype"),rs.getFloat("orders.change") ,rs.getInt("numitems"),items));
				}while(rs.next());
			}
			else {
				st.close();		// statement object close
				con.close();	// connection close
				return false;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   	return true;
   }
   //=============================================================
}

