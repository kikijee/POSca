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
import java.awt.Font;

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
	private JPanel panelPay;
	private JPanel panelCash;
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
	private int placeholder = 0;
	private JTable table_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel prev_panel;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
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
					Order orderTemp = add_order();
					clear_table(table,subtotalField,totalField,numItemField);
					orderTemp.draw_orders((DefaultTableModel) table_3.getModel(),textField_3,textField_4,textField_5);
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
				clear_table(table_2,subtotalOrdersField,totalOrdersField,itemsOrdersField);
				table_1.getSelectionModel().clearSelection();
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
					Order edit = orders.get(table_1.getSelectedRow());
					clear_table(table_2,subtotalOrdersField,totalOrdersField,itemsOrdersField);
					table_1.getSelectionModel().clearSelection();
					row = -1;
					
					edit.draw_orders((DefaultTableModel) table_3.getModel(), textField_3, textField_4, textField_5);
					
					switchMainPanel(panelPay);
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
						clear_table(table_2,subtotalOrdersField,totalOrdersField,itemsOrdersField);
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
		
		panelPay = new JPanel();
		layeredMainPane.add(panelPay, "name_29246515247100");
		panelPay.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(600, 11, 354, 467);
		panelPay.add(scrollPane_1);
		
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
		
		JLayeredPane layeredPanePay = new JLayeredPane();
		layeredPanePay.setBounds(10, 11, 578, 580);
		panelPay.add(layeredPanePay);
		layeredPanePay.setLayout(new CardLayout(0, 0));
		
		JPanel panelPayOption = new JPanel();
		layeredPanePay.add(panelPayOption, "name_29905756660300");
		panelPayOption.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("CASH");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch_panel(layeredPanePay,panelCash);
			}
		});
		btnNewButton_3.setBounds(105, 192, 89, 89);
		panelPayOption.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("CARD");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3_1.setBounds(389, 192, 89, 89);
		panelPayOption.add(btnNewButton_3_1);
		
		JButton btnNewButton_4 = new JButton("back");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear_table(table_3,textField_3,textField_4,textField_5);
				switchMainPanel(prev_panel);
			}
		});
		btnNewButton_4.setBounds(10, 546, 89, 23);
		panelPayOption.add(btnNewButton_4);
		
		panelCash = new JPanel();
		layeredPanePay.add(panelCash, "name_30349975776200");
		panelCash.setLayout(null);
		
		JButton btnNewButton_5 = new JButton("1");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(1,false,false);
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5.setBounds(27, 128, 89, 89);
		panelCash.add(btnNewButton_5);
		
		JButton btnNewButton_5_1 = new JButton("2");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(2,false,false);
			}
		});
		btnNewButton_5_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1.setBounds(126, 128, 89, 89);
		panelCash.add(btnNewButton_5_1);
		
		JButton btnNewButton_5_1_1 = new JButton("3");
		btnNewButton_5_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(3,false,false);
			}
		});
		btnNewButton_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1.setBounds(225, 128, 89, 89);
		panelCash.add(btnNewButton_5_1_1);
		
		JButton btnNewButton_5_1_1_1 = new JButton("4");
		btnNewButton_5_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(4,false,false);
			}
		});
		btnNewButton_5_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1.setBounds(27, 228, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1);
		
		JButton btnNewButton_5_1_1_1_1 = new JButton("5");
		btnNewButton_5_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(5,false,false);
			}
		});
		btnNewButton_5_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1.setBounds(126, 228, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1);
		
		JButton btnNewButton_5_1_1_1_1_1 = new JButton("6");
		btnNewButton_5_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(6,false,false);
			}
		});
		btnNewButton_5_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1_1.setBounds(225, 228, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1_1);
		
		JButton btnNewButton_5_1_1_1_1_1_1 = new JButton("8");
		btnNewButton_5_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(8,false,false);
			}
		});
		btnNewButton_5_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1_1_1.setBounds(126, 328, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1_1_1);
		
		JButton btnNewButton_5_1_1_1_1_1_1_1 = new JButton("7");
		btnNewButton_5_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(7,false,false);
			}
		});
		btnNewButton_5_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1_1_1_1.setBounds(27, 328, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1_1_1_1);
		
		JButton btnNewButton_5_1_1_1_1_1_1_1_1 = new JButton("9");
		btnNewButton_5_1_1_1_1_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(9,false,false);
			}
		});
		btnNewButton_5_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1_1_1_1_1.setBounds(225, 328, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1_1_1_1_1);
		
		JButton btnNewButton_5_1_1_1_1_1_1_2 = new JButton("0");
		btnNewButton_5_1_1_1_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(0,false,false);
			}
		});
		btnNewButton_5_1_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1_1_1_2.setBounds(126, 428, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1_1_1_2);
		
		JButton btnNewButton_5_1_1_1_1_1_1_2_1 = new JButton("C");
		btnNewButton_5_1_1_1_1_1_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(-1,true,false);
			}
		});
		btnNewButton_5_1_1_1_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1_1_1_2_1.setBounds(27, 428, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1_1_1_2_1);
		
		JButton btnNewButton_5_1_1_1_1_1_1_2_1_1 = new JButton("DEL");
		btnNewButton_5_1_1_1_1_1_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				num_input(-1,false,true);
			}
		});
		btnNewButton_5_1_1_1_1_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton_5_1_1_1_1_1_1_2_1_1.setBounds(225, 428, 89, 89);
		panelCash.add(btnNewButton_5_1_1_1_1_1_1_2_1_1);
		
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
				switch_panel(layeredPanePay,panelPayOption);
			}
		});
		btnNewButton_6.setBounds(10, 546, 89, 23);
		panelCash.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Pay");
		btnNewButton_7.setBounds(479, 546, 89, 23);
		panelCash.add(btnNewButton_7);
		
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
		
		JPanel panelCard = new JPanel();
		layeredPanePay.add(panelCard, "name_30377410911500");
		panelCard.setLayout(null);
		
		JButton btnNewButton_8 = new JButton("Back");
		btnNewButton_8.setBounds(10, 546, 89, 23);
		panelCard.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Pay");
		btnNewButton_9.setBounds(479, 546, 89, 23);
		panelCard.add(btnNewButton_9);
		
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
	
	public Order add_order() { // will add order to orders ArrayList
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Calendar date = Calendar.getInstance();
		String time = String.valueOf(date.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(date.get(Calendar.MINUTE));
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
			orders.add(orderTemp);
			add_to_order_table();
			return orderTemp;
		}
		clear_table(table,subtotalField,totalField,numItemField);
		return null;
	}
	
	public void print_orders() {
		for(int i = 0; i < orders.size(); i++) {
			orders.get(i).print_order();
		}
	}
	
	public void clear_table(JTable table, JTextField sub, JTextField tot, JTextField it) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		sub.setText("0.00");
		tot.setText("0.00");
		it.setText("0");
	}
	
	public String addChar(String str, char ch, int position) {
	    return str.substring(0, position) + ch + str.substring(position);
	}
	
	public String replaceChar(String str, char ch, int index) {
		str = str.substring(0, index) + ch
	              + str.substring(index + 1);
		return str;
	}
	
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
				//text = replaceChar(text,(char)(num+'0'),(text.length()-1)-placeholder);
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
}
