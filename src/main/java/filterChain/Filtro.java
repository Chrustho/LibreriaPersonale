package filterChain;

import model.Libro;

import java.util.List;

/**
 * Interfaccia base per il filtro, a differenza del semplice ordinamento, qui otterremo una sottolista della libreria originale
 */
public interface Filtro {
    List<Libro> prossimoOutput(List<Libro> lista);
    Filtro prossimoFiltro(Filtro filtro);
}
