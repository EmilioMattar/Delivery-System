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
import java.awt.event.ActionEvent;
public class ManagerMenu extends JFrame {
	private JPanel contentPane;
	public ManagerMenu(Managers manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 558, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ManagerMenu.class.getResource("/Images/shutterstock_793046887-min-scaled (1).jpg")));
		lblNewLabel.setBounds(0, 20, 558, 407);
		contentPane.add(lblNewLabel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setBounds(0, 0, 558, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Members");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Member");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					new AddMember(manager).setVisible(true);
				} catch (MemberExistedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mntmNewMenuItem.setForeground(Color.BLACK);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_5 = new JMenu("Summaries");
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_20 = new JMenuItem("Show Short Deliveries");
		mntmNewMenuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new JFrameShowShortDels(manager).setVisible(true);
			}
		});
		mntmNewMenuItem_20.setForeground(Color.BLACK);
		mnNewMenu_5.add(mntmNewMenuItem_20);
		JMenuItem mntmNewMenuItem_21 = new JMenuItem("Show Customers with Short Deliveries");
		mntmNewMenuItem_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new CustomersWithSDels(manager).setVisible(true);
			}
		});
		mntmNewMenuItem_21.setForeground(Color.BLACK);
		mnNewMenu_5.add(mntmNewMenuItem_21);
		JMenuItem mntmNewMenuItem_22 = new JMenuItem("Show Cities of short Deliveries");
		mntmNewMenuItem_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new cities(manager).setVisible(true);
			}
		});
		mntmNewMenuItem_22.setForeground(Color.BLACK);
		mnNewMenu_5.add(mntmNewMenuItem_22);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Last Deliveries");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LastDeliveries(manager).setVisible(true);
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem_7);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Delete Member");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.getMembers().size()==0)
				{
					JOptionPane.showMessageDialog(null,"No members exist for this manager.");
				}
				else {
					setVisible(false);
				try {
					new DeleteMember(manager).setVisible(true);
				} catch (ShortDeliveryException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Manager");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Manager");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AddManager(manager).setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Add Sub Manager");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AddSubManager(manager).setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_3 = new JMenu("Deliveries");
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Add Delivery");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(manager.getMembers().size()==0)
				{
					JOptionPane.showMessageDialog(null,"No members exist for this manager.");
				}
				else {
					setVisible(false);
					new DeliveriesGui(manager).setVisible(true);
				}
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_5);
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Remove Short Delivery");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DataBase.shortDeleviries.size()==0)
				{
					JOptionPane.showMessageDialog(null,"No Short Deliveries exist.");
				}
				else {
					setVisible(false);
					new RemoveShortDel(manager).setVisible(true);
				}
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_6);
		JMenu mnNewMenu_6 = new JMenu("Information retrieval");
		menuBar.add(mnNewMenu_6);
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Short Deliveries by date");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new deliveriesByDate(manager).setVisible(true);	
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_8);
		JMenuItem mntmNewMenuItem_9 =  new JMenuItem("All Deliveries by code");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new deliveriesByCode(manager).setVisible(true);	
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_9);
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("All Deliveries in your location");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new DeliveriesOfArea(manager).setVisible(true);
			}
		});
		mnNewMenu_6.add(mntmNewMenuItem_10);
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
		
	}
}
