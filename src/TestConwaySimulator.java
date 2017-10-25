import gui.*;
import java.awt.Color;
import java.awt.Point;

public class TestConwaySimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        // On choisit arbitrairement de créer trois balles par défaut
        int taille = (int)(Math.random() * 90);
        Point[] tab = new Point[taille];
        for (int i = 0; i < taille; i++) {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            tab[i] = new Point(x,y);
        }
        gui.setSimulable(new ConwaySimulator(500, 500, tab, gui));
    }
}
