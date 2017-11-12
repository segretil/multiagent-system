import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;

public class TestBoidSimulator {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        // On choisit arbitrairement de créer trois balles par défaut
        ArrayList<Boid> boids = new ArrayList<Boid>();
        EventManager manager = new EventManager();
        int nbBoids = 250;

        for (int i = 0; i < nbBoids; i++) {
            int x = (int) (Math.random() * 500);
            int y = (int) (Math.random() * 500);
            boids.add(new Boid(x, y));
        }
        EssainBoids swarm = new EssainBoids(boids, manager, Color.WHITE);
        manager.addEvent(swarm);

        gui.setSimulable(new BoidSimulator(swarm, gui, manager));
    }
}
