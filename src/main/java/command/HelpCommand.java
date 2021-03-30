package command;

import java.util.HashMap;
import java.util.Map;

/** Help command
 * Show all allowed commands and their description
 */
public class HelpCommand extends CommandAbstract {
    private final HashMap<String, CommandInterface> commands;

    /**Command constructor
     * @param commands - hash map from invoker with commands. It is used to use getDescription() method for each command
     * @see utility.Invoker
     */
    public HelpCommand(HashMap<String, CommandInterface> commands) {
        super("help", "Show allowed commands");
        this.commands = commands;
    }

    @Override
    public void exe(String arg) {
        for (Map.Entry<String, CommandInterface> entry : commands.entrySet()) {
            if (entry.getKey().equals("add") | entry.getKey().equals("update_id") | entry.getKey().equals("add_if_max") | entry.getKey().equals("remove_lower") | entry.getKey().equals("remove_greater")) {
                System.out.println("*" + entry.getKey() + " {element}");
            } else if (entry.getKey().equals("remove_by_id")) {
                System.out.println("*" + entry.getKey() + " id");
            } else if (entry.getKey().equals("count_less_than_start_date") | entry.getKey().equals("filter_greater_than_start_date")) {
                System.out.println("*" + entry.getKey() + " startDate");
            } else System.out.println("*" + entry.getKey());
            System.out.println("   " + entry.getValue().getDescription());
        }
    }
}
