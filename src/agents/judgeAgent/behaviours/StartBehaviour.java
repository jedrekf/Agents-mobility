package agents.judgeAgent.behaviours;

import agents.judgeAgent.JudgeAgent;
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
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        for (int i=0; i< Counter.getTeam_count(); i++) {
            msg.addReceiver(new AID("agent" + i + '1', AID.ISLOCALNAME)); //agent"#team_no""comp._no"
        }
        msg.setContent("start");
        JudgeAgent.timer.start();
        myAgent.send(msg);
    }
}
