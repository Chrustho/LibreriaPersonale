package strategy;

import model.Libro;
import java.util.Comparator;

/**
 * Se si ordina per valutazione e si incontrano due libri con lo stesso voto, si ordina sulla base del compareTo fra i titoli
 */
public class OrdinamentoValutazione implements StrategiaOrdinamento{
    @Override
    public Comparator<? super Libro> creaComparatore() {
        return new Comparator<Libro>() {
            @Override
            public int compare(Libro o1, Libro o2) {
                if (o1.getValutazione()>o2.getValutazione()){ return 1;}
                if (o1.getValutazione()<o2.getValutazione()){ return -1;}
                return o1.getTitolo().compareTo(o2.getTitolo());
            }
        };
    }
}
