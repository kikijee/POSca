import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.Color;

public class Window extends JFrame {

	private JPanel contentPane;
	private JPanel panelOrders;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLayeredPane layeredMainPane;
	private JPanel panelLogin;
	private JPanel panelMenu;
	private IDandPassword logObj;
	private Items itemsObj;
	private JTextField subtotalField;
	private JTextField totalField;
	private JTextField numItemField;
	private JTextField orderNumField;
	private JPanel panelMakeOrder;
	private JLayeredPane layeredItemPane;
	private JPanel panelDrink;
	private JPanel panelAppetizer;
	private JPanel panelSushi;
	private JPanel panelRoll;
	private JPanel panelEntree;
	private JPanel panelDessert;
	private JTable table;
	NumberFormat number = NumberFormat.getInstance();			// for two decimal point for total
	private ArrayList<Order>orders = new ArrayList<Order>();	// list for all orders
	private JTable table_1;
	//private String currUser;
	private User currUser;
	private JTextField subtotalOrdersField;
	private JTextField totalOrdersField;
	private JTextField itemsOrdersField;
	private JTable table_2;
	private int row = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
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
	public Window() {
		number.setMaximumFractionDigits(2);
		setVisible(true);
		// setting window and contentPane settings 
		logObj = new IDandPassword();
		itemsObj = new Items();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		layeredMainPane = new JLayeredPane();
		layeredMainPane.setBounds(10, 11, 964, 602);
		contentPane.add(layeredMainPane);
		layeredMainPane.setLayout(new CardLayout(0, 0));
		
		panelLogin = new JPanel();
		layeredMainPane.add(panelLogin, "name_226065968568000");
		panelLogin.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setText("");
		usernameField.setBounds(370, 190, 164, 20);
		panelLogin.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(370, 165, 164, 14);
		panelLogin.add(usernameLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(370, 260, 164, 20);
		panelLogin.add(passwordField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(370, 235, 164, 14);
		panelLogin.add(passwordLabel);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setBounds(10, 0, 77, 35);
		panelLogin.add(loginLabel);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(logObj.validate(usernameField.getText(),new String(passwordField.getPassword()))) {
					currUser = logObj.get_user(usernameField.getText());
					usernameField.setText(null);
					passwordField.setText(null);
					switchMainPanel(panelMenu);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password","ERROR",JOptionPane.INFORMATION_MESSAGE);
					usernameField.setText(null);
					passwordField.setText(null);
				}
				
			}
		});
		loginButton.setBounds(445, 318, 89, 23);
		panelLogin.add(loginButton);
		
		panelMenu = new JPanel();
		layeredMainPane.add(panelMenu, "name_226113370985000");
		panelMenu.setLayout(null);
		
		JButton viewOrderButton = new JButton("View Order");
		viewOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMainPanel(panelOrders);
			}
		});
		viewOrderButton.setBounds(283, 210, 89, 80);
		panelMenu.add(viewOrderButton);
		
		JButton makeOrderButton = new JButton("Make Order");
		makeOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMainPanel(panelMakeOrder);
				switchItemPanel(panelDrink);
			}
		});
		
		makeOrderButton.setBounds(575, 210, 89, 80);
		panelMenu.add(makeOrderButton);
		
		JButton logoutButton = new JButton("LOGOUT");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMainPanel(panelLogin);
			}
		});
		logoutButton.setBounds(40, 548, 89, 23);
		panelMenu.add(logoutButton);
		
		panelMakeOrder = new JPanel();
		layeredMainPane.add(panelMakeOrder, "name_247097960798500");
		panelMakeOrder.setLayout(null);
		
		subtotalField = new JTextField();
		subtotalField.setText("0.00");
		subtotalField.setEditable(false);
		subtotalField.setBounds(817, 441, 137, 20);
		panelMakeOrder.add(subtotalField);
		subtotalField.setColumns(10);
		
		JLabel subtotalLabel = new JLabel("Sub-Total:");
		subtotalLabel.setBounds(738, 444, 79, 14);
		panelMakeOrder.add(subtotalLabel);
		
		totalField = new JTextField();
		totalField.setText("0.00");
		totalField.setEditable(false);
		totalField.setBounds(817, 466, 137, 20);
		panelMakeOrder.add(totalField);
		totalField.setColumns(10);
		
		JLabel totalLabel = new JLabel("Total:");
		totalLabel.setBounds(738, 469, 46, 14);
		panelMakeOrder.add(totalLabel);
		
		numItemField = new JTextField();
		numItemField.setText("0");
		numItemField.setEditable(false);
		numItemField.setBounds(817, 491, 86, 20);
		panelMakeOrder.add(numItemField);
		numItemField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("# items:");
		lblNewLabel.setBounds(738, 494, 69, 14);
		panelMakeOrder.add(lblNewLabel);
		
		orderNumField = new JTextField();
		orderNumField.setEditable(false);
		orderNumField.setBounds(817, 610, 86, 20);
		panelMakeOrder.add(orderNumField);
		orderNumField.setColumns(10);
		
		JLabel orderNumLabel = new JLabel("Order #");
		orderNumLabel.setBounds(728, 613, 46, 14);
		panelMakeOrder.add(orderNumLabel);
		
		// START ORDER PANEL BUTTONS
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_order();
			}
		});
		
		sendButton.setBounds(739, 568, 89, 23);
		panelMakeOrder.add(sendButton);
		
		JButton sendPayButton = new JButton("Send & Pay");
		sendPayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print_orders();
			}
		});
		sendPayButton.setBounds(848, 568, 89, 23);
		panelMakeOrder.add(sendPayButton);
		
		JButton voidButton = new JButton("Void");
		voidButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remove_from_table();
			}
		});
		voidButton.setBounds(738, 534, 89, 23);
		panelMakeOrder.add(voidButton);
		
		JButton orderBackButton = new JButton("Back");
		orderBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector().removeAllElements();
				model.fireTableDataChanged();
				switchMainPanel(panelMenu);
			}
		});
		orderBackButton.setBounds(10, 568, 89, 23);
		panelMakeOrder.add(orderBackButton);
		// END ORDER PANEL BUTTONS
		
		//===============Food Category Buttons===============
		//Drink button
		JButton drinkButton = new JButton("Drinks");
		drinkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelDrink);
			}
		});
		drinkButton.setBounds(10, 12, 89, 23);
		panelMakeOrder.add(drinkButton);
		//Appetizer button
		JButton appetizerButton = new JButton("Appetizers");
		appetizerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelAppetizer);
			}
		});
		appetizerButton.setBounds(10, 46, 89, 23);
		panelMakeOrder.add(appetizerButton);
		//Sushi button
		JButton sushiButton = new JButton("Sushi");
		sushiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelSushi);
			}
		});
		sushiButton.setBounds(10, 80, 89, 23);
		panelMakeOrder.add(sushiButton);
		//Roll button
		JButton rollButton = new JButton("Rolls");
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelRoll);
			}
		});
		rollButton.setBounds(10, 114, 89, 23);
		panelMakeOrder.add(rollButton);
		//Entree button
		JButton entreeButton = new JButton("Entree's");
		entreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelEntree);
			}
		});
		entreeButton.setBounds(10, 148, 89, 23);
		panelMakeOrder.add(entreeButton);
		//Dessert button
		JButton dessertButton = new JButton("Dessert");
		dessertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelDessert);
			}
		});
		dessertButton.setBounds(10, 182, 89, 23);
		panelMakeOrder.add(dessertButton);
		//======================================================
		
		//==========Layered item pane and panels===========
		layeredItemPane = new JLayeredPane();
		layeredItemPane.setBounds(117, 11, 582, 591);
		panelMakeOrder.add(layeredItemPane);
		layeredItemPane.setLayout(new CardLayout(0, 0));
		//Drink panel
		panelDrink = new JPanel();
		layeredItemPane.add(panelDrink, "name_255391516279300");
		panelDrink.setLayout(null);
		//Appetizer Panel
		panelAppetizer = new JPanel();
		layeredItemPane.add(panelAppetizer, "name_255397136104900");
		panelAppetizer.setLayout(null);
		//Sushi Panel
		panelSushi = new JPanel();
		panelSushi.setForeground(new Color(0, 128, 128));
		layeredItemPane.add(panelSushi, "name_255400090207500");
		panelSushi.setLayout(null);
		//Roll Panel
		panelRoll = new JPanel();
		layeredItemPane.add(panelRoll, "name_255403077667200");
		panelRoll.setLayout(null);
		//Entree Panel
		panelEntree = new JPanel();
		layeredItemPane.add(panelEntree, "name_255407088247600");
		panelEntree.setLayout(null);
		//Dessert Panel
		panelDessert = new JPanel();
		layeredItemPane.add(panelDessert, "name_255414797153500");
		panelDessert.setLayout(null);
		//==================================================
		
		//===============Drink Options===============
		JButton asahiButton = new JButton("");
		Image asahiImage = new ImageIcon(this.getClass().getResource("/asahi.jpg")).getImage();
		asahiButton.setIcon(new ImageIcon(asahiImage));
		asahiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(itemsObj.return_item_data(e.getSource()).toString());
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		asahiButton.setBounds(10, 11, 100, 100);
		panelDrink.add(asahiButton);
		itemsObj.add_item(asahiButton,4.99F,"Asahi");
		//===========================================
		
		//===============Appetizer Options===============
		JButton seafoodSpringRollsButton = new JButton("");
		Image springrollImage = new ImageIcon(this.getClass().getResource("/springroll.jpg")).getImage();
		seafoodSpringRollsButton.setIcon(new ImageIcon(springrollImage));
		seafoodSpringRollsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(itemsObj.return_item_data(e.getSource()).toString());
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		seafoodSpringRollsButton.setBounds(10, 11, 100, 100);
		panelAppetizer.add(seafoodSpringRollsButton);
		itemsObj.add_item(seafoodSpringRollsButton,5.99F,"Seafood Spring Rolls");
		//===============================================
		
		//===============Sushi Options===============
		JButton salmonSushiButton = new JButton("");
		Image sushiImage = new ImageIcon(this.getClass().getResource("/sushi.png")).getImage();
		salmonSushiButton.setIcon(new ImageIcon(sushiImage));
		salmonSushiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(itemsObj.return_item_data(e.getSource()).toString());
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		salmonSushiButton.setBounds(10, 11, 100, 100);
		panelSushi.add(salmonSushiButton);
		itemsObj.add_item(salmonSushiButton,6.99F,"Salmon Sushi");
		//===========================================
		
		//===============Roll Options===============
		JButton blueOceanButton = new JButton("");
		Image blueOceanImage = new ImageIcon(this.getClass().getResource("/blueOcean.jpg")).getImage();
		blueOceanButton.setIcon(new ImageIcon(blueOceanImage));
		blueOceanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(itemsObj.return_item_data(e.getSource()).toString());
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		blueOceanButton.setBounds(10, 11, 100, 100);
		panelRoll.add(blueOceanButton);
		itemsObj.add_item(blueOceanButton,19.99F,"Blue Ocean Roll");
		//==========================================
		
		//===============Entree Options===============
		JButton chickenKatsuButton = new JButton("");
		Image katsuImage = new ImageIcon(this.getClass().getResource("/katsu.jpg")).getImage();
		chickenKatsuButton.setIcon(new ImageIcon(katsuImage));
		chickenKatsuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(itemsObj.return_item_data(e.getSource()).toString());
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		chickenKatsuButton.setBounds(10, 11, 100, 100);
		panelEntree.add(chickenKatsuButton);
		itemsObj.add_item(chickenKatsuButton,21.99F,"Chicken Katsu Entree");
		//============================================
		
		//===============Desert Options===============
		JButton carmelAppleButton = new JButton("");
		Image appleImage = new ImageIcon(this.getClass().getResource("/apple.jpeg")).getImage();
		carmelAppleButton.setIcon(new ImageIcon(appleImage));
		carmelAppleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(itemsObj.return_item_data(e.getSource()).toString());
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		carmelAppleButton.setBounds(10, 11, 100, 100);
		panelDessert.add(carmelAppleButton);
		itemsObj.add_item(carmelAppleButton,4.99F,"Carmel Apple");
		//============================================
		
		//=================Item Labels=================
		JLabel lblNewLabel_1 = new JLabel("Seafood Spring Rolls");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 122, 100, 14);
		panelAppetizer.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Asahi");
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 122, 100, 14);
		panelDrink.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Salmon Sushi");
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 122, 100, 14);
		panelSushi.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Blue Ocean Roll");
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 122, 100, 14);
		panelRoll.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("<html>Chicken Katsu<br/>Entree</html>");
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 122, 100, 28);
		panelEntree.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Carmel Apple");
		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 122, 100, 14);
		panelDessert.add(lblNewLabel_6);
		
		JScrollPane OrderscrollPane = new JScrollPane();
		OrderscrollPane.setBounds(709, 11, 245, 419);
		panelMakeOrder.add(OrderscrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "Quantity", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
		table.getColumnModel().getColumn(1).setPreferredWidth(52);
		OrderscrollPane.setViewportView(table);
		
		panelOrders = new JPanel();
		layeredMainPane.add(panelOrders, "name_71322738432400");
		panelOrders.setLayout(null);
		
		JScrollPane viewOrderscrollPane = new JScrollPane();
		viewOrderscrollPane.setBounds(10, 11, 569, 580);
		panelOrders.add(viewOrderscrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Total", "Time", "Server", "Paid"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Float.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		viewOrderscrollPane.setViewportView(table_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(589, 11, 365, 470);
		panelOrders.add(scrollPane);
		
		
		// table_1 mouse click event
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				if(table_1.getSelectedRow() == -1) {return;}
				else if(table_1.getSelectedRow() != row) {
					DefaultTableModel model = (DefaultTableModel) table_2.getModel();
					model.getDataVector().removeAllElements();
					//subtotalOrdersField.setText(currUser.return_username());
					model.fireTableDataChanged();
					orders.get(table_1.getSelectedRow()).draw_orders(model,subtotalOrdersField,totalOrdersField,itemsOrdersField);
					row = table_1.getSelectedRow();
				}
			}
		});
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "Quantity", "Price"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table_2);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table_2.getModel();
				model.getDataVector().removeAllElements();
				model.fireTableDataChanged();
				table_1.getSelectionModel().clearSelection();
				subtotalOrdersField.setText("0.00");
				totalOrdersField.setText("0.00");
				itemsOrdersField.setText("0");
				switchMainPanel(panelMenu);
				row = -1;
			}
		});
		btnNewButton.setBounds(589, 568, 89, 23);
		panelOrders.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table_1.getSelectionModel().isSelectionEmpty()) {
					Order edit = orders.get(table_1.getSelectedRow());
					DefaultTableModel model = (DefaultTableModel) table_2.getModel();
					model.getDataVector().removeAllElements();
					model.fireTableDataChanged();
					table_1.getSelectionModel().clearSelection();
					subtotalOrdersField.setText("0.00");
					totalOrdersField.setText("0.00");
					itemsOrdersField.setText("0");
					row = -1;
					
					edit.draw_orders((DefaultTableModel) table.getModel(), subtotalField, totalField, numItemField);
					switchMainPanel(panelMakeOrder);
				}
			}
		});
		btnNewButton_1.setBounds(589, 492, 89, 23);
		panelOrders.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Void");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table_1.getSelectionModel().isSelectionEmpty()) {
					if(currUser.is_admin()) {
						int remove = table_1.getSelectedRow();
						// table_1 edit
						DefaultTableModel model = (DefaultTableModel) table_1.getModel();
						model.removeRow(remove);
						// removal of order from array
						orders.remove(remove);
						//table_2 edit
						model = (DefaultTableModel) table_2.getModel();
						model.getDataVector().removeAllElements();
						model.fireTableDataChanged();
						// reset fields
						subtotalOrdersField.setText("0.00");
						totalOrdersField.setText("0.00");
						itemsOrdersField.setText("0");
						table_1.getSelectionModel().clearSelection();
						row = -1;
						
					}
				}
			}
		});
		btnNewButton_2.setBounds(589, 526, 89, 23);
		panelOrders.add(btnNewButton_2);
		
		JLabel lblNewLabel_7 = new JLabel("Total:");
		lblNewLabel_7.setBounds(714, 530, 46, 14);
		panelOrders.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Subtotal:");
		lblNewLabel_8.setBounds(714, 496, 46, 14);
		panelOrders.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("# Items:");
		lblNewLabel_9.setBounds(714, 572, 46, 14);
		panelOrders.add(lblNewLabel_9);
		
		subtotalOrdersField = new JTextField();
		subtotalOrdersField.setText("0.00");
		subtotalOrdersField.setEditable(false);
		subtotalOrdersField.setBounds(770, 493, 86, 20);
		panelOrders.add(subtotalOrdersField);
		subtotalOrdersField.setColumns(10);
		
		totalOrdersField = new JTextField();
		totalOrdersField.setText("0.00");
		totalOrdersField.setEditable(false);
		totalOrdersField.setColumns(10);
		totalOrdersField.setBounds(770, 527, 86, 20);
		panelOrders.add(totalOrdersField);
		
		itemsOrdersField = new JTextField();
		itemsOrdersField.setText("0");
		itemsOrdersField.setEditable(false);
		itemsOrdersField.setColumns(10);
		itemsOrdersField.setBounds(770, 569, 86, 20);
		panelOrders.add(itemsOrdersField);
		//=============================================
		
		
		
	}
	
	public void switchMainPanel(JPanel panel){
        layeredMainPane.removeAll();
        layeredMainPane.add(panel);
        layeredMainPane.repaint();
        layeredMainPane.revalidate();
    }
	
	public void switchItemPanel(JPanel panel) {
		layeredItemPane.removeAll();
		layeredItemPane.add(panel);
		layeredItemPane.repaint();
		layeredItemPane.revalidate();
	}
	
	public void add_to_table(Tuple<String,Float>tuple) {	// adding item to table (Tuple object has item name and price)
		int check = if_exist(tuple.x);	// checking if item exists already
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if(check != -1) {
			model.setValueAt(Integer.valueOf(model.getValueAt(check,1).toString())+Integer.valueOf(1),check,1);
			model.setValueAt(Float.parseFloat(model.getValueAt(check,2).toString())+tuple.y,check,2);
		}
		else {
			model.addRow(new Object[] {tuple.x,1,tuple.y});
		}
		update_price(tuple.y);
	}
	
	public void add_to_order_table() {
		if(!orders.isEmpty()) {
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			orders.get(orders.size()-1).add_order(model);
			
		}
	}
	
	public void remove_from_table() {	// function removes item from order table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if(table.getSelectedRow() != -1) {
			Float price = (Float.parseFloat(model.getValueAt(table.getSelectedRow(),2).toString()))/(Integer.valueOf(model.getValueAt(table.getSelectedRow(),1).toString()));
			
			if(Integer.valueOf(model.getValueAt(table.getSelectedRow(),1).toString()) == 1) {
				model.removeRow(table.getSelectedRow());
			}
			else {
				model.setValueAt(Integer.valueOf(model.getValueAt(table.getSelectedRow(),1).toString())-Integer.valueOf(1),table.getSelectedRow(),1);
				model.setValueAt(Float.parseFloat(model.getValueAt(table.getSelectedRow(),2).toString())-(Float.parseFloat(model.getValueAt(table.getSelectedRow(),2).toString()))/(Integer.valueOf(model.getValueAt(table.getSelectedRow(),1).toString())+1),table.getSelectedRow(),2);
			}
			update_price(Float.parseFloat(number.format(-1*price)));
		}
	}
	
	public int if_exist(String item) {	// checks if item exists in the order table
		for(int i = 0; i < table.getRowCount(); i++) {
			if(table.getModel().getValueAt(i, 0) == item) {
				return i;
			}
		}
		return -1;
	}
	
	public void update_price(Float priceUpdate) { // will update price when user adds/removes items
		//System.out.println(priceUpdate);
		Float price = Float.parseFloat(subtotalField.getText());
		price += priceUpdate;
		subtotalField.setText(number.format((price)));
		
		price = Float.parseFloat(totalField.getText());
		price += (priceUpdate * 1.08F);
		totalField.setText(number.format((price)));
		
		if(priceUpdate > 0) {
			numItemField.setText(String.valueOf(Integer.parseInt(numItemField.getText())+1));
		}
		else {
			numItemField.setText(String.valueOf(Integer.parseInt(numItemField.getText())-1));
		}
	}
	
	public void add_order() { // will add order to orders ArrayList
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Calendar date = Calendar.getInstance();
		String time = String.valueOf(date.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(date.get(Calendar.MINUTE));
		//System.out.println(time);
		
		if(model.getRowCount() != 0) {
			ArrayList<Thuple<String,Integer,Float>>items = new ArrayList<Thuple<String,Integer,Float>>();
			Thuple<String,Integer,Float> item;
			String name;
			Integer quantity;
			Float price;
			for(int i = 0; i < model.getRowCount(); i++) {
				name = String.valueOf(model.getValueAt(i, 0));
				quantity = Integer.valueOf(model.getValueAt(i, 1).toString());
				price = Float.valueOf(model.getValueAt(i, 2).toString());
				item = new Thuple<String,Integer,Float>(name,quantity,price);
				items.add(item);
			}
			Order ordertemp = new Order(Float.valueOf(subtotalField.getText()),Float.valueOf(totalField.getText()),Integer.valueOf(numItemField.getText()),false,currUser.return_username(),time,items);
			orders.add(ordertemp);
			add_to_order_table();
		}
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		subtotalField.setText("0.00");
		totalField.setText("0.00");
		numItemField.setText("0");
	}
	
	public void print_orders() {
		for(int i = 0; i < orders.size(); i++) {
			orders.get(i).print_order();
		}
	}
	
}
