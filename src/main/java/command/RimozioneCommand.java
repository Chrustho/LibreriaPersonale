package command;

import database.OperazioniPersistenza;
import model.Libro;

public class RimozioneCommand implements  CommandBase{

    private final Libro libro;
    private final OperazioniPersistenza operazioniPersistenza;

    public RimozioneCommand(OperazioniPersistenza operazioniPersistenza, Libro libro) {
        this.operazioniPersistenza = operazioniPersistenza;
        this.libro = libro;
    }

    @Override
    public boolean execute() {
        return operazioniPersistenza.eliminaLibro(libro);
    }

    @Override
    public boolean undo() {
        return operazioniPersistenza.aggiungiLibro(libro);
    }
}
