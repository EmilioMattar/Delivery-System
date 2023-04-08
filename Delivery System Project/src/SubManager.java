import java.util.ArrayList;
public class SubManager extends Managers{
	private Integer DeliveryCount;
	private ArrayList<ShortDistanceDelivery> shortDel = new ArrayList<ShortDistanceDelivery>();
	public SubManager(String firstName, String lastName, String phone, String id, String username, String password,
			String location, Integer deliveryCount) {
		super(firstName, lastName, phone, id, username, password, location);
		DeliveryCount = deliveryCount;
	}

	public Integer getDeliveryCount() {
		return DeliveryCount;
	}

	public void setDeliveryCount(Integer deliveryCount) {
		DeliveryCount = deliveryCount;
	}

	
	public ArrayList<ShortDistanceDelivery> getShortDel() {
		return shortDel;
	}

	public void setShortDel(ArrayList<ShortDistanceDelivery> shortDel) {
		this.shortDel = shortDel;
	}

	@Override
	public String toString() {
		return " SubManager "+super.toString()+" [ DeliveryCount= " + DeliveryCount + " , shortDel= " + shortDel + "]";
	}

}
