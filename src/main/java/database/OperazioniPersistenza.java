package database;

import model.Genere;
import model.Libro;
import model.StatoDiLettura;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public abstract class OperazioniPersistenza {
    protected File database;
    private final Collection<Libro> libreria= new ArrayList<>();

    public OperazioniPersistenza(File database){
        this.database=database;
    }

    public Collection<Libro> ottieniLista(){
        return libreria;
    }

    /**
     * Dobbiamo garantire all'interno del sistema di archiviazione le operazioni fondamentali di <Strong> CRUD</Strong>
     * <p> <Strong>C</Strong> create</p>
     * <p> <Strong>R</Strong> read</p>
     * <p> <Strong>U</Strong> update</p>
     * <p> <Strong>D</Strong> delete</p>
     **/
    abstract void estraiDati();

    public boolean aggiungiLibro(Libro nuovoLibro){
        if (libreria.contains(nuovoLibro)){
            System.out.println("In libreria è già presente il libro: "+nuovoLibro+"!");
            return false;
        }
        libreria.add(nuovoLibro);
        return true;
    }

    public abstract void scriviLibro(Libro nuovoLibro);

    public Libro getLibro(String riga, String separatore){
        StringTokenizer st= new StringTokenizer(riga, separatore);
        String titolo= st.nextToken();
        String autore=st.nextToken();
        long isbn= Long.parseLong(st.nextToken());
        Genere genere= Genere.valueOf(st.nextToken());
        int valutazione=Integer.parseInt(st.nextToken());
        StatoDiLettura stato= StatoDiLettura.valueOf(st.nextToken());
        Libro obj= new Libro(new Libro.LibroBuilder().setTitolo(titolo).setAutore(autore).setIsbn(isbn).setGenere(genere).setValutazione(valutazione).setStato(stato));
        return obj;
    }

    public abstract void modificaLibro(Libro l);

    public abstract void eliminaLibro(long isbn);


    public String creaLinea(Libro nuovoLibro, String separatore) {
        String stringBuilder = nuovoLibro.getTitolo() + separatore +
                nuovoLibro.getTitolo() + separatore +
                nuovoLibro.getIsbn() + separatore +
                nuovoLibro.getGenere() + separatore +
                nuovoLibro.getValutazione() + separatore +
                nuovoLibro.getStato() + "\n";
        return stringBuilder;
    }


}
