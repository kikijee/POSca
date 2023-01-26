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
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class Window extends JFrame {
	private JButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnC,btnD,btnPay; // all the cash pay buttons
	private JPanel contentPane; // main panel
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLayeredPane layeredMainPane,layeredItemPane;	
	private JPanel panelLogin,panelMenu,panelMakeOrder,panelOrders; // sub panels of the content pane
	private JTextField subtotalField,totalField,numItemField; 		// text fields in the make order panel
	private JPanel panelDrink,panelAppetizer,panelSushi,panelRoll,panelEntree,panelDessert,panelPay; // all food category panels
	private JPanel panelCash,panelCard;				// pay option panels
	private JTable table,table_1,table_2,table_3;	// all table objects
	private JButton btnPayCard;
	private JTextField subtotalOrdersField,totalOrdersField,itemsOrdersField;	// text fields for panelOrders
	private JTextField textField,textField_1,textField_2;		// text fields for panelCash
	private JTextField textField_3,textField_4,textField_5;		// text fields for panelPay
	
	NumberFormat number = NumberFormat.getInstance();			// for two decimal point for total (String type)
	private ArrayList<Order>orders = new ArrayList<Order>();	// list for all orders
	private Order currOrder;		// pointer to order that will be processed
	private JPanel prev_panel;		// pointer to prev panel;
	private Items itemsObj;			// items object that holds all items
	private User currUser;			// pointer to user object that is currently logged in
	//private IDandPassword logObj;	// object that holds all the users ***CLASS AND ITS FUNCTIONALITY IS DEPRECIATED*** (code pre sql implementation)
	private int row = -1;			// placeholder for row selection (utility for order selection on the table in the panelOrders)
	private int placeholder = 0;	// placeholder int value (utility for the cash input functionality)
	private PosDatabase myData = new PosDatabase();	// POS database object holding all functionality for the sql queries
	
	// main function
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
		number.setMaximumFractionDigits(2);	// for Float to String conversion 2 decimal point precision 
		//logObj = new IDandPassword();		// login object holding all user info (MAY CHANGE FOR THE DATABASE) ***DEPRECIATED***
		itemsObj = new Items();				// item object creation (MAY CHANGE FOR THE DATABASE)
		
		//=================START DEFUALT WINDOW SETTINGS=================
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		//===============================================================
		
		//==================ALL LAYERED PANELS==================
		layeredMainPane = new JLayeredPane();
		layeredMainPane.setBounds(10, 11, 964, 602);
		contentPane.add(layeredMainPane);
		layeredMainPane.setLayout(new CardLayout(0, 0));
		
		layeredItemPane = new JLayeredPane();
		layeredItemPane.setBounds(117, 11, 582, 591);
		layeredItemPane.setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPanePay = new JLayeredPane();
		layeredPanePay.setBounds(10, 11, 578, 580);
		layeredPanePay.setLayout(new CardLayout(0, 0));
		//======================================================
		
		//==================ALL MAIN PANELS==================
		panelLogin = new JPanel();
		layeredMainPane.add(panelLogin, "name_226065968568000");
		panelLogin.setLayout(null);
		
		panelMenu = new JPanel();
		layeredMainPane.add(panelMenu, "name_226113370985000");
		panelMenu.setLayout(null);
		
		panelMakeOrder = new JPanel();
		layeredMainPane.add(panelMakeOrder, "name_247097960798500");
		panelMakeOrder.setLayout(null);
		
		panelOrders = new JPanel();
		layeredMainPane.add(panelOrders, "name_71322738432400");
		panelOrders.setLayout(null);
		
		panelPay = new JPanel();
		layeredMainPane.add(panelPay, "name_29246515247100");
		panelPay.setLayout(null);
		
		JPanel panelPayOption = new JPanel();
		layeredPanePay.add(panelPayOption, "name_29905756660300");
		panelPayOption.setLayout(null);
		//===================================================
		panelMakeOrder.add(layeredItemPane);
		panelPay.add(layeredPanePay);
		
		//=================START LOGIN OBJECT CREATION AND FUNCTIONALITY=================
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
				currUser = myData.login(usernameField.getText(),new String(passwordField.getPassword()));
				if(currUser != null) {
					usernameField.setText(null);
					passwordField.setText(null);
					switchMainPanel(panelMenu);
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password","ERROR",JOptionPane.INFORMATION_MESSAGE);
					usernameField.setText(null);
					passwordField.setText(null);
				}
				/*	(code pre sql implementation)
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
				*/
				
			}
		});
		loginButton.setBounds(445, 318, 89, 23);
		panelLogin.add(loginButton);
		//===============================================================================
		
		//=================START MENU SELECTION OBJECT CREATION AND FUNCTIONALITY=================
		JButton viewOrderButton = new JButton("View Order");
		viewOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revalidate_orders();
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
		//========================================================================================
		
		//=================START MAKE ORDER OBJECT CREATION AND FUNCTIONALITY=================
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
		
		JLabel orderNumLabel = new JLabel("Order #");
		orderNumLabel.setBounds(728, 613, 46, 14);
		panelMakeOrder.add(orderNumLabel);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getRowCount() != 0) {
					add_order();
					clear_table(table,subtotalField,totalField,numItemField);
				}
			}
		});
		
		sendButton.setBounds(739, 568, 89, 23);
		panelMakeOrder.add(sendButton);
		
		JButton sendPayButton = new JButton("Send & Pay");
		sendPayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getRowCount() != 0) {
					prev_panel = panelMenu;
					currOrder = add_order();
					//clear_table(table,subtotalField,totalField,numItemField);
					revalidate_orders();
					currOrder.draw_orders((DefaultTableModel) table_3.getModel(),textField_3,textField_4,textField_5);
					switchMainPanel(panelPay);
				}
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
				clear_table(table,subtotalField,totalField,numItemField);
				switchMainPanel(panelMenu);
			}
		});
		orderBackButton.setBounds(10, 568, 89, 23);
		panelMakeOrder.add(orderBackButton);
		//====================================================================================
		
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
		//====================================================
		
		//==========panels for food items===========
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
		//==========================================
		
		//===============Drink Options===============
		JButton asahiButton = new JButton("");
		Image asahiImage = new ImageIcon(this.getClass().getResource("/asahi.jpg")).getImage();
		asahiButton.setIcon(new ImageIcon(asahiImage));
		asahiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		asahiButton.setBounds(10, 11, 100, 100);
		panelDrink.add(asahiButton);
		itemsObj.add_item(asahiButton,4.99F,"Asahi", 001);
		//===========================================
		
		//===============Appetizer Options===============
		JButton seafoodSpringRollsButton = new JButton("");
		Image springrollImage = new ImageIcon(this.getClass().getResource("/springroll.jpg")).getImage();
		seafoodSpringRollsButton.setIcon(new ImageIcon(springrollImage));
		seafoodSpringRollsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		seafoodSpringRollsButton.setBounds(10, 11, 100, 100);
		panelAppetizer.add(seafoodSpringRollsButton);
		itemsObj.add_item(seafoodSpringRollsButton,5.99F,"Seafood Spring Rolls", 002);
		//===============================================
		
		//===============Sushi Options===============
		JButton salmonSushiButton = new JButton("");
		Image sushiImage = new ImageIcon(this.getClass().getResource("/sushi.png")).getImage();
		salmonSushiButton.setIcon(new ImageIcon(sushiImage));
		salmonSushiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		salmonSushiButton.setBounds(10, 11, 100, 100);
		panelSushi.add(salmonSushiButton);
		itemsObj.add_item(salmonSushiButton,6.99F,"Salmon Sushi", 003);
		//===========================================
		
		//===============Roll Options===============
		JButton blueOceanButton = new JButton("");
		Image blueOceanImage = new ImageIcon(this.getClass().getResource("/blueOcean.jpg")).getImage();
		blueOceanButton.setIcon(new ImageIcon(blueOceanImage));
		blueOceanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		blueOceanButton.setBounds(10, 11, 100, 100);
		panelRoll.add(blueOceanButton);
		itemsObj.add_item(blueOceanButton,19.99F,"Blue Ocean Roll", 004);
		//==========================================
		
		//===============Entree Options===============
		JButton chickenKatsuButton = new JButton("");
		Image katsuImage = new ImageIcon(this.getClass().getResource("/katsu.jpg")).getImage();
		chickenKatsuButton.setIcon(new ImageIcon(katsuImage));
		chickenKatsuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		chickenKatsuButton.setBounds(10, 11, 100, 100);
		panelEntree.add(chickenKatsuButton);
		itemsObj.add_item(chickenKatsuButton,21.99F,"Chicken Katsu Entree", 005);
		//============================================
		
		//===============Desert Options===============
		JButton carmelAppleButton = new JButton("");
		Image appleImage = new ImageIcon(this.getClass().getResource("/apple.jpeg")).getImage();
		carmelAppleButton.setIcon(new ImageIcon(appleImage));
		carmelAppleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_to_table(itemsObj.return_item_data(e.getSource()));
			}
		});
		carmelAppleButton.setBounds(10, 11, 100, 100);
		panelDessert.add(carmelAppleButton);
		itemsObj.add_item(carmelAppleButton,4.99F,"Carmel Apple", 006);
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
		//=============================================
		
		//==============ALL SCROLL PANELS FOR TABLES==============
		JScrollPane OrderscrollPane = new JScrollPane();
		OrderscrollPane.setBounds(709, 11, 245, 419);
		panelMakeOrder.add(OrderscrollPane);
		
		JScrollPane viewOrderscrollPane = new JScrollPane();
		viewOrderscrollPane.setBounds(10, 11, 569, 580);
		panelOrders.add(viewOrderscrollPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(589, 11, 365, 470);
		panelOrders.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(600, 11, 354, 467);
		panelPay.add(scrollPane_1);
		//========================================================
		
		//==============ALL TABLES==============
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
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Total", "Time", "Server", "Paid", "Type"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Float.class, String.class, String.class, Boolean.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		viewOrderscrollPane.setViewportView(table_1);
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
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
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
		scrollPane_1.setViewportView(table_3);
		//======================================
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear_table(table_2,subtotalOrdersField,totalOrdersField,itemsOrdersField);
				table_1.getSelectionModel().clearSelection();
				clear_table(table_1,subtotalOrdersField,totalOrdersField,itemsOrdersField);
				switchMainPanel(panelMenu);
				row = -1;
			}
		});
		btnNewButton.setBounds(589, 568, 89, 23);
		panelOrders.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pay");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!table_1.getSelectionModel().isSelectionEmpty()) {
					prev_panel = panelOrders;
					currOrder = orders.get(table_1.getSelectedRow());
					clear_table(table_2,subtotalOrdersField,totalOrdersField,itemsOrdersField);
					table_1.getSelectionModel().clearSelection();
					row = -1;
					
					currOrder.draw_orders((DefaultTableModel) table_3.getModel(), textField_3, textField_4, textField_5);
					
					switchMainPanel(panelPay);
				}
			}
		});
		btnNewButton_1.setBounds(589, 492, 89, 23);
		panelOrders.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Void");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				void_order();
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
		
		JButton btnNewButton_3 = new JButton("CASH");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currOrder.return_paid()) {toggle_pay_cash(false);}
				else{toggle_pay_cash(true);}
				textField_1.setText(String.valueOf(currOrder.return_owed()));
				if(currOrder.return_change() == null) {textField_2.setText("");}
				else {textField_2.setText(number.format(currOrder.return_change()));}
				textField.setText("-.--");
				switch_panel(layeredPanePay,panelCash);
			}
		});
		btnNewButton_3.setBounds(105, 192, 89, 89);
		panelPayOption.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("CARD");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currOrder.return_paid()) {toggle_pay_card(false);}
				else {toggle_pay_card(true);}
				switch_panel(layeredPanePay,panelCard);
			}
		});
		btnNewButton_3_1.setBounds(389, 192, 89, 89);
		panelPayOption.add(btnNewButton_3_1);
		
		JButton btnNewButton_4 = new JButton("back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currOrder = null;
				clear_table(table_3,textField_3,textField_4,textField_5);
				switchMainPanel(prev_panel);
			}
		});
		btnNewButton_4.setBounds(10, 546, 89, 23);
		panelPayOption.add(btnNewButton_4);
		
		panelCash = new JPanel();
		layeredPanePay.add(panelCash, "name_30349975776200");
		panelCash.setLayout(null);
		
		btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(1,false,false);
			}
		});
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn1.setBounds(27, 128, 89, 89);
		panelCash.add(btn1);
		
		btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(2,false,false);
			}
		});
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn2.setBounds(126, 128, 89, 89);
		panelCash.add(btn2);
		
		btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(3,false,false);
			}
		});
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn3.setBounds(225, 128, 89, 89);
		panelCash.add(btn3);
		
		btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(4,false,false);
			}
		});
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn4.setBounds(27, 228, 89, 89);
		panelCash.add(btn4);
		
		btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(5,false,false);
			}
		});
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn5.setBounds(126, 228, 89, 89);
		panelCash.add(btn5);
		
		btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(6,false,false);
			}
		});
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn6.setBounds(225, 228, 89, 89);
		panelCash.add(btn6);
		
		btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(8,false,false);
			}
		});
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn8.setBounds(126, 328, 89, 89);
		panelCash.add(btn8);
		
		btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(7,false,false);
			}
		});
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn7.setBounds(27, 328, 89, 89);
		panelCash.add(btn7);
		
		btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(9,false,false);
			}
		});
		btn9.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn9.setBounds(225, 328, 89, 89);
		panelCash.add(btn9);
		
		btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(0,false,false);
			}
		});
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn0.setBounds(126, 428, 89, 89);
		panelCash.add(btn0);
		
		btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(-1,true,false);
			}
		});
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnC.setBounds(27, 428, 89, 89);
		panelCash.add(btnC);
		
		btnD = new JButton("DEL");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(-1,false,true);
			}
		});
		btnD.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnD.setBounds(225, 428, 89, 89);
		panelCash.add(btnD);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("-.--");
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setBounds(10, 11, 325, 89);
		panelCash.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("Back");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("-.--");
				placeholder = 0; 
				revalidate_orders();
				switch_panel(layeredPanePay,panelPayOption);
			}
		});
		btnNewButton_6.setBounds(10, 546, 89, 23);
		panelCash.add(btnNewButton_6);
		
		btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay_cash();
			}
		});
		btnPay.setBounds(479, 546, 89, 23);
		panelCash.add(btnPay);
		
		JLabel lblNewLabel_10 = new JLabel("Amount Owed:");
		lblNewLabel_10.setBounds(350, 173, 75, 14);
		panelCash.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Change:");
		lblNewLabel_11.setBounds(350, 273, 46, 14);
		panelCash.add(lblNewLabel_11);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(435, 170, 111, 20);
		panelCash.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(435, 270, 111, 20);
		panelCash.add(textField_2);
		
		panelCard = new JPanel();
		layeredPanePay.add(panelCard, "name_30377410911500");
		panelCard.setLayout(null);
		
		JButton btnNewButton_8 = new JButton("Back");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				revalidate_orders();
				switch_panel(layeredPanePay,panelPayOption);
			}
		});
		btnNewButton_8.setBounds(10, 546, 89, 23);
		panelCard.add(btnNewButton_8);
		
		btnPayCard = new JButton("Pay");
		btnPayCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay_card();
				toggle_pay_card(false);
			}
		});
		btnPayCard.setBounds(479, 546, 89, 23);
		panelCard.add(btnPayCard);
		
		JLabel lblNewLabel_12 = new JLabel("Sub-Total:");
		lblNewLabel_12.setBounds(610, 489, 58, 14);
		panelPay.add(lblNewLabel_12);
		
		JLabel lblNewLabel_12_1 = new JLabel("Total:");
		lblNewLabel_12_1.setBounds(610, 514, 58, 14);
		panelPay.add(lblNewLabel_12_1);
		
		JLabel lblNewLabel_12_1_1 = new JLabel("# Items:");
		lblNewLabel_12_1_1.setBounds(610, 541, 58, 14);
		panelPay.add(lblNewLabel_12_1_1);
		
		textField_3 = new JTextField();
		textField_3.setText("0.00");
		textField_3.setEditable(false);
		textField_3.setBounds(670, 486, 127, 20);
		panelPay.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setText("0.00");
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(670, 511, 127, 20);
		panelPay.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("0");
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(670, 538, 127, 20);
		panelPay.add(textField_5);
		//=============================================
		
		
		
	}
	
	// functions to switch panels
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
	
	public void switch_panel(JLayeredPane layeredPanel, JPanel panel) {
		layeredPanel.removeAll();
		layeredPanel.add(panel);
		layeredPanel.repaint();
		layeredPanel.revalidate();
	}
	
	// adding item to JTable (Tuple object has item name and price... for making orders)
	public void add_to_table(Thuple<String,Float, Integer>thuple) {
		int check = if_exist(thuple.x);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if(check != -1) {
			model.setValueAt(Integer.valueOf(model.getValueAt(check,1).toString())+Integer.valueOf(1),check,1);
			model.setValueAt(Float.parseFloat(model.getValueAt(check,2).toString())+thuple.y,check,2);
		}
		else {
			model.addRow(new Object[] {thuple.x,1,thuple.y, thuple.z});
		}
		update_price(thuple.y);
	}
	
	// adds item to JTable (for viewing order) ***USE MAY CHANGE FOR DATABASE CONNECTION***
	public void add_to_order_table() {
		if(!orders.isEmpty()) {
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			orders.get(orders.size()-1).add_order(model);
			
		}
	}
	
	// function removes item from order pre sending and paying (for making order)
	public void remove_from_table() {	
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
	
	// checks to see if an item already exists in a JTable (to help with quantity count of an item)
	public int if_exist(String item) {	// checks if item exists in the order table
		for(int i = 0; i < table.getRowCount(); i++) {
			if(table.getModel().getValueAt(i, 0) == item) {
				return i;
			}
		}
		return -1;
	}
	
	// will update price when user adds/removes items (for making orders)
	public void update_price(Float priceUpdate) {
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
	
	public String get_time() {
		Calendar date = Calendar.getInstance();
		return String.valueOf(date.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(date.get(Calendar.MINUTE));
	}
	
	// creates and adds an order object to the order ArrayList
	public Order add_order() { 
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String time = get_time();
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
			Order orderTemp = new Order(Float.valueOf(subtotalField.getText()),Float.valueOf(totalField.getText()),Integer.valueOf(numItemField.getText()),false,currUser.return_username(),time,items);
			myData.validate_id(orderTemp);
			myData.add_order(orderTemp.return_id(), Float.valueOf(subtotalField.getText()), Float.valueOf(totalField.getText()), time, currUser.return_username(), false, Integer.valueOf(numItemField.getText()),items);	// sql data insertion
			orders.add(orderTemp);
			//add_to_order_table();
			return orderTemp;
		}
		clear_table(table,subtotalField,totalField,numItemField);
		return null;
	}
	
	void void_order() {
		if(!table_1.getSelectionModel().isSelectionEmpty()) {
			if(currUser.is_admin()) {
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				int remove = table_1.getSelectedRow();
				int id = Integer.valueOf(model.getValueAt(remove,0).toString());
				myData.void_order(id,get_time(),currUser.return_username(),null);
				// table_1 edit
				model.removeRow(remove);
				// removal of order from array
				orders.remove(remove);
				clear_table(table_2,subtotalOrdersField,totalOrdersField,itemsOrdersField);
				table_1.getSelectionModel().clearSelection();
				row = -1;
				
			}
		}
	}
	
	// prints order list(DEBUGGING USE ONLY)
	public void print_orders() {
		for(int i = 0; i < orders.size(); i++) {
			orders.get(i).print_order();
		}
	}
	
	// function clears a JTable and its text fields
	public void clear_table(JTable table, JTextField sub, JTextField tot, JTextField it) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		sub.setText("0.00");
		tot.setText("0.00");
		it.setText("0");
	}
	
	// helper functions for num_input(...) 
	public String addChar(String str, char ch, int position) {
	    return str.substring(0, position) + ch + str.substring(position);
	}
	
	public String replaceChar(String str, char ch, int index) {
		str = str.substring(0, index) + ch
	              + str.substring(index + 1);
		return str;
	}
	
	// function to handle cash input in the pay state
	public void num_input(int num, boolean clear, boolean back) {
		if(placeholder == 2) {placeholder++;}
		String text = textField.getText();
		if(num != -1) {
			if(placeholder > 3) {
				text = text.replace(".", "");
				text = text.concat(String.valueOf(num));
				text = addChar(text,'.',text.length()-2);
			}
			else {
				text = text.replace(".","");
				text = text.replace("-","");
				text = text.concat(String.valueOf(num));
				if(text.length() == 1) {
					text = "-.-"+text;
				}
				else if(text.length() == 2) {
					text = "-."+text;
				}
				else {
					text = addChar(text,'.',1);
				}
			}
			placeholder++;
		}
		else if(clear) {
			if(placeholder == 0) {return;}
			else {
				placeholder = 0;
				textField.setText("-.--");
				return;
			}
		}
		else if(back){
			if(placeholder == 0) {return;}
			else {
				text = text.replace(".","");
				if(placeholder == 1) {
					text = "-.--";
				}
				else if(placeholder == 3 || placeholder == 4) {
					text = text.substring(0,text.length()-1);
					text = "-."+text;
				}
				else {
					text = text.substring(0,text.length()-1);
					text = addChar(text,'.',text.length()-2);
				}
			}
			if(placeholder == 3) {placeholder = 1;}
			else {placeholder--;}
		}
		textField.setText(text);
	}
	
	public void pay_cash() {
		Float change = currOrder.pay_cash(Float.valueOf(textField.getText().replace("-","0")));
		change = Float.valueOf(number.format(change));
		if(change != null) {
			myData.pay_cash(Float.valueOf(textField.getText().replace("-","0")), currOrder.return_id(), change); // SQL
			textField_1.setText("0.00");
			textField_2.setText(number.format((change)));
			toggle_pay_cash(false);
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setValueAt(true,orders.indexOf(currOrder),4);
			model.setValueAt(String.valueOf(currOrder.return_type()),orders.indexOf(currOrder),5);
		}
		else {
			JOptionPane.showMessageDialog(null, "Invalid Amount","ERROR",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	void pay_card() {
		myData.pay_card(currOrder.return_id());
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		model.setValueAt(true,orders.indexOf(currOrder),4);
		model.setValueAt(String.valueOf(currOrder.return_type()),orders.indexOf(currOrder),5);
	}
	
	void toggle_pay_cash(boolean mode) {
		btn0.setEnabled(mode);
		btn1.setEnabled(mode);
		btn2.setEnabled(mode);
		btn3.setEnabled(mode);
		btn4.setEnabled(mode);
		btn5.setEnabled(mode);
		btn6.setEnabled(mode);
		btn7.setEnabled(mode);
		btn8.setEnabled(mode);
		btn9.setEnabled(mode);
		btnC.setEnabled(mode);
		btnD.setEnabled(mode);
		btnPay.setEnabled(mode);
	}
	void toggle_pay_card(boolean mode) {
		btnPayCard.setEnabled(mode);
	}
	
	void revalidate_orders() {
		if(myData.redraw_orders(orders)) {	// retrieving any existing order information from the database
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.getDataVector().removeAllElements();
			model.fireTableDataChanged();
			for(int i = 0; i < orders.size(); i++) {
				orders.get(i).add_order((DefaultTableModel) table_1.getModel());
			}
		}
	}
	
}
