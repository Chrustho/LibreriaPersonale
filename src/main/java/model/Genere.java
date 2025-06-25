package model;

import java.io.Serializable;

/**
 * <p> Per consentire un sistema di filtraggio basato sul genere letterario
 * ho preferito creare una enumerazione dei più popolari </p>
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
