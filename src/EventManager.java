import java.util.*;

public class EventManager {
    private long currentDate = 0;
    private HashMap<Long, ArrayList<Event> > events = new HashMap<>();
    private HashMap<Long, ArrayList<Event> > origin = new HashMap<>();


    public void addEvent(Event e){
        if (events.containsKey(e.getDate())){
            events.get(e.getDate()).add(e);
        } else {
            ArrayList<Event> ev = new ArrayList<>();

            ev.add(e);
            origin.put(e.getDate(), ev);
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

    public void restart(){
        currentDate = 0;
        events = new HashMap<>(origin);
    }

}
