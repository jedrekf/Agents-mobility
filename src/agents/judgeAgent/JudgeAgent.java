package agents.judgeAgent;

import agents.judgeAgent.behaviours.ReceivingBehaviour;
import agents.judgeAgent.behaviours.StartBehaviour;
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
        System.out.println("launch");
        addBehaviour(new StartBehaviour());
        //timer.start();
        System.out.println("timer start");

        addBehaviour(new ReceivingBehaviour());
    }
}

