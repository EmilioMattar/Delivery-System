import java.util.ArrayList;
enum Area{South, North, Center;}
public class Main {
	public static void main(String[] args) { 
		System.out.println("                        Welcome to Deliviries For You\n");
		DataBase.Managers();
		boolean login=false;
		login=Service.logIn();
		ArrayList<Managers>arr= DataBase.managers;
		while(!login)
		{
			Managers manager=Service.isManager();
			Service.options(manager,arr);
			login=Service.logIn();
		}
		System.out.println("May we meet again!, Goodbye.");
		System.exit(0);
	}
	}
