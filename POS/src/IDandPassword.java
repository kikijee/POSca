import java.util.HashMap;
public class IDandPassword {
	
	//***THIS CLASS USE HAS DEPRECIATED DUE TO THE USE OF THE SQL DATABASE HANDELING OF USERS***
	
	private HashMap<String,User> login = new HashMap<String,User>();
	
    IDandPassword(){
    	/*
        login.put("j3","beezy");
        login.put("name","pass");
        login.put("java","deez");
        login.put("christian", "mani");
        login.put("alex", "nels");
        */
    	login.put("j3", new User("j3","beezy",false));
    	login.put("java", new User("java","deez",false));
    	login.put("christian", new User("christian","mani",true));
    	
    }
    
    public boolean validate(String username, String password) {
    	if(login.get(username) == null) {return false;}
    	else if(login.get(username).validate(password)) {
    		return true;
    	}
    	else {
    		return false;
    		}
    }
    
    public void add_credentials(String username,String password,Boolean admin) {
    	if(login.get(username) == null) {
    		login.put(username, new User(username,password,admin));
    	}
    }
    
    public User get_user(String username) {
    	return login.get(username);
    }
}
