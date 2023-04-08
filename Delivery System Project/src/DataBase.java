import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
public class DataBase {
	public static int countTree=100;
	public static ArrayList<Managers> managers = new ArrayList<Managers>();	
	public static HashMap<String,Managers> allManagers = new HashMap<String,Managers>();/**/	
	public static HashMap<String,SubManager> allSubManagers = new HashMap<String,SubManager>();	/**/	
	public static HashMap<String, Members> members=new HashMap<String, Members>(); /**/	
	public static Map<Members,Delivery> ourLastDeliveries=new TreeMap<Members,Delivery>(); /**/
	public static Map<Integer,Members> ourMembersAndDeluveries1=new TreeMap<Integer, Members >();
	public static Map<Integer,Delivery> ourMembersAndDeluveries2=new TreeMap<Integer, Delivery >();
	public static ArrayList<ShortDistanceDelivery> shortDeleviries = new ArrayList<ShortDistanceDelivery>(); /**/	
	public static ArrayList<Members> membersOfShortDels=new ArrayList<Members>(); /**/
	public static ArrayList<String> codesOfDels = new ArrayList<String>();  /**/
	public static HashMap<Members,Managers> managerOfMembers=new HashMap<Members,Managers>(); /**/
	public static void managers()
	{
		DataBase.allManagers.put("emilio",new Managers("emilio","Hosein","50309856","208348734","emilio","123","South"));
		DataBase.allManagers.put("samer",new Managers("samer","Hosein","5349843","208238734","samer","456","North"));
		DataBase.allManagers.put("kamil",new Managers("kamil","Hosein","2342333","398723833","kamil","789","Center"));
			File a2=null;
			PrintWriter a = null;
			try {
				a2 =new File("src/Managers");
				if(a2.length()==0) {
					a = new PrintWriter(new File("src/Managers"));
					for (Entry<String, Managers> set :
						allManagers.entrySet()) {
						a.print(set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+
						set.getValue().getPhone()+" "+set.getValue().getId()+" "+set.getValue().getUsername()+" "+set.getValue().getPassword()+" "+set.getValue().getLocation()+"\n");
				}
					a.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	public static void Managers()
	{
		managers.add(new Managers("emilio","Hosein","50309856","208348734","emilio","123","South"));
		managers.add(new Managers("samer","Hosein","5349843","208238734","samer","456","North"));
		managers.add(new Managers("kamil","Hosein","2342333","398723833","kamil","789","Center"));
	}
	/*Function to insert all data from the files to the database.*/
	public static void insertAllFiles() {
		String line = new String();
		StringTokenizer s;
		try {
			SaveFile Managers = new SaveFile("src/Managers");
			while((line = Managers.read.readLine())!=null) {
				s=new StringTokenizer(line);
				String s1=s.nextToken();
				Managers man= new Managers(s1,s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken());
				allManagers.put(s1, man);
			}
			Managers.read.close();
			Managers.write.close();
			
			SaveFile Members = new SaveFile("src/members");
			while((line = Members.read.readLine())!=null) {
				s=new StringTokenizer(line);
				String s1=s.nextToken();
				String man=s.nextToken();
				Members member=new Members(s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken());
				members.put(s1,member);
				for (Entry<String, Managers> entry : allManagers.entrySet()) {
					if(entry.getValue().getUsername().equals(man)) {
						entry.getValue().getMembers().put(s1, member);
						managerOfMembers.put(member, entry.getValue());
					}
				}
			}
			Members.read.close();
			Members.write.close();
			SaveFile subManagers = new SaveFile("src/SubManagers");
			while((line = subManagers.read.readLine())!=null) {
				s=new StringTokenizer(line);
				String s1=s.nextToken();
				allSubManagers.put(s1,new SubManager(s1,s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),Integer.valueOf(s.nextToken())));
			}
			subManagers.read.close();
			subManagers.write.close();
			
			SaveFile ShortDelivery = new SaveFile("src/ShortDeliveries");
			while((line = ShortDelivery.read.readLine())!=null) {
				s=new StringTokenizer(line);
				String code=s.nextToken();
				String man=s.nextToken();
				String s1=s.nextToken();
				Members member=members.get(s1);
				String s2=s.nextToken();
				 Date firstDate = null;
				try {
					firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(s2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ShortDistanceDelivery del=new ShortDistanceDelivery(s.nextToken(),s.nextToken(),firstDate,Integer.valueOf(s.nextToken())
						,Double.valueOf(s.nextToken()),member,code,Double.valueOf(s.nextToken()));
				shortDeleviries.add(del);
				member.getDeliveries().add(del);
				for (Entry<String, SubManager> entry : allSubManagers.entrySet()) {
					if(entry.getKey().equals(man)) {
						allSubManagers.get(man).getShortDel().add(del);
					}
				}
				codesOfDels.add(del.getCode());
			}
			subManagers.read.close();
			subManagers.write.close();
			
			SaveFile Delivery = new SaveFile("src/Deliveries");
			while((line = Delivery.read.readLine())!=null) {
				s=new StringTokenizer(line);
				String type=s.nextToken();
				String id=s.nextToken();
				if(type.equals("express"))
				{
					ExpressDelivery del= new ExpressDelivery(s.nextToken(),s.nextToken(),Double.valueOf(s.nextToken()),Boolean.valueOf(s.nextToken()),s.nextToken());
					members.get(id).getDeliveries().add(del);
					codesOfDels.add(del.getCode());
				}
				else if(type.equals("business")) {
					BuisnessDelivery del = new BuisnessDelivery(s.nextToken(),s.nextToken(),Double.valueOf(s.nextToken()),s.nextToken(),s.nextToken());
					members.get(id).getDeliveries().add(del);
					codesOfDels.add(del.getCode());
				}
				else if(type.equals("regular")) {
					Delivery del = new Delivery(s.nextToken(),s.nextToken(),Double.valueOf(s.nextToken()),s.nextToken());
					members.get(id).getDeliveries().add(del);
					codesOfDels.add(del.getCode());
				}
			}
			Delivery.read.close();
			Delivery.write.close();
			
			SaveFile Deliveries = new SaveFile("src/AllDeliveries");
			while((line = Deliveries.read.readLine())!=null) {
				s=new StringTokenizer(line);
				String location=s.nextToken();
				Members member=new Members(s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken());
				String type=s.nextToken();
				if(type.equals("express"))
				{
					ExpressDelivery del= new ExpressDelivery(s.nextToken(),s.nextToken(),Double.valueOf(s.nextToken()),Boolean.valueOf(s.nextToken()),s.nextToken());
					ourLastDeliveries.put(member, del);
					for (Entry<String, Managers> entry : allManagers.entrySet()) {
							if(entry.getValue().getLocation().equals(location)) {
								managerOfMembers.put(member, entry.getValue());
						}
					}
				}
				else if(type.equals("business")) {
					BuisnessDelivery del = new BuisnessDelivery(s.nextToken(),s.nextToken(),Double.valueOf(s.nextToken()),s.nextToken(),s.nextToken());
					ourLastDeliveries.put(member, del);
					for (Entry<String, Managers> entry : allManagers.entrySet()) {
						if(entry.getValue().getLocation().equals(location)) {
							managerOfMembers.put(member, entry.getValue());
					}
				}
				}
				else if(type.equals("regular")) {
					Delivery del = new Delivery(s.nextToken(),s.nextToken(),Double.valueOf(s.nextToken()),s.nextToken());
					ourLastDeliveries.put(member, del);
					for (Entry<String, Managers> entry : allManagers.entrySet()) {
						if(entry.getValue().getLocation().equals(location)) {
							managerOfMembers.put(member, entry.getValue());
					}
				}
				}
				else if(type.equals("short")) {
					Date firstDate=null;
					String date=s.nextToken();
					try {
						firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					String destination=s.nextToken();
					String source=s.nextToken();
					Integer distance=Integer.valueOf(s.nextToken());
					Double price=Double.valueOf(s.nextToken());
					String code=s.nextToken();
					Double priceperKm=Double.valueOf(s.nextToken());
					ShortDistanceDelivery del=new ShortDistanceDelivery(destination,source,firstDate,distance,price,member,code,priceperKm);
					ourLastDeliveries.put(member, del);
					for (Entry<String, Managers> entry : allManagers.entrySet()) {
						if(entry.getValue().getLocation().equals(location)) {
							managerOfMembers.put(member, entry.getValue());
					}
				}
				}
			}
			SaveFile membersOfDels = new SaveFile("src/MembersOrderedShortDels");
			while((line = membersOfDels.read.readLine())!=null) {
				s=new StringTokenizer(line);
				Members member=new Members(s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken(),s.nextToken());
				if(!membersOfShortDels.contains(member))
					membersOfShortDels.add(member);
			}
		}
		catch(FileNotFoundException ex) {}
		catch(IOException ex) {}
}}