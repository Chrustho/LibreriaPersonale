package command;

import database.OperazioniPersistenza;
import database.PersistenzaCSV;
import model.Genere;
import model.Libro;
import model.StatoDiLettura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import strategy.OrdinamentoTitolo;
import utils.Costanti;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    private List<Libro> libri=new ArrayList<Libro>();
    private OperazioniPersistenza persisten=new PersistenzaCSV(new File(Costanti.pathCSV));
    private HistoryHandler handler=new HistoryHandler(50);
    private CommandBase cmd;

    @BeforeEach
    public void setup() {
        persisten.riempiLibreria();
        libri=persisten.ottieniLista();
    }

    @Test
    @Order(1)
    @DisplayName("Aggiunta libro")
    public void aggiunta() {
        Libro nuovo= new Libro("prova", "prova", 1234,Genere.Saggio,2, StatoDiLettura.In_Lettura);
        cmd=new AggiuntaCommand(persisten,nuovo);
        handler.handle(cmd);
        persisten.riempiLibreria();
        libri=persisten.ottieniLista();
        for (Libro libro : libri) {
            if (libro.getIsbn()==nuovo.getIsbn()) {
                assertEquals(libro,nuovo);
            }
        }
    }

    @Test
    @Order(2)
    @DisplayName("Modifica libro")
    public void modifica() {
        Libro nuovo= new Libro("prova", "prova", 1234,Genere.Saggio,2, StatoDiLettura.In_Lettura);
        Libro mod=new Libro("prova", "test", 1234,Genere.Saggio,2, StatoDiLettura.In_Lettura);
        cmd=new ModificaCommand(persisten,mod, nuovo);
        handler.handle(cmd);
        persisten.riempiLibreria();
        libri=persisten.ottieniLista();
        for (Libro libro : libri) {
            if (libro.getIsbn()==nuovo.getIsbn()) {
                assertEquals("test",libro.getAutore());
            }
        }
    }



    @Test
    @Order(3)
    @DisplayName("Rimozione libro")
    public void rimozione() {
        Libro nuovo= new Libro("prova", "prova", 1234,Genere.Saggio,2, StatoDiLettura.In_Lettura);
        cmd=new RimozioneCommand(persisten,nuovo);
        handler.handle(cmd);
        persisten.riempiLibreria();
        libri=persisten.ottieniLista();
        for (Libro libro : libri) {
            if (libro.getIsbn()==nuovo.getIsbn()) {
                assertEquals(libro,nuovo);
            }
        }
    }


}
