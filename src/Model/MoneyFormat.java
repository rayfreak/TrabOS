package Model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MoneyFormat {

	private static final Locale BRAZIL = new Locale("pt","BR");  
    /** 
     * Simbolos especificos do Real Brasileiro 
     */  
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);  
    /** 
     * Mascara de dinheiro para Real Brasileiro 
     */  
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL);  

    /** 
     * Mascara texto com formatacao monetaria 
     * @param valor Valor a ser mascarado 
     * @param moeda Padrao monetario a ser usado 
     * @return Valor mascarado de acordo com o padrao especificado 
     */  
    public static String mascaraDinheiro(double valor, DecimalFormat moeda){  
        return moeda.format(valor);  
    }  
}
