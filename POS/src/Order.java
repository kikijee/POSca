import java.util.ArrayList;

public class Order {
	private float subtotal;
	private float total;
	private int numItems;
	private boolean paid;
	private ArrayList<Thuple<String,Integer,Float>>  items;
	private int id;
	private static int idCounter = 0;
	
	public Order(float sub, float tot, int num, boolean p, ArrayList<Thuple<String,Integer,Float>> it){
		this.subtotal = sub;
		this.total = tot;
		this.numItems = num;
		this.paid = p;
		//this.items = new ArrayList<Thuple<String,Integer,Float>>(it); // copy
		this.items = it;
		this.id = idCounter++;
	}
	
	public void print_order() {
		System.out.println("id:"+id+" subtotal:"+subtotal+" total:"+total+" numItems:"+numItems+" paid:"+paid);
		for(int i = 0; i < items.size(); i++) {
			System.out.println(items.get(i).toString());
		}
	}
}
