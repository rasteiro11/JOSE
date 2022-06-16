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

    private static final int CENTER = 14;
    private static final int RADIUS = 13;
    private JLabel[][] grid;
    private JTextField removeField;
    private JButton removeButton;
    private Color corLads;
    private Color corFundo;
    private JPanel panel;
    private Container coords;
    private Container otherThings;
    private int nIndiv;

    public GUI(int size) {
        JFrame.isDefaultLookAndFeelDecorated();
        this.setSize(1150, 1100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.removeButton = new JButton("REMOVE");
        this.removeField = new JTextField(15);
        this.coords = new JPanel();
        this.otherThings = new JPanel();
        this.nIndiv = size;

        corLads = Color.BLUE;
        corFundo = Color.BLACK;

        this.grid = new JLabel[30][30];
        this.coords.setLayout(new GridLayout(30, 30));

        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 30; j++) {
                this.grid[i][j] = new JLabel("");
                this.grid[i][j].setPreferredSize(new Dimension(30, 30));
                this.grid[i][j].setMaximumSize(new Dimension(30, 30));
                this.grid[i][j].setMinimumSize(new Dimension(30, 30));
                this.grid[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
                this.grid[i][j].setOpaque(true);
                this.grid[i][j].setBackground(corFundo);
                this.coords.add(this.grid[i][j], 0);
            }

        this.panel.setLayout(new BorderLayout());
        this.panel.add(this.coords, BorderLayout.CENTER);
        this.otherThings.add(this.removeButton);
        this.otherThings.add(this.removeField);
        this.panel.add(this.otherThings, BorderLayout.SOUTH);

        this.removeButton.addActionListener(this);

        this.setContentPane(panel);
        this.setVisible(true);

    }

    public Point2D getPosToRender(double angle) {
        int x = (int) Math.floor(RADIUS * Math.cos(angle) + CENTER);
        int y = (int) Math.floor(RADIUS * Math.sin(angle) + CENTER);
        return new Point2D(x, y);
    }

    public void drawSquare(int num) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object elem = e.getSource();
        if (elem == this.removeButton) {
            System.out.println("REMOVE BUTTON CLICKED");
            drawSquare(4);
        }
    }

}
