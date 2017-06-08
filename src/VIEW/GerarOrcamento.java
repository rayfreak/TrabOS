package VIEW;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class GerarOrcamento extends JFrame {

	private JPanel contentPane;
	private JTextField JTFequipamento;
	private JFormattedTextField JTFPE;
	private JTextField JTFSR;
	private JFormattedTextField JTFPSR;
	private JTextField JTFOrcamento;

	public GerarOrcamento(){
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarCliente.class.getResource("/VIEW/Images/add.png")));
		setTitle("Sistema Gerenciador OS - Orçamento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel title = new JLabel("Gerar Or\u00e7amento/Diagnóstico :");
		title.setFont(new Font("Dialog", Font.BOLD, 16));
		title.setForeground(Color.WHITE);
		title.setBounds(187, 12, 324, 26);
		contentPane.add(title);
		
		JLabel JLE = new JLabel("*Equipamento usado :");
		JLE.setFont(new Font("Dialog", Font.PLAIN, 14));
		JLE.setForeground(Color.WHITE);
		JLE.setBounds(91, 90, 181, 20);
		contentPane.add(JLE);
		
		JLabel JLPreco = new JLabel("*Preço do equipamento :");
		JLPreco.setFont(new Font("Dialog", Font.PLAIN, 14));
		JLPreco.setForeground(Color.WHITE);
		JLPreco.setBounds(416, 92, 200, 17);
		contentPane.add(JLPreco);
		
		JLabel JLSR = new JLabel("*Serviço realizado :");
		JLSR.setFont(new Font("Dialog", Font.PLAIN, 14));
		JLSR.setForeground(Color.WHITE);
		JLSR.setBounds(91, 184, 181, 20);
		contentPane.add(JLSR);
		
		JLabel JLPSR = new JLabel("*Preço do Serviço :");
		JLPSR.setFont(new Font("Dialog", Font.PLAIN, 14));
		JLPSR.setForeground(Color.WHITE);
		JLPSR.setBounds(426, 184, 173, 20);
		contentPane.add(JLPSR);
		
		JLabel JLOrcamento = new JLabel("Total (Orçamento) :");
		JLOrcamento.setFont(new Font("Dialog", Font.PLAIN, 14));
		JLOrcamento.setForeground(Color.WHITE);
		JLOrcamento.setBounds(281, 295, 153, 26);
		contentPane.add(JLOrcamento);
		
		JTFequipamento = new JTextField();
		JTFequipamento.setBounds(91, 122, 200, 36);
		contentPane.add(JTFequipamento);
		JTFequipamento.setColumns(10);
		
		JTFPE = new JFormattedTextField();
		JTFPE.setBounds(426, 121, 124, 36);
		contentPane.add(JTFPE);
		JTFPE.setColumns(10);
		
		JTFSR = new JTextField();
		JTFSR.setBounds(91, 216, 200, 36);
		contentPane.add(JTFSR);
		JTFSR.setColumns(10);
		
		JTFPSR = new JFormattedTextField();
		JTFPSR.setBounds(426, 216, 124, 36);
		contentPane.add(JTFPSR);
		JTFPSR.setColumns(10);
		
		JTFOrcamento = new JTextField();
		JTFOrcamento.setEditable(false);
		JTFOrcamento.setBounds(291, 334, 114, 36);
		contentPane.add(JTFOrcamento);
		JTFOrcamento.setColumns(10);
		
		JButton JLSO = new JButton("Salvar Orçamento");
		JLSO.setBackground(new Color(51, 102, 153));
		JLSO.setIcon(new ImageIcon(GerarOrcamento.class.getResource("/VIEW/Images/cifrao.png")));
		JLSO.setFont(new Font("Dialog", Font.PLAIN, 13));
		JLSO.setForeground(Color.WHITE);
		JLSO.setBounds(262, 406, 181, 43);
		contentPane.add(JLSO);
		
		JLabel JLBG = new JLabel("");
		JLBG.setIcon(new ImageIcon(GerarOrcamento.class.getResource("/VIEW/Images/degrade3.jpg")));
		JLBG.setBounds(0, 0, 694, 522);
		contentPane.add(JLBG);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
	}
}
