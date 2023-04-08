import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;

public class SubDeliveriesByDate extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel_4;
	private JTextField textField;
	private JButton btnNewButton_1;
	public SubDeliveriesByDate(SubManager manager) {
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
	        Object[] culomn = {"destination","Source","date","distance","Price","member id","code","price per km"};
	        model.setColumnIdentifiers(culomn);
	        scrollPane.setViewportView(table);
	        table.setModel(model);
		     lblNewLabel = new JLabel("Show Short deliveries by instering date");
		     lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel.setBounds(16, 12, 321, 16);
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
		     btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		     btnNewButton.setBounds(197, 721, 83, 29);
		     contentPane.add(btnNewButton);
		     
		     lblNewLabel_4 = new JLabel("Insert date:");
		     lblNewLabel_4.setBounds(16, 51, 88, 16);
		     contentPane.add(lblNewLabel_4);
		     
		     textField = new JTextField();
		     textField.setBounds(105, 46, 130, 26);
		     contentPane.add(textField);
		     textField.setColumns(10);
		     
		     btnNewButton_1 = new JButton("Get Deliveries");
		     /*I check if the date entered in the textfield is the same date that exists in each line of the file ShortDeliveries(for all the deliveries) to know which delivery to present.*/
		     btnNewButton_1.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		model.setRowCount(0);
		     		String line = new String();
					StringTokenizer s;
					try {
						SaveFile dels = new SaveFile("src/ShortDeliveries");
						while((line = dels.read.readLine())!=null) {
							s=new StringTokenizer(line);
							String code=s.nextToken();
							String man=s.nextToken();
							String s1=s.nextToken();
							String s2=s.nextToken();
							if(s2.equals(textField.getText())) {
							rowData[0]=s.nextToken();
							rowData[1]=s.nextToken();
							rowData[2]=s2;
							rowData[3]=s.nextToken();
							rowData[4]=s.nextToken();
							rowData[5]=s1;
							rowData[6]=code;
							rowData[7]=s.nextToken();
							model.addRow(rowData);
							}
						}
						dels.read.close();
						dels.write.close();
						scrollPane.setViewportView(table);
					    table.setModel(model);
					}
					catch(FileNotFoundException ex) {}
					catch(IOException ex) {}     
		     	}
		     });
		     btnNewButton_1.setBounds(247, 46, 117, 29);
		     contentPane.add(btnNewButton_1);
}}
