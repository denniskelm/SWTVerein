package client.gui.Registrieren;

import javax.swing.*;

public class Hinweis4GUI extends  JFrame{
    private JPanel panel1;
    private JPanel Hinweis4;

    public Hinweis4GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis4);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis4GUI("Hinweis");
        frame.setVisible(true);
    }
}
