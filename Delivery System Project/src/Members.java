import java.util.ArrayList;
public class Members extends Person implements Comparable<Members>{
	private String location;
	private ArrayList<Delivery> deliveries;
	public Members(String firstName, String lastName, String phone, String id, String location) {
		super(firstName, lastName, phone, id);
		this.location = location;
		this.deliveries = new ArrayList<Delivery>(0);
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public ArrayList<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(ArrayList<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	public int compareTo(Members b)
	{
		return this.lastName.compareTo(b.lastName);
	}
	@Override
	public String toString() {
		return "Member "+super.toString()+" [location=" + location +"]";
	}
}
