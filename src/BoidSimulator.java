import gui.*;
import java.awt.Color;
import java.util.*;

public class BoidSimulator implements Simulable{
    private GUISimulator window;
    private EssainBoids swarm;
    private ArrayList<Boid> origin;
    private EventManager manager;

    public BoidSimulator(ArrayList<Boid> boids, GUISimulator window) {
        manager = new EventManager();
        swarm = new EssainBoids(boids, manager);
        manager.addEvent(swarm);
        this.window = window;
        this.origin = copy(boids);
        afficher();
    }

    private void afficher() {
        this.window.reset();
        ArrayList<Boid> boids = swarm.getBoids();
        for (Boid boid : boids) {
            this.window.addGraphicalElement(new Oval((int) boid.location.x, (int) boid.location.y, Color.WHITE, Color.WHITE, boid.size));
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
