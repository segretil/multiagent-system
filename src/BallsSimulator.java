import gui.*;
import java.awt.Point;
import java.awt.Color;

public class BallsSimulator implements Simulable {
    // Gère l'animation des balls
    private Balls[] balles;
    private GUISimulator window;
    private int[] dx;
    private int[] dy;

    public BallsSimulator(Point[] ballons, GUISimulator window) {
        int nbBalles = ballons.length;
        this.dx = new int[nbBalles];
        this.dy = new int[nbBalles];
        this.balles = new Balls[nbBalles];
        for (int k =0; k < nbBalles; k++){
            this.dx[k] = 5;
            this.dy[k] = 5;
            Point[] point = new Point[1];
            point[0] = ballons[k];
            this.balles[k] = new Balls(point);
        }
        this.window = window;
        Afficher();
    }

    private void Afficher() {
        this.window.reset();
        for (int i = 0; i < this.balles.length; i++) {
            this.window.addGraphicalElement(
            new Oval(this.balles[i].getBalls()[0].x,
            this.balles[i].getBalls()[0].y, balles[i].colorinside, balles[i].colorcercle,
            balles[i].getRayon()));
        }
    }

    @Override
    public void next() {
        // Bouge les balles de 5x et 8y
        for (int i = 0; i < this.balles.length; i++) {
            this.balles[i].translate(this.dx[i], this.dy[i]);
            int x = this.balles[i].getBalls()[0].x;
            int y = this.balles[i].getBalls()[0].y;
            // Changer le 490 par la taille de la window - 10 (la moitie du cercle)
            // Je sais pas comment la recuperer lire la doc
            if (x < balles[i].getRayon()/2 || x > 500 - balles[i].getRayon()/2) {
                this.dx[i] *= -1;
                // On translate 2 fois pour revenir en dehors du cadre
                this.balles[i].translate(this.dx[i] * 2, 0);
            }
            if (y > 500 - balles[i].getRayon()|| y < balles[i].getRayon()/2) {
                this.dy[i] *= -1;
                this.balles[i].translate(0, this.dy[i] * 2);
            }
        }
        Afficher();
    }

    @Override
    public void restart() {
        // Remet les balles à son origine
        for (int i = 0; i < this.balles.length; i++) {
            this.balles[i].reInit();
        }
        Afficher();
    }
}
