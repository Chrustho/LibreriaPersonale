package database;

import model.Libro;
import utils.Costanti;

import java.io.*;
import java.nio.file.*;
import java.util.List;


public class PersistenzaCSV extends OperazioniPersistenza{

    private static final String separatore= ",";
    private static final String estensione= ".csv";

    public PersistenzaCSV(File database) {
        super(database);
    }

    @Override
    void estraiDati() {
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(database));
            while (true){
                String riga=bufferedReader.readLine();
                if (riga==null){
                    bufferedReader.close();
                    return;
                }
                Libro nuovo=super.getLibro(riga, PersistenzaCSV.separatore);
                if (super.aggiungiLibro(nuovo)){
                    System.out.println("Aggiunto libro: "+nuovo);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void scriviLibro(Libro nuovoLibro) {
            try {
                PrintWriter printWriter=new PrintWriter(new FileWriter(database),true);
                printWriter.println(creaLinea(nuovoLibro,PersistenzaCSV.separatore));
                printWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * Per le operazioni di Modifica e Eliminazione mi affiodo all'uso di un file temporaneo che svorascriverà quello orignale per gestire le modifiche
     * @param l
     * @return
     */

    @Override
    public void modificaLibro(Libro l) {
        try {
            File tempFile=new File(Costanti.pathTemp+PersistenzaCSV.estensione);
            Path destinazione= Paths.get(database.getAbsolutePath());
            Path sorgente= Paths.get(tempFile.getAbsolutePath());
            BufferedReader bufferedReader=new BufferedReader(new FileReader(database));
            PrintWriter printWriter=new PrintWriter(tempFile);
            while (true){
                String riga=bufferedReader.readLine();
                if (riga==null){
                    bufferedReader.close();
                    printWriter.close();
                    break;
                }
                Libro corrente= getLibro(riga,PersistenzaCSV.separatore);
                if (corrente.getIsbn()!=l.getIsbn()){
                    printWriter.println(riga);
                }else {
                    String linea = creaLinea(l, PersistenzaCSV.estensione);
                    printWriter.println(linea);
                }
            }
            Files.move(sorgente, destinazione, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(sorgente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminaLibro(long isbn) {
        try {
            File tempFile=new File(Costanti.pathTemp+PersistenzaCSV.estensione);
            Path destinazione= Paths.get(database.getAbsolutePath());
            Path sorgente= Paths.get(tempFile.getAbsolutePath());
            BufferedReader bufferedReader=new BufferedReader(new FileReader(database));
            PrintWriter printWriter=new PrintWriter(tempFile);
            while (true){
                String riga=bufferedReader.readLine();
                if (riga==null){
                    bufferedReader.close();
                    printWriter.close();
                    break;
                }
                Libro corrente= getLibro(riga,PersistenzaCSV.separatore);
                if (corrente.getIsbn()!=isbn){
                    printWriter.println(riga);
                }
            }
            Files.move(sorgente, destinazione, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(sorgente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
