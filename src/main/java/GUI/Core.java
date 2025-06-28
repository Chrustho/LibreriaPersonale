package GUI;


import facade.FacadeController;
import model.CategorieOrdinamento;
import model.Genere;
import model.Libro;
import model.StatoDiLettura;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;


public class Core extends JFrame {

    private DefaultTableModel tabella;
    private JTable table;

    private JTextField ricerca;
    private JComboBox<Genere> filtroGeneri;
    private JComboBox<StatoDiLettura> filtroStati;
    private JComboBox<Integer> filtroVoti;
    private JComboBox<CategorieOrdinamento> filtroOrdinamento;

    private final FacadeController facadeController;



    public Core(){
        super("Gestore libreria personale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400,1400);
        facadeController=new FacadeController();
        initComponents();
    }

    private void initComponents() {
        // Pannello superiore con bottoni per operazioni, barra di ricerca e filtri
        JToolBar toolBar = new JToolBar();
        JButton aggiungi= new JButton("Aggiungi");
        JButton modifica=new JButton("Modifica");
        JButton rimuovi=new JButton("Rimuovi");
        JButton undo=new JButton("Undo");

        toolBar.add(aggiungi);
        toolBar.add(modifica);
        toolBar.add(rimuovi);
        toolBar.addSeparator();
        toolBar.add(new JLabel("Cerca: "));
        ricerca=new JTextField(10);
        toolBar.add(ricerca);
        toolBar.addSeparator();
        toolBar.add(new JLabel("Generi: "));

        Genere[] generi= Genere.values();
        filtroGeneri = new JComboBox<>(generi);
        toolBar.add(filtroGeneri);
        toolBar.addSeparator();

        toolBar.add(new JLabel("Stato: "));
        StatoDiLettura[] stati= StatoDiLettura.values();
        filtroStati = new JComboBox<>(stati);
        toolBar.add(filtroStati);
        toolBar.addSeparator();

        toolBar.add(new JLabel("Voti: "));
        Integer[] voti={1,2,3,4,5};
        filtroVoti = new JComboBox<>(voti);
        toolBar.add(filtroVoti);
        toolBar.addSeparator();

        toolBar.add(new JLabel("Ordinamento: "));
        CategorieOrdinamento[] categorie= CategorieOrdinamento.values();
        filtroOrdinamento=new JComboBox<>(categorie);
        toolBar.add(filtroOrdinamento);
        toolBar.addSeparator();

        toolBar.add(undo);
        add(toolBar, BorderLayout.NORTH);



        // Assegno gli actionListener ai vari componenti
        aggiungiActionListener(aggiungi,modifica,rimuovi, undo);
        ActionListener filterListener = e -> riceraEFiltri();
        ricerca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                riceraEFiltri();
            }
        });
        filtroGeneri.addActionListener(filterListener);
        filtroStati.addActionListener(filterListener);
        filtroVoti.addActionListener(filterListener);
        filtroOrdinamento.addActionListener(filterListener);



        // Tabella per visualizzazione stile databse
        String[] attributi={"Titolo","Autore","ISBN","Genere","Valutazione", "Stato"};
        tabella=new DefaultTableModel(attributi,0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table=new JTable(tabella);
        mostraLibri(facadeController.getLibri());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }



    private void mostraLibri(List<Libro> libri) {
        tabella.setRowCount(0);
        for(Libro libro:libri){
            Object[] riga={
                    libro.getTitolo(),
                    libro.getAutore(),
                    libro.getIsbn(),
                    libro.getGenere(),
                    libro.getValutazione(),
                    libro.getStato()
            };
            tabella.addRow(riga);
        }
    }

    private void riceraEFiltri() {
        String input=ricerca.getText().trim().toLowerCase();
        System.out.println(input);
        Genere genere= (Genere) filtroGeneri.getSelectedItem();
        StatoDiLettura stato= (StatoDiLettura) filtroStati.getSelectedItem();
        Integer voto= (Integer) filtroVoti.getSelectedItem();
        CategorieOrdinamento categorie= (CategorieOrdinamento) filtroOrdinamento.getSelectedItem();
        List<Libro> filtrati= facadeController.filtra(genere,stato,voto,input,categorie);
        mostraLibri(filtrati);
    }

    private void aggiungiActionListener(JButton aggiungi, JButton modifica, JButton rimuovi, JButton undo) {
        aggiungi.addActionListener(e -> {
            dialogLibro(null);
        });
        modifica.addActionListener(e -> {
            int indice= table.getSelectedRow();
            System.out.println(indice);
            if (indice>=0){
                Libro libro = Libro.fromObject(tabella.getDataVector().elementAt(indice).toArray());
                dialogLibro(libro);
            }else {
                JOptionPane.showMessageDialog(null, "Seleziona un libro da modificare");
            }
        });
        rimuovi.addActionListener(e -> {
            int indice= table.getSelectedRow();
            System.out.println(indice+" da eliminare!");
            if (indice>=0){
                facadeController.rimuoviLibro(Libro.fromObject(tabella.getDataVector().elementAt(indice).toArray()));
                tabella.removeRow(indice);
                mostraLibri(facadeController.getLibri());
            }
        });
        undo.addActionListener(e -> {
            facadeController.undo();
        });
    }


    private void dialogLibro(Libro libro) {
        JTextField titolo = new JTextField();
        JTextField autore = new JTextField();
        JTextField isbn = new JTextField();

        // In questo modo seleziono solo i valor reali di Genere e di StatoDiLettura ed escludo il placeholder per il filtro

        Genere[] generi_selezionabili= new Genere[Genere.values().length-1];
        System.arraycopy(Genere.values(), 1, generi_selezionabili, 0, generi_selezionabili.length);


        StatoDiLettura[] statoDiLettura_selezionabile = new StatoDiLettura[StatoDiLettura.values().length-1];
        System.arraycopy(StatoDiLettura.values(),1,statoDiLettura_selezionabile,0,statoDiLettura_selezionabile.length );


        JComboBox<Genere> generi = new JComboBox<>(generi_selezionabili);
        JComboBox<Integer> valutazioni = new JComboBox<>(new Integer[]{1,2,3,4,5});
        JComboBox<StatoDiLettura> stati = new JComboBox<>(statoDiLettura_selezionabile);

        if (libro!=null){
            titolo.setText(libro.getTitolo());
            autore.setText(libro.getAutore());
            isbn.setText(((Long) libro.getIsbn()).toString());
            generi.setSelectedItem(libro.getGenere());
            valutazioni.setSelectedItem(libro.getValutazione());
            stati.setSelectedItem(libro.getStato());
        }

        JPanel panel=new JPanel(new GridLayout(0,2,5,5));
        panel.add(new JLabel("Titolo:")); panel.add(titolo);
        panel.add(new JLabel("Autore:")); panel.add(autore);
        panel.add(new JLabel("ISBN:")); panel.add(isbn);
        panel.add(new JLabel("Genere:")); panel.add(generi);
        panel.add(new JLabel("Valutazione:")); panel.add(valutazioni);
        panel.add(new JLabel("Stato di lettura:")); panel.add(stati);

        int result = JOptionPane.showConfirmDialog(this, panel,
                libro == null ? "Aggiungi Libro" : "Modifica Libro",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Libro nuovoLibro= new Libro(titolo.getText(),autore.getText(),Long.parseLong(isbn.getText()),(Genere) generi.getSelectedItem(), (Integer) valutazioni.getSelectedItem(), (StatoDiLettura) stati.getSelectedItem());
            Object[] riga=nuovoLibro.toObject();
            System.out.println(riga[0]);
            if (libro == null) {
                tabella.addRow(riga);
                facadeController.aggiungiLibro(nuovoLibro);
                mostraLibri(facadeController.getLibri());
            }
            else {
                int idx = table.getSelectedRow();
                for (int i = 0; i < riga.length; i++) {
                    table.setValueAt(riga[i], idx, i);
                    System.out.println(riga[i]);
                }
                facadeController.modificaLibro(nuovoLibro, libro);
                mostraLibri(facadeController.getLibri());
            }
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Core().setVisible(true));
    }


}
