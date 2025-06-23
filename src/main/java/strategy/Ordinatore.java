package strategy;

import model.Libro;

import java.util.List;

public class Ordinatore {
    private final List<Libro> libreria;
    private StrategiaOrdinamento strategiaOrdinamento;

    /**
     * La strategia di ordinamento di defautl sarà quella per il titolo, ogni filtro imposterà la strategia appropriata
     **/
    public Ordinatore(List<Libro> libreria) {
        this.libreria = libreria;
        strategiaOrdinamento= new OrdinamentoTitolo();
    }

    public void setStrategiaOrdinamento(StrategiaOrdinamento strategiaOrdinamento) {
        this.strategiaOrdinamento = strategiaOrdinamento;
    }

    /**
     * Ogni lista sarà di default ordinata in senso crescente
     */
    public void ordinaLibreria() {
        strategiaOrdinamento.ordina(libreria,true);
    }
}
