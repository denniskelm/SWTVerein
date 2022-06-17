package client.gui.Geraete;


import client.ClientDefaults;
import client.Kategorie;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.Profilseite.Profilseite;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//TODO Searchbar
// Kategorie
public class GeraeteListeGUI {
    private JPanel GeraeteListe;
    private JTable Geraeteliste;

    private JScrollPane scrollPane;
    private JTextField sucheTextField;
    private DefaultTableModel model;

    private static JFrame frame;

    Map<JTextField, Boolean> onceChanged = new HashMap<>();

    public GeraeteListeGUI() {

        frame = new JFrame("Ger" + Umlaut.ae() + "teliste");
        frame = ClientDefaults.standardizeFrame(frame, GeraeteListe);

        createTable();

        ClientDefaults.enhanceTextField(sucheTextField, onceChanged);
    }

    private void createTable() {

        try {
            Object[][] geraete = Vereinssoftware.geraeteverwaltung.omniGeraeteDaten();

            model = new DefaultTableModel() {

                @Override
                public boolean isCellEditable(int row, int column) {
                    //macht Tabelle fuer den Nutzer unbearbeitbar
                    return false;
                }
            };

            String[] columns = {"GeraeteID", "Geraet", "Beschreibung", "Kategorie", "Spender", "Leihfrist", "Status", "Abholort", "Aktion"};
            ClientDefaults.createColumnsFromArray(columns, model);

            for (Object[] geraet : geraete) {

                if (geraet[0] == null)
                    break;

                model.addRow(new Object[]{
                        geraet[0],
                        geraet[1],
                        geraet[2],
                        geraet[3],
                        geraet[4],
                        geraet[5],
                        geraet[6],
                        geraet[7],
                        "Reservieren"
                });
            }

            Geraeteliste.setModel(model);

            GeraeteListe.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = Geraeteliste.rowAtPoint(evt.getPoint());
                    int col = Geraeteliste.columnAtPoint(evt.getPoint());
                    String iD = null;
                    if (row >= 0 && col >= 0) {
                        if (col == 4) {
                            Profilseite profilseite = new Profilseite(geraete[row][4].toString());
                        } else {
                            iD = model.getValueAt(row, 0).toString(); //GeraeteID
                            System.out.println("id: " + iD);
                            String gname = model.getValueAt(row, 1).toString(); //Geraet
                            String spender = model.getValueAt(row, 2).toString(); //Spender
                            String ort = model.getValueAt(row, 3).toString(); //Ausgabeort
                            String beschreibung = model.getValueAt(row, 4).toString(); //Geraetebeschreibung

                            if (iD == null) return; // TODO Exception

                            GeraetAnzeigenGUI geraetAnzeigenGUI = new GeraetAnzeigenGUI(
                                    geraete[row][0].toString(),
                                    geraete[row][10].toString(),
                                    geraete[row][1].toString(),
                                    geraete[row][4].toString(),
                                    geraete[row][7].toString(),
                                    ((Object[][]) geraete[row][9]).length,
                                    Integer.parseInt(geraete[row][5].toString()),
                                    geraete[row][2].toString(),
                                    geraete[row][3].toString());
                            GeraeteListeGUI.frame.dispose();
                        }
                    }

                }
            });


        } catch (RemoteException e) {
            System.out.println("Fehler");
            throw new RuntimeException(e);
        }

        ClientDefaults.addSearchFunctionality(Geraeteliste, sucheTextField);

    }

}

