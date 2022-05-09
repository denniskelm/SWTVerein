package client.gui.dienstleistungen;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import client.DefaultsClient;
import client.gui.DefaultSmallPopup;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//TODO Was macht diese Klasse?
public class DienstleistungsgesucheGUI {
    private JPanel dienstleistungsgesuchePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable dienstleistungsgesucheTable;
    private JTextPane suchenTextPane;


    public DienstleistungsgesucheGUI() {
        JFrame frame = new JFrame("Dienstleistungsgesuche");
        //DienstleistungsgesucheGUI thisgui = new DienstleistungsgesucheGUI();
        frame = DefaultsClient.standardizeFrame(frame, this.dienstleistungsgesuchePanel);

        this.generateTable();
    }

    private void generateTable() {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle für den Nutzer unbearbeitbar
                return false;
            }
        };
        dienstleistungsgesucheTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "Gesuch",
                "Beschreibung",
                "Kategorie",
                "Datum",
                "Anbieter"
        };
        DefaultsClient.createColumnsFromArray(columns, model);

        //Testdaten
        model.addRow(new Object[]{
                "Gesuch 1",
                "Beschreibung 1",
                "Kategorie 1",
                "07.05.2022 - 14.05.2022",
                "Stefan"
        });

        dienstleistungsgesucheTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsgesucheTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsgesucheTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                    DefaultSmallPopup smallPopup = new DefaultSmallPopup("Test test", "Du hast den Button in Zeile " + row + " und Spalte " + col + " geklickt!");
                }
            }
        });
    }


    public static void main(String[] args) {
        //TODO Am Ende entfernen
        FlatLightLaf.setup();


    }
}
/*
    class MultiLineTableCellRenderer extends JList<String> implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            //make multi line where the cell value is String[]
            if (value instanceof String[]) {
                setListData((String[]) value);
            }

            //cell backgroud color when selected
            if (isSelected) {
                setBackground(UIManager.getColor("Table.selectionBackground"));
            } else {
                setBackground(UIManager.getColor("Table.background"));
            }

            return this;
        }
    }

*/