package VIEW;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Label;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField JTFUser;
	private JPasswordField JPFSenha;

	public Login() {
		setResizable(false);
		setTitle("Sistema Gerenciador OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(607, 418);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLIcon = new JLabel("");
		JLIcon.setBounds(0, 76, 281, 239);
		JLIcon.setIcon(new ImageIcon(Login.class.getResource("/VIEW/Images/pctech.png")));
		contentPane.add(JLIcon);
		
		JLabel JLUser = new JLabel("Usu\u00E1rio :");
		JLUser.setForeground(Color.WHITE);
		JLUser.setBounds(400, 76, 86, 33);
		JLUser.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLUser);
		
		JTFUser = new JTextField();
		JTFUser.setBounds(365, 120, 191, 27);
		contentPane.add(JTFUser);
		JTFUser.setColumns(10);
		
		JLabel JLSenha = new JLabel("Senha :");
		JLSenha.setForeground(Color.WHITE);
		JLSenha.setBounds(400, 158, 80, 27);
		JLSenha.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLSenha);
		
		JButton JBLogar = new JButton("Autenticar");
		JBLogar.setBounds(400, 279, 110, 33);
		JBLogar.setForeground(Color.WHITE);
		JBLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Menu tela = new Menu();
				tela.setVisible(true);
				Login.this.dispose();
			
			}
		});
		JBLogar.setBackground(new Color(51, 102, 204));
		JBLogar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(JBLogar);
		
		JLabel lblNewLabel = new JLabel("Bem Vindo ao Gerenciador de Ordem de Servi\u00E7o OS");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(98, 11, 388, 33);
		lblNewLabel.setFont(new Font("Arial", Font.ITALIC, 15));
		contentPane.add(lblNewLabel);
		
		JPFSenha = new JPasswordField();
		JPFSenha.setBounds(365, 196, 191, 27);
		contentPane.add(JPFSenha);
		
		JLabel JLBG = new JLabel("");
		JLBG.setIcon(new ImageIcon(Login.class.getResource("/VIEW/Images/Desktop-Clean-Images.jpg")));
		JLBG.setBounds(0, 0, 601, 390);
		contentPane.add(JLBG);
	}
}
