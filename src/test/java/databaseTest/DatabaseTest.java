package databaseTest;

import database.OperazioniPersistenza;
import database.PersistenzaCSV;
import filterChain.CatenaFiltri;
import filterChain.FilterTest;
import filterChain.FiltroGenere;
import model.CategorieOrdinamento;
import model.Genere;
import model.Libro;
import model.StatoDiLettura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Costanti;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    //ho già testato le operazioni di aggiunta/rimozione e modifica con il CommandTest

    private List<Libro> libri= new LinkedList<>();
    private OperazioniPersistenza persistenza=new PersistenzaCSV(new File(Costanti.pathCSV));

    @BeforeEach
    public void setup() {
        persistenza.riempiLibreria();
        libri=persistenza.ottieniLista();
    }

    @Test
    @DisplayName("Test ricerca")
    public void ricerca() {
        String input= "a";
        List<Libro> out= persistenza.filtra(Genere.Seleziona_un_genere,StatoDiLettura.Seleziona_uno_stato, 1, input, CategorieOrdinamento.Autore_crescente);
        System.out.println(out);
        for(Libro libro:out){
            assertEquals(true,libro.getAutore().toLowerCase().contains(input) || libro.getTitolo().toLowerCase().contains(input));
        }
    }

}
