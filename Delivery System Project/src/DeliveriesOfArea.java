import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
public class DeliveriesOfArea extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	public DeliveriesOfArea(Managers manager) {
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
	        Object[] culomn = {"","","","","","",""};
	        model.setColumnIdentifiers(culomn);
		     lblNewLabel = new JLabel("Show deliveries of manager area");
		     lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel.setBounds(16, 12, 271, 16);
		     contentPane.add(lblNewLabel);
		     lblNewLabel_1 = new JLabel("Return to the menu-->");
		     lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel_1.setBounds(6, 720, 192, 27);
		     contentPane.add(lblNewLabel_1);
		     model.setRowCount(0);
	     		String line = new String();
				StringTokenizer s;
				try {
					SaveFile dels2 = new SaveFile("src/Deliveries");
					while((line = dels2.read.readLine())!=null) {
						s=new StringTokenizer(line);
						String code=s.nextToken();
						String id=s.nextToken();
						String destination=s.nextToken();
						String source=s.nextToken();
						String price=s.nextToken();
						/*I check the first word in each line of Deliveries file to know what kind of delivery I have and I present it accordingly.*/
							if(code.equals("express"))
							{
								String check=s.nextToken();
								String codeOfDel=s.nextToken();
								String location=s.nextToken();
								if(location.equals(manager.getLocation())) {
									rowData[0]="destination";
									rowData[1]="source";
									rowData[2]="price";
									rowData[3]="postOfficeDelivery";
									rowData[4]="code";
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
									rowData[0]=destination;
									rowData[1]=source;
									rowData[2]=price;
									rowData[3]=check;
									rowData[4]=codeOfDel;
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
							}}
							else if(code.equals("business"))
							{
								String BusinessName=s.nextToken();
								String codeOfDel=s.nextToken();
								String location=s.nextToken();
								if(location.equals(manager.getLocation())) {
									rowData[0]="destination";
									rowData[1]="source";
									rowData[2]="price";
									rowData[3]="BusinessName";
									rowData[4]="code";
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
									rowData[0]=destination;
									rowData[1]=source;
									rowData[2]=price;
									rowData[3]=BusinessName;
									rowData[4]=codeOfDel;
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
							}}
							else if(code.equals("regular"))
							{
								String codeOfDel=s.nextToken();
								String location=s.nextToken();
								if(location.equals(manager.getLocation())) {
								rowData[0]="destination";
								rowData[1]="source";
								rowData[2]="price";
								rowData[3]="code";
								rowData[4]="";
								rowData[5]="";
								rowData[6]="";
								rowData[7]="";
								model.addRow(rowData);
								rowData[0]=destination;
								rowData[1]=source;
								rowData[2]=price;
								rowData[3]=codeOfDel;
								rowData[4]="";
								rowData[5]="";
								rowData[6]="";
								rowData[7]="";
								model.addRow(rowData);
							}}
						}
					dels2.read.close();
					dels2.write.close();
					
					}
				catch(FileNotFoundException ex) {}
				catch(IOException ex) {}  
			scrollPane.setViewportView(table);
			table.setModel(model);
		     btnNewButton = new JButton("Menu");
		     btnNewButton.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		setVisible(false);
		     		new ManagerMenu(manager).setVisible(true);
		     	}
		     });
		     btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		     btnNewButton.setBounds(197, 721, 83, 29);
		     contentPane.add(btnNewButton);
}}
