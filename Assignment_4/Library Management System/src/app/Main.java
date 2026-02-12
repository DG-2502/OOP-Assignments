import console.AppConsole;
import console.Console;

void main() {
    System.out.println("---Data Management System---");


    Console console = new AppConsole();
    while (!console.getExitOption()) {
        console.run();
    }
}