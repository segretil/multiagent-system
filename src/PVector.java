public class PVector {
    public double x;
    public double y;

    public PVector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public PVector(PVector vector){
       this.x = vector.x;
       this.y = vector.y;
    }

    public void add(PVector vecteur){
        this.x += vecteur.x;
        this.y += vecteur.y;
    }

    public void sub(PVector vecteur){
        this.x -= vecteur.x;
        this.y -= vecteur.y;
    }

    public double norm(){
        return Math.sqrt(x*x + y*y);
    }

    public void limit(double max){
        double hypothenuse = this.norm();
        if (hypothenuse > max){
            setMag(max);
        }
    }

    public void setMag(double max){
        double hypothenuse = this.norm();
        if (hypothenuse == 0){
            this.y = -max;
            this.x = 0;
        } else {
            this.div(hypothenuse);
            this.mult(max);
        }
    }

    public void mult(double lambda){
        this.x *= lambda;
        this.y *= lambda;
    }

    public void div(double lambda){
        this.x /= lambda;
        this.y /= lambda;
    }

    public double distance(PVector vector){
        return Math.sqrt((vector.x - x)*(vector.x - x) + (vector.y - y)*(vector.y - y));
    }

    public static double distance(PVector vector, PVector vector2){
        return Math.sqrt((vector.x - vector2.x)*(vector.x - vector2.x) + (vector.y - vector2.y)*(vector.y - vector2.y));
    }

    public static PVector sub(PVector vector1, PVector vector2){
        return new PVector(vector2.x - vector1.x, vector2.y - vector1.y);
    }

    public static PVector add(PVector vector1, PVector vector2){
        return new PVector(vector2.x + vector1.x, vector2.y + vector1.y);
    }
}
