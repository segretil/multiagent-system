import gui.*;
import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;

public class TestConwaySimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        int nbrlines = 10;
        //nbrmax de vivants doit toujours faire la totalite du tableau
        int nbrmaxvivants = nbrlines * nbrlines;

        HashMap<Point, Integer> vivants = new HashMap<Point, Integer>();

        initialisationVivants(nbrmaxvivants, nbrlines,
        2, vivants);

        gui.setSimulable(new ConwaySimulator(vivants, gui));


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
