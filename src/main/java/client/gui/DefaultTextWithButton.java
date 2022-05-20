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
Dennis Kelm
*/

import client.ClientDefaults;

import javax.swing.*;

//TODO Was macht diese Klasse?
public class DefaultTextWithButton {
    private JPanel defaultTextWithButtonPanel;
    private JScrollPane textScrollbar;
    private JLabel textLabel;
    private JButton actionButton;

    //Konstruktor für ein Dialog mit Button, dabei muss für den actionButton in der anderen Klasse ein ActionListener
    //hinzugefügt werden
    public DefaultTextWithButton(String title, String popupText, String actionButtonText) {
        JFrame frame = new JFrame(title);
        textLabel.setText("<html><p style=\"width: 500px; text-align: center; margin-left: 20px; margin-right: 20px;\">" + popupText + "</p>");
        frame = ClientDefaults.standardizeFrame(frame, defaultTextWithButtonPanel);
        actionButton.setText(actionButtonText);


    }

    public JButton getActionButton() {
        return this.actionButton;
    }
}
