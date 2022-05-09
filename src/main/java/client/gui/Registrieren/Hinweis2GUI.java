package client.gui.Registrieren;

import javax.swing.*;

public class Hinweis2GUI extends JFrame {
    private JPanel Hinweis2;

    public Hinweis2GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis2);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis2GUI("Hinweis");
        frame.setVisible(true);
    }
}
