import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLayeredPane layeredMainPane;
	private JPanel panelLogin;
	private JPanel panelMenu;
	private IDandPassword logObj;
	private Items itemsObj;
	private JTextField subtotalField;
	private JTextField totalField;
	private JTextField textField;
	private JTextField orderNumField;
	private JPanel panelMakeOrder;
	private JLayeredPane layeredItemPane;
	private JPanel panelDrink;
	private JPanel panelAppetizer;
	private JPanel panelSushi;
	private JPanel panelRoll;
	private JPanel panelEntree;
	private JPanel panelDessert;

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
		setVisible(true);
		// setting window and contentPane settings 
		logObj = new IDandPassword();
		itemsObj = new Items();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setResizable(false);
		
		layeredMainPane = new JLayeredPane();
		layeredMainPane.setBounds(10, 11, 964, 739);
		contentPane.add(layeredMainPane);
		layeredMainPane.setLayout(new CardLayout(0, 0));
		
		panelLogin = new JPanel();
		layeredMainPane.add(panelLogin, "name_226065968568000");
		panelLogin.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setText("");
		usernameField.setBounds(370, 289, 164, 20);
		panelLogin.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(370, 264, 164, 14);
		panelLogin.add(usernameLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(370, 398, 164, 20);
		panelLogin.add(passwordField);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(370, 373, 164, 14);
		panelLogin.add(passwordLabel);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setBounds(10, 0, 77, 35);
		panelLogin.add(loginLabel);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(logObj.validate(usernameField.getText(),new String(passwordField.getPassword()))) {
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
		loginButton.setBounds(445, 494, 89, 23);
		panelLogin.add(loginButton);
		
		panelMenu = new JPanel();
		layeredMainPane.add(panelMenu, "name_226113370985000");
		panelMenu.setLayout(null);
		
		JButton viewOrderButton = new JButton("View Order");
		viewOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewOrderButton.setBounds(286, 314, 89, 80);
		panelMenu.add(viewOrderButton);
		
		JButton makeOrderButton = new JButton("Make Order");
		makeOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMainPanel(panelMakeOrder);
				switchItemPanel(panelDrink);
			}
		});
		
		makeOrderButton.setBounds(567, 314, 89, 80);
		panelMenu.add(makeOrderButton);
		
		JButton logoutButton = new JButton("LOGOUT");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMainPanel(panelLogin);
			}
		});
		logoutButton.setBounds(10, 705, 89, 23);
		panelMenu.add(logoutButton);
		
		panelMakeOrder = new JPanel();
		layeredMainPane.add(panelMakeOrder, "name_247097960798500");
		panelMakeOrder.setLayout(null);
		
		subtotalField = new JTextField();
		subtotalField.setEditable(false);
		subtotalField.setBounds(817, 517, 137, 20);
		panelMakeOrder.add(subtotalField);
		subtotalField.setColumns(10);
		
		JLabel subtotalLabel = new JLabel("Sub-Total:");
		subtotalLabel.setBounds(728, 520, 79, 14);
		panelMakeOrder.add(subtotalLabel);
		
		totalField = new JTextField();
		totalField.setEditable(false);
		totalField.setBounds(817, 548, 137, 20);
		panelMakeOrder.add(totalField);
		totalField.setColumns(10);
		
		JLabel totalLabel = new JLabel("Total:");
		totalLabel.setBounds(728, 551, 46, 14);
		panelMakeOrder.add(totalLabel);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(817, 579, 86, 20);
		panelMakeOrder.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("# items:");
		lblNewLabel.setBounds(728, 582, 46, 14);
		panelMakeOrder.add(lblNewLabel);
		
		JTextArea orderArea = new JTextArea();
		orderArea.setEditable(false);
		orderArea.setBounds(728, 11, 226, 495);
		panelMakeOrder.add(orderArea);
		
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
			}
		});
		
		sendButton.setBounds(728, 705, 89, 23);
		panelMakeOrder.add(sendButton);
		
		JButton sendPayButton = new JButton("Send & Pay");
		sendPayButton.setBounds(865, 705, 89, 23);
		panelMakeOrder.add(sendPayButton);
		
		JButton voidButton = new JButton("Void");
		voidButton.setBounds(728, 671, 89, 23);
		panelMakeOrder.add(voidButton);
		
		JButton orderBackButton = new JButton("Back");
		orderBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchMainPanel(panelMenu);
			}
		});
		orderBackButton.setBounds(10, 705, 89, 23);
		panelMakeOrder.add(orderBackButton);
		// END ORDER PANEL BUTTONS
		
		// START FOOD CATEGORY BUTTONS
		JButton drinkButton = new JButton("Drinks");
		drinkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelDrink);
			}
		});
		drinkButton.setBounds(10, 12, 89, 23);
		panelMakeOrder.add(drinkButton);
		
		JButton appetizerButton = new JButton("Appetizers");
		appetizerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelAppetizer);
			}
		});
		appetizerButton.setBounds(10, 46, 89, 23);
		panelMakeOrder.add(appetizerButton);
		
		JButton sushiButton = new JButton("Sushi");
		sushiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelSushi);
			}
		});
		sushiButton.setBounds(10, 80, 89, 23);
		panelMakeOrder.add(sushiButton);
		
		JButton rollButton = new JButton("Rolls");
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelRoll);
			}
		});
		rollButton.setBounds(10, 114, 89, 23);
		panelMakeOrder.add(rollButton);
		
		JButton entreeButton = new JButton("Entree's");
		entreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelEntree);
			}
		});
		entreeButton.setBounds(10, 148, 89, 23);
		panelMakeOrder.add(entreeButton);
		
		JButton dessertButton = new JButton("Dessert");
		dessertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchItemPanel(panelDessert);
			}
		});
		dessertButton.setBounds(10, 182, 89, 23);
		panelMakeOrder.add(dessertButton);
		//END FOOD CATEGORY BUTTONS
		
		layeredItemPane = new JLayeredPane();
		layeredItemPane.setBounds(117, 11, 582, 672);
		panelMakeOrder.add(layeredItemPane);
		layeredItemPane.setLayout(new CardLayout(0, 0));
		
		panelDrink = new JPanel();
		layeredItemPane.add(panelDrink, "name_255391516279300");
		panelDrink.setLayout(null);
		
		JButton asahiButton = new JButton("");
		asahiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(itemsObj.return_price(e.getSource()));
			}
		});
		asahiButton.setBounds(10, 11, 100, 100);
		panelDrink.add(asahiButton);
		itemsObj.add_item(asahiButton,4.99F);
		
		
		JLabel lblNewLabel_2 = new JLabel("Asahi");
		lblNewLabel_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 122, 100, 14);
		panelDrink.add(lblNewLabel_2);
		
		panelAppetizer = new JPanel();
		layeredItemPane.add(panelAppetizer, "name_255397136104900");
		panelAppetizer.setLayout(null);
		
		JButton seafoodSpringRollsButton = new JButton("");
		seafoodSpringRollsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(itemsObj.return_price(e.getSource()));
			}
		});
		seafoodSpringRollsButton.setBounds(10, 11, 100, 100);
		panelAppetizer.add(seafoodSpringRollsButton);
		itemsObj.add_item(seafoodSpringRollsButton,5.99F);
		
		JLabel lblNewLabel_1 = new JLabel("Seafood Spring Rolls");
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 122, 100, 14);
		panelAppetizer.add(lblNewLabel_1);
		
		
		panelSushi = new JPanel();
		layeredItemPane.add(panelSushi, "name_255400090207500");
		panelSushi.setLayout(null);
		
		JButton salmonSushiButton = new JButton("");
		salmonSushiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(itemsObj.return_price(e.getSource()));
			}
		});
		salmonSushiButton.setBounds(10, 11, 100, 100);
		panelSushi.add(salmonSushiButton);
		itemsObj.add_item(salmonSushiButton,6.99F);
		
		JLabel lblNewLabel_3 = new JLabel("Salmon Sushi");
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 122, 100, 14);
		panelSushi.add(lblNewLabel_3);
		
		panelRoll = new JPanel();
		layeredItemPane.add(panelRoll, "name_255403077667200");
		panelRoll.setLayout(null);
		
		JButton blueOceanButton = new JButton("");
		blueOceanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(itemsObj.return_price(e.getSource()));
			}
		});
		blueOceanButton.setBounds(10, 11, 100, 100);
		panelRoll.add(blueOceanButton);
		itemsObj.add_item(blueOceanButton,19.99F);
		
		JLabel lblNewLabel_4 = new JLabel("Blue Ocean Roll");
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 122, 100, 14);
		panelRoll.add(lblNewLabel_4);
		
		panelEntree = new JPanel();
		layeredItemPane.add(panelEntree, "name_255407088247600");
		panelEntree.setLayout(null);
		
		JButton chickenKatsuButton = new JButton("");
		chickenKatsuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(itemsObj.return_price(e.getSource()));
			}
		});
		chickenKatsuButton.setBounds(10, 11, 100, 100);
		panelEntree.add(chickenKatsuButton);
		itemsObj.add_item(chickenKatsuButton,21.99F);
		
		JLabel lblNewLabel_5 = new JLabel("<html>Chicken Katsu<br/>Entree</html>");
		lblNewLabel_5.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 122, 100, 28);
		panelEntree.add(lblNewLabel_5);
		
		panelDessert = new JPanel();
		layeredItemPane.add(panelDessert, "name_255414797153500");
		panelDessert.setLayout(null);
		
		JButton carmelAppleButton = new JButton("");
		carmelAppleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(itemsObj.return_price(e.getSource()));
			}
		});
		carmelAppleButton.setBounds(10, 11, 100, 100);
		panelDessert.add(carmelAppleButton);
		itemsObj.add_item(carmelAppleButton,4.99F);
		
		JLabel lblNewLabel_6 = new JLabel("Carmel Apple");
		lblNewLabel_6.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 122, 100, 14);
		panelDessert.add(lblNewLabel_6);
		
		
		
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
		layeredMainPane.repaint();
		layeredItemPane.revalidate();
	}
}
