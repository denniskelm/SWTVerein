package client.gui.Geräte.GeräteAusleihenNochNIcht;

import client.gui.Geräte.GeräteVerwaltenGUI;

import javax.swing.*;

public class GeräteAusleihenGUI extends JFrame{
    private JButton jetztReservierenButton;
    private JPanel GerätAusleihen;
    private JLabel picture;

    public GeräteAusleihenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GerätAusleihen);
        this.pack();
        picture.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));
    }

    public static void main(String[] args) {
        JFrame frame = new GeräteAusleihenGUI("Gerät Ausleihen");
        frame.setVisible(true);
    }
}
