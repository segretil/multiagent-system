import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

public abstract class StructureCell extends Event{
    protected int nbrStates;
    protected int seuil;
    HashMap<Point, Integer> cell = new HashMap<Point, Integer>();
    HashMap<Point, Integer> futureCell = new HashMap<Point, Integer>();
    protected Queue <Point > emptyPoints = new LinkedList <Point > ();
    protected EventManager manager;

    public StructureCell(int etats, int step, HashMap<Point, Integer> points,
    Queue <Point > pointSansPop, EventManager manager){
        super(0);
        nbrStates = etats;
        seuil = step;
        cell = points;
        emptyPoints = pointSansPop;
        this.manager = manager;
    }

    public int compteVoisinsEtat(Point point){
        //compte le nombre de voisins du point qui sont dans un etat différent
        int nbVoisins = 0;
        Integer etat = cell.get(point);
        for (int i = - 1; i <=  1; i++) {
            for (int j = - 1; j <= 1; j++) {
                Point translate = new Point(point);
                translate.translate(i, j);
                if (voisin(etat, translate)) {
                    nbVoisins++;
                }
            }
        }
        return nbVoisins;
    }

    public abstract boolean voisin(Integer etat, Point translate);

    public void change(Point point){
        // Change l'etat du point actuel
        int nbreVoisins = this.compteVoisinsEtat(point);
        int etat = cell.get(point);
        regle(point, etat, nbreVoisins);
    }

    public abstract void regle(Point point, int etat, int nbreVoisins);

    @Override
    public void execute(){
        // On execute un tour de passe sur tous les vivants
        this.futureCell = this.copie().cell;
        for (Point point : cell.keySet()){
            change(point);
        }
        this.cell = this.futureCell;
        this.setDate(getDate() + 1);
        manager.addEvent(this);
    }

    public abstract StructureCell copie();

}
