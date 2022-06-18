package client.gui;
/*
@author
Dennis Kelm
*/

import client.ClientDefaults;

import javax.swing.*;

//Kleines Fenster fuer viele Klassen mit kleinem Hinweis Text
public class DefaultSmallPopup extends JFrame {
    private JPanel defaultPanel;
    private JScrollPane textScrollbar;
    private JLabel textLabel;
    private JButton button1;

    public DefaultSmallPopup(String title, String popupText) {
        JFrame frame = new JFrame(title);
        textLabel.setText("<html><p style=\"width: 500px; text-align: center; margin-left: 20px; margin-right: 20px;\">" + popupText + "</p>");
        frame = ClientDefaults.standardizeFrame(frame, defaultPanel);

    }


}
