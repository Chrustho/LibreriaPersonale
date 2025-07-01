package model;

import java.io.Serializable;

/**
 * <p> Per consentire un sistema di filtri basato sul genere letterario </p>
 *  <p> ho preferito creare una enumerazione dei più popolari, questo permette di far fronte ad eventuali problemi legati alla possibilità che l'utente  scriva in maniera errata il genere durante l'aggiunta o la modifica del libro, impossibilitando poi la ricerca tramite filtro </p>
 */
public enum Genere implements Serializable {
    Seleziona_un_genere,// Genere placeholder per implementare un filtro dinamico
    Avventura,
    Biografia,
    Commedia,
    Fantascienza,
    Fantasy,
    Giallo,
    Horror,
    Manuale_Scolastico,
    Poesia,
    Romance,
    Saggio,
    Storico,
    Thriller

}
