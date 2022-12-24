import java.util.ArrayList;

public class Order {
	private float subtotal;
	private float total;
	private int numItems;
	private boolean paid;
	private String user;
	private ArrayList<Thuple<String,Integer,Float>>  items;
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
}
