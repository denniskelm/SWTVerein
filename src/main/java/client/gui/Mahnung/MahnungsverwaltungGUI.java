package client.gui.Mahnung;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MahnungsverwaltungGUI extends JFrame {
    private JPanel MahnungVerwaltung;

    private JButton erstellenButton;
    private JTextField verfallsdatumTextField;
    private JTextField GrundfuerMahnung;

    //TODO funktionalitaet

    public MahnungsverwaltungGUI(String title) {
        super(title);


        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(MahnungVerwaltung);
        this.pack();
        setLocationRelativeTo(null);

        erstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }


    public static void main(String[] args) {
        JFrame frame = new MahnungsverwaltungGUI("Mahnung Verwalten");
        frame.setVisible(true);
    }
}
