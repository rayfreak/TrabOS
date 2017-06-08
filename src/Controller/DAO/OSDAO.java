package Controller.DAO;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class OSDAO extends ExecuteSQL {

	public OSDAO(Connection con) {
		super(con);
	}
	Model.OrdemDeServico os = new Model.OrdemDeServico();
	
	public void inserirOS(int id, String equipamento,String marca, String modelo, String problema, String informacao, String ndeserie ){
		Date data = new Date(System.currentTimeMillis());
		Date data2 = new Date(System.currentTimeMillis());
		int m3 = data2.getMonth() + 3;
		data2.setMonth(m3);
		String SQL = "insert into os_ordemdeservico "
		+ "values ('"+0+"','"+"aguardando diagn\u00f3stico"+"', '"+data+"', '"+data2+"','"+equipamento+"','"+marca+"','"+modelo+"','"+ndeserie+"', "
		+ "'"+problema+"','"+null+"' ,'"+informacao+"','"+id+"','"+null+"');";
		try{
			PreparedStatement ps = getCon().prepareStatement(SQL);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cadastrada!");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "N\u00e3o Cadastrada!");
			e.printStackTrace();
		}
	}

	public JTable listarOS(){
		String SQL = "SELECT os_ordemdeservico.ID, os_ordemdeservico.equipamento, os_ordemdeservico.marca, os_ordemdeservico.modelo,"
				+ " os_ordemdeservico.dataentrada, os_cliente.nome, os_cliente.cpf, os_ordemdeservico.status FROM"
				+ " os_ordemdeservico INNER JOIN os_cliente WHERE os_ordemdeservico.ID_Cliente = os_cliente.ID;";
		
		String[] colunas = {"Id", "Equipamento","Marca","Modelo", "Data","Nome do Cliente", "CPF", "Status"};
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
			tabela.setFont(new Font("Arial",0,16));
		    tabela.setSelectionMode(0);
			tabela.getTableHeader().setReorderingAllowed(false);
			tabela.getColumnModel().getColumn(7).setPreferredWidth(110);
			tabela.getColumnModel().getColumn(6).setPreferredWidth(40);
			tabela.setRowHeight(20);
			tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
				public Component getTableCellRendererComponent(JTable table, Object value,
				boolean isSelected, boolean hasFocus, int row, int column) {
				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				Object ref = table.getValueAt(row, 7);//Coluna Status
			
				if (ref != null && ref.equals("aguardando diagn\u00f3stico")) {
					setForeground(new Color(255,140,0));
					} else if(ref != null && ref.equals("Conclu\u00eddo")){
					setForeground(Color.GREEN);
					} else if(ref != null && ref.equals("Aguardando Aprova\u00e7\u00e3o")) {
					setForeground(Color.YELLOW);
					}
					return this;
					}
					});
		return tabela;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao preencher a tabela!");
			System.out.println(e);
		return tabela;
		}
	}
	
	public JTable listarOSId(int search, String ordem){
		String SQL = "SELECT os_ordemdeservico.ID, os_ordemdeservico.equipamento, os_ordemdeservico.marca, os_ordemdeservico.modelo,"
				+ " os_ordemdeservico.dataentrada, os_cliente.nome, os_cliente.cpf, os_ordemdeservico.status FROM"
				+ " os_ordemdeservico INNER JOIN os_cliente ON os_ordemdeservico.ID_Cliente = os_cliente.ID WHERE os_ordemdeservico.ID like '"+search+"%"+"'"+ordem;
		
		String[] colunas = {"Id", "Equipamento","Marca","Modelo", "Data","Nome do Cliente", "CPF", "Status"};
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
		tabela.setFont(new Font("Arial",0,16));
	    tabela.setSelectionMode(0);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(110);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(20);
		tabela.setRowHeight(20);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(40);
		tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			Object ref = table.getValueAt(row, 7);//Coluna Status

			if (ref != null && ref.equals("aguardando diagn\u00f3stico")) {
				setForeground(new Color(255,140,0));
				} else if(ref != null && ref.equals("Conclu\u00eddo")){
				setForeground(Color.GREEN);
				} else if(ref != null && ref.equals("Aguardando Aprova\u00e7\u00e3o")) {
				setForeground(Color.YELLOW);
				}
				return this;
				}
				});
		return tabela;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao preencher a tabela!");
			System.out.println(e);
		return tabela;
		}
	}
	public JTable listarOSNome(String search, String ordem){
		String SQL = "SELECT os_ordemdeservico.ID, os_ordemdeservico.equipamento, os_ordemdeservico.marca, os_ordemdeservico.modelo,"
				+ " os_ordemdeservico.dataentrada, os_cliente.nome, os_cliente.cpf, os_ordemdeservico.status FROM"
				+ " os_ordemdeservico INNER JOIN os_cliente ON os_ordemdeservico.ID_Cliente = os_cliente.ID WHERE os_cliente.nome like '"+search+"%"+"'"+ordem;
		
		String[] colunas = {"Id", "Equipamento","Marca","Modelo", "Data","Nome do Cliente", "CPF", "Status"};
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
		tabela.setFont(new Font("Arial",0,16));
	    tabela.setSelectionMode(0);
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getColumnModel().getColumn(7).setPreferredWidth(110);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(40);
		tabela.setRowHeight(20);
		tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			Object ref = table.getValueAt(row, 7);
			
			if (ref != null && ref.equals("aguardando diagn\u00f3stico")) {
				setForeground(new Color(255,140,0));
				} else if(ref != null && ref.equals("Conclu\u00eddo")){
				setForeground(Color.GREEN);
				} else if(ref != null && ref.equals("Aguardando Aprova\u00e7\u00e3o")) {
				setForeground(Color.YELLOW);
				}
				return this;
				}
				});
		return tabela;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao preencher a tabela!");
			System.out.println(e);
		return tabela;
		}
	}

	public void excluirOS(int id){
		String SQL = "delete from os_ordemdeservico where ID = '"+id+"';";
		try{
			PreparedStatement ps = getCon().prepareStatement(SQL);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Exclu\u00eddo!");
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "N\u00e3o Exclu\u00eddo!");

		}
	}
	
	public void pegarOS(int id){
        String SQL = "select * from os_ordemdeservico where ID = '"+id+"';";
        try{
        	PreparedStatement ps = getCon().prepareStatement(SQL);
        	ResultSet rs = ps.executeQuery();
        	String equipamento = null, marca = null, modelo = null, ndeserie = null, problemadescrito = null, informacaoadicional = null;
        	if(rs != null) {
				while(rs.next()) {
        	equipamento = rs.getString("equipamento");
        	marca = rs.getString("marca");
        	modelo= rs.getString("modelo");
        	ndeserie = rs.getString("ndeserie");
        	problemadescrito = rs.getString("problemadescrito");
        	informacaoadicional = rs.getString("informacaoadicional");
				}
				os.setId(id);
				os.setEquipamento(equipamento);
				os.setMarca(marca);
				os.setModelo(modelo);
				os.setNdeserie(ndeserie);
				os.setProblemadescrito(problemadescrito);
				os.setInformacaoadicional(informacaoadicional);
			}
        }catch(Exception e){
        	JOptionPane.showMessageDialog(null, "Erro:" + e);
        }
	}
	
	public void editarOS(int id, String equipamento , String marca, String modelo, String problema, String informacao, String ndeserie){
		String SQL = "UPDATE os_ordemdeservico SET equipamento = '"+equipamento+"', marca = '"+marca+"', modelo= '"+modelo+"', problemadescrito = '"+problema+"', informacaoadicional = '"+informacao+"', ndeserie = '"+ndeserie+"'  WHERE ID = '"+id+"';";
		try{
		PreparedStatement ps = getCon().prepareStatement(SQL);
		ps.executeUpdate();	
		JOptionPane.showMessageDialog(null, "Alterardo!");
		os.setEquipamento(null);
		os.setMarca(null);
		os.setModelo(null);
		os.setProblemadescrito(null);
		os.setId(0);
		os.setInformacaoadicional(null);
		os.setNdeserie(null);
		}catch(Exception e){
		JOptionPane.showMessageDialog(null, "Erro: " + e);
		}
	}
	public void gerarOrcamento(int id, String equipusado, double precoequipusado, String servico, double precoservico, double total){
		String SQL = "INSERT INTO os_orcamento VALUES ('"+equipusado+"', '"+precoequipusado+"','"+servico+"','"+precoservico+"','"+total+"','"+id+"');";
	}
}
