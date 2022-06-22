/**
 * Point2D
 */
public class Point2D {

    private int x;
    private int y;

    public Point2D(double angle, int RADIUS, int OFFSET) 
    {
        this.x = (int) Math.floor(RADIUS * Math.cos(angle) + OFFSET);
        this.y = (int) Math.floor(RADIUS * Math.sin(angle) + OFFSET);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(x,y) = (" + (this.x + 1)  + ',' + (this.y + 1) + ')';
    }
}
