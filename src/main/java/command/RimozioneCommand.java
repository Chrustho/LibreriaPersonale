package command;

import database.OperazioniPersistenza;
import model.Libro;

/**
 * Classe concreta per l'incapsulamento della richiesta di rimozione di un libro
 * <p> la sua operazione di undo() equivale al reinserimento del libro stesso (una volta rimosso) </p>
 */
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
