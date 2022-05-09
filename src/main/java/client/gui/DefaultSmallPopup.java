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

import client.DefaultsClient;

import javax.swing.*;

//TODO Was macht diese Klasse?
public class DefaultSmallPopup {
    private JPanel defaultPanel;
    private JScrollPane textScrollbar;
    private JLabel textLabel;

    public DefaultSmallPopup(String title, String popupText) {
        JFrame frame = new JFrame(title);
        textLabel.setText("<html><p style=\"width: 500px; text-align: center; margin-left: 20px; margin-right: 20px;\">" + popupText + "</p>");
        frame = DefaultsClient.standardizeFrame(frame, defaultPanel);


    }


}
