package client.gui.Rollenverwaltung;

import client.gui.Login.HinweisGUI;

import javax.swing.*;

public class WertÄndernGUI extends  JFrame{
    private JTextField vornameNachnameTextField;
    private JTextField IDTextField;
    private JTextField eingabeÄndernTextField;
    private JButton OKButton;
    private JButton abbrechenButton;
    private JPanel WertÄndern;

    public WertÄndernGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(WertÄndern);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new WertÄndernGUI("Wert ändern");
        frame.setVisible(true);
    }
}
