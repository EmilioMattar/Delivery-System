import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
public class cities extends JFrame {

	private JPanel contentPane;
	public cities(Managers manager) {
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
		textArea.setBounds(0, 63, 700, 424);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Cities which sent to them short deliveries within 30 days:");
		lblNewLabel_1.setFont(new Font("Lao Sangam MN", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(6, 22, 412, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(cities.class.getResource("/Images/photo-1567157577867-05ccb1388e66 (1).jpeg")));
		lblNewLabel_2.setBounds(0, 0, 700, 772);
		contentPane.add(lblNewLabel_2);
		/*I made an arraylist to save the values of the cities in it to not have duplicates then i presented them.*/
		ArrayList<String> cities = new ArrayList<String>();
		Date secondDate = new Date();
		for(ShortDistanceDelivery del:DataBase.shortDeleviries) {
			int daysdiff = 0;
			 long res = (secondDate.getTime()-del.getDate().getTime());
			 long diffDays = res / (24 * 60 * 60 * 1000);
			 daysdiff = (int) diffDays;
			 if(daysdiff<31 && daysdiff>0)
			 {
				 if(!cities.contains(del.getDestination())) {
					 cities.add(del.getDestination());
					 textArea.insert(del.getDestination()+", ", 0);
				 }
			 }
		}
	}

}
