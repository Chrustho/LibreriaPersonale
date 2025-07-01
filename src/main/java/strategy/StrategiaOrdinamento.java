package strategy;

import model.Libro;

import java.util.Comparator;
import java.util.List;

/**
 * Una semplice interfaccia per l'ordinamento, ogmni classe che la implementa dovrà implementare il metodo creaComparatore() in maniera coerente
 */
public interface StrategiaOrdinamento {

    default void ordina(List<Libro> libreria, boolean crescente){
       Comparator<? super Libro> comparator= creaComparatore();
       if (crescente){
           libreria.sort(comparator);
       }else {
           libreria.sort(comparator.reversed());
       }
    }

    Comparator<? super Libro> creaComparatore();
}
