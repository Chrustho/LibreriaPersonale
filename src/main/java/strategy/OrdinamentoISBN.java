package strategy;

import model.Libro;
import java.util.Comparator;

public class OrdinamentoISBN implements StrategiaOrdinamento {

    @Override
    public Comparator<? super Libro> creaComparatore() {
        return new Comparator<Libro>() {
            @Override
            public int compare(Libro o1, Libro o2) {return Long.compare(o1.getIsbn(), o2.getIsbn());}
        };
    }
}
