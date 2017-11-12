import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;

public class TestBoidSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        // On choisit arbitrairement de créer trois balles par défaut
        ArrayList<Boid> boidstotal = new ArrayList<Boid>();


        EventManager manager = new EventManager();
        int nbBoids = 50;

        generateRandomPopulation(boidstotal, Color.WHITE, nbBoids, manager);
        generateRandomPopulation(boidstotal, Color.BLUE, nbBoids, manager);
        generateRandomPopulation(boidstotal, Color.RED, nbBoids, manager);


        gui.setSimulable(new BoidSimulator(boidstotal, gui, manager));
    }

    public static void generateRandomPopulation(ArrayList<Boid> boidsTotal, Color color, int nbBoids,
                                                       EventManager manager){
        ArrayList<Boid> newBoids = new ArrayList<>();
        for (int i = 0; i < nbBoids; i++) {
            int x = (int) (Math.random() * 500);
            int y = (int) (Math.random() * 500);
            Boid newBoid = new Boid(x, y, color);
            boidsTotal.add(newBoid);
            newBoids.add(newBoid);
        }
        EssainBoids swarm = new EssainBoids(newBoids, manager, color);
        manager.addEvent(swarm);

    }
}
