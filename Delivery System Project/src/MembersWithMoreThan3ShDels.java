import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
public class MembersWithMoreThan3ShDels extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	public MembersWithMoreThan3ShDels(SubManager manager) {
		getContentPane().setLayout(null);
		table = new JTable();
		table.setBounds(200, 202, 200, -50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 900, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 79, 870, 509);
		contentPane.add(scrollPane);
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
	        Object rowData[] = new Object[8];
	        scrollPane.setViewportView(table);
	        table.setModel(model);
		     lblNewLabel = new JLabel("Members who has 3 short deliveries or more");
		     lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel.setBounds(16, 12, 360, 16);
		     contentPane.add(lblNewLabel);
		     
		     lblNewLabel_1 = new JLabel("Return to the menu-->");
		     lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel_1.setBounds(6, 720, 192, 27);
		     contentPane.add(lblNewLabel_1);
		     
		     btnNewButton = new JButton("Menu");
		     btnNewButton.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		setVisible(false);
		     		new SubManagerMenu(manager).setVisible(true);
		     	}
		     });
		     Object[] culomn = {"First Name","Last Name","Phone","Id","Location"};
		        model.setColumnIdentifiers(culomn);
		     String tmp=null;
		     String line = new String();
		     String temp = new String();
		     String line1 = new String();
		     String line2 = new String();
		     String line3 = new String();
			StringTokenizer s,s1,s2,s3,st;
			try {
				SaveFile dels = new SaveFile("src/MembersWithTheirShDelivries");
				while((line = dels.read.readLine())!=null) {
					st=new StringTokenizer(line);
					String[] check2=new String[5];;
					check2[0]=st.nextToken();
					check2[1]=st.nextToken();
					check2[2]=st.nextToken();
					String id=st.nextToken();
					tmp=st.nextToken();
					check2[3]=id;
					check2[4]=tmp;
					if(tmp.equals("South") || tmp.equals("North") || tmp.equals("Center")) {
					SaveFile dels2 = new SaveFile("src/MembersWithTheirShDelivries");
					while((temp = dels2.read.readLine())!=null) {
						String[] check=new String[5];
						s=new StringTokenizer(temp);
						check[0]=s.nextToken();
						check[1]=s.nextToken();
						check[2]=s.nextToken();
						check[3]=s.nextToken();
						check[4]=s.nextToken();
						if(Arrays.equals(check, check2)) {
						line1=dels2.read.readLine();
						line2=dels2.read.readLine();
						line3=dels2.read.readLine();
						if(line1!=null && line2!=null && line3!=null) {
							s1=new StringTokenizer(line1);
							s2=new StringTokenizer(line2);
							s3=new StringTokenizer(line3);
							String temp55=s2.nextToken();
							if(temp55.equals(s1.nextToken())&&temp55.equals(s3.nextToken())) {
								rowData[0]=check[0];
								rowData[1]=check[1];
								rowData[2]=check[2];
								rowData[3]=check[3];
								rowData[4]=check[4];
								model.addRow(rowData);
						}
					}}
				
				}
				dels2.read.close();
				dels2.write.close();
				}
				}
				dels.read.close();
				dels.write.close();
				
			}
			
			catch(FileNotFoundException ex) {}
			catch(IOException ex) {}  
			scrollPane.setViewportView(table);
		    table.setModel(model);
		     btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		     btnNewButton.setBounds(197, 721, 83, 29);
		     contentPane.add(btnNewButton);

}}
