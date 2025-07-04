package strategyTest;



import model.Genere;
import model.Libro;
import model.StatoDiLettura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.OrdinamentoTitolo;
import strategy.StrategiaOrdinamento;
import strategy.OrdinamentoAutore;
import strategy.OrdinamentoISBN;


import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrategyTest {
    private List<Libro> libri= new LinkedList<>();
    private StrategiaOrdinamento strategiaOrdinamento;

    
    @BeforeEach
    public void setup() {
        libri.add(new Libro("a","a",1, Genere.Saggio,4, StatoDiLettura.Da_Leggere));
        libri.add(new Libro("b","b",2, Genere.Saggio,5, StatoDiLettura.Da_Leggere));
        libri.add(new Libro("c","c",3, Genere.Saggio,3, StatoDiLettura.Da_Leggere));
        libri.add(new Libro("d","d",4, Genere.Saggio,3, StatoDiLettura.Da_Leggere));
        libri.add(new Libro("e","e",5, Genere.Saggio,3, StatoDiLettura.Da_Leggere));
    }

    @Test
    @DisplayName("Ordinamento titolo crescente")
    public void titoloCrescente() {
        strategiaOrdinamento=new OrdinamentoTitolo();
        boolean cresc=true;
        strategiaOrdinamento.ordina(libri,cresc);
        assertEquals("a",libri.get(0).getTitolo());
        assertEquals("b",libri.get(1).getTitolo());
        assertEquals("c",libri.get(2).getTitolo());
        assertEquals("d",libri.get(3).getTitolo());
        assertEquals("e",libri.get(4).getTitolo());
    }

    @Test
    @DisplayName("Ordinamento titolo crescente con Fallimento")
    public void titoloCrescenteF() {
        strategiaOrdinamento=new OrdinamentoTitolo();
        boolean cresc=true;
        strategiaOrdinamento.ordina(libri,cresc);
        assertEquals("a",libri.get(0).getTitolo());
        assertEquals("b",libri.get(2).getTitolo());
        assertEquals("c",libri.get(1).getTitolo());
        assertEquals("d",libri.get(3).getTitolo());
        assertEquals("e",libri.get(4).getTitolo());
    }



    @Test
    @DisplayName("Ordinamento titolo decrescente")
    public void titoloDecrescente() {
        strategiaOrdinamento=new OrdinamentoTitolo();
        boolean cresc=false;
        strategiaOrdinamento.ordina(libri,cresc);
        assertEquals("a",libri.get(4).getTitolo());
        assertEquals("b",libri.get(3).getTitolo());
        assertEquals("c",libri.get(2).getTitolo());
        assertEquals("d",libri.get(1).getTitolo());
        assertEquals("e",libri.get(0).getTitolo());
    }

    @Test
    @DisplayName("Ordinamento autore crescente")
    public void autoreCrescente() {
        strategiaOrdinamento=new OrdinamentoAutore();
        boolean cresc=true;
        strategiaOrdinamento.ordina(libri,cresc);
        assertEquals("a",libri.get(0).getAutore());
        assertEquals("b",libri.get(1).getAutore());
        assertEquals("c",libri.get(2).getAutore());
        assertEquals("d",libri.get(3).getAutore());
        assertEquals("e",libri.get(4).getAutore());
    }

    @Test
    @DisplayName("Ordinamento autore decrescente")
    public void autoreDecrescente() {
        strategiaOrdinamento=new OrdinamentoAutore();
        boolean cresc=false;
        strategiaOrdinamento.ordina(libri,cresc);
        assertEquals("a",libri.get(4).getAutore());
        assertEquals("b",libri.get(3).getAutore());
        assertEquals("c",libri.get(2).getAutore());
        assertEquals("d",libri.get(1).getAutore());
        assertEquals("e",libri.get(0).getAutore());
    }


    @Test
    @DisplayName("Ordinamento ISBN crescente")
    public void isbnCrescente() {
        strategiaOrdinamento=new OrdinamentoISBN();
        boolean cresc=true;
        strategiaOrdinamento.ordina(libri,cresc);
        assertEquals(1,libri.get(0).getIsbn());
        assertEquals(2,libri.get(1).getIsbn());
        assertEquals(3,libri.get(2).getIsbn());
        assertEquals(4,libri.get(3).getIsbn());
        assertEquals(5,libri.get(4).getIsbn());
    }

    @Test
    @DisplayName("Ordinamento ISBN decrescente")
    public void isbnDecrescente() {
        strategiaOrdinamento=new OrdinamentoISBN();
        boolean cresc=false;
        strategiaOrdinamento.ordina(libri,cresc);
        assertEquals(5,libri.get(0).getIsbn());
        assertEquals(4,libri.get(1).getIsbn());
        assertEquals(3,libri.get(2).getIsbn());
        assertEquals(2,libri.get(3).getIsbn());
        assertEquals(1,libri.get(4).getIsbn());
    }





}
