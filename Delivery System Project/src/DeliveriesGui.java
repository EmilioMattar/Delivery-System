import java.util.ArrayList;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
public class DeliveriesGui extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public DeliveriesGui(Managers manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_6 = new JLabel("Post office Delivery:");
		lblNewLabel_6.setBounds(206, 114, 126, 16);
		contentPane.add(lblNewLabel_6);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"true", "false"}));
		comboBox_1.setBounds(344, 110, 79, 27);
		 
		contentPane.add(comboBox_1);
		JLabel lblNewLabel = new JLabel("Add Delivery:");
		lblNewLabel.setBounds(6, 17, 89, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_5 = new JLabel("Business Name:");
		lblNewLabel_5.setBounds(6, 211, 99, 16);
		contentPane.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setBounds(109, 206, 89, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		lblNewLabel_6.setVisible(false);
		comboBox_1.setVisible(false);
		lblNewLabel_5.setVisible(false);
		textField_3.setVisible(false);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Express Delivery"))
				{
					lblNewLabel_6.setVisible(true);
					comboBox_1.setVisible(true);
					lblNewLabel_5.setVisible(false);
					textField_3.setVisible(false);
				}
				else if(comboBox.getSelectedItem().equals("Business Delivery"))
				{
					lblNewLabel_6.setVisible(false);
					comboBox_1.setVisible(false);
					lblNewLabel_5.setVisible(true);
					textField_3.setVisible(true);
				}
				else {
					lblNewLabel_6.setVisible(false);
					comboBox_1.setVisible(false);
					lblNewLabel_5.setVisible(false);
					textField_3.setVisible(false);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Regular Delivery", "Express Delivery", "Business Delivery"}));
		comboBox.setBounds(114, 70, 159, 27);
		contentPane.add(comboBox);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(115, 42, 119, 27);
		comboBox_2.removeAllItems();
		for (Map.Entry<String, Members> set : DataBase.members.entrySet()) {
			 if(set.getValue().getLocation().equals(manager.getLocation()))
			 {
				 comboBox_2.addItem(set.getKey());
			 }
		 }
		contentPane.add(comboBox_2);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Delivery:");
		lblNewLabel_1.setBounds(6, 74, 114, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Destination:");
		lblNewLabel_2.setBounds(6, 114, 89, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Source:");
		lblNewLabel_3.setBounds(6, 145, 53, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setBounds(6, 173, 44, 16);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(88, 109, 79, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(88, 140, 79, 26);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(88, 168, 79, 26);
		contentPane.add(textField_2);
		JLabel lblNewLabel_7 = new JLabel("Member id:");
		lblNewLabel_7.setBounds(6, 46, 79, 16);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("Submit");
		/*i check if the code of the delivery entered exists in the program, check if any textfield is blank. if not i add the delivery to the database,member...*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Express Delivery"))
				{
					if(textField.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Wrong Destination.");	
					}
					else if(textField_1.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Wrong source.");	
					}
					else if(textField_2.getText().isBlank() || Double.valueOf(textField_2.getText())<0)
					{
						JOptionPane.showMessageDialog(null,"Wrong price.");	
					}
					else if(textField_4.getText().isBlank() || DataBase.codesOfDels.contains(textField_4.getText()))
					{
						JOptionPane.showMessageDialog(null,"Code should be right/isn't used value.");	
					}
					else
					{
						DataBase.codesOfDels.add(textField_4.getText());
						boolean check;
						if(comboBox_1.getSelectedItem().equals("false"))
							check=false;
						else
							check=true;
						ExpressDelivery del=new ExpressDelivery(textField.getText(),textField_1.getText(),Double.valueOf(textField_2.getText()),check,textField_4.getText());
						for (Map.Entry<String, Members> set :DataBase.members.entrySet()) {
							if(set.getValue().getId().equals(comboBox_2.getSelectedItem()))
							{
								ArrayList<Delivery>temp=new ArrayList<Delivery>();
								temp.add(del);
								set.getValue().setDeliveries(temp);
								DataBase.ourLastDeliveries.put(set.getValue(),del);
								File a2=null;
								PrintWriter a = null;
								try {
									/*I fill the Deliveries files with all the deliveries and I recognize them by the first word in it as: "express","business","regular" or short*/
									a2 =new File("src/Deliveries");
									if(a2.length()==0) {
										a = new PrintWriter(new FileWriter("src/Deliveries"));
										a.println("express"+" "+set.getValue().getId()+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+check+" "+textField_4.getText()+" "+manager.getLocation());
										a.close();
									}
									else {
										FileWriter fw = null;
										BufferedWriter bw = null;
										PrintWriter pw = null;
										try {
											fw=new FileWriter("src/Deliveries",true);
											bw = new BufferedWriter(fw);
											pw = new PrintWriter(bw);
											pw.println("express"+" "+set.getValue().getId()+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+check+" "+textField_4.getText()+" "+manager.getLocation());
											pw.flush();
											}
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									try {
										fw.close();
										pw.close();
										bw.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
									}
								 catch (IOException e4) {
									// TODO Auto-generated catch block
									e4.printStackTrace();
								}
								File a3=null;
								PrintWriter a1 = null;
								try {
									a3 =new File("src/AllDeliveries");
									if(a3.length()==0) {
										a1 = new PrintWriter(new FileWriter("src/AllDeliveries"));
										a1.println(manager.getLocation()+" "+set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+set.getValue().getPhone()+" "+set.getValue().getId()+" "+
										set.getValue().getLocation()+" "+"express"+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+check+" "+textField_4.getText()+" "+manager.getLocation());
										a1.close();
									}
									else {
										FileWriter fw = null;
										BufferedWriter bw = null;
										PrintWriter pw = null;
										try {
											fw=new FileWriter("src/AllDeliveries",true);
											bw = new BufferedWriter(fw);
											pw = new PrintWriter(bw);
											pw.println(manager.getLocation()+" "+set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+set.getValue().getPhone()+" "+set.getValue().getId()+" "+
													set.getValue().getLocation()+" "+"express"+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+check+" "+textField_4.getText()+" "+manager.getLocation());
											pw.flush();
											}
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									try {
										fw.close();
										pw.close();
										bw.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
									}
								 catch (IOException e4) {
									// TODO Auto-generated catch block
									e4.printStackTrace();
								}
								JOptionPane.showMessageDialog(null,"Successful.");
								setVisible(false);
								new ManagerMenu(manager).setVisible(true);
							}
						}
					}
				}
				else if(comboBox.getSelectedItem().equals("Business Delivery"))
				{
					if(textField.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Wrong Destination.");	
					}
					else if(textField_1.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Wrong source.");	
					}
					else if(textField_2.getText().isBlank() || Double.valueOf(textField_2.getText())<0)
					{
						JOptionPane.showMessageDialog(null,"Wrong price.");	
					}
					else if(textField_3.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Wrong Business name.");
					}
					else if(textField_4.getText().isBlank() || DataBase.codesOfDels.contains(textField_4.getText()))
					{
						JOptionPane.showMessageDialog(null,"Code should be right/isn't used value.");
					}
					else
					{
						DataBase.codesOfDels.add(textField_4.getText());
						BuisnessDelivery del=new BuisnessDelivery(textField.getText(),textField_1.getText(),Double.valueOf(textField_2.getText()),textField_3.getText(),textField_4.getText());
						for (Map.Entry<String, Members> set :DataBase.members.entrySet()) {
							if(set.getValue().getId().equals(comboBox_2.getSelectedItem()))
							{
								ArrayList<Delivery>temp=new ArrayList<Delivery>();
								temp.add(del);
								set.getValue().setDeliveries(temp);
								DataBase.ourLastDeliveries.put(set.getValue(),del);
								File a2=null;
								PrintWriter a = null;
								try {
									a2 =new File("src/Deliveries");
									if(a2.length()==0) {
										a = new PrintWriter(new FileWriter("src/Deliveries"));
										a.println("business"+" "+set.getValue().getId()+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_3.getText()+" "+textField_4.getText()+" "+manager.getLocation());
										a.close();
									}
									else {
										FileWriter fw = null;
										BufferedWriter bw = null;
										PrintWriter pw = null;
										try {
											fw=new FileWriter("src/Deliveries",true);
											bw = new BufferedWriter(fw);
											pw = new PrintWriter(bw);
											pw.println("business"+" "+set.getValue().getId()+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_3.getText()+" "+textField_4.getText()+" "+manager.getLocation());
											pw.flush();
											}
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									try {
										fw.close();
										pw.close();
										bw.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
									}
								 catch (IOException e4) {
									// TODO Auto-generated catch block
									e4.printStackTrace();
								}
								File a3=null;
								PrintWriter a1 = null;
								try {
									a3 =new File("src/AllDeliveries");
									if(a3.length()==0) {
										a1 = new PrintWriter(new FileWriter("src/AllDeliveries"));
										a1.println(manager.getLocation()+" "+set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+set.getValue().getPhone()+" "+set.getValue().getId()+" "+
										set.getValue().getLocation()+" "+"business"+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_3.getText()+" "+textField_4.getText()+" "+manager.getLocation());
										a1.close();
									}
									else {
										FileWriter fw = null;
										BufferedWriter bw = null;
										PrintWriter pw = null;
										try {
											fw=new FileWriter("src/AllDeliveries",true);
											bw = new BufferedWriter(fw);
											pw = new PrintWriter(bw);
											pw.println(manager.getLocation()+" "+set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+set.getValue().getPhone()+" "+set.getValue().getId()+" "+
													set.getValue().getLocation()+" "+"business"+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_3.getText()+" "+textField_4.getText()+" "+manager.getLocation());
											pw.flush();
											}
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									try {
										fw.close();
										pw.close();
										bw.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
									}
								 catch (IOException e4) {
									// TODO Auto-generated catch block
									e4.printStackTrace();
								}
								setVisible(false);
								new ManagerMenu(manager).setVisible(true);
							}
						}
						
					}
				}
				else {
					if(textField.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Wrong Destination.");	
					}
					else if(textField_1.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Wrong source.");	
					}
					else if(textField_2.getText().isBlank() || Double.valueOf(textField_2.getText())<0)
					{
						JOptionPane.showMessageDialog(null,"Wrong price.");	
					}
					else if(textField_4.getText().isBlank() || DataBase.codesOfDels.contains(textField_4.getText()))
					{
						JOptionPane.showMessageDialog(null,"Code should be right/isn't used value.");
					}
					else
					{
						DataBase.codesOfDels.add(textField_4.getText());
						Delivery del=new Delivery(textField.getText(),textField_1.getText(),Double.valueOf(textField_2.getText()),textField_4.getText());
						for (Map.Entry<String, Members> set :DataBase.members.entrySet()) {
							if(set.getValue().getId().equals(comboBox_2.getSelectedItem()))
							{
								ArrayList<Delivery>temp=new ArrayList<Delivery>();
								temp.add(del);
								set.getValue().setDeliveries(temp);
								DataBase.ourLastDeliveries.put(set.getValue(),del);
								File a2=null;
								PrintWriter a = null;
								try {
									a2 =new File("src/Deliveries");
									if(a2.length()==0) {
										a = new PrintWriter(new FileWriter("src/Deliveries"));
										a.println("regular"+" "+set.getValue().getId()+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_4.getText()+" "+manager.getLocation());
										a.close();
									}
									else {
										FileWriter fw = null;
										BufferedWriter bw = null;
										PrintWriter pw = null;
										try {
											fw=new FileWriter("src/Deliveries",true);
											bw = new BufferedWriter(fw);
											pw = new PrintWriter(bw);
											pw.println("regular"+" "+set.getValue().getId()+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_4.getText()+" "+manager.getLocation());
											pw.flush();
											}
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									try {
										fw.close();
										pw.close();
										bw.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
									}
								 catch (IOException e4) {
									// TODO Auto-generated catch block
									e4.printStackTrace();
								}
								File a3=null;
								PrintWriter a1 = null;
								try {
									a3 =new File("src/AllDeliveries");
									if(a3.length()==0) {
										a1 = new PrintWriter(new FileWriter("src/AllDeliveries"));
										a1.println(manager.getLocation()+" "+set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+set.getValue().getPhone()+" "+set.getValue().getId()+" "+
										set.getValue().getLocation()+" "+"regular"+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_4.getText()+" "+manager.getLocation());
										a1.close();
									}
									else {
										FileWriter fw = null;
										BufferedWriter bw = null;
										PrintWriter pw = null;
										try {
											fw=new FileWriter("src/AllDeliveries",true);
											bw = new BufferedWriter(fw);
											pw = new PrintWriter(bw);
											pw.println(manager.getLocation()+" "+set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+set.getValue().getPhone()+" "+set.getValue().getId()+" "+
													set.getValue().getLocation()+" "+"regular"+" "+textField.getText()+" "+textField_1.getText()+" "+Double.valueOf(textField_2.getText())+" "+textField_4.getText()+" "+manager.getLocation());
											pw.flush();
											}
										 catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									try {
										fw.close();
										pw.close();
										bw.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									}
									}
								 catch (IOException e4) {
									// TODO Auto-generated catch block
									e4.printStackTrace();
								}
								setVisible(false);
								new ManagerMenu(manager).setVisible(true);
							}
						}
					}
				}
			}
		});
		btnNewButton.setBounds(263, 206, 71, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_8 = new JLabel("Code:");
		lblNewLabel_8.setBounds(206, 157, 61, 16);
		contentPane.add(lblNewLabel_8);
		
		textField_4 = new JTextField();
		textField_4.setBounds(270, 152, 53, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Return to the Menu-->");
		lblNewLabel_9.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_9.setBounds(6, 388, 172, 30);
		contentPane.add(lblNewLabel_9);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManagerMenu(manager).setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBounds(181, 391, 89, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(DeliveriesGui.class.getResource("/Images/delivery_faq.jpeg")));
		lblNewLabel_10.setBounds(0, 0, 558, 424);
		contentPane.add(lblNewLabel_10);
		
	}
}
