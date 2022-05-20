package client.gui.Mahnung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MahnungErstellenGUI extends JFrame{
    private JPanel Mahnungsverwaltung;
    private JButton jaButton;
    private JButton neinButton;

    //TODO funktionalität


    public MahnungErstellenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Mahnungsverwaltung);
        this.pack();
        setLocationRelativeTo(null);

        jaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        neinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new MahnungErstellenGUI("Mahnung erstellen");
        frame.setVisible(true);
    }
}
