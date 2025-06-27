package command;

import database.OperazioniPersistenza;
import model.Libro;

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

    /**
     * L'operazione di undo consisterà in eliminazione del libro modificato e aggiunta del libro "vecchio"
     * @return
     */
    @Override
    public boolean undo() {
        if (operazioniPersistenza.eliminaLibro(libroModifica)) {
            return operazioniPersistenza.aggiungiLibro(libroVecchio);
        }
        return false;
    }
}
