import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

public class Conway {
    HashMap<Point, Integer> conway = new HashMap<Point, Integer>();
    HashMap<Point, Integer> futureConway = new HashMap<Point, Integer>();

    // j'ai modifier
    public Conway(HashMap<Point, Integer> points){
        conway = points;
    }

    public int comptevoisinsetat(Point point){
        //compte le nombre de voisins du point qui sont dans un etat k+1 (mod n)
        int nbVoisins = 0;
        Integer etat = conway.get(point);
        for (int i = - 1; i <=  1; i++) {
            for (int j = - 1; j <= 1; j++) {
                Point translate = new Point(point);
                translate.translate(i, j);
                if (conway.containsKey(translate)) {
                    if (conway.get(translate) == 1) {
                        nbVoisins ++;
                   }
                }
            }
        }
        if (etat == 1) {
            nbVoisins--;
        }
        return nbVoisins;
    }

    public void change(Point point){
        // Change l'etat du point actuel
        int nbreVoisins = this.comptevoisinsetat(point);
        int etat = conway.get(point);
        if (etat == 1 && nbreVoisins != 2 && nbreVoisins != 3){
            futureConway.remove(point);
            futureConway.put(point, 0);
        } else if (etat == 0 && nbreVoisins == 3) {
            futureConway.remove(point);
            futureConway.put(point, 1);
        }
    }


    public void execute(){
        // On execute un tour de passe sur tous les vivants
        this.futureConway = this.copie().conway;
        Set<Point> temp = conway.keySet();
        for (Point point : temp){
            change(point);
        }
        this.conway = this.futureConway;
    }

    // j'ai modifier
    public Conway copie(){
        // On copie la structure pour pouvoir la garder en memoire
        Conway cop = new Conway(
        new HashMap<Point, Integer>(conway));
        return cop;
    }
}
