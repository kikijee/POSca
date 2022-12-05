import java.util.HashMap;
public class IDandPassword {
	
	private HashMap<String,String> login = new HashMap<String,String>();

    IDandPassword(){
        login.put("j3","beezy");
        login.put("name","pass");
        login.put("java","deez");
        login.put("christian", "mani");
        login.put("alex", "nels");
    }
    
    public boolean validate(String username, String password) {
    	if(login.get(username) == null) {return false;}
    	else if(login.get(username).equals(password)) {
    		return true;
    	}
    	else {
    		return false;
    		}
    }
    
    public void add_credentials(String username,String password) {
    	login.put(username, password);
    }
   
}
