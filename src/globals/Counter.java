package globals;

/**
 * Created by jedrek on 01.05.16.
 */
public class Counter {
    private static int lap_count = 2;
    private static int team_count = 1;
    private static int machine_count = 2;

    public static int getLaps() {
        return lap_count;
    }

    public static void setLaps(int _laps) {
        lap_count = _laps;
    }

    public static int getTeam_count() {
        return team_count;
    }

    public static void setTeam_count(int _team_count) {
        team_count = _team_count;
    }


    public static int getMachine_count() {
        return machine_count;
    }

    public static void setMachine_count(int _machine_count) {
        machine_count = _machine_count;
    }
}
