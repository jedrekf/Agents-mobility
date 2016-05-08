# Agents-mobility
Exercise for testing the mobility of agents in the JADE agent platform.

naming convention: 

Runner/local Agent: "agent" + "#team_number" + "#computer_number"

Judge Agent: "judge"

Each launch has to have proper values in Counter, for this example: lap_count = X , team_count = 1, machine_count = 2

The first Machine has to be launched with 2 agents because the first starts running the one local has to stay behind for a full cycle to be completed.

run the configs one after another(Example for one team and 2 containers)
How to run:

run-conf1: -gui agent00:agents.runnerAgent.RunnerAgent;agent01:agents.runnerAgent.RunnerAgent

run-conf2: -container agent02:agents.runnerAgent.RunnerAgent

After running those you have to manually add the JudgeAgent to the Main Container using the GUI, name it "judge" 
otherwise the race finish and start won't trigger.
