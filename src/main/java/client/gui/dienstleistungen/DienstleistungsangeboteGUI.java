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
TODO Dennis Kelm
*/

import client.DefaultsClient;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

//TODO Was macht diese Klasse?
public class DienstleistungsangeboteGUI {
    private JTextArea suchenTextArea;
    private JPanel dienstleistungsangebotePanel;
    private JScrollPane bigTableScrollPanel;
    private JTable table2;
    private JTable dienstleistungsangeboteTable;


    private DienstleistungsangeboteGUI() {
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
        table2.setModel(model);

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
/*
        dienstleistungsangeboteTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = dienstleistungsangeboteTable.rowAtPoint(evt.getPoint());
                int col = dienstleistungsangeboteTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    System.out.println(row + ", " + col);
                }
            }
        });*/
    }


    public static void main(String[] args) {
        //TODO Am Ende entfernen
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Dienstleistungsangebote");
        frame = DefaultsClient.standardizeFrame(frame, new DienstleistungsangeboteGUI().dienstleistungsangebotePanel);

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