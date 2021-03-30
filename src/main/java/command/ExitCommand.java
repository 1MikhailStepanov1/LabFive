package command;


import utility.Invoker;

/** Exit command
 * Stops command reader. This action stops work of the whole app
 * @see utility.CommandReader
 */
public class ExitCommand extends CommandAbstract {
    private final Invoker invoker;

    /** Command constructor
     * @param invoker - invoker
     */
    public ExitCommand(Invoker invoker) {
        super("Exit", "End programme without saving to the file");
        this.invoker = invoker;
    }

    @Override
    public void exe(String arg) {
        invoker.requestExit(this);
    }
}
