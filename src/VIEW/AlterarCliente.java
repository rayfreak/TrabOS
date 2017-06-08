package VIEW;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Controller.DAO.Conexao;

public class AlterarCliente extends JFrame {
	
	private JPanel contentPane;
	
	public AlterarCliente() {
		Model.Cliente mcliente = new Model.Cliente();
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlterarCliente.class.getResource("/VIEW/Images/edit.png")));
		setTitle("Sistema Gerenciador OS - Altera\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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

		 JTextField JTFNome = new JTextField(mcliente.getNome());
		JTFNome.setBounds(27, 120, 277, 40);
		JTFNome.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFNome);
		JTFNome.setColumns(10);

		JLabel JLCpf = new JLabel("*CPF :");
		JLCpf.setBounds(27, 265, 110, 27);
		JLCpf.setForeground(UIManager.getColor("Button.highlight"));
		JLCpf.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLCpf);
		
		JFormattedTextField JTFCpf = new JFormattedTextField(MFcpf);
		JTFCpf.setText(mcliente.getCpf());
		JTFCpf.setBounds(27, 303, 277, 40);
		JTFCpf.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFCpf);

		JLabel JLTelefone = new JLabel("*Telefone :");
		JLTelefone.setBounds(378, 171, 105, 27);
		JLTelefone.setForeground(UIManager.getColor("Button.highlight"));
		JLTelefone.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLTelefone);

		JTextField JTFTelefone = new JFormattedTextField(MFtelefone);
		JTFTelefone.setText(mcliente.getTelefone()); 
		JTFTelefone.setBounds(378, 209, 277, 40);
		JTFTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFTelefone);
		JTFTelefone.setColumns(10);

		JLabel JLCep = new JLabel("*CEP : ");
		JLCep.setBounds(378, 89, 115, 20);
		JLCep.setForeground(UIManager.getColor("Button.highlight"));
		JLCep.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLCep);

		JTextField JTFCep = new JFormattedTextField(MFcep);
		JTFCep.setText(mcliente.getCep()); 
		JTFCep.setBounds(378, 120, 277, 40);
		JTFCep.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFCep);
		JTFCep.setColumns(10);
		
		JTextField JTFEmail = new JTextField(mcliente.getEmail());
		JTFEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		JTFEmail.setBounds(27, 209, 277, 45);
		contentPane.add(JTFEmail);
		
		JTextField JTFTelefone2 = new JFormattedTextField(MFtelefone);
		JTFTelefone2.setText(mcliente.getTelefone2());
		JTFTelefone2.setFont(new Font("Arial", Font.PLAIN, 14));
		JTFTelefone2.setBounds(378, 301, 277, 45);
		contentPane.add(JTFTelefone2);
		

		JButton JBAlterar = new JButton("Alterar");
		JBAlterar.setForeground(Color.WHITE);
		JBAlterar.setSelectedIcon(new ImageIcon(AlterarCliente.class.getResource("/VIEW/Images/edit.png")));
		JBAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cpf,nome,telefone,cep, telefone2 = null, email = null, endereco = null;
				int id = mcliente.getId();
				cpf = JTFCpf.getText();
				nome = JTFNome.getText();
				telefone = JTFTelefone.getText();
				cep = JTFCep.getText();
                telefone2 = JTFTelefone2.getText();
                email = JTFEmail.getText();
                if(cpf =="" || nome == "" || telefone =="" || cep == ""){
					JOptionPane.showMessageDialog(null, "Preencha os campos obrigatrios!");
				}else{
					Conexao conexao = new Conexao();
					Connection con = conexao.conectar();
					Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
				    cliente.editarCliente(id, cpf, nome, telefone,cep, email, telefone2, endereco);
				    conexao.desconectar(con);
				}
			}
		});
		JBAlterar.setBackground(new Color(51, 102, 153));
		JBAlterar.setFont(new Font("Arial", Font.PLAIN, 13));
		JBAlterar.setIcon(new ImageIcon(AlterarCliente.class.getResource("/VIEW/Images/edit.png")));
		JBAlterar.setBounds(285, 400, 132, 45);
		contentPane.add(JBAlterar);

		JLabel JLEmail = new JLabel("Email :");
		JLEmail.setBounds(27, 171, 110, 27);
		JLEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		JLEmail.setForeground(Color.WHITE);
		contentPane.add(JLEmail);
		
		JLabel JLTelefone2 = new JLabel("Telefone (outro) :");
		JLTelefone2.setBounds(378, 268, 140, 20);
		JLTelefone2.setForeground(Color.WHITE);
		JLTelefone2.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLTelefone2);
		
		JLabel JLBG = new JLabel("");
		JLBG.setIcon(new ImageIcon(AlterarCliente.class.getResource("/VIEW/Images/degrade3.jpg")));
		JLBG.setBounds(0, 0, 684, 511);
		contentPane.add(JLBG);
	}
}
