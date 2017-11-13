import java.awt.Point;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
/**
On définit dans cette classe ce qu'est un automate cellulaire
 */
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

    /** Définit la notion de voisinage
     *
     * @param etat etat courant
     * @param translate un potentiel voisin
     * @return si c'est un voisin ou non
     */
    public abstract boolean voisin(Integer etat, Point translate);

    /** Change d'état selon une certaine règle
     *
     * @param point point à changer
     */
    public void change(Point point){
        // Change l'etat du point actuel
        int nbreVoisins = this.compteVoisinsEtat(point);
        int etat = cell.get(point);
        regle(point, etat, nbreVoisins);
    }

    /** Règle de changement d'état
     *
     * @param point point courant
     * @param etat etat cellule
     * @param nbreVoisins nbVoisins du point
     */
    public abstract void regle(Point point, int etat, int nbreVoisins);

    /** Parcours tout le tableau et applique la règle
     *
     */
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

    /** Fais une copie de l'object et le renvoie
     *
     * @return copie de l'object
     */
    public abstract StructureCell copie();

}
