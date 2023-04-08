
public class Person {
	protected String firstName;
	protected String lastName;
	protected String phone;
	protected String id;
	public Person(String firstName, String lastName, String phone, String id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Person [ firstName= " + firstName + " , lastName= " + lastName + " , phone= " + phone + " , id= " + id + "]";
	}

}
