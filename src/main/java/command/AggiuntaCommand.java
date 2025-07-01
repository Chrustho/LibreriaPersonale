package command;

import database.OperazioniPersistenza;
import model.Libro;

/**
 * Classe concreta per l'incapsulamento della richiesta di aggiunta di un libro
 * <p> la sua operazione di undo() equivale alla rimozione del libro stesso (una volta aggiunto) </p>
 */
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
