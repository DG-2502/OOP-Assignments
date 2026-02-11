package console;

import util.MyDate;

public abstract class UserConsole extends BasicConsole {
    public String query;
    public MyDate myDate = MyDate.getInstance();

    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)) {
            return true;
        }
        return false;
    }

    public void executeCommands() {
        super.executeCommands();
        if (getExitOption()) {
            System.out.println("Logging out of the system");
        }
        if (getHelpOption()) {
            System.out.println("exit - Log out of the system");
        }
    }
}
