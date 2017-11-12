import gui.*;
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
            this.window.addGraphicalElement(new Oval((int) boid.location.x, (int) boid.location.y, boid.getColor(), boid.getColor(), boid.size));
        }
    }

    @Override
    public void next() {
        manager.next();
        afficher();
    }

    @Override
    public void restart(){
        this.softCopy(copy(origin));
        afficher();
    }

    public ArrayList<Boid> copy(ArrayList<Boid> boids){
        ArrayList<Boid> copy = new ArrayList<Boid>();
        for (Boid boid : boids){
            copy.add(new Boid(boid));
        }
        return copy;
    }

    public void softCopy(ArrayList<Boid> boids){
        int compteur = 0;
        ArrayList<Boid> previousBoids = swarm.getBoids();
        for (Boid pBoid : previousBoids){
            pBoid.location = new PVector(boids.get(compteur).location);
            pBoid.velocity = new PVector(boids.get(compteur).velocity);
            pBoid.acceleration = new PVector(boids.get(compteur).acceleration);
            compteur ++;
        }

    }

}
