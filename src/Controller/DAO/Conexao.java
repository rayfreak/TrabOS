package Controller.DAO;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
	Connection conexao = null;
	public Connection conectar(){
		try{
			String servidor = "jdbc:mysql://localhost:3306/os";
			String usuario = "root";
			String senha = "qwe123";
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			conexao = DriverManager.getConnection(servidor, usuario, senha);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return conexao;
	}

	public void desconectar(Connection conexao){
		try{
			conexao.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}
}