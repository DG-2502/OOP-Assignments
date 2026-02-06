package console;

public class UserConsole extends BasicConsole {
    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)){
            return true;
        }
        return false;
    }

    public void executeCommands() {
        super.executeCommands();
        if (getExitOption()) {
            System.out.println("Logging out of the system");
        }
    }
}
