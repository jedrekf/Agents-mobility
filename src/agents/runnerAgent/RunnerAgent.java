package agents.runnerAgent;

import jade.core.Agent;
import agents.runnerAgent.behaviours.LocalBehaviour;


/**
 * Created by Jedrek on 2016-04-21.
 */
/*
Runner Agent class depending on context is a local agent or a runner agent( moves to the next PC)
 */
public class RunnerAgent extends Agent {
    public static int lap_counter = 0;
    @Override
    protected void setup() {
        addBehaviour(new LocalBehaviour());
    }

}
