package command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe completà che gestirà l'esecuzione dei command, la loro storia e le operazioni di undo
 */

public class HistoryHandler implements CommandHandler {

    private int lunghezzaMassimaStoria=50;
    private final List<CommandBase> listaStoria=new ArrayList<>();



    public HistoryHandler(int lunghezzaMassimaStoria){
        if(lunghezzaMassimaStoria<0){
            throw new IllegalArgumentException("La storia deve avere una lunghezzza maggiore di 0!");
        }
        this.lunghezzaMassimaStoria=lunghezzaMassimaStoria;
    }


    public List<CommandBase> getListaStoria() {
        return listaStoria;
    }

    public void aggiungiAStoria(CommandBase commandBase) {
        listaStoria.addFirst(commandBase);
        if(listaStoria.size()>lunghezzaMassimaStoria){
            listaStoria.removeLast();
        }
    }


    public void undo(){
        if (!listaStoria.isEmpty()){
            CommandBase commandBase = listaStoria.removeFirst();
            commandBase.undo();
        }
    }




    @Override
    public boolean handle(CommandBase commandBase) {
        if(commandBase==null){
            return false;
        }
        boolean check=false;
        if (commandBase.execute()){
            aggiungiAStoria(commandBase);
            check=true;
        }else {
            listaStoria.clear();
        }
        return check;
    }
}
