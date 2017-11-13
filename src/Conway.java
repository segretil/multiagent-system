import java.awt.Point;
import java.util.HashMap;
import java.util.Set;

public class Conway extends StructureCell {

    public Conway(HashMap<Point, Integer> points, EventManager manager){
        super(2, 0, points, null, manager);
    }

    @Override
    public boolean voisin(Integer etat, Point translate) {
        if (super.cell.containsKey(translate) && super.cell.get(translate) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public void regle(Point point, int etat, int nbreVoisins) {
        if (etat == 1 && nbreVoisins != 4 && nbreVoisins != 3){
            super.futureCell.remove(point);
            super.futureCell.put(point, 0);
        } else if (etat == 0 && nbreVoisins == 3) {
            super.futureCell.remove(point);
            super.futureCell.put(point, 1);
        }
    }

    public StructureCell copie(){
        // On copie la structure pour pouvoir la garder en memoire
        Conway cop = new Conway(
        new HashMap<Point, Integer>(cell), super.manager);
        return cop;
    }
}
