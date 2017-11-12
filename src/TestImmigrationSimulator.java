import gui.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;


public class TestImmigrationSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        int seuil = 3;
        int etats = 4;
        int nbrlines = 10;
        //nbrmax de vivants doit toujours faire la totalite du tableau
        int nbrmaxvivants = nbrlines * nbrlines;

        HashMap<Point, Integer> vivants = new HashMap<Point, Integer>();

        initialisationVivants(nbrmaxvivants, nbrlines,
        etats, vivants);

        gui.setSimulable(new ImmigrationSimulatorEvent(etats, seuil, vivants, gui));


    }


    public static void initialisationVivants(int nbrmaxvivants, int nbrlines,
    int etats, HashMap<Point, Integer> vivants){
        //initialisation des vivants
        for (int indiceVivant = 0; indiceVivant < nbrmaxvivants; indiceVivant ++){
            //tirage aléatoire de l'état et de la position des points
            int etat = (int) (Math.random() * etats);
            Point newPoint = new Point(indiceVivant%nbrlines, indiceVivant/nbrlines);
            vivants.put(newPoint, etat);
        }

    }

}
