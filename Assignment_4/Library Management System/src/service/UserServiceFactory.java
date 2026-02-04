package service;

public interface UserServiceFactory {
    void executeCommands();
    void readInput();
    boolean getShouldExit();
}
