package database;

import model.Libro;

import javax.swing.table.TableRowSorter;
import java.io.*;


public class PersistenzaCSV extends OperazioniPersistenza{

    private static final String separatore= ",";

    public PersistenzaCSV(File database) {
        super(database);
    }


    @Override
    void estraiDati() {
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader(database));
            super.ottieniLista().clear();
            while (true){
                String riga=bufferedReader.readLine();
                if (riga==null || riga.isEmpty()){
                    bufferedReader.close();
                    return;
                }
                Libro nuovo=super.getLibro(riga, PersistenzaCSV.separatore);
                super.ottieniLista().add(nuovo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void scriviLibro(Libro nuovoLibro) {
            try {
                PrintWriter printWriter=new PrintWriter(new FileWriter(database,true),true);
                printWriter.println(creaLinea(nuovoLibro,PersistenzaCSV.separatore));
                printWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public void riscriviFile() {
        try {
            PrintWriter printWriter=new PrintWriter(new FileWriter(database, false),true);
            for (Libro l: ottieniLista()){
                printWriter.println(creaLinea(l,PersistenzaCSV.separatore));
            }
            printWriter.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


}
