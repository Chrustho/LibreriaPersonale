package filterChain;

import model.Libro;
import model.StatoDiLettura;

import java.util.ArrayList;
import java.util.List;

public class FiltroStato extends CatenaFiltri{
    private StatoDiLettura statoDiLettura;


    public FiltroStato(StatoDiLettura statoDiLettura) {
        this.statoDiLettura=statoDiLettura;
    }

    @Override
    protected List<Libro> filtraLista(List<Libro> lista) {
        List<Libro> out= new ArrayList<Libro>();
        if (statoDiLettura.equals(StatoDiLettura.Seleziona_uno_stato)) return lista;
        for (Libro libro : lista) {
            if(libro.getStato()==null){
                continue;
            } else if (libro.getStato().equals(statoDiLettura)) {
                out.add(libro);
            }
        }
        return out;
    }
}
