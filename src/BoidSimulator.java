import gui.*;
import java.awt.Color;
import java.util.*;

public class BoidSimulator implements Simulable{
    private GUISimulator window;
    ArrayList<Boid> boids;
    ArrayList<Boid> origin;

    public BoidSimulator(ArrayList<Boid> boids, GUISimulator window) {
        // On crée la Schelling des vivants du jeu de la vie
        this.boids = boids;
        this.window = window;
        this.origin = copy(boids);
        afficher();
    }

    private void afficher() {
        // On affiche la Schelling du jeu de la vie
        this.window.reset();
        for (Boid boid : boids) {
            this.window.addGraphicalElement(new Oval((int) boid.location.x, (int) boid.location.y, Color.WHITE, Color.WHITE, boid.size));
        }
    }

    @Override
    public void next() {
        // On passe à l'étape suivante du jeu de la vie
        for (Boid boid : boids){
            boid.applyRules(boids);
        }
        afficher();
    }

    @Override
    public void restart(){
        this.boids = copy(origin);
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
