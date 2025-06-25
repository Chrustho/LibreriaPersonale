package command;

/**
 * L'interfaccia sarà implementata da un oggetto che gestirà la storia delle operazioni ed eventuali undo e redo
 */
public interface CommandHandler {
    boolean handle(CommandBase commandBase);
}
