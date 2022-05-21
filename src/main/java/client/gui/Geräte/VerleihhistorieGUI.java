package client.gui.Geräte;

import client.Vereinssoftware;
import server.geraetemodul.Ausleiher;
import server.geraetemodul.Geraet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class VerleihhistorieGUI extends JFrame {
    private JPanel Verleihhsitorie;
    private JTextField gerätTextField;
    private JTable table1;
    private final String geraeteID;

    //TODO
    // Gerätnamen anzeigen
    // Stuff hinzufügen

    public VerleihhistorieGUI(String title, String geraeteID) throws RemoteException {
        super(title);
        this.geraeteID = geraeteID;

        try {
            creatTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Verleihhsitorie);
        this.pack();
        setLocationRelativeTo(null);
    }


    private void creatTable() throws RemoteException {
        String[][] data = null;

        String[] columns = {"Nutzername", "NutzerID", "von", "bis"};

        for (Geraet g : Vereinssoftware.geraeteverwaltung.getGeraete())
            if (g.getGeraeteID().equals(geraeteID)) {
                data = new String[g.getHistorie().size()][4];
                for (int i = 0; i < g.getHistorie().size(); i++) {
                    Ausleiher a = g.getHistorie().get(i);
                    data[i][0] = ""; // TODO
                    data[i][1] = a.getMitlgiedsID();
                    data[i][2] = a.getFristBeginn().toString();
                    data[i][3] = a.getFristBeginn().plusDays(g.getLeihfrist()).toString();

                }
            }

        table1.setModel(new DefaultTableModel(
                data, columns)
        );

        JTable table = new JTable(data, columns);
    }


    public static void main(String[] args) {
        JFrame frame = null;
        try {
            frame = new VerleihhistorieGUI("Verleihhistorie", "0");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        frame.setVisible(true);

    }
}
