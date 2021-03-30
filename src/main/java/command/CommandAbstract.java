package command;

/**
 * Abstract command class contains name and description
 */
public class CommandAbstract implements CommandInterface {
    private final String name;
    private final String description;

    public CommandAbstract(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @return Name of the command
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return Description of the command
     */
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void exe(String arg) {

    }
}
