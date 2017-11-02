import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;




public class SchellingStructure{
    private int nbrstates;
    private int seuil;
    HashMap<Point, Integer> schelling = new HashMap<Point, Integer>();
    HashMap<Point, Integer> futureschelling = new HashMap<Point, Integer>();
    private Queue <Point > emptypoints = new LinkedList <Point > ();

    public SchellingStructure(int etats, int step, HashMap<Point, Integer> points,
    Queue <Point > pointssanspop){
        nbrstates = etats;
        seuil = step;
        schelling = points;
        emptypoints = pointssanspop;
    }

    public int comptevoisinsetat(Point point){
        //compte le nombre de voisins du point qui sont dans un etat différent
        int nbVoisins = 0;
        Integer etat = schelling.get(point);
        for (int i = - 1; i <=  1; i++) {
            for (int j = - 1; j <= 1; j++) {
                Point translate = new Point(point);
                translate.translate(i, j);
                if (schelling.containsKey(translate)) {
                    if (etat != schelling.get(translate)) {
                        nbVoisins ++;
                   }
                }
            }
        }
        return nbVoisins;

    }

    public void change(Point point){
        int nbreVoisins = this.comptevoisinsetat(point);
        int etat = schelling.get(point);
        System.out.println(point);
        System.out.println(nbreVoisins);
        if (nbreVoisins >= seuil){
            futureschelling.remove(point);
            emptypoints.add(point);
            Point newPoint = emptypoints.remove();
            if (newPoint != null){
                futureschelling.put(newPoint, etat);
            }
        }
    }

    public void execute(){
        this.futureschelling = this.copie().schelling;
        Set<Point> temp = schelling.keySet();
        for (Point point : temp){
            change(point);
        }
        this.schelling = this.futureschelling;
    }

    public SchellingStructure copie(){
        SchellingStructure cop = new SchellingStructure(nbrstates, seuil,
        new HashMap<Point, Integer>(schelling), emptypoints);
        return cop;
    }

    public SchellingStructure copiedeep(){
        SchellingStructure cop = new SchellingStructure(nbrstates, seuil,
        new HashMap<Point, Integer>(schelling), new LinkedList<Point>(emptypoints));
        return cop;
    }

}
