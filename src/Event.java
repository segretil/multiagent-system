public abstract class Event {
    private long date;

    public Event(long date){
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }


    public abstract void execute();
}
