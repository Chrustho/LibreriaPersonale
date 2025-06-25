package filterChain;

import model.Libro;

import java.util.ArrayList;
import java.util.List;

public class FiltroVoto extends CatenaFiltri{
    private int soglia;

    public FiltroVoto(int soglia){
        this.soglia=soglia;
    }

    @Override
    protected List<Libro> filtraLista(List<Libro> lista) {
        List<Libro> out= new ArrayList<>();
        if (soglia<0){
            return out;
        }
        for (Libro libro : lista) {
            if (libro.getValutazione()>=soglia){
                out.add(libro);
            }
        }
        return out;
    }
}
