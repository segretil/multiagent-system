import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

public class BallsSimulatorEvent implements Simulable {
    // Gère l'animation des balls
    private BallsEvent balles;
    private GUISimulator window;
    private EventManager manager;

    public BallsSimulatorEvent(Point[] ballons, GUISimulator window) {
        manager = new EventManager();
        this.balles = new BallsEvent(ballons, manager);
        manager.addEvent(balles);
        balles.dx = new int[this.balles.getNbBalles()];
        balles.dy = new int[this.balles.getNbBalles()];
        for (int k =0; k < balles.getNbBalles(); k++){
          //Default speed
            balles.dx[k] = 5;
            balles.dy[k] = 5;
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
        manager.next();
        Afficher();
    }

    @Override
    public void restart() {
        // Remet les balles à son origine
        this.balles.reInit();
        manager.restart();
        for (int k =0; k < balles.getNbBalles(); k++){
            //Default speed
            balles.dx[k] = 5;
            balles.dy[k] = 5;
        }
        Afficher();
    }
}
