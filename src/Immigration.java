import java.awt.Point;
import java.util.HashMap;
import java.util.Set;

public class Immigration extends StructureCell {
    private int nbrstates;
    private int seuil;
    HashMap<Point, Integer> immigration = new HashMap<Point, Integer>();
    HashMap<Point, Integer> futureImmigration = new HashMap<Point, Integer>();
    private EventManager manage;

    public Immigration(int etats, int step, HashMap<Point, Integer> points, EventManager manager){
        super(etats, step, points, null, manager);
    }

    @Override
    public boolean voisin(Integer etat, Point translate) {
        if (super.cell.containsKey(translate)) {
            if ((etat+1)% super.nbrStates == super.cell.get(translate)) {
                return true;
           }
        }
        return false;
    }

    @Override
    public void regle(Point point, int etat, int nbreVoisins) {
        if (nbreVoisins >= super.seuil){
            super.futureCell.remove(point);
            super.futureCell.put(point, (etat+1)%super.nbrStates);
        }
    }

    @Override
    public StructureCell copie(){
        // On copie la structure pour pouvoir la garder en memoire
        Immigration cop = new Immigration(nbrstates, seuil,
        new HashMap<Point, Integer>(cell), manage);
        return cop;
    }
}
