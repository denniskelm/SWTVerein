package client.gui.Login;

import client.gui.Registrieren.RegistrierenGUI;

import javax.swing.*;

public class LoginGUI extends JFrame{
    private JPanel Login;
    private JTextField eMailAdresseTextField;
    private JTextField passwortTextField;
    private JButton loginButton;
    private JButton registrierenButton;


    public LoginGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Login);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new LoginGUI("Registrieren");
        frame.setVisible(true);
    }
}
