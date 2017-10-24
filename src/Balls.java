import gui.*;
import java.awt.Point;

public class Balls {
    private Point[] tabBalle;
    private int dxTotal = 0;
    private int dyTotal = 0;

    public Balls(Point[] balls) {
      int nbBalles = balls.length;
      tabBalle = new Point[nbBalles];
      for (int k =0; k < nbBalles; k++){
        tabBalle[k] = balls[k];
      }
    }

    public Point[] getBalls() {
        return this.tabBalle;
    }

    public void translate(int dx, int dy){
      dxTotal += dx;
      dyTotal += dy;
      for (int i = 0; i < this.tabBalle.length; i++) {
          this.tabBalle[i].x += dx;
          this.tabBalle[i].y += dy;
      }
    }

    public void reInit(){
      for (int i = 0; i < this.tabBalle.length; i++) {
        this.tabBalle[i].x -= dxTotal;
        this.tabBalle[i].y -= dyTotal;
      }
      dxTotal = 0;
      dyTotal = 0;
    }

    @Override
    public String toString() {
        String reponse = "[";
        int longeur = tabBalle.length;

        for (int i = 0; i < longeur - 1 ; i++) {
            reponse  += "(" + tabBalle[i].x  + ", " + tabBalle[i].y + "), ";
        }
        reponse += "(" + tabBalle[longeur - 1].x + ", " + tabBalle[longeur - 1].y + ")]";
        return reponse;
    }
}
