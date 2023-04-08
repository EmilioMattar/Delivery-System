public class ExpressDelivery extends Delivery{
	private boolean postOfficeDelivery;
	public ExpressDelivery(String destination, String source, double price, boolean postOfficeDelivery,String code) {
		super(destination, source, price,code);
		this.postOfficeDelivery = postOfficeDelivery;
	}
	
	public boolean isPostOfficeDelivery() {
		return postOfficeDelivery;
	}
	
	public void setPostOfficeDelivery(boolean postOfficeDelivery) {
		this.postOfficeDelivery = postOfficeDelivery;
	}
	
	@Override
	public String toString() {
		return "ExpressDelivery "+ super.toString()+ " [postOfficeDelivery=" + postOfficeDelivery + "]";
	}
}