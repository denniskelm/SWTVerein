package client.gui.Geräte.Reservierungsliste;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class Hinweis8GUI extends JFrame{
    private JPanel Hinweis;
    private JButton okayButton;

    public Hinweis8GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis8GUI("Hinweis");
        frame.setVisible(true);
    }
}
