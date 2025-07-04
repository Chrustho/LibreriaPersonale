package filterChain;

import command.AggiuntaCommand;
import command.CommandBase;
import command.CommandHandler;
import model.CategorieOrdinamento;
import model.Genere;
import model.Libro;
import model.StatoDiLettura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.OrdinamentoTitolo;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilterTest {
    private List<Libro> libri= new LinkedList<>();
    private CatenaFiltri catenaFiltri;

    @BeforeEach
    public void setup() {
        libri.add(new Libro("a","a",1, Genere.Saggio,4, StatoDiLettura.Letto));
        libri.add(new Libro("b","b",2, Genere.Saggio,5, StatoDiLettura.Da_Leggere));
        libri.add(new Libro("c","c",3, Genere.Avventura,3, StatoDiLettura.Da_Leggere));
        libri.add(new Libro("d","d",4, Genere.Horror,3, StatoDiLettura.Letto));
        libri.add(new Libro("e","e",5, Genere.Manuale_Scolastico,3, StatoDiLettura.Da_Leggere));
    }

    @Test
    @DisplayName("Filtro genere")
    public void filtroGenere() {
        catenaFiltri=new FiltroGenere(Genere.Saggio);
        List<Libro> out= catenaFiltri.filtraLista(libri);
        assertEquals(2, out.size());
        for(Libro libro:out){
            assertEquals(Genere.Saggio,libro.getGenere());
        }
    }

    @Test
    @DisplayName("Filtro genere fallito")
    public void filtroGenereF() {
        catenaFiltri=new FiltroGenere(Genere.Commedia);
        List<Libro> out= catenaFiltri.filtraLista(libri);
        assertEquals(2, out.size());
        for(Libro libro:out){
            assertEquals(Genere.Saggio,libro.getGenere());
        }
    }

    @Test
    @DisplayName("Filtro voto")
    public void filtroVoto() {
        catenaFiltri=new FiltroVoto(5);
        List<Libro> out= catenaFiltri.filtraLista(libri);
        assertEquals(1, out.size());
        for(Libro libro:out){
            assertEquals(5,libro.getValutazione());
        }
    }

    @Test
    @DisplayName("Filtro voto falito")
    public void filtroVotoF() {
        catenaFiltri=new FiltroVoto(2);
        List<Libro> out= catenaFiltri.filtraLista(libri);
        assertEquals(1, out.size());
        for(Libro libro:out){
            assertEquals(2,libro.getValutazione());
        }
    }

    @Test
    @DisplayName("Filtro stato")
    public void filtroStato() {
        catenaFiltri=new FiltroStato(StatoDiLettura.Letto);
        List<Libro> out= catenaFiltri.filtraLista(libri);
        assertEquals(2, out.size());
        for(Libro libro:out){
            assertEquals(StatoDiLettura.Letto,libro.getStato());
        }
    }

    @Test
    @DisplayName("Filtro stato fallito")
    public void filtroStatoF() {
        catenaFiltri=new FiltroStato(StatoDiLettura.In_Lettura);
        List<Libro> out= catenaFiltri.filtraLista(libri);
        assertEquals(0, out.size());
        for(Libro libro:out){
            assertEquals(StatoDiLettura.In_Lettura,libro.getStato());
        }
    }






}
