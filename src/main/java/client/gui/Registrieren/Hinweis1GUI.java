package client.gui.Registrieren;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class Hinweis1GUI extends  JFrame{
    private JPanel Hinweis1;

    public Hinweis1GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis1);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis1GUI("Hinweis");
        frame.setVisible(true);
    }
}
