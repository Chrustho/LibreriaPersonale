package database;

import filterChain.Filtro;
import filterChain.FiltroGenere;
import filterChain.FiltroStato;
import filterChain.FiltroVoto;
import model.CategorieOrdinamento;
import model.Genere;
import model.Libro;
import model.StatoDiLettura;
import strategy.*;

import java.io.File;
import java.util.*;

public abstract class OperazioniPersistenza {
    protected File database;
    private final List<Libro> libreria= new ArrayList<>();

    public OperazioniPersistenza(File database){
        this.database=database;
    }

    public List<Libro> ottieniLista(){
        return libreria;
    }

    public void riempiLibreria(){
        estraiDati();
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
            System.out.println("In libreria è già presente il libro: "+nuovoLibro.getTitolo()+"di "+nuovoLibro.getAutore()+"!");
            return false;
        }
        scriviLibro(nuovoLibro);
        riempiLibreria();
        return true;
    }

    public List<Libro> cerca(String input){
        if (input.isEmpty()){
            return new LinkedList<>(ottieniLista());
        }
        List<Libro> res = new LinkedList<>();
        for (Libro libro : ottieniLista()){
            if (libro.getTitolo().toLowerCase().contains(input) || libro.getAutore().toLowerCase().contains(input)){
                res.add(libro);
            }
        }
        return res;
    }

    public abstract void scriviLibro(Libro nuovoLibro);

    public Libro getLibro(String riga, String separatore){
        StringTokenizer st= new StringTokenizer(riga, separatore);
        String titolo=st.nextToken();
        String autore=st.nextToken();
        long isbn= Long.parseLong(st.nextToken());
        Genere genere= Genere.valueOf(st.nextToken());
        int valutazione=Integer.parseInt(st.nextToken());
        StatoDiLettura stato= StatoDiLettura.valueOf(st.nextToken());
        Libro obj= new Libro(titolo,autore,isbn,genere,valutazione,stato);
        return obj;
    }

    public boolean modificaLibro(Libro l){
        if (l==null){
            System.out.println("Inserisci un libro valido!");
            return false;
        }
        if (!libreria.contains(l)){
            System.out.println(l+ " non esiste! Si prega di aggiungerlo tramite il bottone apposito");
            return false;
        }
        ListIterator<Libro> iter= libreria.listIterator();
        while (iter.hasNext()){
            Libro obj= iter.next();
            if (obj.equals(l)){
                iter.set(l);
                riscriviFile();
                return true;
            }
        }
        return false;
    }

    public abstract void riscriviFile();

    public boolean eliminaLibro(Libro l){
        if (l==null){
            System.out.println("Inserisci un libro valido!");
            return false;
        }
        if (!libreria.contains(l)){
            System.out.println("Libro già eliminato!");
            return false;
        }
        ListIterator<Libro> iter= libreria.listIterator();
        while (iter.hasNext()){
            Libro obj= iter.next();
            if (obj.equals(l)){
                iter.remove();
                riscriviFile();
                return true;
            }
        }
        return false;
    }


    public String creaLinea(Libro nuovoLibro, String separatore) {
        String stringBuilder = nuovoLibro.getTitolo() + separatore +
                nuovoLibro.getAutore() + separatore +
                nuovoLibro.getIsbn() + separatore +
                nuovoLibro.getGenere() + separatore +
                nuovoLibro.getValutazione() + separatore +
                nuovoLibro.getStato();
        return stringBuilder;
    }

    public List<Libro> ordina(List<Libro> libri, StrategiaOrdinamento ordinamento, boolean crescente){
        List<Libro> res;
        if (libri==null) {
            res = new LinkedList<>(ottieniLista());
        }else {
            res= new LinkedList<>(libri);
        }
        ordinamento.ordina(res,crescente);
        return res;
    }


    public List<Libro> filtra(Genere genere, StatoDiLettura statoDiLettura, int voto, String input, CategorieOrdinamento categoria){
        List<Libro> temp;
        if(input!=null){
            temp=this.cerca(input);
        }else {
            temp=new LinkedList<>(ottieniLista());
        }
        Filtro primoFiltro=new FiltroGenere(genere);
        Filtro secondoFilltro=new FiltroStato(statoDiLettura);
        Filtro terzoFiltro=new FiltroVoto(voto);
        primoFiltro.prossimoFiltro(secondoFilltro);
        secondoFilltro.prossimoFiltro(terzoFiltro);
        List<Libro> preOrdinamento=primoFiltro.prossimoOutput(temp);
        return ordinamento(preOrdinamento,categoria);
    }

    private List<Libro> ordinamento(List<Libro> libri, CategorieOrdinamento categoria) {
        List<Libro> out;
        switch (categoria) {
            case Voto_crescente -> {
                out=ordina(libri,new OrdinamentoValutazione(),true);
            }
            case Voto_decrescente -> {
                out=ordina(libri,new OrdinamentoValutazione(),false);
            }
            case Titolo_crescente -> {
                out=ordina(libri,new OrdinamentoTitolo(),true);
            }
            case Titolo_decrescente -> {
                out=ordina(libri,new OrdinamentoTitolo(),false);
            }
            case Autore_crescente -> {
                out=ordina(libri,new OrdinamentoAutore(),true);
            }
            case Autore_decrescente -> {
                out=ordina(libri,new OrdinamentoAutore(),false);
            }
            case ISBN_Crescente ->{
                out=ordina(libri,new OrdinamentoISBN(),true);
            }
            case ISBN_decrescente ->{
                out=ordina(libri,new OrdinamentoISBN(),false);
            }
            case null, default -> out=ottieniLista();
        }
        return out;
    }

}
