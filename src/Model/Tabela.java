package Model;
import javax.swing.JTable;

public class Tabela extends JTable {  
      
    public Tabela() {  
        super();  
    }  
    public boolean isCellEditable(int row, int column) {  
        return false;  
    }  
    
}  
