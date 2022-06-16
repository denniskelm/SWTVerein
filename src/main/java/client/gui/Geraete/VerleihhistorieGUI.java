package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import shared.communication.IAusleiher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * GUI fuer die Verleihhistorie eines Geraets
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class VerleihhistorieGUI {
    private JPanel Verleihhistorie;
    private JTextField geraetTextField;
    private JTable table1;
    private final String geraeteID;

    private static JFrame frame;
    //TODO
    // Geraetnamen anzeigen
    // Stuff hinzufuegen

    public VerleihhistorieGUI(String geraeteID) throws RemoteException {
        this.geraeteID = geraeteID;

        frame = new JFrame("Verleihhistorie");
        frame = ClientDefaults.standardizeFrame(frame, Verleihhistorie);

        try {
            creatTable();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }

    private void creatTable() throws RemoteException {
        String[][] data = null;

        String[] columns = {"Nutzername", "NutzerID", "von", "bis"};

        //Daten des Geraetes abrufen
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
}
