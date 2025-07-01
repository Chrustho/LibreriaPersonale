package filterChain;

import model.Libro;

import java.util.List;

/**
 * Classe astratta utilizzata per la definizione della catena di filtri
 */
public abstract class CatenaFiltri implements Filtro {
    protected Filtro filtro;

    public void prossimoFiltro(Filtro filtro) {
        this.filtro = filtro;
    }

    @Override
    public List<Libro> prossimoOutput(List<Libro> lista) {
        List<Libro> out = filtraLista(lista);
        if (filtro!=null) {
            return filtro.prossimoOutput(out);
        }
        return out;
    }

    protected abstract List<Libro> filtraLista(List<Libro> lista);
}
