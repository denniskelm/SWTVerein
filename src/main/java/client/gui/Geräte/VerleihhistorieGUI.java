package client.gui.Geräte;

import client.Vereinssoftware;
import shared.communication.IAusleiher;
import shared.communication.IGeraet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.NoSuchObjectException;
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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Verleihhsitorie);
        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);  //TODO entfernen?
    }


    private void creatTable() throws RemoteException {
        String[][] data = null;

        String[] columns = {"Nutzername", "NutzerID", "von", "bis"};

        //Daten des Gerätes abrufen
        Object[][] alleGeraeteDaten = Vereinssoftware.geraeteverwaltung.omniGeraeteDaten();

        for (Object[] geraeteDaten : alleGeraeteDaten) {
            if (geraeteID.equals(geraeteDaten[0].toString())) {
                ArrayList<IAusleiher> historie = (ArrayList<IAusleiher>) geraeteDaten[8];

                if (historie.size() == 0) {
                    data = new String[][]{{"Historie ist leer", "", "", ""}};
                    break;
                }

                data = new String[historie.size()][4];

                for (int i = 0; i < historie.size(); i++) {
                    IAusleiher a = historie.get(i);
                    data[i][0] = ""; // TODO
                    data[i][1] = a.getMitgliedsID();
                    data[i][2] = a.getFristBeginn().toString();
                    data[i][3] = a.getFristBeginn().plusDays((long) geraeteDaten[5]).toString();
                }

                break;
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
