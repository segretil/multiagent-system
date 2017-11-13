import java.awt.*;
import java.util.ArrayList;

/**
 * Crée essaim de Boids qui possède une couleur et un EventManager
 */
public class EssaimBoids extends Event{
    private ArrayList<Boid> boids;
    private EventManager manager;
    private Color color;

    public EssaimBoids(ArrayList<Boid> essain, EventManager manager, Color couleur){
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

}
