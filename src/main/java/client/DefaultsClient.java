package client;
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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//TODO Was macht diese Klasse?
public class DefaultsClient {


    //Nimmt einen frame und das dazugehörige Panel der Klasse und nimmt grundlegende Einstellungen vor
    public static JFrame standardizeFrame(JFrame frame, JPanel panel) {
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    public static void createColumnsFromArray(String[] columns, DefaultTableModel model) {
        for (String c :
                columns) {
            model.addColumn(c);
        }
    }

}
