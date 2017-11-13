import gui.*;
import java.awt.Point;
import java.util.HashMap;
import java.awt.Color;

public class ImmigrationSimulator implements Simulable {
    private Immigration simu;
    private Immigration origine;
    private GUISimulator window;
    private EventManager manager;

    public ImmigrationSimulator(int etats, int step, HashMap<Point, Integer> points,
                                GUISimulator window) {
        // On crée l'immigration du jeu de l'immigration
        manager = new EventManager();
        this.simu = new Immigration(etats, step, points, manager);
        manager.addEvent(simu);
        this.origine = (Immigration) this.simu.copie();
        this.window = window;
        afficher();
    }

    private void afficher() {
        this.window.reset();
        for (Point point : this.simu.cell.keySet()) {
            affiche_rect(point);
        }
    }

    private void affiche_rect(Point point) {
        // Affiche le bon rectangle en fonction de l'etat du point
        int state = this.simu.cell.get(point);
        // Si l'on veut augmenter le nombre d'etats, il faut rajouter des case
        int codeColor = 255 / this.simu.nbrStates;
        this.window.addGraphicalElement(new Rectangle(point.x*50 + 25,
                    point.y*50 + 25, new Color(codeColor * state,codeColor * state,codeColor * state),
                    new Color(codeColor * state,codeColor * state,codeColor * state), 45));
    }

    @Override
    public void next() {
        // On passe à l'étape suivante du jeu de la vie
        manager.next();
        afficher();
    }

    @Override
    public void restart() {
        // Remet les points à leur point d'origine
        manager.restart();
        this.simu = (Immigration) this.origine.copie();
        manager.addEvent(simu);
        afficher();
    }
}
