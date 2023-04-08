import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
public class Service {
	
	/*function to log in/exit from the program*/
	public static boolean logIn()
	{
		Scanner input=new Scanner(System.in);
		String log;
		do {
			System.out.println("1. Log in");
			System.out.println("2. Turn off the program");
			log=input.nextLine();
		}while(!log.equals("1") && !log.equals("2"));
		if(log.equals("2"))
		{
			return true;
		}
		return false;
	}
	/*function for the options in the program*/
	public static int options()
	{
		Scanner input=new Scanner(System.in);
		System.out.println("Press 1 to add a member.");
		System.out.println("Press 2 to delete a member.");
		System.out.println("Press 3 to make a new delivery.");
		System.out.println("Press 4 to get last delivery of a customer.");
		System.out.println("Press 5 to get all deliviries of specific area.");
		System.out.println("Press 6 to get all the customers with all their deliviries.");
		System.out.println("Press 7 to log out.");
		String option=input.next();
		int number;
		if(option.equals("1"))
			number=1;
		else if(option.equals("2"))
			number=2;
		else if(option.equals("3"))
			number=3;
		else if(option.equals("4"))
			number=4;
		else if(option.equals("5"))
			number=5;
		else if(option.equals("6"))
			number=6;
		else if(option.equals("7"))
			number=7;
		else number=8;
		return number;
	}
	/*function to print all the customers of a specific area(North,South,Center)*/
	public static void customersOfArea()
	{
		Scanner input=new Scanner(System.in);
		System.out.print("enter location:");
		String location=input.next();
		Area area=null;
		boolean check=false;
		while(!check) {
			if(location.equals("North") || location.equals("north"))
			{
				area=Area.North;
				check=true;
			}
			
			else if(location.equals("South") || location.equals("south"))
			{
				area=Area.South;
				check=true;
			}
			else if(location.equals("Center") || location.equals("center"))
				{
					area=Area.Center;
					check=true;
				}
			else {
				System.out.println("Location doesn't exist, enter (North,South,Center):");
				location=input.next();
			}
		}
		check=false;
		Map<Integer,Members> del1=DataBase.ourMembersAndDeluveries1;
		Map<Integer,Delivery> del2=DataBase.ourMembersAndDeluveries2;
		for(Map.Entry<Integer,Members> delPointer: del1.entrySet())
		 {
			if(delPointer.getValue().getLocation().equals(String.valueOf(area)))
			{
				System.out.println(del2.get(delPointer.getKey()));
				check=true;
			}
		 }	 
		if(!check)
		{
			System.out.println("No orders to print.");
		}
	}
	/*Function to print all the customers with their delliviries*/
	public static void printAllCustomersAndDeleviries()
	{
		boolean check=false;
		ArrayList<Managers>managers=DataBase.managers;
		for(int i=0;i<managers.size();i++)
		{
			for(Map.Entry<String, Members> members1: managers.get(i).getMembers().entrySet())
			{
				System.out.println(members1.getValue());
				check=true;
			}
		}
		if(!check)
		{
			System.out.println("No orders to print.");
		}
	}
	/*to print options of the deliviries*/
	public static void printDelOptions()
	{
		System.out.println("Please choose your Delivery type(1,2,3):");
		System.out.println("1. Express Delivery.");
		System.out.println("2. Buisness Delivery.");
		System.out.println("3. Regular Delivery.");
	}
	/*to enter the manager to the program*/
	public static Managers isManager()
	{
		boolean login=false,check;
		String username,password;
		Scanner input=new Scanner(System.in);
		ArrayList<Managers>arr= DataBase.managers;
		Iterator<Managers> itr;
		Managers manager=null;
		System.out.println("Hello, to continue enter username and password:");

			check=false;
			while(!check)
			{
				System.out.println("Username:");
				username=input.nextLine();
				arr= DataBase.managers;
				itr=arr.iterator();
				while(itr.hasNext() && !check)
				{
					manager=itr.next();
					if(arr.contains(manager) && manager.getUsername().equals(username))
					{
						check=true;
					}
				}
				if(!check)
					System.out.println("Wrong username.");
			}
			check=false;
			while(!check)
			{
				System.out.println("password:");
				password=input.nextLine();
				if(password.equals(manager.getPassword()))
				{
					check=true;
				}
				else
					System.out.println("Wrong password.");
			}
			System.out.println("Welcome "+ manager.getFirstName()+ " " + manager.getLastName()+ "!");
	
		return manager;
}
	public static void options(Managers manager,ArrayList<Managers> arr)
	{
		boolean check=false;
		int number;
		while(check==false)
		{
			number=Service.options();
			switch(number){
				case 1:
					manager.addMembers(arr);
					break;
				case 2:
					manager.deleteMembers();
					break;
				case 3:
					manager.addDelivery();
					break;
				case 4:
					manager.customerDeliveries();
					break;
				case 5:
					Service.customersOfArea();
					break;
				case 6:
					Service.printAllCustomersAndDeleviries();
					break;
				case 7:
					check=true;
					break;
				default:
					System.out.println("Please type a number between 1 and 4");
					break;
				}
		}
	}
	/*I use this function in order to add a delivery to a specific member.*/
	public static void DeliveryAddToCustomer(Managers manager,String id,Delivery delivery)
	{
		ArrayList<Delivery> deliviries=manager.getMembers().get(id).getDeliveries();
		deliviries.add(delivery);
		manager.getMembers().get(id).setDeliveries(deliviries);
		DataBase.ourMembersAndDeluveries1.put(DataBase.countTree, manager.getMembers().get(id));
		DataBase.ourMembersAndDeluveries2.put(DataBase.countTree, delivery);
		DataBase.countTree+=5;
		DataBase.ourLastDeliveries.put(manager.getMembers().get(id), delivery);
		System.out.println("Shipment has been added to the customer.");
	}
}
