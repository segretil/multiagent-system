public class TestEventManager {
    public static void main(String[] args) throws InterruptedException {
        EventManager manager = new EventManager();

        for (int i = 2; i <= 10; i += 2) {
            manager.addEvent(new MessageEvent(i, "[Ping]"));
        }

        for (int i = 3; i <= 9; i += 3){
            manager.addEvent(new MessageEvent(i, "[Pong]"));
        }

        while (!manager.isFinished()){
            manager.next();
            Thread.sleep(1000);
        }
    }
}
