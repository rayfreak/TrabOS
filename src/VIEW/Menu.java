package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JDesktopPane;

public class Menu extends JFrame {

	private JPanel contentPane;

	public Menu() {
		setTitle("Sistema Gerenciador OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 700);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu JMCadastrar = new JMenu("Cadastrar");
		JMCadastrar.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/add.png")));
		menuBar.add(JMCadastrar);
		
		JMenuItem JMICClientes = new JMenuItem("Cadastrar Clientes");
		JMICClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastrarCliente tela = new CadastrarCliente();
				tela.setVisible(true);
			}
		});
		JMICClientes.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/add.png")));
		JMCadastrar.add(JMICClientes);
		
		JMenuItem JMICOS = new JMenuItem("Cadastrar OS");
		JMICOS.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/add.png")));
		JMICOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarClientes tela = new ConsultarClientes();
				tela.setVisible(true);			}
		});
		JMCadastrar.add(JMICOS);
		
		JMenu JMConsultas = new JMenu("Consultas");
		JMConsultas.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/book.png")));
		menuBar.add(JMConsultas);
		
		JMenuItem JMIVClientes = new JMenuItem("Consultar Clientes");
		JMIVClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarClientes tela = new ConsultarClientes();
				tela.setVisible(true);
			}
		});
		JMIVClientes.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/book.png")));
		JMConsultas.add(JMIVClientes);
		
		JMenuItem JMIVOS = new JMenuItem("Consultar OS");
		JMIVOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarOS os = new ConsultarOS();
				os.setVisible(true);
			}
		});
		JMIVOS.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/book.png")));
		JMConsultas.add(JMIVOS);
		
		JMenu JMAjuda = new JMenu("Ajuda");
		JMAjuda.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/help.png")));
		menuBar.add(JMAjuda);
		
		JMenuItem JMIHelp = new JMenuItem("Ajuda");
		JMIHelp.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/help.png")));
		JMAjuda.add(JMIHelp);
		
		JMenuItem JMITutorial = new JMenuItem("Tutorial");
		JMITutorial.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/help.png")));
		JMAjuda.add(JMITutorial);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Panel JPPainel = new Panel();
		JPPainel.setBackground(new Color(53, 76, 113));
		JPPainel.setBounds(0, 0, 884, 640);
		contentPane.add(JPPainel);
		JPPainel.setLayout(null);
		
		JLabel JLIcon = new JLabel("");
		JLIcon.setIcon(new ImageIcon(Menu.class.getResource("/VIEW/Images/cloud.png")));
		JLIcon.setBounds(326, 101, 221, 301);
		JPPainel.add(JLIcon);
		
		JLabel JLWelcome = new JLabel("Bem vindo ao Sistema Gerenciador de Ordem de Servi\u00E7o OS !");
		JLWelcome.setForeground(Color.WHITE);
		JLWelcome.setFont(new Font("Arial Black", Font.PLAIN, 17));
		JLWelcome.setBounds(146, 37, 622, 36);
		JPPainel.add(JLWelcome);
	}
}
