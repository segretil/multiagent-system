import gui.*;
import java.awt.Point;
import java.awt.Color;

public class BallsSimulator implements Simulable {
    // Gère l'animation des balls
    private Balls balles;
    private GUISimulator window;

    public BallsSimulator(Point[] ballons, GUISimulator window) {
        this.balles = new Balls(ballons);
        this.window = window;
        Afficher();
    }

    private void Afficher() {
        window.reset();
        for (int i = 0; i < this.balles.getBalls().length; i++) {
            this.window.addGraphicalElement(
            new Oval(this.balles.getBalls()[i].x, this.balles.getBalls()[i].y, Color.WHITE, Color.WHITE, 10));
        }
    }

    @Override
    public void next() {
        // Bouge les balles de 5x et 8y
        this.balles.translate(5, 8);
        Afficher();
        System.out.println("Next: ");
        System.out.println(this.balles);
    }

    @Override
    public void restart() {
        // Remet les balles à son origine
        this.balles.reInit();
        Afficher();
        System.out.println("Restart: ");
        System.out.println(this.balles);
    }
}
