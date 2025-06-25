package filterChain;

import model.Libro;

import java.util.ArrayList;
import java.util.List;

public abstract class CatenaFiltri implements Filtro {
    protected Filtro filtro;

    public Filtro prossimoFiltro(Filtro filtro) {
        this.filtro = filtro;
        return filtro;
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
