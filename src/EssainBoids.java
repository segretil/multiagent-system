import java.awt.*;
import java.util.ArrayList;

public class EssainBoids extends Event{
    private ArrayList<Boid> boids;
    private EventManager manager;
    private Color color;

    public EssainBoids(ArrayList<Boid> essain, EventManager manager, Color couleur){
        super(0);
        this.manager = manager;
        this.boids = essain;
        this.color = couleur;
    }

    @Override
    public void execute() {
        for (Boid boid : boids){
            boid.applyRules(boids);
        }
        this.setDate(getDate() + 1);
        manager.addEvent(this);
    }

    public Color getColor() {
        return color;
    }

    public void setBoids(ArrayList<Boid> boids) {
        this.boids = boids;
    }

    public ArrayList<Boid> getBoids() {
        return boids;
    }
}
