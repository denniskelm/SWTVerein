package client.gui.Registrieren;

import javax.swing.*;

public class Hinweis5GUI extends JFrame{
    private JPanel Hinweis5;

    public Hinweis5GUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Hinweis5);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new Hinweis5GUI("Hinweis");
        frame.setVisible(true);
    }
}
