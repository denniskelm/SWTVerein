package client.gui.Geräte;

import client.Vereinssoftware;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GerätHinzufügenGUI extends JFrame{
    private JPanel Geräthinzufügen;
    private JTextField gerätenameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField gerätebeschreibungTextField;
    private JTextField leihfristTextField;
    private JTextField abholortTextField;
    private JLabel Picture;
    private JButton geraetErstellenButton;

    public GerätHinzufügenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Geräthinzufügen);
        this.pack();


        //TODO Gerät erstellen
        geraetErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetHinzufuegen(gerätenameTextField.getText(),
                                                                        spenderTextField.getText(),
                                                                        Integer.parseInt(leihfristTextField.getText()),
                                                                        kategorieTextField.getText(),
                                                                        gerätebeschreibungTextField.getText(),
                                                                        abholortTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GerätHinzufügenGUI("Gerät Hinzufügen");
        frame.setVisible(true);
    }
}
