package client.gui.Rollenverwaltung;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class WarnungGUI extends JFrame{


    private JPanel Warning;

    public WarnungGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Warning);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new WarnungGUI("Warnung");
        frame.setVisible(true);
    }
}
