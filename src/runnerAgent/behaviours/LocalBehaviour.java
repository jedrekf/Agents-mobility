package runnerAgent.behaviours;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Created by jedrek on 01.05.16.
 */
public class LocalBehaviour extends CyclicBehaviour{

    @Override
    public void action() {
        MessageTemplate msgTemplate = MessageTemplate.MatchSender(new AID("judge", AID.ISLOCALNAME));

        ACLMessage msg = myAgent.receive(msgTemplate);
        if(msg != null) {
            myAgent.addBehaviour(new RunnerBehaviour());
            myAgent.removeBehaviour(this);
        }else{

            block();
        }

        msg = myAgent.receive();
        if(msg != null) {
            ACLMessage replymsg = msg.createReply();
            replymsg.setPerformative(ACLMessage.CONFIRM);
            myAgent.send(replymsg);

            myAgent.addBehaviour(new RunnerBehaviour());
            myAgent.removeBehaviour(this);
        }else{

            block();
        }

    }
}
