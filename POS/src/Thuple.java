import java.io.Serializable;

public class Thuple<X,Y,Z> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final X x;
	public final Y y;
	public final Z z;
	Thuple(X x, Y y, Z z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
}
