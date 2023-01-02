
public class User {
	private String username;
	private String password;
	private Boolean admin;	// true if admin false if not
	
	public User(String user, String pass, Boolean ad){
		username = user;
		password = pass;
		admin = ad;
	}
	
	public Boolean validate(String pass) {
		return pass.equals(password);
	}
	public String return_username() {
		return username;
	}
	
	public Boolean is_admin() {
		return admin;
	}
}
