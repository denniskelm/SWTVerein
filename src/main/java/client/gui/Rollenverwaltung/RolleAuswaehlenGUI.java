package client.gui.Rollenverwaltung;

import client.ClientDefaults;
import client.Umlaut;
import client.Vereinssoftware;
import client.gui.DefaultSmallPopup;
import server.users.Rolle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

/**
 * GUI das Aendern der Rolle eines Nutzers
 * <p>
 * Hauptautor
 *
 * @author Gia Huy Hans Tran
 * <p>
 * Kleine Verbesserungen
 * @author Dennis Kelm
 */
public class RolleAuswaehlenGUI {
    private JPanel RolleAuswaehlen;
    private JButton OKButton;
    private JButton abbrechenButton;
    private JRadioButton MitgliedButton;
    private JRadioButton MitarbeiterButton;
    private JRadioButton VereinsvorstandButton;
    private JRadioButton LoeschenButton;

    public static JFrame frame;

    public RolleAuswaehlenGUI(String mitgliedsID) {

        frame = new JFrame("Rolle " + Umlaut.ae() + "ndern");
        frame = ClientDefaults.standardizeFrame(frame, RolleAuswaehlen);

        try {
            Rolle MahnerRolle = Vereinssoftware.session.getRolle();
            Rolle GemahnterRolle = Vereinssoftware.rollenverwaltung.fetchRolle(mitgliedsID);

            OKButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if((MitgliedButton.isSelected() && MahnerRolle == Rolle.VORSITZ && GemahnterRolle != Rolle.VORSITZ) ||
                            (MitgliedButton.isSelected() && GemahnterRolle != Rolle.VORSITZ && GemahnterRolle != Rolle.MITARBEITER)) {
                        try {
                            Vereinssoftware.rollenverwaltung.rolleAendern(mitgliedsID, Rolle.MITGLIED );
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else
                        new DefaultSmallPopup("Fehlende Berechtigung", "Dafür fehlt Ihnen die Berechtigung.");

                    if((MitarbeiterButton.isSelected() && MahnerRolle == Rolle.VORSITZ && GemahnterRolle != Rolle.VORSITZ) ||
                            (MitarbeiterButton.isSelected() && GemahnterRolle != Rolle.VORSITZ && GemahnterRolle != Rolle.MITARBEITER)) {
                        try {
                            Vereinssoftware.rollenverwaltung.rolleAendern(mitgliedsID, Rolle.MITARBEITER );
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else
                        new DefaultSmallPopup("Fehlende Berechtigung", "Dafür fehlt Ihnen die Berechtigung.");

                    if(VereinsvorstandButton.isSelected() && MahnerRolle == Rolle.VORSITZ) {
                        try {
                            Vereinssoftware.rollenverwaltung.rolleAendern(mitgliedsID, Rolle.VORSITZ );
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    else
                        new DefaultSmallPopup("Fehlende Berechtigung", "Dafür fehlt Ihnen die Berechtigung.");


                    if((LoeschenButton.isSelected() && MahnerRolle == Rolle.VORSITZ && GemahnterRolle != Rolle.VORSITZ) ||
                            (MitgliedButton.isSelected() && GemahnterRolle != Rolle.VORSITZ && GemahnterRolle != Rolle.MITARBEITER)) {
                        try {
                            Vereinssoftware.rollenverwaltung.nutzerAusAlterListeEntfernen(mitgliedsID, GemahnterRolle);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();

                }
            });
        } catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }
}
