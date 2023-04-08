import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
public class SubManAddShortDel extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_2;
	public SubManAddShortDel(SubManager subManager) throws Over30kmException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Sub Manager");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(183, 6, 195, 16);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(175, 98, 124, 27);
		comboBox.removeAllItems();
		if(comboBox.getItemCount()==0)
		{
			for (Map.Entry<String, Members> set : DataBase.members.entrySet()) {
				comboBox.addItem(set.getKey());
			}
		}
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Choose a member:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(6, 101, 157, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Add a short delivery:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(6, 42, 170, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Distance:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setBounds(6, 170, 83, 16);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(88, 160, 48, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Price:");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setBounds(6, 194, 55, 16);
		contentPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(67, 190, 69, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Source:");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_6.setBounds(169, 194, 83, 16);
		contentPane.add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(242, 190, 48, 26);
		contentPane.add(textField_3);
		
		JLabel lblNewLabel_7 = new JLabel("Destination:");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_7.setBounds(148, 164, 94, 16);
		contentPane.add(lblNewLabel_7);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(242, 160, 48, 26);
		contentPane.add(textField_4);
		
		textField_7 = new JTextField();
		textField_7.setBounds(242, 218, 49, 26);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(199, 252, 48, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		/*sub manager can add short deliveries to whom ever exists in the program. Checks if everything is alright(code doesnt exist, blank textfields) then adds the delivery.*/
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date firstDate=null;
				try {
					firstDate = new SimpleDateFormat("dd/MM/yyyy").parse(textField_6.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				 try {
				if(subManager.getDeliveryCount()==subManager.getShortDel().size())
				{
					JOptionPane.showMessageDialog(null,"SubManager cannot add deliveries.");
					setVisible(false);
					new SubManagerMenu(subManager).setVisible(true);

				}
				else if(textField.getText().isBlank())
					JOptionPane.showMessageDialog(null,"Wrong Details");
				else if(Integer.valueOf(textField.getText())>30 || Integer.valueOf(textField.getText())<0 )
				{
					throw new Over30kmException();
				}
				else if(textField_1.getText().isBlank() || Integer.valueOf(textField_1.getText())<=0)
				{
					JOptionPane.showMessageDialog(null,"price should be bigger than 0.");
				}
				else if(textField_4.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null,"Destination should be right value.");
				}
				else if(textField_3.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null,"Source should be right value.");
				}
				else if(textField_7.getText().isBlank() || DataBase.codesOfDels.contains(textField_7.getText())) {
					JOptionPane.showMessageDialog(null,"Code should be right/isn't used value.");
				}
				else if(textField_2.getText().isBlank() || Double.valueOf(textField_2.getText())<0)
				{
					JOptionPane.showMessageDialog(null,"Price per KM should be higher than 0.");
				}
				else
				{
					DataBase.codesOfDels.add(textField_7.getText());
					Members member=null;
					for (Entry<String, Members> set : DataBase.members.entrySet()) {
						if(set.getKey().equals(comboBox.getSelectedItem()))
						{
							member=set.getValue();
						}
						
					}
					
					ShortDistanceDelivery shortdel=new ShortDistanceDelivery(textField_4.getText(),textField_3.getText(),firstDate,
							Integer.valueOf(textField.getText()),Double.valueOf(textField_1.getText()),member,textField_7.getText(),Double.valueOf(textField_2.getText()));
					DataBase.ourLastDeliveries.put(member,shortdel);
					ArrayList<ShortDistanceDelivery> temp=subManager.getShortDel();
					temp.add(shortdel);
					subManager.setShortDel(temp);
					for (Entry<String, Members> set : DataBase.members.entrySet()) {
						if(set.getValue().equals(member))
						{
							ArrayList<Delivery>temp1=member.getDeliveries();
							temp1.add(shortdel);
							member.setDeliveries(temp1);
							DataBase.members.put(set.getKey(),member);
							DataBase.shortDeleviries.add(shortdel);
							if(!DataBase.membersOfShortDels.contains(member))
							{
								DataBase.membersOfShortDels.add(member);
							}
						}
						
					}
					File a2=null;
					PrintWriter a = null;
					try {
						a2 =new File("src/ShortDeliveries");
						if(a2.length()==0) {
							a = new PrintWriter(new FileWriter("src/ShortDeliveries"));
							a.println(textField_7.getText()+" "+subManager.getUsername()+" "+member.getId()+" "+textField_6.getText()+" "+textField_4.getText()+" "+textField_3.getText()+" "+" "+Integer.valueOf(textField.getText())+" "+
									Double.valueOf(textField_1.getText())+" "+Double.valueOf(textField_2.getText()));
							a.close();
						}
						else {
							FileWriter fw = null;
							BufferedWriter bw = null;
							PrintWriter pw = null;
							try {
								fw=new FileWriter("src/ShortDeliveries",true);
								bw = new BufferedWriter(fw);
								pw = new PrintWriter(bw);
								pw.println(textField_7.getText()+" "+subManager.getUsername()+" "+member.getId()+" "+textField_6.getText()+" "+textField_4.getText()+" "+textField_3.getText()+" "+" "+Integer.valueOf(textField.getText())+" "+
										Double.valueOf(textField_1.getText())+" "+Double.valueOf(textField_2.getText()));
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
							a1.println(member.getLocation()+" "+member.getFirstName()+" "+member.getLastName()+" "+member.getPhone()+" "+member.getId()+" "+
									member.getLocation()+" "+"short"+" "+textField_6.getText()+" "+textField_4.getText()+" "+textField_3.getText()+" "+" "+Integer.valueOf(textField.getText())+" "+
							Double.valueOf(textField_1.getText())+" "+textField_7.getText()+" "+Double.valueOf(textField_2.getText()));
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
								pw.println(member.getLocation()+" "+member.getFirstName()+" "+member.getLastName()+" "+member.getPhone()+" "+member.getId()+" "+
										member.getLocation()+" "+"short"+" "+textField_6.getText()+" "+textField_4.getText()+" "+textField_3.getText()+" "+" "+Integer.valueOf(textField.getText())+" "+
										Double.valueOf(textField_1.getText())+" "+textField_7.getText()+" "+Double.valueOf(textField_2.getText()));
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
					
					
					
					try {
						a2 =new File("src/MembersOrderedShortDels");
						if(a2.length()==0) {
							a = new PrintWriter(new FileWriter("src/MembersOrderedShortDels"));
							a.println(member.getFirstName()+" "+member.getLastName()+" "+member.getPhone()+" "+member.getId()+" "+member.getLocation());
							a.close();
						}
						else {
							FileWriter fw = null;
							BufferedWriter bw = null;
							PrintWriter pw = null;
							try {
								fw=new FileWriter("src/MembersOrderedShortDels",true);
								bw = new BufferedWriter(fw);
								pw = new PrintWriter(bw);
								pw.println(member.getFirstName()+" "+member.getLastName()+" "+member.getId()+" "+member.getPhone()+" "+member.getLocation());
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
					new SubManagerMenu(subManager).setVisible(true);
				}
				 }
				 catch(Over30kmException exc2) {
						JOptionPane.showMessageDialog(getContentPane(),exc2.getMessage());
			}
			}
		});
		btnNewButton.setFont(new Font("Kailasa", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(173, 303, 69, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setIcon(new ImageIcon("/Users/ossamamat/Downloads/fast-delivery-icon-free-vector.jpeg"));
		lblNewLabel_8_1.setBounds(358, 318, 200, 109);
		contentPane.add(lblNewLabel_8_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(67, 218, 69, 26);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel_10 = new JLabel("Date:");
		lblNewLabel_10.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_10.setBounds(6, 222, 61, 16);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Code:");
		lblNewLabel_11.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_11.setBounds(169, 219, 61, 22);
		contentPane.add(lblNewLabel_11);
		
		
		JLabel lblNewLabel_13 = new JLabel("Return to the menu-->");
		lblNewLabel_13.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_13.setBounds(22, 393, 189, 16);
		contentPane.add(lblNewLabel_13);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SubManagerMenu(subManager).setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_1.setBounds(223, 388, 94, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("Price Per Km:");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_5.setBounds(69, 256, 122, 16);
		contentPane.add(lblNewLabel_5);
	
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(SubManAddShortDel.class.getResource("/Images/manager-is-introducing-new-employee-company-meeting-with-business-team_160672-3033 (1).jpeg")));
		lblNewLabel_12.setBounds(0, 0, 558, 427);
		contentPane.add(lblNewLabel_12);
	}
}