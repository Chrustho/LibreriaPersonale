package command;

/**
 * L'interfaccia sarà implementata da un oggetto che gestirà la storia delle operazioni e le operazioni di undo
 */
public interface CommandHandler {
    boolean handle(CommandBase commandBase);
}
