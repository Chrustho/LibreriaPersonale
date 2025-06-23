package strategy;

import model.Libro;
import java.util.Comparator;

public class OrdinamentoAutore implements StrategiaOrdinamento{

    @Override
    public Comparator<? super Libro> creaComparatore() {
        return new Comparator<Libro>() {
            @Override
            public int compare(Libro o1, Libro o2) {
                return o1.getAutore().compareTo(o2.getAutore());
            }
        };
    }
}
