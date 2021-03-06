import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;

public class TestBoidSimulator {
    public static void main(String[] args) {
        /*
        On génère ici 3 population de boids :
            - Population blanche : toute ses reglès sont équivalentes
            - Population rouge : Forte force de séparation
            - Population blue : Forte force de cohésion
         */
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        ArrayList<Boid> boidstotal = new ArrayList<Boid>();


        EventManager manager = new EventManager();
        int nbBoids = 50;

        generateRandomPopulation(boidstotal, Color.WHITE, nbBoids, manager);
        generateRandomPopulation(boidstotal, Color.RED, nbBoids, manager, 0.1, 0.1, 2);
        generateRandomPopulation(boidstotal, Color.BLUE, nbBoids, manager, 1, 0.25, 0.1);

        gui.setSimulable(new BoidSimulator(boidstotal, gui, manager));
    }

    public static void generateRandomPopulation(ArrayList<Boid> boidsTotal, Color color, int nbBoids,
                                                       EventManager manager){
        /*
        Génère une population aléatoire sans règles particulières
         */
        ArrayList<Boid> newBoids = new ArrayList<>();
        for (int i = 0; i < nbBoids; i++) {
            int x = (int) (Math.random() * 500);
            int y = (int) (Math.random() * 500);
            Boid newBoid = new Boid(x, y, color);
            boidsTotal.add(newBoid);
            newBoids.add(newBoid);
        }
        EssaimBoids swarm = new EssaimBoids(newBoids, manager, color);
        manager.addEvent(swarm);

    }

    public static void generateRandomPopulation(ArrayList<Boid> boidsTotal, Color color, int nbBoids,
                                                EventManager manager, double coeffcohesion, double coeffalignement,
                                                double coeffseparation){
        /*
        Génère une population aléatoire avec règles particulières
         */
        ArrayList<Boid> newBoids = new ArrayList<>();
        for (int i = 0; i < nbBoids; i++) {
            int x = (int) (Math.random() * 500);
            int y = (int) (Math.random() * 500);
            Boid newBoid = new Boid(x, y, color, coeffcohesion, coeffalignement, coeffseparation);
            boidsTotal.add(newBoid);
            newBoids.add(newBoid);
        }
        EssaimBoids swarm = new EssaimBoids(newBoids, manager, color);
        manager.addEvent(swarm);

    }
}
