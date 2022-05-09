package client.gui.Geräte.GeräteAusleihenNochNIcht;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class Hinweis10GUI extends JFrame{
    private JPanel panel1;
    private JButton okayButton;

    public Hinweis10GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis10GUI("Hinweis");
        frame.setVisible(true);
    }
}
