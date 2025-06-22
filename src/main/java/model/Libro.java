package model;

import java.io.Serializable;
import java.util.Objects;

public class Libro implements Serializable {

    private String titolo;
    private String autore;
    private long isbn;
    private Genere genere;
    private int valutazione;
    private StatoDiLettura stato;

    /**
     * <Strong> Getters </Strong>
     **/

    public String getTitolo() {
        return titolo;
    }

    public String getAutore(){
        return autore;
    }

    public long getIsbn(){
        return isbn;
    }

    public Genere getGenere(){
        return genere;
    }

    public int getValutazione() {
        return valutazione;
    }

    public StatoDiLettura getStato(){
        return stato;
    }

    /**
     *  <Strong> Setters</Strong>
     *  <p>L'unico vincolo imposto riguarda la valutazione che deve essere compresa tra 0 e 5 </p>
     **/

    public void setTitolo(String titolo) {
        this.titolo=titolo;
    }

    public void setAutore(String autore) {
        this.autore=autore;
    }

    public void setIsbn(long isbn) {
        this.isbn=isbn;
    }

    public void setGenere(Genere genere) {
        this.genere=genere;
    }

    public void setValutazione(int valutazione) {
        if (valutazione>=0 && valutazione<=5){
            this.valutazione=valutazione;
        }else {
            throw new IllegalArgumentException("La valutazione deve essere compresa tra 0 e 5!");
        }
    }

    public void setStato(StatoDiLettura stato) {
        this.stato=stato;
    }

    public Libro(LibroBuilder libroBuilder){
        titolo=libroBuilder.titolo;
        autore=libroBuilder.autore;
        isbn=libroBuilder.isbn;
        genere= libroBuilder.genere;
        valutazione=libroBuilder.valutazione;
        stato=libroBuilder.stato;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", isbn=" + isbn +
                ", genere=" + genere +
                ", valutazione=" + valutazione +
                ", stato=" + stato +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o==null || getClass()!=o.getClass()) return false;
        if (this==o) return true;
        Libro confronto=(Libro) o;
        return this.isbn==confronto.getIsbn();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIsbn());
    }

    /**
     * <p> Introduco ora la classe statica <Strong> "LibroBuilder" </Strong> che sarà utilizzata per implementare il pattern creazionale <Strong> "Builder"</Strong> </p>
     * <p> Suppongo che l'unico parametro obbligatorio sia il codice ISBN, essendo un codice identificativo universale, si può pensare di espandere il progetto inserendo la possibilità di compilare titolo e autore direttamente tramite ricerca in rete attraverso l'ISBN </p>
     * <p> Impongo come valori di default per la valutazione "0", per lo StatoDiLettura "Da_Leggere" e per il genere "Saggio" </p>
     * <p> Questo risulta, a mio parere, la scelta più logica </p>
     **/

    public static class LibroBuilder{
        /**
         * <Strong> Dovrà possedere gli stessi parametri di Libro </Strong>
         **/
        private String titolo="";
        private String autore="";
        private long isbn;
        private Genere genere=Genere.Saggio;
        private int valutazione=0;
        private StatoDiLettura stato=StatoDiLettura.Da_Leggere;

        public LibroBuilder setTitolo(String titolo){
            this.titolo = titolo;
            return this;
        }

        public LibroBuilder setAutore(String autore){
            this.autore = autore;
            return this;
        }

        public LibroBuilder setIsbn(long isbn){
            // TODO aggiungere verifica preliminare che consenta di controllare se l'isbn è già presente nel db
            if (isbn <= 0) {
                throw new IllegalArgumentException("Inserisci un codice ISBN valido!");
            }
            this.isbn = isbn;
            return this;
        }

        public LibroBuilder setGenere(Genere genere){
            this.genere = genere;
            return this;
        }

        public LibroBuilder setValutazione(int valutazione){
            this.valutazione = valutazione;
            return this;
        }

        public LibroBuilder setStato(StatoDiLettura stato){
            this.stato = stato;
            return this;
        }


        public Libro build(){
            return new Libro(this);
        }

    }





}
