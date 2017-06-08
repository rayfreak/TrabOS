package Controller.DAO;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ClienteDAO extends ExecuteSQL{
	
	static Model.Cliente cliente = new Model.Cliente();
	
	public ClienteDAO(Connection con) {
		super(con);
	}

	public void inserirCliente(String cpf, String nome, String telefone, String cep, String email, String telefone2 , String endereco){
		String SQL = "insert into os_cliente values ('"+0+"','"+cpf+"','"+nome+"','"+email+"','"+telefone+"','"+telefone2+"','"+cep+"','"+endereco+"');";
		try{
			PreparedStatement ps = getCon().prepareStatement(SQL);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cadastrado!");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N\u00e3o Cadastrado!");
		}
	}
	
	public JTable listarClientes(){
		String SQL = "select ID, cpf, nome, telefone, email from os_cliente;";
		
		String[] colunas = {"Id", "CPF", "Nome", "Telefone", "Email"};
		JTable tabela = new Model.Tabela();
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setColumnIdentifiers(colunas);
		try{
		PreparedStatement ps = getCon().prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numcol = rsmd.getColumnCount();
			while(rs.next()){
				Object[] valores = new Object[numcol];
				for(int i = 0; i < numcol; i++) {
					valores[i] = rs.getObject(i+1);
				}
				modelo.addRow(valores);	
			}
			tabela.setModel(modelo);
			tabela.setFont(new Font("Arial",0,15));
		    tabela.setSelectionMode(0);
			tabela.getTableHeader().setReorderingAllowed(false);
		return tabela;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao preencher a tabela!");
			System.out.println(e);
		return tabela;
		}
	}
	
	public JTable listarClientesId(int search, String ordem){
		String SQL = "select ID, cpf, nome, telefone, email from os_cliente where ID like '"+search+"%"+"'" + ordem;
		
		String[] colunas = {"Id", "CPF", "Nome", "Telefone", "Email"};
		JTable tabela = new Model.Tabela();
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setColumnIdentifiers(colunas);
		try{
		PreparedStatement ps = getCon().prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numcol = rsmd.getColumnCount();
		
		while(rs.next()){
			Object[] valores = new Object[numcol];
			for(int i = 0; i < numcol; i++) {
				valores[i] = rs.getObject(i+1);
			}
			modelo.addRow(valores);	
		}
		tabela.setModel(modelo);
		tabela.setFont(new Font("Arial",0,15));
	    tabela.setSelectionMode(0);
		tabela.getTableHeader().setReorderingAllowed(false);
		return tabela;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao preencher a tabela!");
			System.out.println(e);
		return tabela;
		}
	}

	public JTable listarClientesNome(String search, String ordem){
		String SQL = "select ID, cpf, nome, telefone, email from os_cliente where nome like '"+search+"%"+"'" + ordem;
		
		String[] colunas = {"Id", "CPF", "Nome", "Telefone", "Email"};
		JTable tabela = new Model.Tabela();
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		modelo.setColumnIdentifiers(colunas);
		try{
		PreparedStatement ps = getCon().prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int numcol = rsmd.getColumnCount();
		
		while(rs.next()){
			Object[] valores = new Object[numcol];
			for(int i = 0; i < numcol; i++) {
				valores[i] = rs.getObject(i+1);
			}
			modelo.addRow(valores);	
		}
		tabela.setModel(modelo);
		tabela.setFont(new Font("Arial",0,15));
	    tabela.setSelectionMode(0);
		tabela.getTableHeader().setReorderingAllowed(false);
		return tabela;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao preencher a tabela!");
			System.out.println(e);
		return tabela;
		}
	}
	
	public void excluirCliente(int id){
		String SQL = "delete from os_cliente where ID = '"+id+"';";
		try{
			PreparedStatement ps = getCon().prepareStatement(SQL);
			ps.executeUpdate();
			SQL = "delete from os_ordemdeservico where ID_Cliente = '"+id+"';";
			JOptionPane.showMessageDialog(null, "Exclu\u00eddo!");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "N\u00e3o Exclu\u00eddo!");

		}
	}

	public void pegarCliente(int id){
        String SQL = "select * from os_cliente where ID = '"+id+"';";
        try{
        	PreparedStatement ps = getCon().prepareStatement(SQL);
        	ResultSet rs = ps.executeQuery();
        	String cpf = null,nome = null, telefone = null, cep = null, endereco, telefone2 = null, email = null;
        	if(rs != null) {
				while(rs.next()) {
        	cpf = rs.getString("cpf");
        	nome = rs.getString("nome");
        	telefone = rs.getString("telefone");
        	cep = rs.getString("cep");
        	telefone2 = rs.getString("telefone2");
        	email = rs.getString("email");
        	endereco = rs.getString("endereco");
				}
				cliente.setId(id);
				cliente.setCpf(cpf);
				cliente.setNome(nome);
				cliente.setTelefone(telefone);
				cliente.setCep(cep);
				cliente.setEmail(email);
				cliente.setTelefone2(telefone2);
			}
        }catch(Exception e){
        	JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
	}
	
	public void editarCliente(int id, String cpf , String nome, String telefone, String cep, String email, String telefone2, String endereco){
		String SQL = "UPDATE os_cliente SET cpf = '"+cpf+"', telefone2 = '"+telefone2+"',email = '"+email+"',nome = '"+nome+"', telefone = '"+telefone+"', cep = '"+cep+"', endereco = '"+endereco+"' WHERE ID = '"+id+"';";
		try{
		PreparedStatement ps = getCon().prepareStatement(SQL);
		ps.executeUpdate();	
		JOptionPane.showMessageDialog(null, "Alterardo!");
		cliente.setCpf(null);
		cliente.setCep(null);
		cliente.setNome(null);
		cliente.setTelefone(null);
		cliente.setId(0);
		}catch(Exception e){
		JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}

}
