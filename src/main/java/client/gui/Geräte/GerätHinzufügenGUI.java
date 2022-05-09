package client.gui.Geräte;

import client.gui.Login.LoginGUI;

import javax.swing.*;

public class GerätHinzufügenGUI extends JFrame{
    private JPanel Geräthinzufügen;
    private JTextField gerätenameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField gerätebeschreibungTextField;
    private JTextField leihfristTextField;
    private JTextField abholortTextField;
    private JLabel Picture;

    public GerätHinzufügenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Geräthinzufügen);
        this.pack();
        Picture.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));
    }

    public static void main(String[] args) {
        JFrame frame = new GerätHinzufügenGUI("Gerät Hinzufügen");
        frame.setVisible(true);
    }
}
