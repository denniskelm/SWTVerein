package client.gui.Geraete;

import client.Vereinssoftware;
import server.geraetemodul.Geraetedaten;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GeraeteVerwaltenGUI extends JFrame {
    private JPanel GeraetVerwalten;
    private JTextField geraetnameTextField;
    private JTextField spenderTextField;
    private JTextField kategorieTextField;
    private JTextField geraetbeschreibungTextField;
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
    private JButton geraeteLoeschenButton;
    private JLabel picture;
    private JButton BildaendernButton;
    private JTextField akutellerEntleiherTextField;
    private JTextField verbliebendeLeihdauerTextField;
    private JTextField ausgabeortTextField;
    private JButton AusgabeortaendernButton;
    private final String geraeteID;

    public GeraeteVerwaltenGUI(String title, String geraeteID) {
        super(title);
        this.geraeteID = geraeteID;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(GeraetVerwalten);
        this.pack();
        setLocationRelativeTo(null);
        picture.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));


        GeraetaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        SpenderaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("", Geraetedaten.SPENDERNAME, spenderTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        KategorieaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("", Geraetedaten.KATEGORIE, kategorieTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        GeraetBeschreibungaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("", Geraetedaten.BESCHREIBUNG, geraetbeschreibungTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        LeihfristaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten("", Geraetedaten.LEIHFRIST, leihfristTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        BildaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        AusgabeortaendernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraeteDatenVerwalten(geraeteID, Geraetedaten.ABHOLORT, ausgabeortTextField.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        reserveierungslisteAnzeigenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
            }
        });

        verleihhistorieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new VerleihhistorieGUI("Verleihhistorie von " + geraetnameTextField.getText(), geraeteID);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        geraeteLoeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vereinssoftware.geraeteverwaltung.geraetEntfernen(geraeteID);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new GeraeteVerwaltenGUI("Geraet Verwalten", "0");
        frame.setVisible(true);
    }
}
