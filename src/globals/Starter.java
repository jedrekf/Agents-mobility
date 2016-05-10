package globals;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.lang.acl.StringACLCodec;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

/**
 * Created by adam on 5/10/16.
 * Class to start several agents and start race
 */
public class Starter
{
    /**
     * Create proper number of agents and containers.
     *
     * @param numOfTeams number of teams
     * @param numOfLaps number of laps
     * @param numOfMachines number of machines
     */
    private static void Start(int numOfTeams, int numOfLaps, int numOfMachines)
    {

        Counter.setLaps(numOfLaps);
        Counter.setMachine_count(numOfMachines);
        Counter.setTeam_count(numOfTeams);

        Runtime rt = Runtime.instance();

        rt.setCloseVM(true);
        AgentContainer mc = rt.createMainContainer(new ProfileImpl());
        for (int i = 1; i <= numOfMachines; i++){
            Profile p = new ProfileImpl();
            p.setParameter("container-name", "Container"+i);
            // p.setParameter(Profile.MAIN_HOST, "192.168.110.140");
            // p.setParameter(Profile.MAIN_PORT, "1099");
            AgentContainer currContainer = rt.createAgentContainer(p);
            AgentController runnerAgent;

            try {
                for (int j = 0; j<numOfTeams; j++){
                    if (i==1) { //create additional agent on the first container
                        runnerAgent = currContainer.createNewAgent("agent" + String.format("%02d",j) + "0", "agents.runnerAgent.RunnerAgent", null);
                        runnerAgent.start();
                    }
                    runnerAgent = currContainer.createNewAgent("agent"+String.format("%02d",j) +""+i, "agents.runnerAgent.RunnerAgent", null);
                    runnerAgent.start();
                }
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        }

        try{
            AgentController rmaAgent = mc.createNewAgent("rma", "jade.tools.rma.rma", null);
            rmaAgent.start();
            AgentController judgeAgent = mc.createNewAgent("judge", "agents.judgeAgent.JudgeAgent", null);
            judgeAgent.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Start(12,4,4);
    }
}
