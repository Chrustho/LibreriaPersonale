package command;

/**
 * Uso dei command che saranno invocati dai bottoni della GUI, scelgo di implementare operazioni di undo e redo
 */
public interface CommandBase {
    boolean execute();
    boolean undo();
}
