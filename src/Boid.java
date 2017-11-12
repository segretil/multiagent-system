import java.awt.*;
import java.util.*;

public class Boid {
    PVector location;
    PVector velocity;
    PVector acceleration;
    // Maximum speed
    double maxspeed;
    // Now we also have maximum force.
    double maxforce;
    int size;
    private Color color;

    public Boid(double x, double y, Color colour){
        location = new PVector(x, y);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        maxspeed = 4;
        maxforce = 0.1;
        size = 5;
        color = colour;
    }

    public Boid(Boid boid){
        location = new PVector(boid.location);
        velocity = new PVector(boid.velocity);
        acceleration = new PVector(boid.acceleration);
        maxspeed = boid.maxspeed;
        maxforce = boid.maxforce;
        size = boid.size;
        color = boid.color;
    }

    public Color getColor() {
        return color;
    }

    public PVector align(ArrayList<Boid> boids){
        PVector sum = new PVector(0,0);
        double voisinsDistanceMax = 50;
        int compteur = 0;
        for (Boid other : boids) {
            double distance = location.distance(other.location);
            if ((distance > 0) && (distance < voisinsDistanceMax)){
                sum.add(other.velocity);
                compteur ++;
            }
        }
        if (compteur > 0){
            sum.div(compteur);
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
        double voisinsDistanceMax = 50;
        int compteur = 0;
        for (Boid other : boids) {
            double distance = location.distance(other.location);
            if ((distance > 0) && (distance < voisinsDistanceMax)){
                sum.add(other.location);
                compteur ++;
            }
        }
        if (compteur > 0){
            sum.div(compteur);
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

    public void applyRules(ArrayList<Boid> boids){
        PVector cohesion = cohesion(boids);
        PVector align = align(boids);
        PVector separation = separate(boids);

        acceleration = PVector.add(cohesion, align);
        acceleration.add(separation);


        velocity.add(acceleration);
        velocity.limit(maxspeed);
        location.add(velocity);
        if (location.x > 500){
            this.location.x = 0;
        }
        if (location.x < 0){
            this.location.x = 500;
        }
        if (location.y < 0){
            this.location.y = 500;
        }
        if (location.y > 500){
            this.location.y = 0;
        }
        acceleration.mult(0);


    }

    public PVector separate (ArrayList<Boid> boids) {
        float desiredseparation = size*4;
        PVector sum = new PVector(0, 0);
        int count = 0;
        for (Boid other : boids) {
            double d = PVector.distance(location, other.location);
            if ((d > 0) && (d < desiredseparation)) {
                PVector diff = PVector.sub(location, other.location);
                diff.setMag(1/d); //On se separe inversement proportionelment à la distance
                sum.add(diff);
                count++;
            }
        }
        if (count > 0) {
            sum.setMag(maxspeed);
            PVector steer = PVector.sub(sum, velocity);
            steer.limit(maxforce);
            return steer;
        } else {
            return new PVector(0,0);
        }

    }



}
