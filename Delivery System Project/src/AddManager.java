import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
public class AddManager extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JTextField textField_3;
	private JTextField textField_4;
	public AddManager(Managers manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add a Manager:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setBounds(6, 0, 145, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Firstname:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(6, 60, 88, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lastname:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(6, 88, 82, 16);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(99, 55, 94, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 83, 93, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Username:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setBounds(6, 116, 88, 16);
		contentPane.add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(99, 112, 94, 26);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Password:");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setBounds(6, 145, 82, 16);
		contentPane.add(lblNewLabel_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(99, 141, 94, 26);
		contentPane.add(passwordField);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Center", "South", "North"}));
		comboBox.setSelectedItem(null);
		comboBox.setBounds(284, 142, 94, 27);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("Area:");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_5.setBounds(226, 145, 46, 16);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			/*Checking if textFields are blank, variable named check, checks if username exists or doesnt exist. if not we can register a manager.*/
			public void actionPerformed(ActionEvent e) {
				boolean check=false;
				if(DataBase.allManagers.containsKey(textField_2.getText())) {
							JOptionPane.showMessageDialog(null,"Username exists.");
							check=true;
					}
				if(DataBase.allSubManagers.containsKey(textField_2.getText())) {
					JOptionPane.showMessageDialog(null,"Username exists.");
					check=true;
				}
				if(!check)
				{
					if(textField.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Not available firstname.");
					}
					else if(textField_1.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Not available Lastname.");
					}
					else if(textField_2.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Not available username.");
					}
					else if(String.valueOf(passwordField.getPassword()).isBlank())
					{
						JOptionPane.showMessageDialog(null,"Not available password.");
					}
					else if(textField_3.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Not available phone.");
					}
					else if(textField_4.getText().isBlank())
					{
						JOptionPane.showMessageDialog(null,"Not available id.");
					}
					else if(comboBox.getSelectedItem().equals(null))
					{
						JOptionPane.showMessageDialog(null,"Not available location.");
					}
					else
					{
						Managers m=new Managers(textField.getText(),textField_1.getText(),textField_3.getText(),textField_4.getText(),textField_2.getText(),String.valueOf(passwordField.getPassword()),	String.valueOf(comboBox.getSelectedItem()));
						DataBase.allManagers.put(textField_2.getText(),m);
						FileWriter fw = null;
						BufferedWriter bw = null;
						PrintWriter pw = null;
						/*I save the managers in fiole named Managers.*/
						try {
							fw=new FileWriter("src/Managers",true);
							bw = new BufferedWriter(fw);
							pw = new PrintWriter(bw);
							pw.println(m.getFirstName()+" "+m.getLastName()+" "+
									m.getPhone()+" "+m.getId()+" "+m.getUsername()+" "+m.getPassword()+" "+m.getLocation());
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
					JOptionPane.showMessageDialog(null,"Successful.");
						setVisible(false);
						new ManagerMenu(manager).setVisible(true);
					}
				}}
					
				});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(199, 189, 88, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("Phone:");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_6.setBounds(226, 88, 61, 16);
		contentPane.add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		textField_3.setBounds(284, 84, 94, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Id:");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_7.setBounds(226, 116, 38, 16);
		contentPane.add(lblNewLabel_7);
		
		textField_4 = new JTextField();
		textField_4.setBounds(284, 112, 94, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Return to the menu-->");
		lblNewLabel_8.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_8.setBounds(22, 389, 171, 16);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManagerMenu(manager).setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBounds(199, 385, 82, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setIcon(new ImageIcon(AddManager.class.getResource("/Images/manager-is-introducing-new-employee-company-meeting-with-business-team_160672-3033 (1).jpeg")));
		lblNewLabel_9.setBounds(0, 0, 558, 427);
		contentPane.add(lblNewLabel_9);
	}
}