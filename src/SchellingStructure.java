import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;




public class SchellingStructure extends Event{
    private int nbrstates;
    private int seuil;
    HashMap<Point, Integer> schelling = new HashMap<Point, Integer>();
    HashMap<Point, Integer> futureschelling = new HashMap<Point, Integer>();
    private Queue <Point > emptypoints = new LinkedList <Point > ();
    private EventManager manager;

    public SchellingStructure(int etats, int step, HashMap<Point, Integer> points,
    Queue <Point > pointssanspop, EventManager manager){
        super(0);
        nbrstates = etats;
        seuil = step;
        schelling = points;
        emptypoints = pointssanspop;
        this.manager = manager;
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
        // Change l'etat du point actuel
        int nbreVoisins = this.comptevoisinsetat(point);
        int etat = schelling.get(point);
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
        // On execute un tour de passe sur tous les vivants
        this.futureschelling = this.copie().schelling;
        Set<Point> temp = schelling.keySet();
        for (Point point : temp){
            change(point);
        }
        this.schelling = this.futureschelling;
        this.setDate(getDate() + 1);
        manager.addEvent(this);
    }

    public SchellingStructure copie(){
        // On copie la structure pour pouvoir la garder en memoire
        // Avec la file d'attente emptypoints qui ne se copie pas
        SchellingStructure cop = new SchellingStructure(nbrstates, seuil,
        new HashMap<Point, Integer>(schelling), emptypoints, manager);
        return cop;
    }

    public SchellingStructure copiedeep(){
        // On copie la structure pour pouvoir la garder en memoire
        // Avec la file d'attente emptypoints qui se copie elle aussi
        SchellingStructure cop = new SchellingStructure(nbrstates, seuil,
        new HashMap<Point, Integer>(schelling), new LinkedList<Point>(emptypoints), manager);
        return cop;
    }

}
