import java.util.ArrayList;

public class Order {
	private int subtotal;
	private int total;
	private int numItems;
	private ArrayList<Thuple<String,Integer,Float>>  items;
	private static int id = 0;
	
	public Order(int sub, int tot, int num, ArrayList<Thuple<String,Integer,Float>> it){
		this.subtotal = sub;
		this.total = tot;
		this.numItems = num;
		this.items = new ArrayList<Thuple<String,Integer,Float>>(it); // copy
		this.id++;
	}
}
