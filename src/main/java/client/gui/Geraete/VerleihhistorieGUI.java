package client.gui.Geraete;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import shared.communication.IAusleiher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
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
            if (geraeteDaten[0] == null)
                break;

            if (geraeteID.equals(geraeteDaten[0].toString())) {
                Object[][] historie = (Object[][]) geraeteDaten[8];

                if (historie.length == 0) {
                    data = new String[][]{{"Historie ist leer", "", "", ""}};
                    break;
                }

                data = new String[historie.length][4];

                for (int i = 0; i < historie.length; i++) {
                    Object[] ausleiher = historie[i];

                    try {
                        data[i][0] = Vereinssoftware.rollenverwaltung.getMitgliedsNamen(String.valueOf(ausleiher[0]));
                        data[i][1] = String.valueOf(ausleiher[0]);
                        data[i][2] = ((LocalDateTime) ausleiher[2]).toString();
                        data[i][3] = ((LocalDateTime) ausleiher[2]).plusDays(Long.parseLong(geraeteDaten[5].toString())).toString();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }

                break;
            }
        }

        table1.setModel(new DefaultTableModel(
                data, columns)
        );

    }
}
