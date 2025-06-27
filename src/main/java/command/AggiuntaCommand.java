package command;

import database.OperazioniPersistenza;
import model.Libro;

public class AggiuntaCommand implements CommandBase {
    private final Libro nuovoLibro;
    private final OperazioniPersistenza operazioniPersistenza;

    public AggiuntaCommand(OperazioniPersistenza operazioniPersistenza, Libro nuovoLibro) {
        this.operazioniPersistenza = operazioniPersistenza;
        this.nuovoLibro = nuovoLibro;
    }



    @Override
    public boolean execute() {
        return operazioniPersistenza.aggiungiLibro(nuovoLibro);
    }

    @Override
    public boolean undo() {
        return operazioniPersistenza.eliminaLibro(nuovoLibro);
    }
}
