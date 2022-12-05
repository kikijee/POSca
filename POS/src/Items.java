import java.util.HashMap;

import javax.swing.JButton;

public class Items {
	private HashMap<JButton,Float> item_data = new HashMap<JButton,Float>();
	
	Items(){
		
	}
	
	public void add_item(JButton button,Float price) {
		item_data.put(button, price);
	}
	public Float return_price(Object button) {
		if(item_data.get(button) == null) {return 0.00F;}
		else {return item_data.get(button);}
	}
}
