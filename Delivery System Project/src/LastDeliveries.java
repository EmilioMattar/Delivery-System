import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Map.Entry;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
public class LastDeliveries extends JFrame {
	private JPanel contentPane;
	public LastDeliveries(Managers manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 900, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.removeAllItems();
		for (Entry<Members, Delivery> entry : DataBase.ourLastDeliveries.entrySet()) {
			comboBox.addItem(String.valueOf(entry.getValue().getCode()));
		}
		comboBox.setBounds(355, 55, 99, 33);
		contentPane.add(comboBox);
		JLabel lblNewLabel = new JLabel("Choose a delivery code of last deliveries:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(6, 55, 337, 33);
		contentPane.add(lblNewLabel);
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 144, 900, 326);
		contentPane.add(textArea);
		JButton btnNewButton = new JButton("Get details:");
		/*I get the details of all the last deliveries of every and each member exists in the program.*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String data = "";
				 Members member=null;
				for (Entry<Members, Delivery> entry : DataBase.ourLastDeliveries.entrySet()) {
            		if(String.valueOf(entry.getValue().getCode()).equals(comboBox.getSelectedItem())) {
            			data =entry.getKey().toString()+"\n"+entry.getValue().toString();
            			member=entry.getKey();
            		}}
				Managers manager=DataBase.managerOfMembers.get(member);
				data=data+"\n"+"Manger: "+  " First name:"+ manager.getFirstName()+ " Last name:"+ manager.getLastName();
				textArea.setText(data);
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.setBounds(302, 115, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Return to the menu-->");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(0, 735, 198, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManagerMenu(manager).setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBounds(190, 737, 92, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(LastDeliveries.class.getResource("/Images/CAMLOGIS-Deliveries-2.png")));
		lblNewLabel_2.setBounds(0, 469, 454, 303);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon(LastDeliveries.class.getResource("/Images/CAMLOGIS-Deliveries-2.png")));
		lblNewLabel_2_1.setBounds(446, 469, 454, 303);
		contentPane.add(lblNewLabel_2_1);
		
		
	}
}
