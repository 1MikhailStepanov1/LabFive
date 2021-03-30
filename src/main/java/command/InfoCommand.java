package command;


import utility.CollectionManager;

/** Info command
 * Show information about collection
 */
public class InfoCommand extends CommandAbstract {
    private final CollectionManager collectionManager;

    /** Command constructor
     * @param collectionManager - collection manager, receiver
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("Info", "Show information about collection (type, initialization time and etc.)");
        this.collectionManager = collectionManager;
    }

    @Override
    public void exe(String arg) {
        for (String line : collectionManager.getInfo()) {
            System.out.println(line);
        }
    }
}
