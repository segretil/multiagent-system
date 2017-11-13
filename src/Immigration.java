import java.awt.Point;
import java.util.HashMap;
import java.util.Set;

public class Immigration extends Event {
    private int nbrstates;
    private int seuil;
    HashMap<Point, Integer> immigration = new HashMap<Point, Integer>();
    HashMap<Point, Integer> futureImmigration = new HashMap<Point, Integer>();
    private EventManager manage;

    public Immigration(int etats, int step, HashMap<Point, Integer> points, EventManager manager){
        super(0);
        nbrstates = etats;
        seuil = step;
        immigration = points;
        manage = manager;
    }

    public int comptevoisinsetat(Point point){
        //compte le nombre de voisins du point qui sont dans un etat k+1 (mod n)
        int nbVoisins = 0;
        Integer etat = immigration.get(point);
        for (int i = - 1; i <=  1; i++) {
            for (int j = - 1; j <= 1; j++) {
                Point translate = new Point(point);
                translate.translate(i, j);
                if (immigration.containsKey(translate)) {
                    if ((etat+1)%nbrstates == immigration.get(translate)) {
                        nbVoisins ++;
                   }
                }
            }
        }
        return nbVoisins;
    }

    public int getNbState() {
        return nbrstates;
    }

    public void change(Point point){
        // Change l'etat du point actuel
        int nbreVoisins = this.comptevoisinsetat(point);
        int etat = immigration.get(point);
        if (nbreVoisins >= seuil){
            futureImmigration.remove(point);
            futureImmigration.put(point, (etat+1)%nbrstates);
        }
    }

    @Override
    public void execute(){
        // On execute un tour de passe sur tous les vivants
        this.futureImmigration = this.copie().immigration;
        Set<Point> temp = immigration.keySet();
        for (Point point : temp){
            change(point);
        }
        this.immigration = this.futureImmigration;
        this.setDate(getDate() + 1);
        manage.addEvent(this);
    }

    public Immigration copie(){
        // On copie la structure pour pouvoir la garder en memoire
        Immigration cop = new Immigration(nbrstates, seuil,
        new HashMap<Point, Integer>(immigration), manage);
        return cop;
    }
}
