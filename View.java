import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * View
 */
public class View {

    static int size;
    static int step;

    public static void main(String args[]) {
        String input;
        size = 0;
        do {
            input = JOptionPane.showInputDialog(null, "Escolha quantos Individuos\n");
            try {
                size = Integer.parseInt(input);
                if (size == 0)
                    JOptionPane.showMessageDialog(null, "Valor Incorreto\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor Incorreto\n Digite um Numero\n");
            }
        } while (size == 0);

        String stepInput;
        step = 0;

        do {
            stepInput = JOptionPane.showInputDialog(null, "Escolha quantos Individuos\n");
            try {
                step = Integer.parseInt(stepInput);
                if (step == 0)
                    JOptionPane.showMessageDialog(null, "Valor Incorreto\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor Incorreto\n Digite um Numero\n");
            }
        } while (step == 0);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI(size, step);
            }
        });

    }
}
