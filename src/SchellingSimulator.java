import gui.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Color;

public class SchellingSimulator implements Simulable {
    // On gère le simulateur graphique du jeu
    private SchellingStructure simu;
    private SchellingStructure origine;
    private GUISimulator window;

    public SchellingSimulator(int etats,int step, HashMap<Point, Integer> points,
    Queue <Point > pointssanspop, GUISimulator window) {
        // On crée la Schelling des vivants du jeu de la vie
        this.simu = new SchellingStructure(etats, step, points, pointssanspop);
        this.origine = this.simu.copiedeep();
        this.window = window;
        afficher();
    }

    private void afficher() {
        // On affiche la Schelling du jeu de la vie
        this.window.reset();
        for (Point point : this.simu.schelling.keySet()) {
            affiche_rect(point);
        }
    }

    private void affiche_rect(Point point) {
        int state = this.simu.schelling.get(point);
        switch (state)
        {
          case 0:
          this.window.addGraphicalElement(new Rectangle(point.x*50 + 25, point.y*50 + 25, Color.WHITE, Color.WHITE, 50));
          break;
          case 1:
          this.window.addGraphicalElement(new Rectangle(point.x*50 + 25, point.y*50 + 25, Color.RED, Color.RED, 50));
            break;
          case 2:
          this.window.addGraphicalElement(new Rectangle(point.x*50 + 25, point.y*50 + 25, Color.BLUE, Color.BLUE, 50));
            break;
          default:
        }
    }

    @Override
    public void next() {
        // On passe à l'étape suivante du jeu de la vie
        simu.execute();
        afficher();
    }

    @Override
    public void restart() {
        // Remet les points à leur point d'origine
        this.simu = this.origine.copiedeep();
        afficher();
    }
}
