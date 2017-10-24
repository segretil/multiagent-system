import gui.*;
import java.awt.Point;
import java.awt.Color;

public class BallsSimulator implements Simulable {
    // Gère l'animation des balls
    private Balls balles;
    private GUISimulator window;
    private int[] dx;
    private int[] dy;

    public BallsSimulator(Point[] ballons, GUISimulator window) {
        this.balles = new Balls(ballons);
        this.dx = new int[this.balles.getNbBalles()];
        this.dy = new int[this.balles.getNbBalles()];
        for (int k =0; k < balles.getNbBalles(); k++){
          //Default speed
            this.dx[k] = 5;
            this.dy[k] = 5;
        }
        this.window = window;
        Afficher();
    }

    private void Afficher() {
        this.window.reset();
        for (int i = 0; i < balles.getNbBalles(); i++) {
            this.window.addGraphicalElement(
            new Oval(this.balles.getBalls()[i].x,
            this.balles.getBalls()[i].y, balles.colorinside, balles.colorcercle,
            balles.getRayon()));
        }
    }

    @Override
    public void next() {
        // Bouge les balles de 5x et 8y
        for (int i = 0; i < balles.getNbBalles(); i++) {
            this.balles.translateBallIndice(this.dx[i], this.dy[i], i);
            int x = this.balles.getBalls()[i].x;
            int y = this.balles.getBalls()[i].y;
            // Changer le 490 par la taille de la window - 10 (la moitie du cercle)
            // Je sais pas comment la recuperer lire la doc
            if (x < balles.getRayon()/2 || x > 500 - balles.getRayon()/2) {
                this.dx[i] *= -1;
                // On translate 2 fois pour revenir en dedans du cadre
                this.balles.translateBallIndice(this.dx[i] * 2, 0, i);
            }
            if (y > 500 - balles.getRayon()|| y < balles.getRayon()/2) {
                this.dy[i] *= -1;
                this.balles.translateBallIndice(0, this.dy[i] * 2, i);
            }
        }
        Afficher();
    }

    @Override
    public void restart() {
        // Remet les balles à son origine
        this.balles.reInit();
        Afficher();
    }
}
