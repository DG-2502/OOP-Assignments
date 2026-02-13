package console;

import util.MyDate;

public abstract class UserConsole extends BasicConsole {
    public String query;
    public MyDate myDate = MyDate.getInstance();
    public boolean nextDayOption = false;

    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)) {
            return true;
        }
        if (command.equals("nextday")) {
            query = option;
            return nextDayOption = true;
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
            System.out.println("nextday none/number - Move on to the next day");
        }
        if (nextDayOption) {
            if (query.matches("\\d+")) {
                myDate.nextDay(Integer.parseInt(query));
            } else {
                myDate.nextDay(1);
            }
            System.out.println("The date is: " + myDate.getDate());
            nextDayOption = false;
        }
    }
}
