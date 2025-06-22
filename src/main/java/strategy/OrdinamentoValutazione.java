package strategy;

import model.Libro;

import java.util.Comparator;

public class OrdinamentoValutazione implements StrategiaOrdinamento{
    @Override
    public Comparator<? super Libro> creaComparatore() {
        return new Comparator<Libro>() {
            @Override
            public int compare(Libro o1, Libro o2) {
                return Integer.compare(o1.getValutazione(), o2.getValutazione());
            }
        };
    }
}
