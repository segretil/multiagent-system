import gui.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Color;

/**
 *     On gère le simulateur graphique du jeu
 */
public class SchellingSimulator implements Simulable {
    private SchellingStructure simu;
    private SchellingStructure origine;
    private GUISimulator window;
    private EventManager manager;

    public SchellingSimulator(int etats,int step, HashMap<Point, Integer> points,
    Queue <Point > pointssanspop, GUISimulator window) {
        // On crée la Schelling des vivants du jeu de la vie
        this.manager = new EventManager();
        this.simu = new SchellingStructure(etats, step, points, pointssanspop, manager);
        this.manager.addEvent(this.simu);
        this.origine = (SchellingStructure) this.simu.copiedeep();
        this.window = window;
        afficher();
    }

    /** On affiche la Schelling du jeu de la vie
     *
     */
    private void afficher() {
        this.window.reset();
        for (Point point : this.simu.cell.keySet()) {
            affiche_rect(point);
        }
    }

    /** Affiche le bon rectangle en fonction de l'etat du point
     *
     * @param point
     */
    private void affiche_rect(Point point) {
        int state = this.simu.cell.get(point);
        // Si l'on veut augmenter le nombre d'etats, il faut rajouter des case
        switch (state)
        {
          case 0:
          this.window.addGraphicalElement(new Rectangle(point.x*50 + 25, point.y*50 + 25, Color.WHITE, Color.WHITE, 45));
          break;
          case 1:
          this.window.addGraphicalElement(new Rectangle(point.x*50 + 25, point.y*50 + 25, Color.RED, Color.RED, 45));
            break;
          case 2:
          this.window.addGraphicalElement(new Rectangle(point.x*50 + 25, point.y*50 + 25, Color.BLUE, Color.BLUE, 45));
            break;
          default:
        }
    }

    /** On passe à l'étape suivante du jeu de la vie
     *
     */
    @Override
    public void next() {
        manager.next();
        afficher();
    }

    /** Remet les points à leur point d'origine
     *
     */

     @Override
    public void restart() {
        this.simu = (SchellingStructure) this.origine.copiedeep();
        this.simu.setDate(0);
        this.manager.restart();
        this.manager.addEvent(this.simu);
        afficher();
    }
}
