package client.gui.Rollenverwaltung;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class GastLöschenGUI extends JFrame{
    private JPanel GastLöschen;
    private JButton jaButton;
    private JButton neinButton;


    public GastLöschenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GastLöschen);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new GastLöschenGUI("Gast löschen");
        frame.setVisible(true);
    }
}
