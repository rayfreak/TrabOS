package VIEW;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Controller.DAO.Conexao;
import net.sf.jasperreports.engine.JRException;

public class ConsultarOS extends JFrame {

	private JPanel contentPane;
	private JTable tabela = new JTable();
	private JTextField JTFSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public ConsultarOS() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsultarClientes.class.getResource("/VIEW/Images/book.png")));
		setTitle("Sistema Gerenciador OS - Consulta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1300, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane JSP = new JScrollPane();
		JSP.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
			}
		});
		JSP.setBounds(26, 81, 1241, 283);
		JSP.setViewportBorder(null);
		contentPane.add(JSP);
		tabela.setSelectionMode(0);
        tabela.getTableHeader().setReorderingAllowed(false);
		JSP.setViewportView(tabela);
		
		JLabel JLTitle = new JLabel("Tabela para Consulta de Ordens de Servi\u00E7o :");
		JLTitle.setForeground(Color.WHITE);
		JLTitle.setBounds(409, 11, 427, 40);
		JLTitle.setFont(new Font("Arial Black", Font.PLAIN, 17));
		contentPane.add(JLTitle);
		
		JButton JBExcluir = new JButton("Excluir");
		JBExcluir.setBackground(new Color(51, 102, 153));
		JBExcluir.setForeground(Color.WHITE);
		JBExcluir.setFocusable(false);
		JBExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String linha = Integer.toString(tabela.getSelectedRow());
					if(Integer.parseInt(linha) >= 0){
				    	int resultado =JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta Ordem de Servi\u00e7o?\n"
				    	+ " (Esta a\u00e7\u00e3o n\u00e3o pode ser desfeita)");
				    	if(resultado == JOptionPane.YES_OPTION){
				    	DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
	                	int id = (int) modelo.getValueAt(Integer.parseInt(linha), 0);
	                	Controller.DAO.Conexao conexao = new Controller.DAO.Conexao();
	                	Connection con = conexao.conectar();
	                	Controller.DAO.OSDAO os = new Controller.DAO.OSDAO(con);
	                	os.excluirOS(id);
	                	conexao.desconectar(con);
				    	}
				    }else{
				    	JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela\n para poder excluir!");
				    }
			}});
		JBExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBExcluir.setIcon(new ImageIcon(ConsultarOS.class.getResource("/VIEW/Images/delete.png")));
		JBExcluir.setBounds(894, 401, 110, 50);
		contentPane.add(JBExcluir);
		
		JButton JBEditar = new JButton("Editar");
		JBEditar.setBackground(new Color(51, 102, 153));
		JBEditar.setForeground(Color.WHITE);
		JBEditar.setFocusable(false);
		JBEditar.setIcon(new ImageIcon(ConsultarClientes.class.getResource("/VIEW/Images/edit.png")));
		JBEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String linha = Integer.toString(tabela.getSelectedRow());
				if(Integer.parseInt(linha) >= 0){
			    	DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                	int id = (int) modelo.getValueAt(Integer.parseInt(linha), 0);
                	Controller.DAO.Conexao conexao = new Controller.DAO.Conexao();
                	Connection con = conexao.conectar();
                	Controller.DAO.OSDAO os = new Controller.DAO.OSDAO(con);
                	os.pegarOS(id);
                	conexao.desconectar(con);
                	AlterarOS tela = new AlterarOS();
                	tela.setVisible(true);
				}else{
				    JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela\n para poder editar!");
				}
			}
		});
		JBEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBEditar.setBounds(1035, 401, 110, 50);
		contentPane.add(JBEditar);
		
		JButton JBCOS = new JButton("<html> Gerar Or\u00E7amento/<br>    Diagn\u00F3stico </html>");
		JBCOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String linha = Integer.toString(tabela.getSelectedRow());
				if(Integer.parseInt(linha) >= 0){
			    	DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                	int id = (int) modelo.getValueAt(Integer.parseInt(linha), 0);
                	Model.OrdemDeServico os = new Model.OrdemDeServico();
                	os.setId(id);
                	GerarOrcamento go = new GerarOrcamento();
                	go.setVisible(true);
                	dispose();
			    }else{
			    	JOptionPane.showMessageDialog(null, "Selecione uma Ordem de servi\u00e7o\n para gerar o diagn\u00f3stico!");
			    }
			}
		});
		JBCOS.setForeground(Color.WHITE);
		JBCOS.setFocusable(false);
		JBCOS.setBackground(new Color(51, 102, 153));
		JBCOS.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBCOS.setIcon(new ImageIcon(ConsultarOS.class.getResource("/VIEW/Images/cifrao.png")));
		JBCOS.setBounds(683, 401, 179, 50);
		contentPane.add(JBCOS);
		
		JTFSearch = new JTextField("Digite o nome do Cliente ou ID para pesquisar");
		JTFSearch.setForeground(new Color(168,168,168));
		JTFSearch.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				JTFSearch.setText("");
				JTFSearch.setForeground(Color.BLACK);
			}
			public void focusLost(FocusEvent e) {
				JTFSearch.setText("Digite o nome ou ID para pesquisar");
				JTFSearch.setForeground(new Color(168,168,168));
			}
		});
		JTFSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JTFSearch.setBounds(26, 401, 319, 40);
		contentPane.add(JTFSearch);
		JTFSearch.setColumns(10);
		
		JRadioButton JTBID = new JRadioButton("Pesquisar por ID");
		buttonGroup.add(JTBID);
		JTBID.setForeground(Color.WHITE);
		JTBID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JTBID.setBackground(new Color(95, 89, 77));
		JTBID.setSelected(true);
		JTBID.setBounds(49, 473, 147, 23);
		JTBID.setFocusable(false);
		contentPane.add(JTBID);
		
		JRadioButton JRBOA = new JRadioButton("Pesquisar por Ordem Alfab\u00E9tica");
		buttonGroup.add(JRBOA);
		JRBOA.setForeground(Color.WHITE);
		JRBOA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JRBOA.setBackground(new Color(95, 89, 77));
		JRBOA.setBounds(198, 473, 245, 23);
		JRBOA.setFocusable(false);
		contentPane.add(JRBOA);
		
		JButton JBSearch = new JButton("");
		JBSearch.setToolTipText("Clique Aqui para iniciar a Pesquisa");
		JBSearch.setFocusable(false);
		JBSearch.setBackground(new Color(51, 102, 153));
		JBSearch.setIcon(new ImageIcon(ConsultarClientes.class.getResource("/VIEW/Images/search2.png")));
		JBSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		JBSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String search = JTFSearch.getText();
				String ordem;
				if(JRBOA.isSelected()){
					ordem = " order by os_cliente.nome;";
				}else{
					ordem = " order by os_ordemdeservico.ID;";
				}
				if(JTFSearch.getText().equals("") || JTFSearch.getText().equals(null)|| JTFSearch.getText().equals("Digite o nome do Cliente ou ID para pesquisar")){
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.OSDAO cos = new Controller.DAO.OSDAO(con);
				tabela = cos.listarOS();
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
				}else if(numb(search) == true){
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.OSDAO os = new Controller.DAO.OSDAO(con);
				tabela = os.listarOSId(Integer.parseInt(search), ordem);
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
				}else{
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.OSDAO os = new Controller.DAO.OSDAO(con);
				tabela = os.listarOSNome(search, ordem);
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
				}
			}
		});
		JBSearch.setBounds(383, 401, 60, 40);
		contentPane.add(JBSearch);
		
		JButton JBAtualizar = new JButton("Atualizar");
		JBAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.OSDAO os = new Controller.DAO.OSDAO(con);
				tabela = os.listarOS();
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
			}
		});
		JBAtualizar.setBackground(new Color(51, 102, 153));
		JBAtualizar.setForeground(Color.WHITE);
		JBAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBAtualizar.setIcon(new ImageIcon(ConsultarClientes.class.getResource("/VIEW/Images/refresh.png")));
		JBAtualizar.setBounds(507, 401, 147, 50);
		contentPane.add(JBAtualizar);
		
		JButton JBIOS = new JButton("<html> <center> Imprimir Ordem <br> de Servi\u00e7o </center> </html>");
		JBIOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Controller.DAO.Relatorio relatorio = new Controller.DAO.Relatorio();
			try {
				relatorio.gerarRelatorio();
			} catch (JRException e) {
				e.printStackTrace();
			}
			}
		});
		JBIOS.setIcon(new ImageIcon(ConsultarOS.class.getResource("/VIEW/Images/imp.png")));
		JBIOS.setFont(new Font("Dialog", Font.PLAIN, 13));
		JBIOS.setForeground(Color.WHITE);
		JBIOS.setBackground(new Color(51, 102, 153));
		JBIOS.setBounds(683, 484, 179, 50);
		contentPane.add(JBIOS);
		
		JLabel JLBG = new JLabel("");
		JLBG.setIcon(new ImageIcon(ConsultarOS.class.getResource("/VIEW/Images/degradec4.jpg")));
		JLBG.setBounds(0, 0, 1317, 691);
		contentPane.add(JLBG);
		
		contentPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				requestFocusInWindow();
			}
		});
	}
	
	public boolean numb(String search){
		try{
		Integer.parseInt(search);
		return true;	
		}catch(NumberFormatException e){return false;}
	}
}
