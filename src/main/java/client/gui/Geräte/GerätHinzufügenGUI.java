package client.gui.Geräte;

import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import client.gui.StartseiteGUI;

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

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Geräthinzufügen);

        this.pack();

        this.setLocationRelativeTo(null);

        //TODO Gerät erstellen
        geraetErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetHinzufuegen(
                            gerätenameTextField.getText(),
                            spenderTextField.getText(),
                            Integer.parseInt(leihfristTextField.getText()),
                            kategorieTextField.getText(),
                            gerätebeschreibungTextField.getText(),
                            abholortTextField.getText());

                    JOptionPane.showMessageDialog(GerätHinzufügenGUI.super.rootPane, "Gerät erfolgreich hinzugefügt", "",JOptionPane.PLAIN_MESSAGE);

                    dispose();


                } catch (RemoteException ex) {
                    DefaultSmallPopup popup = new DefaultSmallPopup("Hinweis", ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GerätHinzufügenGUI("Gerät Hinzufügen");
        frame.setVisible(true);
    }
}
