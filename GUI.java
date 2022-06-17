import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import javax.swing.JColorChooser;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI
 */
public class GUI extends JFrame implements ActionListener {

    private static final int CENTER = 15;
    private static final Color DEAD_COLOR = Color.RED;
    private static final Color ALIVE_COLOR = Color.GREEN;
    private static final Color GRID_COLOR = Color.BLUE;
    private static final Color BACKGROUND_COLOR = Color.BLACK;
    private static final int RADIUS = 13;
    private JLabel[][] grid;
    private JButton nextButton;
    private JPanel panel;
    private Container coords;
    private Container otherThings;
    private int nIndiv;
    private int step;
    private ListaDuplamenteLigadaCircular joseList;

    public GUI(int size, int step) {
        JFrame.isDefaultLookAndFeelDecorated();
        this.setSize(1150, 1100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.joseList = new ListaDuplamenteLigadaCircular();
        this.panel = new JPanel();
        this.nextButton = new JButton("Next");
        this.coords = new JPanel();
        this.otherThings = new JPanel();
        this.nIndiv = size;
        this.step = step;
        this.grid = new JLabel[31][31];
        this.coords.setLayout(new GridLayout(31, 31));

        for (int i = 0; i < 31; i++)
            for (int j = 0; j < 31; j++) {
                this.grid[i][j] = new JLabel("");
                this.grid[i][j].setPreferredSize(new Dimension(31, 31));
                this.grid[i][j].setMaximumSize(new Dimension(31, 31));
                this.grid[i][j].setMinimumSize(new Dimension(31, 31));
                this.grid[i][j].setBorder(BorderFactory.createLineBorder(GRID_COLOR));
                this.grid[i][j].setOpaque(true);
                this.grid[i][j].setBackground(BACKGROUND_COLOR);
                this.coords.add(this.grid[i][j], 0);
            }

        JLabel aliveLabel = new JLabel("ALIVE");
        JLabel labelColorAlive = new JLabel("");
        labelColorAlive.setPreferredSize(new Dimension(31, 31));
        labelColorAlive.setMaximumSize(new Dimension(31, 31));
        labelColorAlive.setMinimumSize(new Dimension(31, 31));
        labelColorAlive.setBorder(BorderFactory.createLineBorder(GRID_COLOR));
        labelColorAlive.setOpaque(true);
        labelColorAlive.setBackground(ALIVE_COLOR);

        JLabel deadLabel = new JLabel("DEAD");
        JLabel labelColorDead = new JLabel("");
        labelColorDead.setPreferredSize(new Dimension(31, 31));
        labelColorDead.setMaximumSize(new Dimension(31, 31));
        labelColorDead.setMinimumSize(new Dimension(31, 31));
        labelColorDead.setBorder(BorderFactory.createLineBorder(GRID_COLOR));
        labelColorDead.setOpaque(true);
        labelColorDead.setBackground(DEAD_COLOR);

        this.panel.setLayout(new BorderLayout());
        this.panel.add(this.coords, BorderLayout.CENTER);
        this.otherThings.add(this.nextButton);
        this.otherThings.add(deadLabel);
        this.otherThings.add(labelColorDead);
        this.otherThings.add(aliveLabel);
        this.otherThings.add(labelColorAlive);
        this.panel.add(this.otherThings, BorderLayout.SOUTH);

        this.nextButton.addActionListener(this);

        this.setContentPane(panel);
        this.setVisible(true);

        this.drawSquare();

    }

    public Point2D getPosToRender(double angle) {
        int x = (int) Math.floor(RADIUS * Math.cos(angle) + CENTER);
        int y = (int) Math.floor(RADIUS * Math.sin(angle) + CENTER);
        return new Point2D(x, y);
    }

    public double getStep() {
        return 2 * Math.PI / this.nIndiv;
    }

    public void drawSquare() {
        double angle = 0;
        double step = getStep();
        Point2D tempPoint;
        for (int i = 0; i < this.nIndiv; i++) {
            tempPoint = getPosToRender(angle);
            this.joseList.inserirFim(tempPoint);
            System.out.println("ANGLE: " + angle);
            System.out.println(tempPoint);
            this.grid[tempPoint.getX()][tempPoint.getY()].setBackground(ALIVE_COLOR);
            angle += step;
        }
        System.out.println("THIS IS JOSE LIST");
        System.out.println(this.joseList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object elem = e.getSource();
        if (elem == this.nextButton) {
            System.out.println("NEXT BUTTON PRESSED");
            int indexToKill = this.joseList.qtdNos % this.step;
            No tempNo = this.joseList.getInicio();
            while (indexToKill != 0) {
                tempNo = tempNo.getProximo();
                indexToKill--;
            }
            Point2D point = (Point2D) tempNo.getConteudo();
            this.grid[point.getX()][point.getY()].setBackground(DEAD_COLOR);
        }
    }

}
