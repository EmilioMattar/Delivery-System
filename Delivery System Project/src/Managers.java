
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
public class Managers extends Person{
	private String username;
	private String password; 
	private String location;
	private HashMap<String,Members> members;
	public Managers(String firstName, String lastName, String phone, String id, String username, String password,
			String location) {
		super(firstName, lastName, phone, id);
		this.username = username;
		this.password = password;
		this.location = location;
		this.members = new HashMap<String,Members>(0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public HashMap<String, Members> getMembers() {
		return members;
	}

	public void setMembers(HashMap<String, Members> members) {
		this.members = members;
	}
	/*Function to add new members*/
	public void addMembers(ArrayList<Managers>arr) {
		Scanner input=new Scanner(System.in);
		String firstname,lastname,id;
		String phone;
		String location;
		Members member;
		String area = null;
		boolean check=false;
		System.out.print("enter first name: ");
		firstname=input.nextLine();
		System.out.print("enter last name:");
		lastname=input.nextLine();
		System.out.print("enter id:");
		id=input.next();
		for (int counter = 0; counter < arr.size(); counter++) { 	
			 HashMap<String,Members> membs=arr.get(counter).getMembers();
			 for(Map.Entry<String, Members> members2: membs.entrySet())
			 {
				if(members2.getKey().equals(id))
				{
					check=true;
				}
			 }
	      }
		if(check)
		{
			System.out.println("This id is already in the program.");
			return;
		}
		System.out.print("enter phone:");
		phone=input.next();
		System.out.print("enter location:");
		location=input.next();
		while(check==false) {
			if(location.equals("North") || location.equals("north"))
			{
				area="North";
				check=true;
			}
			
			else if(location.equals("South") || location.equals("south"))
			{
				area="South";
				check=true;
			}
			else if(location.equals("Center") || location.equals("center"))
				{
					area="Center";
					check=true;
				}
			else {
				System.out.println("Location doesn't exist, enter (North,South,Center):");
				location=input.next();
			}
		}
		
		if(!(area.equals(this.location)))
		{
			System.out.println("Manager "+this.getFirstName()+" isn't allowed to add members located "+ location);
			return;
		}
		member=new Members(firstname,lastname,phone,id,area);
		this.members.put(id, member);
		System.out.println("Member " + member.getFirstName() + " has been added.");
	}
	
	/*function to delete a member*/
	public void deleteMembers() {
		if(this.members.size()==0)
		{
			System.out.println("There are no members left to delete.");
			return;
		}
		Scanner input=new Scanner(System.in);
		String id;
		System.out.println("What is the id of the member that you want to delete?");
		id=input.next();
		boolean check=false;
		for(Map.Entry<String, Members> memPointer: members.entrySet())
		 {
			if(memPointer.getKey().equals(id))
			{
				check=true;
				this.members.remove(memPointer.getKey());
			}
		 }
		if(!check)
		{
			System.out.println("Member isn't recognized.");
			return;
		}
		System.out.println("Member has been deleted.");
	}
	/*function to add a delivery to a specific customer by identifying him from his id.*/
	public void addDelivery() {
		Scanner input=new Scanner(System.in);
		System.out.println("Enter the id of the member that you want to add a shipment to: ");
		String id=input.next();
		boolean check=false;
		for(Map.Entry<String, Members> memPointer: members.entrySet())
		 {
			if(memPointer.getKey().equals(id))
			{
				check=true;
			}
		 }
		if(!check)
		{
			System.out.println("Member isn't recognized.");
			return;
		}
		int days,num,countTree;
		String destination,source,buisnessName;
		double price;
		boolean postOffice;;
		Service.printDelOptions();
		num=input.nextInt();
		System.out.println("Enter source:");
		source=input.nextLine();
		source=input.nextLine();
		System.out.println("Enter Destination:");
		destination=input.nextLine();
		System.out.println("Enter price:");
		price=input.nextDouble();
		System.out.println("Enter code:");
		String code=input.next();
		Delivery delivery;
		switch(num)
		{
		case 1:
			System.out.println("PostOffice delivery or not?(true/false):");
			postOffice=input.nextBoolean();
			delivery=new ExpressDelivery(destination,source,price,postOffice,code);
			Service.DeliveryAddToCustomer(this,id,delivery);
			break;
		case 2:
			System.out.println("What is the buisness name?");
			buisnessName=input.nextLine();
			buisnessName=input.nextLine();
			System.out.println("Enter code:");
			String code1=input.next();
			delivery=new BuisnessDelivery(destination,source,price,buisnessName,code1);
			Service.DeliveryAddToCustomer(this,id,delivery);
			break;
		case 3:
			System.out.println("Enter code:");
			String code2=input.next();
			delivery=new Delivery(destination,source,price,code2);
			Service.DeliveryAddToCustomer(this,id,delivery);
			break;
		default:
			Service.printDelOptions();
			num=input.nextInt();
			break;
		}
	}
	/*Function to print the last delivery of a specific member*/
	public void customerDeliveries()
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Enter id:");
		String id=input.next();
		boolean check=false;
		for(Map.Entry<String, Members> memPointer: members.entrySet())
		 {
			if(memPointer.getKey().equals(id))
			{
				check=true;
			}
		 }
		if(!check)
		{
			System.out.println("Member isn't recognized.");
			return;
		}
		Map<Members, Delivery> map=DataBase.ourLastDeliveries;
		if(map.get(this.members.get(id))==null)
		{
			System.out.println("This member has no deliviries to print.");
			return;
		}
		System.out.println("Last Delivery: "+ map.get(this.members.get(id)));
	}

	@Override
	public String toString() {
		return super.toString()+" [username= " + username + " , password= " + password + " , location= " + location;
	}

}
