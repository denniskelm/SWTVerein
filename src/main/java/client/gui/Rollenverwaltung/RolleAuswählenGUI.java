package client.gui.Rollenverwaltung;

import javax.swing.*;

public class RolleAuswählenGUI extends JFrame{
    private JPanel RolleAuswählen;
    private JTextField textField1;
    private JButton OKButton;
    private JButton abbrechenButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;



    public RolleAuswählenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(RolleAuswählen);
        this.pack();
    }


    public static void main(String[] args) {
        JFrame frame = new RolleAuswählenGUI("Wert Ändern");

        frame.setVisible(true);
    }
}
