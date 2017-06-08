package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.DAO.Conexao;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsultarClientes extends JFrame {

	private JPanel contentPane;
	private JTable tabela = new JTable();
	private JTextField JTFSearch;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public ConsultarClientes() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsultarClientes.class.getResource("/VIEW/Images/book.png")));
		setTitle("Sistema Gerenciador OS - Consulta");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1200, 670);
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
		JSP.setBounds(49, 81, 1075, 283);
		JSP.setViewportBorder(null);
		contentPane.add(JSP);
		tabela.setSelectionMode(0);
        tabela.getTableHeader().setReorderingAllowed(false);
		JSP.setViewportView(tabela);
		
		JLabel JLTitle = new JLabel("Tabela para Consulta de Clientes  :");
		JLTitle.setForeground(Color.WHITE);
		JLTitle.setBounds(435, 11, 427, 40);
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
				    	int resultado =JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este Cliente?\n"
				    	+ " (Esta a\u00e7\u00e3o n\u00e3o pode ser desfeita)");
				    	if(resultado == JOptionPane.YES_OPTION){
				    	DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
	                	int id = (int) modelo.getValueAt(Integer.parseInt(linha), 0);
	                	Controller.DAO.Conexao conexao = new Controller.DAO.Conexao();
	                	Connection con = conexao.conectar();
	                	Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
	                	cliente.excluirCliente(id);
	                	conexao.desconectar(con);
				    	}
				    }else{
				    	JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela\n para poder excluir!");
				    }
			}});
		JBExcluir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBExcluir.setIcon(new ImageIcon(ConsultarClientes.class.getResource("/VIEW/Images/user_delete.png")));
		JBExcluir.setBounds(844, 401, 110, 50);
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
                	Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
				    cliente.pegarCliente(id);
				    conexao.desconectar(con);
				    AlterarCliente alterar = new AlterarCliente();
				    alterar.setVisible(true);
				}else{
				    JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela\n para poder editar!");
				}
			}
		});
		JBEditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBEditar.setBounds(991, 401, 110, 50);
		contentPane.add(JBEditar);
		
		JButton JBCOS = new JButton("Cadastrar OS");
		JBCOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String linha = Integer.toString(tabela.getSelectedRow());
				if(Integer.parseInt(linha) >= 0){
			    	DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
                	int id = (int) modelo.getValueAt(Integer.parseInt(linha), 0);
                	Controller.DAO.Conexao conexao = new Controller.DAO.Conexao();
                	Connection con = conexao.conectar();
                	Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
                	cliente.pegarCliente(id);
                	CadastrarOS cados = new CadastrarOS();
                	cados.setVisible(true);
                	conexao.desconectar(con);
			    }else{
			    	JOptionPane.showMessageDialog(null, "Selecione uma linha da tabela\n para poder Cadastrar OS!");
			    }
			}
		});
		JBCOS.setForeground(Color.WHITE);
		JBCOS.setFocusable(false);
		JBCOS.setBackground(new Color(51, 102, 153));
		JBCOS.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBCOS.setIcon(new ImageIcon(ConsultarClientes.class.getResource("/VIEW/Images/add.png")));
		JBCOS.setBounds(652, 401, 151, 50);
		contentPane.add(JBCOS);
		
		JTFSearch = new JTextField("Digite o nome ou ID para pesquisar");
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
		JTFSearch.setBounds(45, 401, 236, 40);
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
					ordem = " order by nome;";
				}else{
					ordem = " order by ID;";
				}
				if(JTFSearch.getText().equals("") || JTFSearch.getText().equals("Digite o nome ou ID para pesquisar")){
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
				tabela = cliente.listarClientes();
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
				}else if(numb(search) == true){
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
				tabela = cliente.listarClientesId(Integer.parseInt(search), ordem);
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
				}else{
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
				tabela = cliente.listarClientesNome(search, ordem);
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
				}
			}
		});
		JBSearch.setBounds(317, 401, 60, 40);
		contentPane.add(JBSearch);
		
		JButton JBAtualizar = new JButton("Atualizar");
		JBAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexao conexao = new Conexao();
				Connection con = conexao.conectar();
				Controller.DAO.ClienteDAO cliente = new Controller.DAO.ClienteDAO(con);
				tabela = cliente.listarClientes();
				JSP.setViewportView(tabela);
				conexao.desconectar(con);
			}
		});
		JBAtualizar.setBackground(new Color(51, 102, 153));
		JBAtualizar.setForeground(Color.WHITE);
		JBAtualizar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JBAtualizar.setIcon(new ImageIcon(ConsultarClientes.class.getResource("/VIEW/Images/refresh.png")));
		JBAtualizar.setBounds(452, 401, 147, 50);
		contentPane.add(JBAtualizar);
		
		JLabel JLBG = new JLabel("");
		JLBG.setIcon(new ImageIcon(ConsultarClientes.class.getResource("/VIEW/Images/degradec4.jpg")));
		JLBG.setBounds(0, 0, 1184, 631);
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
