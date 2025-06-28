package facade;

import command.*;
import database.OperazioniPersistenza;
import database.PersistenzaCSV;
import model.CategorieOrdinamento;
import model.Genere;
import model.Libro;
import model.StatoDiLettura;
import strategy.StrategiaOrdinamento;
import utils.Costanti;

import java.io.File;

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


    public boolean aggiungiLibro(Libro libro){
        return historyHandler.handle(new AggiuntaCommand(operazioniPersistenza, libro));
    }

    public boolean modificaLibro(Libro libroVecchio, Libro libroNuovo){
        return historyHandler.handle(new ModificaCommand(operazioniPersistenza, libroVecchio, libroNuovo));
    }

    public boolean rimuoviLibro(Libro libro){
        return historyHandler.handle(new RimozioneCommand(operazioniPersistenza, libro));
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
