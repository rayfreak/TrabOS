package VIEW;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AlterarOS extends JFrame {

	private JPanel contentPane;
	private JTextField JTFERecebido;
	private JTextField JTFProblema;
	private JTextField JTFMarca;
	private JTextField JTFModelo;
	private JTextArea JTAIA ;
	private JTextField JTFNdeserie;
	
	public AlterarOS() {
		Model.OrdemDeServico os = new Model.OrdemDeServico();
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarOS.class.getResource("/VIEW/Images/page_save.png")));
		setTitle("Sistema Gerenciador OS - Altera\u00E7\u00e3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLTitle = new JLabel("Alterar Ordem de Servi\u00E7o :");
		JLTitle.setBounds(205, 24, 328, 28);
		JLTitle.setForeground(Color.WHITE);
		JLTitle.setFont(new Font("Arial", Font.BOLD, 17));
		contentPane.add(JLTitle);
		
		JLabel JLERecebido = new JLabel("*Equipamento Recebido :");
		JLERecebido.setBounds(30, 70, 222, 28);
		JLERecebido.setForeground(Color.WHITE);
		JLERecebido.setFont(new Font("Arial", Font.PLAIN, 16));
		contentPane.add(JLERecebido);
		
		JTFERecebido = new JTextField(os.getEquipamento());
		JTFERecebido.setBounds(30, 109, 253, 45);
		JTFERecebido.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFERecebido);
		JTFERecebido.setColumns(10);
		
		JLabel JLProblema = new JLabel("Problema encontrado no Equipamento :");
		JLProblema.setBounds(356, 66, 328, 36);
		JLProblema.setFont(new Font("Arial", Font.PLAIN, 16));
		JLProblema.setForeground(Color.WHITE);
		contentPane.add(JLProblema);
		
		JTFProblema = new JTextField(os.getProblemadescrito());
		JTFProblema.setBounds(356, 109, 253, 45);
		JTFProblema.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFProblema);
		JTFProblema.setColumns(10);
		
		JButton JBSave = new JButton("Salvar");
		JBSave.setBounds(440, 434, 169, 45);
		JBSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String marca = JTFMarca.getText();
				String modelo = JTFModelo.getText();
				String informacao = JTAIA.getText();
				String equipamento = JTFERecebido.getText();
				String problema = JTFProblema.getText();
				String ndeserie = JTFNdeserie.getText();
				if(!equipamento.equals("") && !marca.equals("") && !modelo.equals("") && !ndeserie.equals("")){
				Controller.DAO.Conexao conexao = new Controller.DAO.Conexao();
            	Connection con = conexao.conectar();
				Controller.DAO.OSDAO os = new Controller.DAO.OSDAO(con);
				Model.OrdemDeServico mos = new Model.OrdemDeServico();
				os.editarOS(mos.getId(),equipamento, marca, modelo, problema, informacao, ndeserie);
				conexao.desconectar(con);
			    }else{
			    	JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigat\u00f3rios");
			    }
			}
		});
		JBSave.setIcon(new ImageIcon(CadastrarOS.class.getResource("/VIEW/Images/page_save.png")));
		JBSave.setBackground(new Color(51, 102, 153));
		JBSave.setForeground(Color.WHITE);
		JBSave.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JBSave);
		
		JLabel JLMarca = new JLabel("*Marca : ");
		JLMarca.setBounds(30, 165, 169, 28);
		JLMarca.setFont(new Font("Arial", Font.PLAIN, 16));
		JLMarca.setForeground(Color.WHITE);
		contentPane.add(JLMarca);
		
		JTFMarca = new JTextField(os.getMarca());
		JTFMarca.setBounds(27, 204, 256, 45);
		JTFMarca.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFMarca);
		JTFMarca.setColumns(10);
		
		JLabel JLModel = new JLabel("*Modelo :");
		JLModel.setBounds(26, 266, 90, 28);
		JLModel.setFont(new Font("Arial", Font.PLAIN, 16));
		JLModel.setForeground(Color.WHITE);
		contentPane.add(JLModel);
		
		JTFModelo = new JTextField(os.getModelo());
		JTFModelo.setBounds(30, 306, 253, 45);
		JTFModelo.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(JTFModelo);
		JTFModelo.setColumns(10);
		
		JLabel JLIA = new JLabel("Informa\u00E7\u00F5es Adicionais :");
		JLIA.setBounds(356, 171, 204, 22);
		JLIA.setFont(new Font("Arial", Font.PLAIN, 16));
		JLIA.setForeground(Color.WHITE);
		contentPane.add(JLIA);
		
		JScrollPane JSP = new JScrollPane();
		JSP.setBounds(356, 204, 253, 126);
		contentPane.add(JSP);
		
	    JTAIA = new JTextArea(os.getInformacaoadicional());
		JTAIA.setFont(new Font("Arial", Font.PLAIN, 14));
		JSP.setViewportView(JTAIA);
		
		JLabel JLNdeserie = new JLabel("*Número de série : ");
		JLNdeserie.setFont(new Font("Dialog", Font.PLAIN, 16));
		JLNdeserie.setForeground(Color.WHITE);
		JLNdeserie.setBounds(30, 374, 169, 22);
		contentPane.add(JLNdeserie);
		
		JTFNdeserie = new JTextField(os.getNdeserie());
		JTFNdeserie.setFont(new Font("Dialog", Font.PLAIN, 14));
		JTFNdeserie.setBounds(30, 408, 259, 45);
		contentPane.add(JTFNdeserie);
		JTFNdeserie.setColumns(10);
		
		JLabel JLBG = new JLabel("");
		JLBG.setBounds(0, 0, 684, 511);
		JLBG.setIcon(new ImageIcon(CadastrarOS.class.getResource("/VIEW/Images/degrade3.jpg")));
		contentPane.add(JLBG);
	}

}
