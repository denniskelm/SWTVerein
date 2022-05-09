package client.gui.Geräte.GeräteAusleihenNochNIcht;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class ErfolgreichAusgeliehenMitarbeiterGUI extends  JFrame{
    private JPanel panel1;
    private JButton bestätigenButton;

    public ErfolgreichAusgeliehenMitarbeiterGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new ErfolgreichAusgeliehenMitarbeiterGUI("Hinweis");
        frame.setVisible(true);
    }
}
