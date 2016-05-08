package agents.runnerAgent.behaviours;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Created by jedrek on 01.05.16.
 * Receives messages:
 * If a message comes from judge (start race) it swaps a behaviour to runner
 * If a message comes from runner it responds with confirm and swaps to runner
 */
public class LocalBehaviour extends CyclicBehaviour{


    @Override
    public void action() {
        MessageTemplate msgStartTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

        ACLMessage msgStart = myAgent.receive(msgStartTemplate);
        if(msgStart != null) {
            myAgent.addBehaviour(new RunnerBehaviour());
            System.out.println("started the run");
            myAgent.removeBehaviour(this);
        }else{

            //other messages will not start flowing untill the REQUEST start message from judge doesn't come
            MessageTemplate msgRunnerTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage msgFromRunner = myAgent.receive(msgRunnerTemplate);
            if(msgFromRunner != null) {

                ACLMessage replymsg = msgFromRunner.createReply();
                replymsg.setPerformative(ACLMessage.CONFIRM);
                myAgent.send(replymsg);
                System.out.println(myAgent.getLocalName() + "received message from runner "+
                        msgFromRunner.getSender().getLocalName() + " , confirming and starting to run");
                myAgent.addBehaviour(new RunnerBehaviour());
                myAgent.removeBehaviour(this);
            }else{

               // block();
            }
        }


    }
}
