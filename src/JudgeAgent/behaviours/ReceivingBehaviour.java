package JudgeAgent.behaviours;

import JudgeAgent.JudgeAgent;
import globals.Counter;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by jedrek on 01.05.16.
 */
public class ReceivingBehaviour extends CyclicBehaviour {
    int team_count = 0;
    @Override
    public void action() {

        ACLMessage msg = myAgent.receive();
        if(msg != null) {
            int group_number = Integer.parseInt(msg.getContent()); //receives the "#group number"

            JudgeAgent.team_times[group_number] = JudgeAgent.timer.getElapsedTime();
            System.out.println("team" + group_number + " finished!");
            team_count++;
            if (team_count == Counter.getTeam_count())
                JudgeAgent.timer.stop();

        }else{
            block();
        }
    }
}
