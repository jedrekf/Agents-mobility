package runnerAgent.behaviours;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import runnerAgent.RunnerAgent;
/**
 * Created by jedrek on 01.05.16.
 */
public class RunnerBehaviour extends OneShotBehaviour{


    public RunnerBehaviour(){


    }

    @Override
    public void action() {
        ACLMessage msgIarrived = new ACLMessage(ACLMessage.INFORM);
        myAgent.send(msgIarrived);

        ACLMessage msgresponse = myAgent.receive();


    }
}
