package JudgeAgent.behaviours;

import JudgeAgent.JudgeAgent;
import globals.Counter;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by jedrek on 01.05.16.
 */
public class StartBehaviour extends OneShotBehaviour {

    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        for (int i=0; i< Counter.getTeam_count(); i++) {
            msg.addReceiver(new AID("runner" + i, AID.ISLOCALNAME));
        }
        msg.setContent("start");
        myAgent.send(msg);
    }
}
