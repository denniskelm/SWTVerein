package client.gui;
/*
@author
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

    private static JFrame frame;

    //Konstruktor fuer ein Dialog mit Button, dabei muss fuer den actionButton in der anderen Klasse ein ActionListener
    //hinzugefuegt werden
    public DefaultTextWithButton(String title, String popupText, String actionButtonText) {
        frame = new JFrame(title);
        textLabel.setText("<html><p style=\"width: 500px; text-align: center; margin-left: 20px; margin-right: 20px;\">" + popupText + "</p>");
        frame = ClientDefaults.standardizeFrame(frame, defaultTextWithButtonPanel);
        frame.setLocationRelativeTo(null);
        actionButton.setText(actionButtonText);

    }

    public void closeFrame() {
        frame.dispose();
    }

    public JButton getActionButton() {
        return this.actionButton;
    }
}
