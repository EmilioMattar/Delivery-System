import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
public class AddMember extends JFrame{
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public AddMember(Managers manager) throws MemberExistedException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Add a member:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(6, 33, 125, 16);
		contentPane.add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("Firstname:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(6, 72, 88, 16);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("Lastname:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(6, 100, 82, 16);
		contentPane.add(lblNewLabel_2);
		JLabel lblNewLabel_6 = new JLabel("Phone:");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_6.setBounds(6, 128, 61, 16);
		contentPane.add(lblNewLabel_6);
		JLabel lblNewLabel_7 = new JLabel("Id:");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_7.setBounds(226, 72, 38, 16);
		contentPane.add(lblNewLabel_7);
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(91, 68, 94, 26);
		contentPane.add(textField);
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(91, 96, 94, 26);
		contentPane.add(textField_1);
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(91, 124, 94, 26);
		contentPane.add(textField_2);
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(276, 68, 94, 26);
		contentPane.add(textField_3);
		JLabel lblNewLabel_5 = new JLabel("Area:");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_5.setBounds(226, 101, 46, 16);
		contentPane.add(lblNewLabel_5);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(276, 97, 94, 27);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Center", "South", "North"}));
		comboBox.setSelectedItem(null);
		contentPane.add(comboBox);
		JButton btnNewButton = new JButton("Add member");
		/*we check if textfields are blank,if not we make a member and add them to the database accordingly.*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if(textField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null,"Wrong first Name.");
				}
				else if(textField_1.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null,"Wrong Last Name.");
				}
				else if(textField_2.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null,"Wrong phone.");
				}
				else if(textField_3.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null,"Wrong id.");
				}
				else if(DataBase.members.containsKey(textField_3.getText()))
					throw new MemberExistedException();
				else {
					Members member=new Members(textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),String.valueOf(comboBox.getSelectedItem()));
					if(member.getLocation().equals(manager.getLocation()))
					{
						HashMap<String,Members>temp=manager.getMembers();
						temp.put(member.getId(), member);
						DataBase.members.put(textField_3.getText(),member);
						manager.setMembers(temp);
						DataBase.managerOfMembers.put(member,manager);
						File a2=null;
						PrintWriter a = null;
						/*I save the members in file named members*/
						try {
							a2 =new File("src/members");
							if(a2.length()==0) {
								a = new PrintWriter(new FileWriter("src/members"));
								a.println(member.getId()+ " "+manager.getUsername()+" "+member.getFirstName()+" "+member.getLastName()+" "+member.getPhone()+" "+member.getId()+" "+member.getLocation());
								a.close();
							}
							else {
								FileWriter fw = null;
								BufferedWriter bw = null;
								PrintWriter pw = null;
								try {
									fw=new FileWriter("src/members",true);
									bw = new BufferedWriter(fw);
									pw = new PrintWriter(bw);
									pw.println(member.getId()+ " "+manager.getUsername()+" "+member.getFirstName()+" "+member.getLastName()+" "+member.getPhone()+" "+member.getId()+" "+member.getLocation());
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
					else {
						JOptionPane.showMessageDialog(null,"This manager isn't responsible for this location.");
					}
				}
				}
				catch(MemberExistedException exc1) {
					JOptionPane.showMessageDialog(getContentPane(),exc1.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(214, 146, 117, 29);
		contentPane.add(btnNewButton);
		JLabel lblNewLabel_3 = new JLabel("Return to the menu-->");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(6, 389, 179, 16);
		contentPane.add(lblNewLabel_3);
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManagerMenu(manager).setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBounds(176, 385, 88, 29);
		contentPane.add(btnNewButton_1);
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(AddMember.class.getResource("/Images/customer-and-store-owner-waving-goodbye (1).jpeg")));
		lblNewLabel_4.setBounds(0, 0, 558, 427);
		contentPane.add(lblNewLabel_4);
	}
}