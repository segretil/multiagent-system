import math;

public class PVector {
    float x;
    float y;

    public PVector(float x, float y){
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

    public float norm(){
        return math.sqrt(x**2 + y**2);
    }

    public void limit(float max){
        float hypothenuse = this.norm();
        if (hypothenuse > max){
            this.y = math.sqrt(h**2 - this.x**2);
        }
    }
}
