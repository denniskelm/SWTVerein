package client.gui.Mahnung;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class MahnungErstellenGUI extends JFrame{
    private JPanel Mahnungsverwaltung;
    private JButton jaButton;
    private JButton neinButton;



    public MahnungErstellenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Mahnungsverwaltung);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new MahnungErstellenGUI("Mahnung erstellen");
        frame.setVisible(true);
    }
}
