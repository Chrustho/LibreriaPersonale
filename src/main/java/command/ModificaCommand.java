package command;

import database.OperazioniPersistenza;
import model.Libro;

/**
 * Classe concreta per l'incapsulamento della richiesta di modifica di un libro
 * <p> la sua operazione di undo() equivale alla rimozione del libro modificato all'aggiunta del libro precedentemente sosituito </p>
 */
public class ModificaCommand implements CommandBase{

    private final Libro libroModifica;
    private final Libro libroVecchio;
    private final OperazioniPersistenza operazioniPersistenza;

    public ModificaCommand(OperazioniPersistenza operazioniPersistenza, Libro libroModifica, Libro libroVecchio) {
        this.operazioniPersistenza = operazioniPersistenza;
        this.libroVecchio = libroVecchio;
        this.libroModifica = libroModifica;
    }

    @Override
    public boolean execute() {
        return operazioniPersistenza.modificaLibro(libroModifica);
    }


    @Override
    public boolean undo() {
        if (operazioniPersistenza.eliminaLibro(libroModifica)) {
            return operazioniPersistenza.aggiungiLibro(libroVecchio);
        }
        return false;
    }
}
