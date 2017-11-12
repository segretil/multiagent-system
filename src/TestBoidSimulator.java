import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;

public class TestBoidSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        // On choisit arbitrairement de créer trois balles par défaut
        ArrayList<Boid> boidstotal = new ArrayList<Boid>();
        ArrayList<Boid> boids1 = new ArrayList<Boid>();
        ArrayList<Boid> boids2= new ArrayList<Boid>();


        EventManager manager = new EventManager();
        int nbBoids = 250;

        for (int i = 0; i < nbBoids; i++) {
            int x = (int) (Math.random() * 500);
            int y = (int) (Math.random() * 500);
            Boid newBoid = new Boid(x, y, Color.WHITE);
            boidstotal.add(newBoid);
            boids1.add(newBoid);
        }
        EssainBoids swarm1 = new EssainBoids(boids1, manager, Color.WHITE);
        manager.addEvent(swarm1);

        for (int i = 0; i < nbBoids; i++) {
            int x = (int) (Math.random() * 500);
            int y = (int) (Math.random() * 500);
            Boid newBoid = new Boid(x, y, Color.RED);
            boidstotal.add(newBoid);
            boids2.add(newBoid);
        }
        EssainBoids swarm2 = new EssainBoids(boids2, manager, Color.RED);
        manager.addEvent(swarm2);

        EssainBoids swarm = new EssainBoids(boidstotal, manager, Color.BLACK);


        gui.setSimulable(new BoidSimulator(swarm, gui, manager));
    }
}
