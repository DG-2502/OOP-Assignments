import console.AppConsole;
import console.Console;
import util.MyDate;

void main() {
    System.out.println("---Data Management System---");

//    MyDate myDate = MyDate.getInstance();
//    System.out.println(myDate.getDate());
//    myDate.nextDay();
//    System.out.println(myDate.inDays(10));
//    System.out.println(myDate.getDate());

    Console console = new AppConsole();
    while (!console.getExitOption()) {
        console.run();
    }
}