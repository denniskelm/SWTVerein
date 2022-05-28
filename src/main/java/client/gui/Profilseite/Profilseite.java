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

import client.ClientDefaults;
import client.Einstellungen;
import client.Vereinssoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.NoSuchObjectException;
import java.util.Objects;

//TODO Was macht diese Klasse?
public class Profilseite {
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
    private String mitgliedsID;

    public Profilseite(String personenID) {
        try {
            this.ownProfilseite = Objects.equals(personenID, Vereinssoftware.session.getUsername());
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        }

        this.mitgliedsID = personenID;

        if (this.ownProfilseite) {
            XXButton.setText("Anfragen"); //TODO Wie viele Anfragen?
            XXButton.addActionListener(e -> {
                AnfragelisteGUI anfragelisteGUI = new AnfragelisteGUI();
            });
        } else {
            XXButton.setText("Kontaktieren");
            XXButton.addActionListener(e -> {
                Desktop desktop;
                if (Desktop.isDesktopSupported()
                        && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                    try {
                        String link = "mailto:" + Vereinssoftware.rollenverwaltung.getMitgliedsMail(mitgliedsID) +
                                "?subject=Kontaktanfrage%20aus%20dem%20Verein&body=Hallo%20MITGLIEDSNAME%2C%0D%0A%0D" +
                                "%0A%0D%0AMit%20freundlichen%20Gr%C3%BC%C3%9Fen%0D%0A";// + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(Vereinssoftware.session.getID()))
                        URI mailto = new URI(link);
                        desktop.mail(mailto);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    throw new RuntimeException("Dieses Gerät hat keine Mail-Funktion!");
                }
            });
        }

        JFrame frame = new JFrame("Profilseite");
        generateTable();
        frame = ClientDefaults.standardizeFrame(frame, eigeneprofilseitePanel);
        frame.setLocationRelativeTo(null);
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
        ClientDefaults.createColumnsFromArray(columns, model);

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
