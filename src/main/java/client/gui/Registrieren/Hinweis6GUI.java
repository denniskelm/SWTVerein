package client.gui.Registrieren;

import javax.swing.*;

public class Hinweis6GUI extends JFrame{
    private JPanel Hinweis6;

    public Hinweis6GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis6);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis6GUI("Hinweis");
        frame.setVisible(true);
    }
}
