package client.gui.Geräte.GeräteAusleihenNochNIcht;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class AusgeliehenMitarbeiterGUI extends  JFrame{
    private JPanel panel1;
    private JButton jetztReservierenButton;
    private JButton abbrechenButton;

    public AusgeliehenMitarbeiterGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new AusgeliehenMitarbeiterGUI("Hinweis");
        frame.setVisible(true);
    }
}
