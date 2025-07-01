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

    public Libro(String titolo, String autore, long isbn, Genere genere, int valutazione, StatoDiLettura stato) {
        this.titolo=titolo;
        this.autore=autore;
        this.isbn=isbn;
        this.genere=genere;
        this.valutazione=valutazione;
        this.stato=stato;
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

    /**
     * Il confronto è basato sull'ISBN in quanto questo è un identificatore univoco
     */
    @Override
    public boolean equals(Object o) {
        if (o==null || getClass()!=o.getClass()) return false;
        if (this==o) return true;
        Libro confronto=(Libro) o;
        return this.isbn==confronto.getIsbn();
    }

    public Object[] toObject(){
        return new Object[]{titolo,autore,isbn,genere,valutazione,stato};
    }

    public static Libro fromObject(Object[] object){
        return new Libro((String) object[0],(String) object[1], (Long) object[2], (Genere) object[3], (Integer) object[4], (StatoDiLettura) object[5]);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getIsbn());
    }







}
