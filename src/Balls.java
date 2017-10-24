import gui.*;
import java.awt.Point;
import java.awt.Color;

public class Balls {
    private Point[] tabBalle;
    private int dxTotal = 0;
    private int dyTotal = 0;
    private int[] dxTotallocal;
    private int[] dyTotallocal;
    public Color colorcercle;
    public Color colorinside;
    private int rayon;
    private int nbBalles;

    public Balls(Point[] balls) {
      nbBalles = balls.length;
      tabBalle = new Point[nbBalles];
      dxTotallocal = new int[nbBalles];
      dyTotallocal = new int[nbBalles];
      // Default White
      colorcercle = Color.WHITE;
      colorinside = Color.WHITE;
      // Default rayon
      rayon = 10;
      for (int k =0; k < nbBalles; k++){
        tabBalle[k] = balls[k];
      }
    }

    public int getNbBalles(){
      return nbBalles;
    }

    public int getRayon(){
      return rayon;
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

    public void translateBallIndice(int dx, int dy, int indiceBall){
      if (indiceBall >= nbBalles || indiceBall < 0){
        throw new IllegalArgumentException("Indice n'est pas correct");
      }
      this.tabBalle[indiceBall].x += dx;
      this.tabBalle[indiceBall].y += dy;
      dxTotallocal[indiceBall] += dx;
      dyTotallocal[indiceBall] += dy;
    }

    public void reInit(){
      for (int i = 0; i < this.tabBalle.length; i++) {
        this.tabBalle[i].x -= dxTotal + dxTotallocal[i];
        this.tabBalle[i].y -= dyTotal + dyTotallocal[i];
        dxTotallocal[i] = 0;
        dyTotallocal[i] = 0;
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
