import java.util.*;

public class Boid {
    PVector location;
    PVector velocity;
    PVector acceleration;
    // Maximum speed
    double maxspeed;
    // Now we also have maximum force.
    double maxforce;

    public Boid(double x, double y){
        location = new PVector(x, y);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        maxspeed = 5;
        maxforce = 0.1;
    }

    public PVector align(ArrayList<Boid> boids){
        PVector sum = new PVector(0,0);
        double voisinsDistanceMax = 20;
        int compteur = 0;
        for (Boid other : boids) {
            double distance = location.distance(other.location);
            if ((distance > 0) && (distance < voisinsDistanceMax)){
                sum.add(other.velocity);
                compteur ++;
            }
        }
        if (compteur > 0){
            sum.div(boids.size());
            sum.setMag(maxspeed);

            sum.sub(velocity);
            sum.limit(maxforce);
            return new PVector(sum);
        } else {
            return new PVector(0, 0);
        }

    }

    public PVector cohesion(ArrayList<Boid> boids){
        PVector sum = new PVector(0,0);
        double voisinsDistanceMax = 20;
        int compteur = 0;
        for (Boid other : boids) {
            double distance = location.distance(other.location);
            if ((distance > 0) && (distance < voisinsDistanceMax)){
                sum.add(other.velocity);
                compteur ++;
            }
        }
        if (compteur > 0){
            sum.div(boids.size());
            sum.setMag(maxspeed);

            sum.sub(velocity);
            sum.limit(maxforce);
            return seek(sum);
        } else {
            return new PVector(0, 0);
        }

    }

    public PVector seek(PVector target){
        PVector voulu = PVector.sub(target, this.location);
        voulu.setMag(maxspeed);
        voulu.sub(velocity);
        PVector force = PVector.sub(voulu, velocity);
        force.limit(maxforce);
        return force;
    }



}
