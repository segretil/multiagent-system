import gui.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Color;

public class ConwaySimulator implements Simulable {
    // On gère le simulateur graphique du jeu
    private Conway simu;
    private Conway origine;
    private GUISimulator window;

    public ConwaySimulator(HashMap<Point, Integer> points,
     GUISimulator window) {
        // On crée conway du jeu de conway
        this.simu = new Conway(points);
        this.origine = this.simu.copie();
        this.window = window;
        afficher();
    }

    private void afficher() {
        // On affiche la Schelling du jeu de la vie
        this.window.reset();
        for (Point point : this.simu.conway.keySet()) {
            affiche_rect(point);
        }
    }

    private void affiche_rect(Point point) {
        // Affiche le bon rectangle en fonction de l'etat du point
        int state = this.simu.conway.get(point);
        // Si l'on veut augmenter le nombre d'etats, il faut rajouter des case
        int codeColor = 255 / 2;
        this.window.addGraphicalElement(new Rectangle(point.x*50 + 25,
                    point.y*50 + 25, new Color(codeColor * state,codeColor * state,codeColor * state),
                    new Color(codeColor * state,codeColor * state,codeColor * state), 45));
    }

    @Override
    public void next() {
        // On passe à l'étape suivante du jeu de la vie
        simu.execute();
        afficher();
    }

    // j'ai modifier
    @Override
    public void restart() {
        // Remet les points à leur point d'origine
        this.simu = this.origine.copie();
        afficher();
    }
}
