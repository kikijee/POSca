import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Order {
	enum Payment{
		CASH,CARD;
	}
	private Float subtotal;
	private Float total;
	private Float owed;
	private int numItems;
	private boolean paid;
	private String user;
	private ArrayList<Thuple<String,Integer,Float>> items;
	private String time;
	private int id;
	private Float change;
	private Payment payType;
	private static int idCounter = 0;
	
	public Order(float sub, float tot, int num, boolean p, String u, String t,ArrayList<Thuple<String,Integer,Float>> it){
		this.subtotal = sub;
		this.total = tot;
		this.numItems = num;
		this.paid = p;
		this.user = u;
		this.items = it;
		this.time = t;
		this.owed = tot;
		this.payType = null;
		this.change = null;
		this.id = idCounter++;
	}
	
	public void print_order() {
		System.out.println("id:"+id+" subtotal:"+subtotal+" total:"+total+" numItems:"+numItems+" paid:"+paid+" user:"+user+" time:"+time);
		for(int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).toString());
		}
	}
	
	public void add_order(DefaultTableModel model) {	// add sent order to the orders table
		model.addRow(new Object[] {id,total,time,user,paid,payType});
	}
	
	public void draw_orders(DefaultTableModel model, JTextField sub, JTextField tot, JTextField it) {
		for(int i = 0; i < items.size(); i++) {
			model.addRow(new Object[] {items.get(i).x,items.get(i).y,items.get(i).z});
			sub.setText(String.valueOf(subtotal));
			tot.setText(String.valueOf(total));
			it.setText(String.valueOf(numItems));
		}
	}
	
	public Float pay_cash(Float amount) {
		if(amount >= total) {
			paid = true;
			payType = Payment.CASH;
			owed = 0.00f;
			change = amount-total;
			return change;
		}
		else {
			return null;
		}
	}
	
	public void pay_card() {
		paid = true;
		payType = Payment.CARD;
		owed = 0.00f;
	}
	
	public boolean return_paid() {return paid;}
	public Float return_total() {return total;}
	public Float return_change() {return change;}
	public Float return_owed() {return owed;}
	public Payment return_type() {return payType;}
	public int return_id() {return id;}
}
