package client.gui.Geräte.Reservierungsliste;

import javax.swing.*;

public class Warnung1GUI extends JFrame{
    private JPanel Warnung;

    public Warnung1GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Warnung);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Warnung1GUI("Hinweis");
        frame.setVisible(true);
    }
}
