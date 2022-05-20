package client.gui.Geräte;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO Searchbar
// Kategorie
public class GeräteListeGUI extends JFrame{
    private JPanel GeräteListe;
    private JTable Geraeteliste;

    private JScrollPane scrollPane;
    private JTextField kategorieTextField;
    private JTextField sucheTextField;
    private JButton geraetHinzufügenButton;

    public GeräteListeGUI(String title) {
        super(title);


        creatTable();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GeräteListe);
        this.pack();


        //TODO Geraet hinzufuegen
        geraetHinzufügenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });


        // clickyclick
        Geraeteliste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);


            }
        });
    }
    //Maximale Anzahl an Reservierungen erreicht
    private void table1MouseClicked(java.awt.event.MouseEvent evt) {

        GastReservierenGUI gast = new GastReservierenGUI("Gast Reservieren");
        gast.setVisible(true);
        GeräteListeGUI.this.setVisible(false);


    }







    private void creatTable() {
        String[] columns = {"Gerät", "Spender", "Ausgabeort", "Gerätebeschreibung", "Reservieren"};
        String[][] data = {
                {"1", "2", "3", "4","5"},
        };
        Geraeteliste.setModel(new DefaultTableModel(
                data, columns));

    }

    public static void main(String[] args) {
        JFrame frame = new GeräteListeGUI("Geräteliste");
        frame.setVisible(true);

    }
}
