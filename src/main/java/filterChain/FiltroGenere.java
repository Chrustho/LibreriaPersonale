package filterChain;

import model.Genere;
import model.Libro;

import java.util.ArrayList;
import java.util.List;

/**
 * Semplice filtro per genere letterario
 * Se il genere "selezionato" risulta essere quello di default allora verrà semplicemente restituita la lista passata in input
 */
public class FiltroGenere extends CatenaFiltri{

    private final Genere genere;

    public FiltroGenere(Genere genere) {
        this.genere = genere;
    }


    @Override
    protected List<Libro> filtraLista(List<Libro> lista) {
        List<Libro> out = new ArrayList<Libro>();
        if (genere.equals(Genere.Seleziona_un_genere)) return lista;
        for (Libro libro : lista) {
            if (libro.getGenere() == null) {
                break;
            } else if (libro.getGenere().equals(genere)) {
                out.add(libro);
            }
        }
        return out;
    }
}
