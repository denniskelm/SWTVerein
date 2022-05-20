package client.gui.Geräte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GerätHinzufügenGUI("Gerät Hinzufügen");
        frame.setVisible(true);
    }
}
