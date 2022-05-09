package client.gui.Geräte.Reservierungsliste;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class GastReservierenGUI extends JFrame{
    private JPanel GastReservieren;
    private JButton jaButton;
    private JButton neinButton;

    public GastReservierenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GastReservieren);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new GastReservierenGUI("Gast Reservieren");
        frame.setVisible(true);
    }
}
