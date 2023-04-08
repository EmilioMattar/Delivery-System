public class BuisnessDelivery extends Delivery {
	private String buisnessName;
	public BuisnessDelivery(String destination, String source, double price,String buisnessName,String code) {
		super(destination, source, price,code);
		this.buisnessName=buisnessName;
	}
	public String getBuisnessName() {
		return buisnessName;
	}
	public void setBuisnessName(String buisnessName) {
		this.buisnessName = buisnessName;
	}
	@Override
	public String toString() {
		return "BuisnessDelivery "+super.toString() + ", buisnessName=" + buisnessName + "]";
	}
}