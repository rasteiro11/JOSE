import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI
 */
public class GUI extends JFrame implements ActionListener {

    /* Swing Library */
    private JLabel[][] grid;
    private JButton nextButton;
    private JPanel panel;
    private Container coords;
    private Container otherThings;
    private Color alive;
    private Color dead;

    /* Mapping */
    private final int CENTER = 15;
    private final int RADIUS = 13;
    private int nIndiv;
    private int step;
    private int kill;

    /* Data */
    private ListaDuplamenteLigadaCircular joseList;

    public ListaDuplamenteLigadaCircular getJoseList() {
        return joseList;
    }

    public void setJoseList(ListaDuplamenteLigadaCircular joseList) {
        this.joseList = joseList;
    }

    public int getnIndiv() {
        return nIndiv;
    }

    public void setnIndiv(int nIndiv) {
        this.nIndiv = nIndiv;
    }

    public GUI(int size, int step) {
        JFrame.isDefaultLookAndFeelDecorated();
        this.setSize(900, 900);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.panel = new JPanel();
        this.nextButton = new JButton("NEXT");
        this.coords = new JPanel();
        this.otherThings = new JPanel();
        this.alive = Color.GREEN;
        this.dead = Color.RED;

        this.nIndiv = size;
        this.step = step;
        this.kill = 0;
        this.joseList = new ListaDuplamenteLigadaCircular();

        this.grid = new JLabel[31][31];
        this.coords.setLayout(new GridLayout(31, 31));

        /* Sets up the Grid */
        for (int i = 0; i < 31; i++)
            for (int j = 0; j < 31; j++) {
                this.grid[i][j] = new JLabel("");
                this.grid[i][j].setPreferredSize(new Dimension(30, 30));
                this.grid[i][j].setMaximumSize(new Dimension(30, 30));
                this.grid[i][j].setMinimumSize(new Dimension(30, 30));
                this.grid[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                this.grid[i][j].setOpaque(true);
                this.grid[i][j].setBackground(Color.BLACK);
                this.coords.add(this.grid[i][j], 0);
            }

        startJosephus();

        this.panel.setLayout(new BorderLayout());
        this.panel.add(this.coords, BorderLayout.CENTER);

        /* Legenda */
        JLabel explainColoralive = new JLabel("");
        JLabel explainColordead = new JLabel("");
        explainColoralive.setPreferredSize(new Dimension(30, 30));
        explainColoralive.setMaximumSize(new Dimension(30, 30));
        explainColoralive.setMinimumSize(new Dimension(30, 30));
        explainColoralive.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        explainColoralive.setOpaque(true);
        explainColoralive.setBackground(alive);

        explainColordead.setPreferredSize(new Dimension(30, 30));
        explainColordead.setMaximumSize(new Dimension(30, 30));
        explainColordead.setMinimumSize(new Dimension(30, 30));
        explainColordead.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        explainColordead.setOpaque(true);
        explainColordead.setBackground(dead);

        /**/

        this.otherThings.add(this.nextButton);
        this.otherThings.add(new JLabel("Vivo:"));
        this.otherThings.add(explainColoralive);
        this.otherThings.add(new JLabel("Morto:"));
        this.otherThings.add(explainColordead);
        this.panel.add(this.otherThings, BorderLayout.SOUTH);

        this.nextButton.addActionListener(this);

        this.setContentPane(panel);
        this.setVisible(true);

    }

    public void startJosephus() {
        int x, y;
        double angle;
        Point2D aux;
        for (int i = 0; i < this.nIndiv; i++) {
            angle = (Math.PI * (i << 1)) / this.nIndiv; // 2 * PI * i/nIndiv
            aux = new Point2D(angle, this.RADIUS, this.CENTER); // The OFFSET is Center + 1 just so that the grid is
                                                                // correct
            x = aux.getX();
            y = aux.getY();
            this.grid[x][y].setBackground(alive);
            this.joseList.inserirFim(aux);
            this.joseList.getFim().setId(i);
        }
        System.out.println(this.joseList.getQtdNos());
        System.out.println(this.joseList.toString());
    }

    public void refresh() {
        this.kill += this.step;
        this.kill %= this.joseList.getQtdNos();

        No search = this.joseList.getInicio();
        for (int i = 0; i < this.kill; i++)
            search = search.getProximo();
        Point2D p_aux = (Point2D) search.getConteudo();
        this.grid[p_aux.getX()][p_aux.getY()].setBackground(dead);
        this.joseList.remover(search.getId());
        this.nIndiv--;

        System.out.println(this.joseList.getQtdNos());
        System.out.println(this.joseList.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object elem = e.getSource();
        if (elem == this.nextButton) {
            if (this.nIndiv > 1)
                refresh();
            else {
                System.exit(0);
            }
        }
    }
}
