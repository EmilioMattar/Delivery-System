import java.util.Objects;

public class Delivery {
	protected String destination;
	protected String source;
	protected double price;
	protected String code;
	public Delivery(String destination, String source, double price,String code) {
		super();
		this.destination = destination;
		this.source = source;
		this.price = price;
		this.code=code;
	}
	public Delivery(String code)
	{
		this.code=code;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(code, other.code);
	}
	@Override
	public String toString() {
		return "Delivery [destination=" + destination + ", source=" + source + ", price=" + price + ", code=" + code
				+ "]";
	}
	
}
