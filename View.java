import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 * View
 */
public class View {

    public static void main(String args[])
    {
        int size = userChoice("Escolha quantos Individuos\nNumero devera esta no seguinte intervalo [2,20]",2,20);
        int step = userChoice("Escolha o passo para Matar Individuos\nNumero devera esta no seguinte intervalo [1,39]",1,39);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI(size,step);
            }
        });
    }

    public static int userChoice(String S, int min, int max)
    {
        int size = 2;
        String input;
        do 
        {
            input = JOptionPane.showInputDialog(null,S);
            try
            {
                size = Integer.parseInt(input);
                if(size < min || size > max)
                    JOptionPane.showMessageDialog(null, "Valor Incorreto\n");
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Valor Incorreto\n Digite um Numero\n");
            }
        } while (size < min || size > max);

        return size;
    }
}
