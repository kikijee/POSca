// general purpose tuple class
public class Tuple <X,Y>{
	public final X x;
	public final Y y;
	Tuple(X x, Y y){
		this.x = x;
		this.y = y;
	}
	public String toString() {
		return "("+x+","+y+")";
	}
}
