import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.ActionEvent;
public class MainPage extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage(){
		setBackground(Color.WHITE);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(DataBase.allManagers.size()==0) 
		{
			DataBase.managers();
			DataBase.insertAllFiles();
		} 
		JLabel lblNewLabel = new JLabel("Welcome to Deliveries for you");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(136, 0, 296, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter your details to log in:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(6, 120, 230, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_2.setBounds(6, 152, 97, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel_3.setBounds(6, 180, 97, 16);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(106, 152, 130, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(106, 180, 130, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		/*Log in if we enter the right username and password from the database. another option is exit to close the program.*/
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check=false;
				for (Map.Entry<String, Managers> set :DataBase.allManagers.entrySet()) {
					if(set.getValue().getUsername().equals(textField.getText()))
					{
						if(set.getValue().getPassword().equals(String.valueOf(textField_1.getPassword())))
						{
							check=true;
							textField.setText("");
							textField_1.setText("");
							setVisible(false);
							new ManagerMenu(set.getValue()).setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null,"Wrong Password.");
							textField.setText("");
							textField_1.setText("");
							check=true;
						}
					}
				}
				for (Map.Entry<String, SubManager> set :DataBase.allSubManagers.entrySet()) {
					if(set.getValue().getUsername().equals(textField.getText()))
					{
						if(set.getValue().getPassword().equals(String.valueOf(textField_1.getPassword())))
						{
							check=true;
							textField.setText("");
							textField_1.setText("");
							setVisible(false);
							new SubManagerMenu(set.getValue()).setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog(null,"Wrong Password.");
							textField.setText("");
							textField_1.setText("");
							check=true;
						}
					}
				}
				if(!check)
				{
					JOptionPane.showMessageDialog(null,"Wrong Username/password.");
					textField.setText("");
					textField_1.setText("");
				}
				
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnNewButton.setBounds(108, 214, 76, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 18));
		btnNewButton_1.setBounds(284, 389, 76, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Press Exit to close the program:");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_4.setBounds(6, 391, 276, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_5.setIcon(new ImageIcon(MainPage.class.getResource("/Images/CAMLOGIS-Deliveries-2.png")));
		lblNewLabel_5.setBounds(0, 101, 560, 332);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(MainPage.class.getResource("/Images/fast-delivery-icon-free-vector copy.jpeg")));
		lblNewLabel_6.setBounds(0, 0, 184, 108);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1_1 = new JLabel("");
		lblNewLabel_6_1_1.setIcon(new ImageIcon(MainPage.class.getResource("/Images/fast-delivery-icon-free-vector copy.jpeg")));
		lblNewLabel_6_1_1.setBounds(369, 0, 191, 108);
		contentPane.add(lblNewLabel_6_1_1);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(MainPage.class.getResource("/Images/icons8-delivery-30.png")));
		lblNewLabel_7.setBounds(224, 46, 30, 30);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("");
		lblNewLabel_7_1.setIcon(new ImageIcon(MainPage.class.getResource("/Images/icons8-delivery-30.png")));
		lblNewLabel_7_1.setBounds(284, 46, 30, 30);
		contentPane.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("/Users/ossamamat/Desktop/Exercise3-photos/wp5209035.jpeg"));
		lblNewLabel_8.setBackground(Color.WHITE);
		lblNewLabel_8.setBounds(178, 0, 191, 108);
		contentPane.add(lblNewLabel_8);
	}
}
