import java.util.HashMap;

import javax.swing.JButton;

public class Items {
	private HashMap<JButton,Tuple<String,Float>> item_data = new HashMap<JButton,Tuple<String,Float>>();
	
	Items(){
		
	}
	
	public void add_item(JButton button,Float price,String name) {
		item_data.put(button, new Tuple<String,Float>(name,price));
	}
	public Tuple<String,Float> return_item_data(Object button) {
		if(item_data.get(button) == null) {return new Tuple<String,Float>("",0.00F);}
		else {return item_data.get(button);}
	}
}
