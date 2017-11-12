import gui.*;
import java.util.*;

public class BoidSimulator implements Simulable{
    private GUISimulator window;
    private ArrayList<Boid> boidsTotal;
    private ArrayList<Boid> origin;
    private EventManager manager;

    public BoidSimulator(ArrayList<Boid> boidsTot, GUISimulator window, EventManager manager) {
        this.manager = manager;
        // Ensemble de tous les boids
        this.boidsTotal = boidsTot;
        this.window = window;
        this.origin = copy(boidsTotal);
        afficher();
    }

    private void afficher() {
        /*
        Affichage graphique des boids
         */
        this.window.reset();
        for (Boid boid : boidsTotal) {
            this.window.addGraphicalElement(new Oval((int) boid.getLocation().x, (int) boid.getLocation().y, boid.getColor(), boid.getColor(), boid.getSize()));
        }
    }

    @Override
    public void next() {
        manager.next();
        afficher();
    }

    @Override
    public void restart(){
        /*
        Ici on ne reset pas le manager car sinon on a plus accès aux différentes populations des boids
         */
        this.softCopy(copy(origin));
        afficher();
    }

    private ArrayList<Boid> copy(ArrayList<Boid> boids){
        ArrayList<Boid> copy = new ArrayList<Boid>();
        for (Boid boid : boids){
            copy.add(new Boid(boid));
        }
        return copy;
    }

    private void softCopy(ArrayList<Boid> boids){
        int compteur = 0;
        for (Boid pBoid : boidsTotal){
            pBoid.setLocation(new PVector(boids.get(compteur).getLocation()));
            pBoid.setVelocity(new PVector(boids.get(compteur).getVelocity()));
            pBoid.setAcceleration(new PVector(boids.get(compteur).getAcceleration()));
            compteur ++;
        }

    }

}
