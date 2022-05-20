package client.gui.Geräte;

import client.Vereinssoftware;
import server.geraetemodul.Geraetedaten;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GeräteVerwaltenGUI extends JFrame{
    private JPanel GerätVerwalten;
    private JTextField gerätnameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField gerätbeschreibungTextField;
    private JTextField leihfristTextField;
    private JButton GeraetaendernButton;
    private JButton SpenderaendernButton;
    private JButton KategorieaendernButton;
    private JButton GeraetBeschreibungaendernButton;
    private JButton LeihfristaendernButton;
    private JButton reserveierungslisteAnzeigenButton;
    private JButton speichernButton;
    private JButton geraetAusgebenButton;
    private JButton verleihhistorieButton;
    private JButton geraeteLöschenButton;
    private JLabel picture;
    private JButton BildaendernButton;
    private JTextField akutellerEntleiherTextField;
    private JTextField verbliebendeLeihdauerTextField;
    private JTextField ausgabeortTextField;
    private JButton AusgabeortaendernButton;
    private String geraeteID;

    public GeräteVerwaltenGUI(String title, String geraeteID)    {
        super(title);
        this.geraeteID = geraeteID;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(GerätVerwalten);
        this.pack();
        setLocationRelativeTo(null);
        picture.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));





        GeraetaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        SpenderaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("",Geraetedaten.SPENDERNAME, spenderTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        KategorieaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("",Geraetedaten.KATEGORIE, kategorieTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        GeraetBeschreibungaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("",Geraetedaten.BESCHREIBUNG, gerätbeschreibungTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        LeihfristaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("",Geraetedaten.LEIHFRIST, leihfristTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        BildaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        AusgabeortaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID,Geraetedaten.ABHOLORT, ausgabeortTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        reserveierungslisteAnzeigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        geraetAusgebenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetAusgeben(geraeteID);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        verleihhistorieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new VerleihhistorieGUI("Verleihhistorie von " + gerätnameTextField.getText(), geraeteID);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });

        geraeteLöschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetEntfernen(geraeteID);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GeräteVerwaltenGUI("Gerät Verwalten", "0");
        frame.setVisible(true);
    }
}
