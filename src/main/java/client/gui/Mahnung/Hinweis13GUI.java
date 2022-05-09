package client.gui.Mahnung;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class Hinweis13GUI extends  JFrame{
    private JPanel Hinweis13;

    public Hinweis13GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis13);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis13GUI("Hinweis");
        frame.setVisible(true);
    }
}
