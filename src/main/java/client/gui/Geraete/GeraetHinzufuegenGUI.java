package client.gui.Geraete;

import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GeraetHinzufuegenGUI extends JFrame {
    private JPanel Geraethinzufuegen;
    private JTextField geraetenameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField geraetebeschreibungTextField;
    private JTextField leihfristTextField;
    private JTextField abholortTextField;
    private JLabel Picture;
    private JButton geraetErstellenButton;

    public GeraetHinzufuegenGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Geraethinzufuegen);

        this.pack();

        this.setLocationRelativeTo(null);

        //TODO Geraet erstellen
        geraetErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetHinzufuegen(
                            geraetenameTextField.getText(),
                            spenderTextField.getText(),
                            Integer.parseInt(leihfristTextField.getText()),
                            kategorieTextField.getText(),
                            geraetebeschreibungTextField.getText(),
                            abholortTextField.getText());

                    JOptionPane.showMessageDialog(GeraetHinzufuegenGUI.super.rootPane, "Geraet erfolgreich hinzugefuegt", "", JOptionPane.PLAIN_MESSAGE);

                    dispose();


                } catch (RemoteException ex) {
                    DefaultSmallPopup popup = new DefaultSmallPopup("Hinweis", ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GeraetHinzufuegenGUI("Geraet Hinzufuegen");
        frame.setVisible(true);
    }
}
