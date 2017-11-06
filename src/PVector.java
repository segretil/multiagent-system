public class PVector {
    public double x;
    public double y;

    public PVector(double x, double y){
        this.x = x;
        this.y = y;
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
            this.y = Math.sqrt(hypothenuse*hypothenuse - x*x);
        }
    }
}
