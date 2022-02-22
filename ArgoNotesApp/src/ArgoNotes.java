import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArgoNotes extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArgoNotes frame = new ArgoNotes();
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
	public ArgoNotes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 401);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ArgoNotes");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setBounds(141, 32, 165, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(46, 108, 91, 28);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(46, 192, 91, 22);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(162, 106, 165, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(162, 187, 165, 36);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Log-in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(165, 262, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign-up");
		btnNewButton_1.setBounds(165, 316, 117, 29);
		contentPane.add(btnNewButton_1);
	}
}

