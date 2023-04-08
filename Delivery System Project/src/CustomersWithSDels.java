import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
public class CustomersWithSDels extends JFrame {
	private JPanel contentPane;
	public CustomersWithSDels(Managers manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Return to the Menu-->");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(6, 730, 180, 22);
		contentPane.add(lblNewLabel);
		JButton btnNewButton = new JButton("Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManagerMenu(manager).setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(187, 729, 76, 29);
		contentPane.add(btnNewButton);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 80, 700, 504);
		contentPane.add(textArea);
		JLabel lblNewLabel_1 = new JLabel("Customers which ordered short deliveries in the past:");
		lblNewLabel_1.setFont(new Font("Kohinoor Telugu", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(6, 19, 340, 29);
		contentPane.add(lblNewLabel_1);
		ArrayList<String>temp1=new ArrayList<String>();
		/*I take all the customers which has short deliveries and present them.*/
		for(Members m:DataBase.membersOfShortDels) {
			String temp="\nFirst name:"+m.getFirstName()+" Last name:"+m.getLastName()
			+" Phone:"+m.getPhone()+" Id:"+m.getId()+" Location:"+m.getLocation()+" ";
			if(!temp1.contains(temp)) {
				textArea.insert(temp,0);
				temp1.add(temp);
			}
			
			
		}
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(0, 110, 700, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(CustomersWithSDels.class.getResource("/Images/ZAhFASRscCscBUj33239dH (1) (1).jpeg")));
		lblNewLabel_3.setBounds(0, 448, 700, 324);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(CustomersWithSDels.class.getResource("/Images/ZAhFASRscCscBUj33239dH (1) (1).jpeg")));
		lblNewLabel_4.setBounds(0, -11, 700, 90);
		contentPane.add(lblNewLabel_4);
	}

}
