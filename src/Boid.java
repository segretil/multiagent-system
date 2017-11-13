import java.awt.*;
import java.util.*;
/**
 * Implémentation de la structure de boid
 */
public class Boid {
    private PVector location;
    private PVector velocity;
    private PVector acceleration;
    // Ne peut pas aller plus que la vitesse maximum
    private double maxspeed;
    //Ne peut pas subire une force supérieure à maxForce
    private double maxforce;
    private int size;
    private Color color;
    //Les 3 attributs suivants servent à faire varier les règles qui s'appliquent sur un boid
    private double coefCoh;
    private double coefAli;
    private double coefSep;

    /**
     * Constructeur général
     *
     * @param x
     *          Abscisse du boid
     * @param y
     *          Ordonnée du boid
     * @param colour
     *          Couleur du boid
     * @param coefficientCohesion
     *          Coefficient pondérant la force de cohesion
     * @param coefficientAlignement
     *          Coefficient pondérant la force d'alignement
     * @param coefficientSeparation
     *          Coefficient pondérant la force de séparation
     */

    public Boid(double x, double y, Color colour, double coefficientCohesion,
                double coefficientAlignement, double coefficientSeparation){
        location = new PVector(x, y);
        velocity = new PVector(0, 0);
        acceleration = new PVector(0, 0);
        maxspeed = 4;
        maxforce = 0.1;
        size = 5;
        color = colour;
        this.coefCoh = coefficientCohesion;
        this.coefAli = coefficientAlignement;
        this.coefSep = coefficientSeparation;
    }

    /**
     * Constructeur pour boid avec des règles de même poids
     * @param x
     *          Abscisse du boid
     * @param y
     *          Ordonnée du boid
     * @param colour
     *          Couleur du boid
     */
    public Boid(double x, double y, Color colour){
        this(x, y, colour, 1, 1, 1);
    }

    /**
     * Constructeur servant pour les deepCopy
     * @param boid
     *          boid à copier
     */
    public Boid(Boid boid){
        location = new PVector(boid.location);
        velocity = new PVector(boid.velocity);
        acceleration = new PVector(boid.acceleration);
        maxspeed = boid.maxspeed;
        maxforce = boid.maxforce;
        size = boid.size;
        color = boid.color;
    }

    public PVector getLocation() {
        return location;
    }

    public int getSize() {
        return size;
    }

    public void setAcceleration(PVector acceleration) {
        this.acceleration = acceleration;
    }

    public void setLocation(PVector location) {
        this.location = location;
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    public PVector getAcceleration() {
        return acceleration;
    }

    public PVector getVelocity() {
        return velocity;
    }

    public Color getColor() {
        return color;
    }

    /**
    Calcul de la force d'alignement
     */
    public PVector align(ArrayList<Boid> boids){
        PVector sum = new PVector(0,0);
        double voisinsDistanceMax = 50;
        int compteur = 0;
        for (Boid other : boids) {
            double distance = location.distance(other.location);
            // On regarde les voisins à une distance de 50 et on fait la moyenne de leur vitesse
            if ((distance > 0) && (distance < voisinsDistanceMax)){
                sum.add(other.velocity);
                compteur ++;
            }
        }
        if (compteur > 0){
            sum.div(compteur);
            //On veut ici que le boid aille à la vitesse maxium vers son objectif
            sum.setMag(maxspeed);
            // Le sub sert à rendre le mouvement plus réaliste
            sum.sub(velocity);
            // On évite ici la divergence
            sum.limit(maxforce);
            return new PVector(sum);
        } else {
            return new PVector(0, 0);
        }

    }
    /**
    Calcul la force de cohesion
    Dans l'idée ressemble beaucoup à align
     */
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
    /**
    Ayant une cible permet de calculer la force pour y arriver
     @param target
     Cible visée
    */
    public PVector seek(PVector target){
        PVector voulu = PVector.sub(target, this.location);
        voulu.setMag(maxspeed);
        voulu.sub(velocity);
        PVector force = PVector.sub(voulu, velocity);
        force.limit(maxforce);
        return force;
    }
    /**
    Applique toutes les règles
    */
    public void applyRules(ArrayList<Boid> boids){
        PVector cohesion = cohesion(boids);
        cohesion.mult(coefCoh);
        PVector align = align(boids);
        align.mult(coefAli);
        PVector separation = separate(boids);
        separation.mult(coefSep);

        acceleration = PVector.add(cohesion, align);
        acceleration.add(separation);

        //Règles du poly
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
    /**
    Calcul la force de séparation
    */
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
