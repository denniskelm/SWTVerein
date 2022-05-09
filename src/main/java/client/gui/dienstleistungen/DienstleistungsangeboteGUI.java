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
public class DienstleistungsangeboteGUI {
    private JPanel dienstleistungsangebotePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable dienstleistungsangeboteTable;
    private JTextPane suchenTextPane;
    private JButton dienstleistungsgesuchErstellenButton;
    private JButton dienstleistungsangebotErstellenButton;


    public DienstleistungsangeboteGUI() {
        JFrame frame = new JFrame("Dienstleistungsangebote");
        //DienstleistungsangeboteGUI thisgui = new DienstleistungsangeboteGUI();
        frame = DefaultsClient.standardizeFrame(frame, this.dienstleistungsangebotePanel);

        this.generateTable();

        dienstleistungsangebotErstellenButton.addActionListener(e -> {
            DienstleistungsangebotErstellenGUI dienstleistungsangebotErstellenGUI = new DienstleistungsangebotErstellenGUI();
        });
    }

    private void generateTable() {
        DefaultTableModel model = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //macht Tabelle für den Nutzer unbearbeitbar
                return false;
            }
        };
        dienstleistungsangeboteTable.setModel(model);

        //set TableCellRenderer into a specified JTable column class

        String[] columns = new String[]{
                "Dienstleistung",
                "Beschreibung",
                "Kategorie",
                "Datum",
                "Anbieter"
        };
        DefaultsClient.createColumnsFromArray(columns, model);

        //Testdaten
        model.addRow(new Object[]{
                "Dienstleistung 1",
                "Beschreibung 1",
                "Kategorie 1",
                "07.05.2022 - 14.05.2022",
                "Stefan"
        });

        dienstleistungsangeboteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsangeboteTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsangeboteTable.columnAtPoint(evt.getPoint());
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


    private void createUIComponents() {
        // TODO: place custom component creation code here


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