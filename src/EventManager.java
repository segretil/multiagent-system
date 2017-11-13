import java.util.*;

/**
 * Implémentation de l'éventManager
 */
public class EventManager {
    private long currentDate = 0;
    private HashMap<Long, ArrayList<Event> > events = new HashMap<>();

    /**Ajoute les évenements
     *
     * @param e un evenement
     */
    public void addEvent(Event e){
        //Si la date existe on ajoute l'événement à la liste chainée
        if (events.containsKey(e.getDate())){
            events.get(e.getDate()).add(e);

            // SInon on crée la liste chainée et on l'ajoute
        } else {
            ArrayList<Event> ev = new ArrayList<>();

            ev.add(e);
            events.put(e.getDate(), ev);
        }
    }

    public void next(){
        if (events.containsKey(currentDate)){
            for (Event event : events.get(currentDate)){
                event.execute();
            }
            events.remove(currentDate);
        }
        currentDate ++;
    }

    public boolean isFinished(){
        return events.isEmpty();
    }

    /**
     * On réinitialise le dictionnaire à 0
     */
    public void restart(){
        currentDate = 0;
        events = new HashMap<>();
    }

}
