import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

public class SchellingStructure extends StructureCell{

    public SchellingStructure(int etats, int step, HashMap<Point, Integer> points,
    Queue <Point > pointSansPop, EventManager manager){
        super(etats, step, points, pointSansPop, manager);
    }

    @Override
    public boolean voisin(Integer etat, Point translate) {
        if (super.cell.containsKey(translate)) {
            if (etat != super.cell.get(translate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void regle(Point point, int etat, int nbreVoisins) {
        HashMap<Point, Integer> futureSchelling = super.futureCell;
        Queue<Point> emptyPoints = super.emptyPoints;
        if (nbreVoisins >= super.seuil){
            futureSchelling.remove(point);
            emptyPoints.add(point);
            Point newPoint = emptyPoints.remove();
            if (newPoint != null){
                futureSchelling.put(newPoint, etat);
            }
        }
    }

    @Override
    public StructureCell copie(){
        // On copie la structure pour pouvoir la garder en memoire
        // Avec la file d'attente emptypoints qui ne se copie pas
        SchellingStructure cop = new SchellingStructure(nbrStates, seuil,
        new HashMap<Point, Integer>(cell), emptyPoints, manager);
        return cop;
    }

    public StructureCell copiedeep(){
        // On copie la structure pour pouvoir la garder en memoire
        // Avec la file d'attente emptypoints qui se copie elle aussi
        SchellingStructure cop = new SchellingStructure(nbrStates, seuil,
        new HashMap<Point, Integer>(cell), new LinkedList<Point>(emptyPoints), manager);
        return cop;
    }
}
