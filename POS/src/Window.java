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

public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLayeredPane layeredMainPane;
	private JPanel panelLogin;
	private JPanel panelMenu;
	private IDandPassword logObj;
	private JTextField subtotalField;
	private JTextField totalField;
	private JTextField textField;
	private JTextField orderNumField;

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
		logObj = new IDandPassword();
		
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
		
		JPanel panelMakeOrder = new JPanel();
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
		
	}
	
	public void switchMainPanel(JPanel panel){
        layeredMainPane.removeAll();
        layeredMainPane.add(panel);
        layeredMainPane.repaint();
        layeredMainPane.revalidate();
    }
}
