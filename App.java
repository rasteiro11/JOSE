import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * App
 */
public class App {

    public static int userChoice(String S, int min, int max) {
        int size = 2;
        String input;
        do {
            input = JOptionPane.showInputDialog(null, S);
            try {
                size = Integer.parseInt(input);
                if (size < min || size > max)
                    JOptionPane.showMessageDialog(null, "Valor Incorreto\n");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor Incorreto\n Digite um Numero\n");
            }
        } while (size < min || size > max);

        return size;
    }

    public static void main(String[] args) {
        int size = userChoice("Escolha quantos Individuos\nNumero devera esta no seguinte intervalo [2,20]", 2, 20);
        int step = userChoice("Escolha o passo para Matar Individuos\nNumero devera esta no seguinte intervalo [1,39]",
                1, 39);

        GUI gui = new GUI(size, step);

        // execute logical thread
        Thread t1 = new LogicService(gui);
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.exit(0);
    }

}

class LogicService extends Thread {
    private GUI gui;
    private boolean signal;

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public LogicService(GUI gui) {
        this.gui = gui;
        this.signal = true;
    }

    @Override
    public void run() {
        try {
            while (this.signal) {
                // execute thread till n indiv < 1
                if (gui.getnIndiv() > 1) {
                    gui.refresh();
                    Thread.sleep(5000);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Individuo na possicao" + gui.getJoseList().toString() + "\nsobreviveu");
                    this.signal = false;
                    this.interrupt();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
