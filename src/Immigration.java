import java.awt.Point;
import java.util.HashMap;

/** Met en place la structure de l'immigration qui decoule de StructureCell
 *
 */
public class Immigration extends StructureCell {

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
        Immigration cop = new Immigration(super.nbrStates, super.seuil,
        new HashMap<Point, Integer>(super.cell), super.manager);
        return cop;
    }
}
