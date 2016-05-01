package runnerAgent;

import jade.core.Agent;
import runnerAgent.behaviours.RunnerBehaviour;
import runnerAgent.behaviours.LocalBehaviour;


/**
 * Created by Jedrek on 2016-04-21.
 */
/*
Runner Agent class depending on context is a local agent or a runner agent( moves to the next PC)
 */
public class RunnerAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new LocalBehaviour());
    }

}
