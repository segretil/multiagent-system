import gui.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;


public class TestSchellingSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        // On choisit arbitrairement de créer trois balles par défaut
        int seuil = 3;
        int etats = 3;

        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 1);
        Point p3 = new Point(0, 2);
        Point p4 = new Point(1, 0);
        Point p5 = new Point(1, 1);
        Point p6 = new Point(1, 2);
        Point p7 = new Point(2, 0);
        Point p8 = new Point(2, 1);
        Point p9 = new Point(2, 2);

        HashMap<Point, Integer> hell = new HashMap<Point, Integer>();
        hell.put(p1, 1);
        hell.put(p2, 0);
        hell.put(p6, 1);
        hell.put(p8, 2);
        hell.put(p5, 0);
        hell.put(p4, 2);


        Queue <Point > emptypoints = new LinkedList <Point > ();
        emptypoints.add(p3);
        emptypoints.add(p7);
        emptypoints.add(p9);

        gui.setSimulable(new SchellingSimulator(etats, seuil, hell, emptypoints, gui));
        //SchellingStructure simu = new SchellingStructure(etats, seuil, hell, emptypoints);
        //simu.execute();
        //simu.execute();




    }
}
