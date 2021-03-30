package command;

/**
 * Interface for commands
 */
public interface CommandInterface {
    String getName();

    String getDescription();

    void exe(String arg);
}
