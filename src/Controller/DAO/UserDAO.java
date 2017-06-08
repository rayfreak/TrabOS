package Controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class UserDAO extends ExecuteSQL {

	public UserDAO(Connection con){
		super(con);
	}

	public boolean logar(String user, String senha){
		String sql = "SELECT user, senha FROM os_adm;";
		boolean b = false;
		try{
			PreparedStatement ps = getCon().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					String user2 = rs.getString("user");
					String senha2 = rs.getString("senha");
					JOptionPane.showMessageDialog(null, senha + user + senha2 + user);
					if(senha2 == senha && user2 == user){
						b = true;
					}
				}
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
			b = false;
		}
		return b;
	}
}
