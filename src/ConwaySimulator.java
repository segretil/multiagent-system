import gui.*;
import java.awt.Point;
import java.awt.Color;

public class ConwaySimulator implements Simulable {
    // On gère le simulateur graphique du jeu
    private Matrice matrice;
    private Matrice origine;
    private GUISimulator window;

    public ConwaySimulator(int tailleX, int tailleY, Point[] vivants, GUISimulator window) {
        // On crée la matrice des vivants du jeu de la vie
        this.matrice = new Matrice(tailleX/50, tailleY/50, vivants);
        this.origine = this.matrice.copied();
        this.window = window;
        afficher();
    }

    private void afficher() {
        // On affiche la matrice du jeu de la vie
        this.window.reset();
        for (Point point : this.matrice.getVivant()) {
            affiche_rect(point);
        }
    }

    private void affiche_rect(Point point) {
        this.window.addGraphicalElement(new Rectangle(point.x*50 + 25, point.y*50 + 25, Color.WHITE, Color.WHITE, 50));
    }

    @Override
    public void next() {
        // On passe à l'étape suivante du jeu de la vie
        matrice.etape();
        afficher();
    }

    @Override
    public void restart() {
        // Remet les points à leur point d'origine
        this.matrice = this.origine.copied();
        afficher();
    }
}
