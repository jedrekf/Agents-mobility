package judgeAgent;

import judgeAgent.behaviours.ReceivingBehaviour;
import judgeAgent.behaviours.StartBehaviour;
import globals.Counter;
import globals.Timer;
import jade.core.Agent;

/**
 * Created by Jedrek on 2016-04-21.
 */
public class JudgeAgent extends Agent{
    public static Timer timer;
    public static long team_times[] = new long [Counter.getTeam_count()];

    @Override
    protected void setup() {
        addBehaviour(new StartBehaviour());
        timer.start();

        addBehaviour(new ReceivingBehaviour());
    }
}

