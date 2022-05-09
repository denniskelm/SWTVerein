package client.gui.Registrieren;

import javax.swing.*;

public class Hinweis7GUI extends JFrame{
    private JPanel Hinweis7;

    public Hinweis7GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis7);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis7GUI("Hinweis");
        frame.setVisible(true);
    }
}
