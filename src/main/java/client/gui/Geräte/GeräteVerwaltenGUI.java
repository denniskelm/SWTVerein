package client.gui.Geräte;

import javax.swing.*;

public class GeräteVerwaltenGUI extends JFrame{
    private JPanel GerätVerwalten;
    private JTextField gerätnameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField gerätbeschreibungTextField;
    private JTextField leihfristTextField;
    private JButton ändernButton;
    private JButton ändernButton1;
    private JButton ändernButton2;
    private JButton ändernButton3;
    private JButton ändernButton4;
    private JButton reserveierungslisteAnzeigenButton;
    private JButton speichernButton;
    private JButton gerätAusgebenButton;
    private JButton verleihhistorieButton;
    private JButton geräteLöschenButton;
    private JLabel picture;
    private JButton ändernButton5;
    private JTextField akutellerEntleiherTextField;
    private JTextField verbliebendeLeihdauerTextField;
    private JTextField ausgabeortTextField;
    private JButton ändernButton6;

    public GeräteVerwaltenGUI(String title)    {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GerätVerwalten);
        this.pack();
        picture.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));
    }

    public static void main(String[] args) {
        JFrame frame = new GeräteVerwaltenGUI("Gerät Verwalten");
        frame.setVisible(true);
    }
}
