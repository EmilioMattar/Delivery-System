import java.util.Date;

public class ShortDistanceDelivery extends Delivery{
	private Date date;
	private Integer distance;
	private Members member;
	private Double pricePerKm;
	public ShortDistanceDelivery(String destination, String source, Date date, Integer distance,double price,
			Members member,String code,Double pricePerKm) {
		super(destination, source, price,code);
		this.date = date;
		this.distance = distance;
		this.member = member;
		this.pricePerKm=pricePerKm;
	}
	public ShortDistanceDelivery(String code)
	{
		super(code);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Members getMember() {
		return member;
	}
	public void setMember(Members member) {
		this.member = member;
	}
	
	public Double getPricePerKm() {
		return pricePerKm;
	}
	public void setPricePerKm(Double pricePerKm) {
		this.pricePerKm = pricePerKm;
	}
	@Override
	public String toString() {
		return "ShortDistanceDelivery [date= " + date + ", distance= " + distance + ", member= " + member + ", pricePerKm= "
				+ pricePerKm + "]";
	}
	

	
}