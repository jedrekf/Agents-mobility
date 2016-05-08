package agents.runnerAgent.behaviours;

import agents.runnerAgent.RunnerAgent;
import globals.Counter;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Result;
import jade.core.AID;
import jade.core.Location;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.WhereIsAgentAction;
import jade.domain.mobility.MobilityOntology;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.Iterator;

/**
 * Created by jedrek on 01.05.16.
 * myAgent moves to the container of the next agent sends a INFORM awits response and swaps behaviour to local
 */
public class RunnerBehaviour extends OneShotBehaviour{

    AID nextAgent;
    int lap_counter=0;

    /**
     * Returns the name of the next agent (if no. comps <= 9 and no. teams <= 9)
     */
    private AID nextAgentAID(){
        String name = myAgent.getLocalName();
        Integer teamno = Integer.parseInt(name.substring(name.length()-2, name.length()-1));
        Integer compno = Integer.parseInt(name.substring(name.length()-1, name.length()));
        compno++;
        if(compno == Counter.getMachine_count()+1){ //means that the full path was covered has to loop now
            compno = 0;
            lap_counter = ++RunnerAgent.lap_counter;
            System.out.println("lap " + lap_counter + " of team: "+teamno);
            if(lap_counter >= Counter.getLaps()){ //if all the laps were done stop and send stop message to judge
                ACLMessage msgTeamFinished = new ACLMessage(ACLMessage.CANCEL);
                msgTeamFinished.setContent(teamno.toString());
                msgTeamFinished.addReceiver(new AID("judge", AID.ISLOCALNAME));
                myAgent.send(msgTeamFinished);
                return null;
            }
        }

        name = name.substring(0, name.length()-2) + teamno.toString() + compno.toString();
        System.out.println("next agent is: " + name);
        return new AID(name, AID.ISLOCALNAME); // returns the name of the next agent(next computer) belonging to the same team
    }

    private Location parseAMSResponse(ACLMessage response) {
        Result results = null;
        try
        {
            results = (Result)myAgent.getContentManager().extractContent(response);
        }
        catch (UngroundedException e) {}
        catch (Codec.CodecException e) {}
        catch (OntologyException e) {}
        Iterator it = results.getItems().iterator();
        Location loc = null;
        if (it.hasNext())
            loc = (Location) it.next();
        return loc;
    }

    private ACLMessage prepareRequestToAMS(AID agent){
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        msg.setOntology(MobilityOntology.NAME);
        msg.addReceiver(myAgent.getAMS());
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        Action act = new Action();
        act.setActor(myAgent.getAMS());

        WhereIsAgentAction action = new WhereIsAgentAction();
        action.setAgentIdentifier(agent);
        act.setAction(action);

        try{
            myAgent.getContentManager().fillContent(msg, act);
        }
        catch(Codec.CodecException e){} catch (OntologyException e) {
            e.printStackTrace();

        }return msg;

    }

    @Override
    public void action() {

        nextAgent = nextAgentAID();
        if(nextAgent != null) {
            myAgent.getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);
            myAgent.getContentManager().registerOntology(MobilityOntology.getInstance());
            System.out.println(myAgent.getLocalName() + " will attempt to move to " + nextAgent.getLocalName());
            final boolean[] notArrived = {true};

            int step = 0;
            Location destination = null;
            switch (step) {
                case 0:
                    ACLMessage request = prepareRequestToAMS(nextAgent);
                    myAgent.send(request);
                    step++;
                case 1:
                    MessageTemplate mt = MessageTemplate.MatchSender(myAgent.getAMS());

                    while (true) {
                        ACLMessage response = myAgent.receive(mt);
                        if (response != null) {
                            destination = parseAMSResponse(response);
                            step++;
                            break;
                        } else {
                            block();
                        }
                    }
                case 2:
                    myAgent.doMove(destination);
                    System.out.println("moving to " + destination.getName() + "and sending arrived message");
                    step++;
                    ACLMessage msgIarrived = new ACLMessage(ACLMessage.INFORM);
                    msgIarrived.addReceiver(nextAgent);
                    myAgent.send(msgIarrived);

                    ACLMessage msgresponse;
                    while (true) {
                        msgresponse = myAgent.receive();
                        if (msgresponse != null) {
                            break;
                        }
                    }
                    break;
            }

            System.out.println("Agent " + myAgent.getLocalName() + " moved");
        }
        myAgent.addBehaviour(new LocalBehaviour());
        myAgent.removeBehaviour(this);
    }
}
