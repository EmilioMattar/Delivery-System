import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class JFrameShowShortDels extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton;
	private JLabel lblNewLabel_2;
	private JLabel label;
	private JLabel lblNewLabel_3;
	public JFrameShowShortDels(Managers manager) {
		getContentPane().setLayout(null);
		table = new JTable();
		table.setBounds(200, 202, 200, -50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 900, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 40, 870, 372);
		contentPane.add(scrollPane);
		/*Jtable of the short deliveries*/
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
	        Object rowData[] = new Object[7];
	        Object[] culomn = {"Member Name","Destination","Source","Code","Distance","Price","Date"};
	        model.setColumnIdentifiers(culomn);
	        for (ShortDistanceDelivery del : DataBase.shortDeleviries) {
				rowData[0]=del.getMember().getFirstName()+" "+del.getMember().getLastName();
				rowData[1]=del.getDestination();
				rowData[2]=del.getSource();
				rowData[3]=del.getCode();
				rowData[4]=String.valueOf(del.getDistance());
				rowData[5]=String.valueOf(del.getPrice());
				rowData[6]=String.valueOf(del.getDate());
	            model.addRow(rowData);
	        }
	        scrollPane.setViewportView(table);
		     table.setModel(model);
		     
		     lblNewLabel = new JLabel("Show active short deliveries in the program: ");
		     lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel.setBounds(16, 12, 352, 16);
		     contentPane.add(lblNewLabel);
		     
		     lblNewLabel_1 = new JLabel("Return to the menu-->");
		     lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 15));
		     lblNewLabel_1.setBounds(6, 720, 192, 27);
		     contentPane.add(lblNewLabel_1);
		     
		     btnNewButton = new JButton("Menu");
		     btnNewButton.addActionListener(new ActionListener() {
		     	public void actionPerformed(ActionEvent e) {
		     		setVisible(false);
		     		new ManagerMenu(manager).setVisible(true);
		     	}
		     });
		     btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		     btnNewButton.setBounds(197, 721, 83, 29);
		     contentPane.add(btnNewButton);
		     
		     lblNewLabel_2 = new JLabel("");
		     lblNewLabel_2.setIcon(new ImageIcon(JFrameShowShortDels.class.getResource("/Images/ZAhFASRscCscBUj33239dH (1) (1).jpeg")));
		     lblNewLabel_2.setBounds(0, 413, 700, 359);
		     contentPane.add(lblNewLabel_2);
		     
		     label = new JLabel("New label");
		     label.setBounds(712, 415, 1, 2);
		     contentPane.add(label);
		     
		     lblNewLabel_3 = new JLabel("");
		     lblNewLabel_3.setIcon(new ImageIcon(JFrameShowShortDels.class.getResource("/Images/ZAhFASRscCscBUj33239dH (1) (1).jpeg")));
		     lblNewLabel_3.setBounds(679, 413, 221, 359);
		     contentPane.add(lblNewLabel_3);
	}
}