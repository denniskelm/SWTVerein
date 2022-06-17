package client.gui.dienstleistungen.dienstleistungsgesuche;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;
import client.Vereinssoftware;
import client.gui.Profilseite.Profilseite;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;

//Erstellt das GUI fuer die Verwaltung der Dienstleistungsgesuche
public class DienstleistungsgesuchVerwaltungGUI {
    private JPanel dienstleistungsgesuchverwaltungPanel;
    private JScrollPane bigTableScrollPanel;
    private JTable dienstleistungsgesucheTable;
    private JTextField suchenTextField;

    private final Map<JTextField, Boolean> onceChanged = new HashMap<JTextField, Boolean>();

    public DienstleistungsgesuchVerwaltungGUI() {
        JFrame frame = new JFrame("Dienstleistungsgesuch-Datenbank");
        try {
            this.generateTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        frame = ClientDefaults.standardizeFrame(frame, this.dienstleistungsgesuchverwaltungPanel);

        ClientDefaults.enhanceTextField(suchenTextField, onceChanged);
    }

    private void generateTable() throws Exception {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle fuer den Nutzer unbearbeitbar
                return false;
            }
        };
        dienstleistungsgesucheTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "ID",
                "Dienstleistung",
                "Beschreibung",
                "Kategorie",
                "Suchender",
                "Bild-URL"
        };
        ClientDefaults.createColumnsFromArray(columns, model);


        Object[][] gesuche = Vereinssoftware.dienstleistungsverwaltung.omniGesuchDaten();


        for (Object[] gesuch :
                gesuche) {
            if (gesuch[0] == null) {
                break;
            }


            model.addRow(new Object[]{
                    gesuch[3],
                    gesuch[0],
                    gesuch[1],
                    gesuch[2],
                    Vereinssoftware.rollenverwaltung.getMitgliedsNamen((String) gesuch[4]),
                    gesuch[5]
            });
        }


        dienstleistungsgesucheTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsgesucheTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsgesucheTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    if (col == 4) {
                        Profilseite profilseite = new Profilseite(gesuche[row][4].toString());
                    } else {
                        try {
                            DienstleistungsgesuchBearbeitenGUI dienstleistungsgesuchBearbeitenGUI = new DienstleistungsgesuchBearbeitenGUI(
                                    gesuche[row][3].toString(), //ID
                                    gesuche[row][0].toString(), //Titel
                                    gesuche[row][5].toString(), //pathToImage
                                    gesuche[row][1].toString(), //beschreibung
                                    gesuche[row][2].toString() //Kategorie
                            );
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
}