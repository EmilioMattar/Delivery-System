import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class deliveriesByCode extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel_4;
	private JTextField textField;
	private JButton btnNewButton_1;
	public deliveriesByCode(Managers manager) {
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
		     lblNewLabel = new JLabel("Show deliveries by instering code");
		     lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel.setBounds(16, 12, 271, 16);
		     contentPane.add(lblNewLabel);
		     
		     lblNewLabel_1 = new JLabel("Return to the menu-->");
		     lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel_1.setBounds(6, 720, 192, 27);
		     contentPane.add(lblNewLabel_1);
		     
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
		     Object[] culomn = {"","","","","","",""};
		        model.setColumnIdentifiers(culomn);
		     lblNewLabel_4 = new JLabel("Insert Member id:");
		     lblNewLabel_4.setBounds(16, 51, 127, 16);
		     contentPane.add(lblNewLabel_4);
		     
		     textField = new JTextField();
		     textField.setBounds(155, 46, 83, 26);
		     contentPane.add(textField);
		     textField.setColumns(10);
		     
		     btnNewButton_1 = new JButton("Get Deliveries");
		     btnNewButton_1.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		model.setRowCount(0);
		     		String line = new String();
					StringTokenizer s;
					try {
						/*I take the file data from ShortDeliveries, take the right data that I want to use then put it in rowDatas as required if the id that we entered in the textField is
						 * right and exists for a member.*/
						SaveFile dels = new SaveFile("src/ShortDeliveries");
						while((line = dels.read.readLine())!=null) {
							s=new StringTokenizer(line);
							String code=s.nextToken();
							String id=s.nextToken();
							String s1=s.nextToken();
							String s2=s.nextToken();
							if(s1.equals(textField.getText())) {
								rowData[0]="destination";
								rowData[1]="date";
								rowData[2]="distance";
								rowData[3]="Price";
								rowData[4]="member id";
								rowData[5]="code";
								rowData[6]="price per km";
								rowData[7]=s.nextToken();
								model.addRow(rowData);
								rowData[0]=s.nextToken();
								rowData[1]=s.nextToken();
								rowData[2]=s2;
								rowData[3]=s.nextToken();
								rowData[4]=s.nextToken();
								rowData[5]=s1;
								rowData[6]=code;
								model.addRow(rowData);
							}
						}
						dels.read.close();
						dels.write.close();
						SaveFile dels2 = new SaveFile("src/Deliveries");
						while((line = dels2.read.readLine())!=null) {
							s=new StringTokenizer(line);
							String code=s.nextToken();
							String id=s.nextToken();
							if(id.equals(textField.getText())) {
							{
								rowData[0]="destination";
								rowData[1]="source";
								rowData[2]="price";
								if(code.equals("express"))
								{
									rowData[3]="postOfficeDelivery";
									rowData[4]="code";
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
									rowData[0]=s.nextToken();
									rowData[1]=s.nextToken();
									rowData[2]=s.nextToken();
									rowData[3]=s.nextToken();
									rowData[4]=s.nextToken();
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
								}
								else if(code.equals("business"))
								{
									rowData[3]="BusinessName";
									rowData[4]="code";
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
									rowData[0]=s.nextToken();
									rowData[1]=s.nextToken();
									rowData[2]=s.nextToken();
									rowData[3]=s.nextToken();
									rowData[4]=s.nextToken();
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
								}
								else if(code.equals("regular"))
								{
									rowData[3]="code";
									rowData[4]="";
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
									rowData[0]=s.nextToken();
									rowData[1]=s.nextToken();
									rowData[2]=s.nextToken();
									rowData[3]=s.nextToken();
									rowData[4]="";
									rowData[5]="";
									rowData[6]="";
									rowData[7]="";
									model.addRow(rowData);
								}
							}
						}}
						dels2.read.close();
						dels2.write.close();
						scrollPane.setViewportView(table);
					    table.setModel(model);
					}
					catch(FileNotFoundException ex) {}
					catch(IOException ex) {}     
		     	}
		     });
		     btnNewButton_1.setBounds(250, 46, 117, 29);
		     contentPane.add(btnNewButton_1);

}}
