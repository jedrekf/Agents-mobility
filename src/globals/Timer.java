package globals;

/**
 * Created by jb on 31/03/16.
 */
public class Timer {
    private static Timer instance = null;
    private long startTime;
    private long stopTime;
    private boolean running;
    private Timer(){
        this.startTime = 0;
        this.stopTime = 0;
        this.running = false;
    }
    public static Timer getInstance(){
        if(instance==null) instance = new Timer();
        return instance;
    }
    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        this.running = false;
    }
    //elaspsed time in milliseconds
    public long getElapsedTime() {
        long elapsed;
        if (running) {
            elapsed = (System.currentTimeMillis() - startTime);
        }
        else {
            elapsed = (stopTime - startTime);
        }
        return elapsed;
    }


    //elaspsed time in seconds
    public long getElapsedTimeSecs() {
        long elapsed;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000);
        }
        else {
            elapsed = ((stopTime - startTime) / 1000);
        }
        return elapsed;
    }

}
