import java.util.HashMap;

import javax.swing.JButton;

public class Items {
	private  HashMap<JButton,Thuple<String,Float,Integer>> item_data = new HashMap<JButton,Thuple<String,Float,Integer>>();
	
	Items(){
		
	}
	
	public void add_item(JButton button,Float price,String name, Integer itemNo) {
		item_data.put(button, new Thuple<String,Float, Integer>(name,price,itemNo));
	}
	
	public Thuple<String,Float, Integer> return_item_data(Object button) {
		if(item_data.get(button) == null) {return new Thuple<String,Float, Integer>("",0.00F, 0);}
		else {return item_data.get(button);}
	}
	
	public HashMap<JButton,Thuple<String,Float,Integer>> returnHashmap() {
		return item_data;
	}
	

}
