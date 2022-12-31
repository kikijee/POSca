import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Order {
	private float subtotal;
	private float total;
	private int numItems;
	private boolean paid;
	private String user;
	private ArrayList<Thuple<String,Integer,Float>> items;
	private String time;
	private int id;
	private static int idCounter = 0;
	
	public Order(float sub, float tot, int num, boolean p, String u, String t,ArrayList<Thuple<String,Integer,Float>> it){
		this.subtotal = sub;
		this.total = tot;
		this.numItems = num;
		this.paid = p;
		this.user = u;
		this.items = it;
		this.time = t;
		this.id = idCounter++;
	}
	
	public void print_order() {
		System.out.println("id:"+id+" subtotal:"+subtotal+" total:"+total+" numItems:"+numItems+" paid:"+paid+" user:"+user+" time:"+time);
		for(int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).toString());
		}
	}
	
	public void add_order(DefaultTableModel model) {	// add sent order to the orders table
		model.addRow(new Object[] {id,total,time,user,paid});
	}
	
	public void draw_orders(DefaultTableModel model, JTextField sub, JTextField tot, JTextField it) {
		for(int i = 0; i < items.size(); i++) {
			model.addRow(new Object[] {items.get(i).x,items.get(i).y,items.get(i).z});
			sub.setText(String.valueOf(subtotal));
			tot.setText(String.valueOf(total));
			it.setText(String.valueOf(numItems));
		}
	}
}
