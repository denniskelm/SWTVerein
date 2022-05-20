package client.gui.Profilseite;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
TODO Dennis Kelm
*/

import client.DefaultsClient;
import client.Session;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.dienstleistungen.dienstleistungsangebote.DienstleistungsangebotAnzeigenGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.NoSuchObjectException;
import java.util.Objects;

//TODO Was macht diese Klasse?
public class ProfilseiteEigene {
    private JPanel namePanel;
    private JLabel nameText;
    private JButton XXButton;
    private JPanel stundenkontoPanel;
    private JLabel stundenkontostandText;
    private JPanel emailPanel;
    private JLabel emailText;
    private JPanel eigeneprofilseitePanel;
    private JPanel mahnungenPanel;
    private JLabel mahnungenText;
    private JTable profilseiteEintraegeTable;
    private JScrollPane profilseiteEintraegeScrollPanel;

    private boolean ownProfilseite = false;

    public ProfilseiteEigene(String personenID) {
        try {
            this.ownProfilseite = Objects.equals(personenID, Vereinssoftware.session.getUsername());
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("Profilseite");
        generateTable();
        frame = DefaultsClient.standardizeFrame(frame, eigeneprofilseitePanel);
    }

    private void generateTable() {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle für den Nutzer unbearbeitbar
                return false;
            }
        };
        profilseiteEintraegeTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "Typ",
                "Titel",
                "Beschreibung",
                "Verfügbar ab",
                "Verfügbar bis"
        };
        DefaultsClient.createColumnsFromArray(columns, model);

        //TODO Alle Geräte, Dienstleistungsgesuche und Dienstleistungsangebote abfragen und jeweils Längen feststellen / in ein Array zusammenfassen & Typ abfragen

        model.addRow(new Object[]{
                "Dienstleistungsgesuch",
                "Gartenzaun reparieren",
                "Ich brauche unbedingt Hilfe bei meinem Gartenzaun, und bin leider nicht ausreichend qualifiziert, den Zaun selber zu streichen",
                "07.05.2022",
                "14.05.2022"
        });

        profilseiteEintraegeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = profilseiteEintraegeTable.rowAtPoint(evt.getPoint());
                int col = profilseiteEintraegeTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                    //TODO Implementierung Klick auf Zelle ++ Erkennen ob Geraet oder nicht
                    //DienstleistungsangebotAnzeigenGUI dienstleistungsangebotAnzeigenGUI = new DienstleistungsangebotAnzeigenGUI(/* getAngebotsID usw */)
                }
            }
        });
    }
}
