package RunnerAgent.behaviours;

import JudgeAgent.JudgeAgent;
import globals.Counter;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by jedrek on 01.05.16.
 */
public class MoveBehaviour extends CyclicBehaviour{

    String nextAgent = myAgent.getLocalName();

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null) {


        }else{
            block();
        }
    }
}
