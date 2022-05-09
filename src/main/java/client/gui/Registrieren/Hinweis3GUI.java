package client.gui.Registrieren;

import javax.swing.*;

public class Hinweis3GUI extends JFrame{
    private JPanel Hinweis3;

    public Hinweis3GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis3);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis3GUI("Hinweis");
        frame.setVisible(true);
    }
}
