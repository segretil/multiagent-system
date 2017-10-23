import gui.*;
import java.awt.Point;

public class Balls {
    private Point[] tabBalle;

    public Balls(Point ball) {
        this.tabBalle = new Point[1];
        this.tabBalle[0] = ball;
    }

    public Balls(int nbBalles) {
        this.tabBalle = new Point[nbBalles];
    }

    @Override
    public String toString() {
        String reponse = "[";
        for (int i = 0; i < this.tabBalle.length; i++) {
            reponse  += "(" + this.tabBalle[i].x  + "," + this.tabBalle[i].y + "), ";
        }
        reponse += "]";
        return reponse;
    }
}
