import gui.*;
import java.awt.Color;
import java.awt.Point;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;


public class TestSchellingSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        int seuil = 4;
        //Ne peut pas depasser 3 états
        int etats = 3;
        int nbrlines = 10;
        //nbrmax de vivants doit toujours < nbrlines**2
        int nbrmaxvivants = 80;

        HashMap<Point, Integer> vivants = new HashMap<Point, Integer>();
        Queue <Point > emptypoints = new LinkedList <Point > ();

        initialisationVivants(nbrmaxvivants, nbrlines,
        etats, vivants);
        initialisationEmptypoints(nbrlines,
        vivants, emptypoints);

        gui.setSimulable(new SchellingSimulator(etats, seuil, vivants, emptypoints, gui));


    }


    public static void initialisationVivants(int nbrmaxvivants, int nbrlines,
    int etats, HashMap<Point, Integer> vivants){
        //initialisation des vivants
        for (int indiceVivant = 0; indiceVivant < nbrmaxvivants; indiceVivant ++){
            //tirage aléatoire de l'état et de la position des points
            int indice = (int) (Math.random() * nbrlines * nbrlines);
            int etat = (int) (Math.random() * etats);
            Point newPoint = new Point(indice%nbrlines, indice/nbrlines);
            //Pour eviter d'avoir des doublons dans les vivants

            while (vivants.containsKey(newPoint)){
                indice = (int) (Math.random() * nbrlines * nbrlines);
                newPoint = new Point(indice%nbrlines, indice/nbrlines);
            }

            vivants.put(newPoint, etat);
        }

    }


    public static void initialisationEmptypoints(int nbrlines,
    HashMap<Point, Integer> vivants, Queue <Point > emptypoints){
        //initialisation des emptypoints
        for (int indice = 0; indice < nbrlines * nbrlines; indice++){
            Point newPoint = new Point(indice%nbrlines, indice/nbrlines);

            if (!(vivants.containsKey(newPoint))){
                emptypoints.add(newPoint);
            }

        }

    }

}
