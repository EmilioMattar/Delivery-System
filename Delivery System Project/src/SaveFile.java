
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class SaveFile {
	BufferedReader read;
	BufferedWriter write;
	public SaveFile(String st)
	{
		try {
		read=new BufferedReader(new FileReader(st));
		write = new BufferedWriter(new FileWriter(st,true));
		}
		catch(FileNotFoundException ex) {}
		catch(IOException ex) {}
	}

}
