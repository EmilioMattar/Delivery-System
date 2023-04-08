import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
public class DeleteMember extends JFrame {
	private JPanel contentPane;
	public DeleteMember(Managers manager) throws ShortDeliveryException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Remove Member");
		lblNewLabel.setFont(new Font("Kefa", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(6, 17, 165, 30);
		contentPane.add(lblNewLabel);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(122, 59, 141, 27);
		for (Map.Entry<String,Members> entry : manager.getMembers().entrySet())
		{
			comboBox.addItem(entry.getKey());
		}
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Choose Member");
		lblNewLabel_1.setBounds(16, 59, 108, 23);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Remove");
		/*I check if the member we selected has short deliveries, if yes, i throw an exception. if no, i delete him and delete his deliveries.*/
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Members member=null;
				boolean check=false;
				for (Entry<String, Members> set :manager.getMembers().entrySet()) {
					for(int i=0;i<set.getValue().getDeliveries().size();i++) {
						if(DataBase.shortDeleviries.contains(set.getValue().getDeliveries().get(i)) && !check)
						{
							check=true;
						}
					}
				}
				if(check)
				{
					throw new ShortDeliveryException();
				}
				else {/*When we delete a specific member, all their deliveries get deleted.*/
					for (Entry<String, Members> set :manager.getMembers().entrySet()) {
						for(int i=0;i<set.getValue().getDeliveries().size();i++)
						{
							DataBase.codesOfDels.remove(set.getValue().getDeliveries().get(i).getCode());
							File inputFile = new File("src/Deliveries");
							File tempFile = new File("myTempFile");
							BufferedReader reader = null;
							try {
								reader = new BufferedReader(new FileReader(inputFile));
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							BufferedWriter writer = null;
							try {
								writer = new BufferedWriter(new FileWriter(tempFile));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							Scanner scanner = new Scanner(System.in);
							String currentLine;
							try {
								while((currentLine = reader.readLine()) != null) {
									if(currentLine.contains(set.getValue().getDeliveries().get(i).getCode() ))
										continue;
								    writer.write(currentLine+"\n");
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							try {
								writer.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							tempFile.renameTo(inputFile);
						}
					}
					manager.getMembers().remove(comboBox.getSelectedItem());
					member=DataBase.members.get(comboBox.getSelectedItem());
					DataBase.members.remove(comboBox.getSelectedItem());	
				}
				/*to delete a member from a file*/
				File inputFile = new File("src/members");
				File tempFile = new File("myTempFile");

				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(inputFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(tempFile));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scanner scanner = new Scanner(System.in);
				String currentLine;
				try {
					while((currentLine = reader.readLine()) != null) {
						if(currentLine.contains(member.getId()))
							continue;
					    writer.write(currentLine+"\n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tempFile.renameTo(inputFile);
				JOptionPane.showMessageDialog(null,"Successful.");
				setVisible(false);
				new ManagerMenu(manager).setVisible(true);
					}
				catch(ShortDeliveryException exc3) {
					JOptionPane.showMessageDialog(getContentPane(),exc3.getMessage());
				}
			}
		});
		btnNewButton.setBounds(6, 119, 103, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Return to the menu-->");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(19, 388, 170, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ManagerMenu(manager).setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.setBounds(194, 384, 84, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(DeleteMember.class.getResource("/Images/customer-and-store-owner-waving-goodbye (1).jpeg")));
		lblNewLabel_3.setBounds(0, 0, 558, 427);
		contentPane.add(lblNewLabel_3);
	}
}