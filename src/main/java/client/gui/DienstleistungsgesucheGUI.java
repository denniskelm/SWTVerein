package client.gui;
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

import javax.swing.*;

//TODO Was macht diese Klasse?
public class DienstleistungsgesucheGUI {
    private JPanel dienstleistungsgesuchePanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("DienstleistungsgesucheGUI");
        frame.setContentPane(new DienstleistungsgesucheGUI().dienstleistungsgesuchePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
