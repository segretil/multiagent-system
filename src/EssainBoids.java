import java.util.ArrayList;

public class EssainBoids extends Event{
    private ArrayList<Boid> boids;
    private EventManager manager;

    public EssainBoids(ArrayList<Boid> essain, EventManager manager){
        super(0);
        this.manager = manager;
        this.boids = essain;
    }

    @Override
    public void execute() {
        for (Boid boid : boids){
            boid.applyRules(boids);
        }
        this.setDate(getDate() + 1);
        manager.addEvent(this);
    }

    public void setBoids(ArrayList<Boid> boids) {
        this.boids = boids;
    }

    public ArrayList<Boid> getBoids() {
        return boids;
    }
}
