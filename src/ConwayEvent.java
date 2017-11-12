import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

public class ConwayEvent extends Event {
    HashMap<Point, Integer> conway = new HashMap<Point, Integer>();
    HashMap<Point, Integer> futureConway = new HashMap<Point, Integer>();
    private EventManager manage;

    public ConwayEvent(HashMap<Point, Integer> points, EventManager manager){
        super(0);
        conway = points;
        manage = manager;
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

    @Override
    public void execute(){
        // On execute un tour de passe sur tous les vivants
        this.futureConway = this.copie().conway;
        Set<Point> temp = conway.keySet();
        for (Point point : temp){
            change(point);
        }
        this.conway = this.futureConway;
        this.setDate(getDate() + 1);
        manage.addEvent(this);
    }

    public ConwayEvent copie(){
        // On copie la structure pour pouvoir la garder en memoire
        ConwayEvent cop = new ConwayEvent(
        new HashMap<Point, Integer>(conway), manage);
        return cop;
    }
}
