# Agents-mobility
Exercise for testing the mobility of agents in the JADE agent platform.

run the configs one after another(Example for one team and 2 containers)
How to run:

run-conf1: -gui agent00:agents.runnerAgent.RunnerAgent;agent01:agents.runnerAgent.RunnerAgent

run-conf2: -container agent02:agents.runnerAgent.RunnerAgent

After running those you have to manually add the JudgeAgent using the GUI, name it "judge" 
otherwise the race finish and start won't trigger.
