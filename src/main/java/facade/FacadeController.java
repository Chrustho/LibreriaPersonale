package facade;

import command.AggiuntaCommand;
import command.HistoryHandler;
import command.ModificaCommand;
import command.RimozioneCommand;
import database.OperazioniPersistenza;
import database.PersistenzaCSV;
import filterChain.Filtro;
import filterChain.FiltroGenere;
import filterChain.FiltroStato;
import filterChain.FiltroVoto;
import model.CategorieOrdinamento;
import model.Genere;
import model.Libro;
import model.StatoDiLettura;
import strategy.StrategiaOrdinamento;
import utils.Costanti;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FacadeController {
    private final OperazioniPersistenza operazioniPersistenza=new PersistenzaCSV(new File(Costanti.pathCSV));
    private final HistoryHandler historyHandler=new HistoryHandler(50);

    public FacadeController(){
        operazioniPersistenza.riempiLibreria();
    }

    public List<Libro> getLibri() {
        return operazioniPersistenza.ottieniLista();
    }


    public void aggiungiLibro(Libro libro){
        historyHandler.handle(new AggiuntaCommand(operazioniPersistenza, libro));
    }

    public void modificaLibro(Libro libroVecchio, Libro libroNuovo){
        historyHandler.handle(new ModificaCommand(operazioniPersistenza, libroVecchio, libroNuovo));
    }

    public void rimuoviLibro(Libro libro){
        historyHandler.handle(new RimozioneCommand(operazioniPersistenza, libro));
    }

    public void undo(){
        historyHandler.undo();
    }


    public List<Libro> ordina(List<Libro> libri, StrategiaOrdinamento ordinamento, boolean crescente){
        return operazioniPersistenza.ordina(libri,ordinamento, crescente);
    }

    public List<Libro> filtra(Genere genere, StatoDiLettura statoDiLettura, int voto, String input, CategorieOrdinamento categorieOrdinamento){
        return operazioniPersistenza.filtra(genere, statoDiLettura, voto, input,categorieOrdinamento);
    }


}
