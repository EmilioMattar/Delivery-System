import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
public class SubManagerMenu extends JFrame {
	private JPanel contentPane;
	public SubManagerMenu(SubManager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SubManagerMenu.class.getResource("/Images/shutterstock_793046887-min-scaled (1).jpg")));
		lblNewLabel.setBounds(0, 17, 558, 410);
		contentPane.add(lblNewLabel);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBounds(0, 0, 558, 22);
		contentPane.add(menuBar);
		JMenu mnNewMenu = new JMenu("Members");
		menuBar.add(mnNewMenu);
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Short Delivery");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(DataBase.members.size()!=0)
				{
					setVisible(false);
					try {
						new SubManAddShortDel(manager).setVisible(true);
					} catch (Over30kmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"No members exist for this manager to add short delivery to.");
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Delete Short Delivery");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DataBase.shortDeleviries.size()==0)
				{
					JOptionPane.showMessageDialog(null,"No Short Deliveries exist.");
				}
				else {
					setVisible(false);
					new SubRemoveShortDel(manager).setVisible(true);
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		JMenu mnNewMenu_6 = new JMenu("Information retrieval");
		menuBar.add(mnNewMenu_6);
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Short Deliveries by date");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SubDeliveriesByDate(manager).setVisible(true);	
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_8);
		JMenu mnNewMenu_2 = new JMenu("Log out");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Log out");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainPage().setVisible(true);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Click to save members w/dels");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintWriter a = null;
				try {
					a = new PrintWriter(new FileWriter("src/MembersWithTheirShDelivries"));
					for (Entry<String, Members> set :
			             DataBase.members.entrySet()) {
							
							a.println(set.getValue().getFirstName()+" "+set.getValue().getLastName()+" "+set.getValue().getPhone()+" "+set.getValue().getId()+" "+set.getValue().getLocation());
							
							for(int i=0;i<set.getValue().getDeliveries().size();i++)
							{
								if(set.getValue().getDeliveries().get(i) instanceof ShortDistanceDelivery) {
									ShortDistanceDelivery del=(ShortDistanceDelivery) set.getValue().getDeliveries().get(i);
									a.println(set.getValue().getId()+" "+del.getDestination()+" "+del.getSource()+" "+
											del.getPrice()+" "+" "+del.getCode()+" "+del.getPricePerKm()+" "+del.getDate()+" "+del.getPrice());
								}
							}
						}
					a.close();
					}
				 catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
}
		});
		mnNewMenu_6.add(mntmNewMenuItem_11);
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Show members w/more than 3 short deliveries");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MembersWithMoreThan3ShDels(manager).setVisible(true);	
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_12);
	}
}