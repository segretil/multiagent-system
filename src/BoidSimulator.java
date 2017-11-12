import gui.*;
import java.awt.Color;
import java.util.*;

public class BoidSimulator implements Simulable{
    private GUISimulator window;
    private EssainBoids swarm;
    private ArrayList<Boid> origin;
    private EventManager manager;

    public BoidSimulator(EssainBoids essain, GUISimulator window, EventManager manager) {
        this.manager = manager;
        swarm = essain;
        this.window = window;
        this.origin = copy(swarm.getBoids());
        afficher();
    }

    private void afficher() {
        this.window.reset();
        ArrayList<Boid> boids = swarm.getBoids();
        for (Boid boid : boids) {
            this.window.addGraphicalElement(new Oval((int) boid.location.x, (int) boid.location.y, swarm.getColor(), swarm.getColor(), boid.size));
        }
    }

    @Override
    public void next() {
        manager.next();
        afficher();
    }

    @Override
    public void restart(){
        swarm.setBoids(copy(origin));
        manager.restart();
        afficher();
    }

    public ArrayList<Boid> copy(ArrayList<Boid> boids){
        ArrayList<Boid> copy = new ArrayList<Boid>();
        for (Boid boid : boids){
            copy.add(new Boid(boid));
        }
        return copy;
    }

}
