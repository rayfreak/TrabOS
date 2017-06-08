package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Controller.DAO.Conexao;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Panel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.InputMethodListener;
import java.io.IOException;
import java.awt.event.InputMethodEvent;

public class CadastrarCliente extends JFrame {

	private JPanel contentPane;
	private JTextField JTFNome;
	private JTextField JTFTelefone;
	private JTextField JTFCep;
	private JTextField JFTFCpf;
	private JTextField JTFEmail;
	private JTextField JFTFTelefone2;
	
	public CadastrarCliente() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarCliente.class.getResource("/VIEW/Images/add.png")));
		setTitle("Sistema Gerenciador OS - Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
		
		MaskFormatter MFcep = null;;
		MaskFormatter MFtelefone = null;
		MaskFormatter MFcpf = null;
		try{
		MFcep = new MaskFormatter("#####-###");
		MFtelefone = new MaskFormatter("(##)#####-####"); 
		MFcpf = new MaskFormatter("###.###.###-##");
		MFcep.setValidCharacters("0123456789");
		MFtelefone.setValidCharacters("0123456789");
		MFcpf.setValidCharacters("0123456789");
		}catch(Exception e){
			e.printStackTrace();
		}
		contentPane.setLayout(null);
		
		JLabel JLTitle = new JLabel("Cadastro de Clientes");
		JLTitle.setBounds(255, 22, 231, 40);
		JLTitle.setForeground(Color.WHITE);
		JLTitle.setFont(new Font("Arial", Font.PLAIN, 19));
		contentPane.add(JLTitle);

		JLabel JLNome = new JLabel("*Nome Completo :");
		JLNome.setBounds(27, 86, 163, 27);
		JLNome.setForeground(UIManager.getColor("Button.highlight"));
		JLNome.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLNome);

		JTFNome = new JTextField();
		JTFNome.setBounds(27, 120, 277, 40);
		JTFNome.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFNome);

		JLabel JLCpf = new JLabel("*CPF :");
		JLCpf.setBounds(27, 265, 110, 27);
		JLCpf.setForeground(UIManager.getColor("Button.highlight"));
		JLCpf.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLCpf);
		
		JFTFCpf = new JFormattedTextField(MFcpf);
		JFTFCpf.setBounds(27, 303, 277, 40);
		JFTFCpf.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JFTFCpf);

		JLabel JLTelefone = new JLabel("*Telefone :");
		JLTelefone.setBounds(378, 171, 105, 27);
		JLTelefone.setForeground(UIManager.getColor("Button.highlight"));
		JLTelefone.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLTelefone);

		JTFTelefone = new JFormattedTextField(MFtelefone);
		JTFTelefone.setBounds(378, 209, 277, 40);
		JTFTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFTelefone);
		JTFTelefone.setColumns(10);

		JLabel JLCep = new JLabel("*CEP : ");
		JLCep.setBounds(378, 89, 115, 20);
		JLCep.setForeground(UIManager.getColor("Button.highlight"));
		JLCep.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLCep);

		JTFCep = new JFormattedTextField(MFcep);
		JTFCep.setBounds(378, 120, 277, 40);
		JTFCep.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFCep);
		JTFCep.setColumns(10);

		JButton JBCadastrar = new JButton("Cadastrar");
		JBCadastrar.setBounds(283, 433, 131, 45);
		JBCadastrar.setForeground(Color.WHITE);
		
		JBCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cpf="",nome=null,telefone="", email="",telefone2="",cep="", endereco ="";
				cpf = JFTFCpf.getText();
				nome = JTFNome.getText();
				email = JTFEmail.getText();
				telefone = JTFTelefone.getText();
				telefone2 = JFTFTelefone2.getText();
				cep = JTFCep.getText();
				if(!nome.equals("") && !cpf.equals("   .   .   -  ") && !cep.equals("     -   ") && !telefone.equals("(  )     -    ")){
					Conexao conexao = new Conexao();
					Connection con = conexao.conectar();
					Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
				    cliente.inserirCliente(cpf, nome, telefone, cep, email, telefone2, endereco);
				    conexao.desconectar(con);
				}else{
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigat\u00f3rios");
				}
			}
		});
		JBCadastrar.setBackground(new Color(51, 102, 153));
		JBCadastrar.setFont(new Font("Arial", Font.PLAIN, 14));
		JBCadastrar.setIcon(new ImageIcon(CadastrarCliente.class.getResource("/VIEW/Images/add.png")));
		contentPane.add(JBCadastrar);
		
		JLabel JLEmail = new JLabel("Email :");
		JLEmail.setBounds(27, 171, 110, 27);
		JLEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		JLEmail.setForeground(Color.WHITE);
		contentPane.add(JLEmail);
		
		JTFEmail = new JTextField();
		JTFEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		JTFEmail.setBounds(27, 209, 277, 45);
		contentPane.add(JTFEmail);
		JTFEmail.setColumns(10);
		
		JLabel JLTelefone2 = new JLabel("Telefone (outro) :");
		JLTelefone2.setBounds(378, 268, 140, 20);
		JLTelefone2.setForeground(Color.WHITE);
		JLTelefone2.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLTelefone2);
		
		JFTFTelefone2 = new JFormattedTextField(MFtelefone);
		JFTFTelefone2.setFont(new Font("Arial", Font.PLAIN, 14));
		JFTFTelefone2.setBounds(378, 301, 277, 45);
		contentPane.add(JFTFTelefone2);
		
		JLabel JLCO = new JLabel("*Campos obrigat\u00f3rios");
		JLCO.setForeground(Color.WHITE);
		JLCO.setBounds(267, 387, 182, 20);
		contentPane.add(JLCO);
		
		JLabel JLBG = new JLabel("");
		JLBG.setBounds(0, 0, 684, 511);
		JLBG.setIcon(new ImageIcon(CadastrarCliente.class.getResource("/VIEW/Images/degrade3.jpg")));
		contentPane.add(JLBG);
	}
}
