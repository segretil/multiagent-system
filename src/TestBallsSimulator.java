import gui.*;
import java.awt.Color;
import java.awt.Point;

public class TestBallsSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        // On choisit arbitrairement de créer trois balles par défaut
        Point a = new Point(0, 0);
        Point b = new Point(100, 1);
        Point c = new Point(200, 2);
        Point[] tab = {a, b, c};
        gui.setSimulable(new BallsSimulator(tab, gui));
    }
}
