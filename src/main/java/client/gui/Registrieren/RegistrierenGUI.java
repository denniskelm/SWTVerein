package client.gui.Registrieren;

import javax.swing.*;

public class RegistrierenGUI extends JFrame{
    private JPanel Registrieren;
    private JTextField vornameTextField;
    private JTextField nachnameTextField;
    private JTextField anschriftTextField;
    private JTextField mitgliedsnummerTextField;
    private JTextField telefonnummerTextField;
    private JTextField eMailTextField;
    private JTextField passwortTextField;
    private JTextField passwortWiederholungTextField;
    private JButton registrierenButton;

    public RegistrierenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Registrieren);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new RegistrierenGUI("Registrieren");
        frame.setVisible(true);
    }
}
