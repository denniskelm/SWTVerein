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
 * @author Ole Adelmann
 * @author Gabriel Kleebaum
 * @author Raphael Kleebaum
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
                            Vereinssoftware.rollenverwaltung.rolleAendern(mitgliedsID, Rolle.MITGLIED);
                            frame.dispose();
                            new DefaultSmallPopup("Rolle erfolgreich ge" + Umlaut.ae() + "ndert",
                                    "Die Rolle von " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(mitgliedsID) + " wurde erfolgreich ge" + Umlaut.ae() + "ndert!");
                            new RollenverwaltungMitgliedGUI();
                        } catch (Exception ex) {
                            if (ex.getMessage().contains("Der Nutzer hat diese Rolle bereits.")) {
                                new DefaultSmallPopup("Fehler", "Der Nutzer hat diese Rolle bereits.");
                            }

                            throw new RuntimeException(ex);
                        }
                    } else if ((MitarbeiterButton.isSelected() && MahnerRolle == Rolle.VORSITZ && GemahnterRolle != Rolle.VORSITZ) ||
                            (MitarbeiterButton.isSelected() && GemahnterRolle != Rolle.VORSITZ && GemahnterRolle != Rolle.MITARBEITER)) {
                        try {
                            Vereinssoftware.rollenverwaltung.rolleAendern(mitgliedsID, Rolle.MITARBEITER);
                            frame.dispose();
                            new DefaultSmallPopup("Rolle erfolgreich ge" + Umlaut.ae() + "ndert",
                                    "Die Rolle von " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(mitgliedsID) + " wurde erfolgreich ge" + Umlaut.ae() + "ndert!");
                            new RollenVerwaltungMitarbeiterGUI();
                        } catch (Exception ex) {
                            if (ex.getMessage().contains("Der Nutzer hat diese Rolle bereits.")) {
                                new DefaultSmallPopup("Fehler", "Der Nutzer hat diese Rolle bereits.");
                            }

                            throw new RuntimeException(ex);
                        }
                    } else if (VereinsvorstandButton.isSelected() && MahnerRolle == Rolle.VORSITZ) {
                        try {
                            Vereinssoftware.rollenverwaltung.rolleAendern(mitgliedsID, Rolle.VORSITZ);
                            frame.dispose();
                            new DefaultSmallPopup("Rolle erfolgreich ge" + Umlaut.ae() + "ndert",
                                    "Die Rolle von " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(mitgliedsID) + " wurde erfolgreich ge" + Umlaut.ae() + "ndert!");
                            new RollenverwaltungVereinsvorstandGUI();
                        } catch (Exception ex) {
                            if (ex.getMessage().contains("Der Nutzer hat diese Rolle bereits.")) {
                                new DefaultSmallPopup("Fehler", "Der Nutzer hat diese Rolle bereits.");
                            }

                            throw new RuntimeException(ex);
                        }
                    } else if ((LoeschenButton.isSelected() && MahnerRolle == Rolle.VORSITZ && GemahnterRolle != Rolle.VORSITZ) ||
                            (LoeschenButton.isSelected() && GemahnterRolle != Rolle.VORSITZ && GemahnterRolle != Rolle.MITARBEITER)) {
                        try {
                            Vereinssoftware.rollenverwaltung.nutzerAusAlterListeEntfernen(mitgliedsID, GemahnterRolle);
                            new DefaultSmallPopup("Nutzer erfolgreich gel" + Umlaut.oe() + "scht",
                                    "Der Nutzer " + Vereinssoftware.rollenverwaltung.getMitgliedsNamen(mitgliedsID) + " wurde erfolgreich gel" + Umlaut.oe() + "scht!");
                            frame.dispose();
                        } catch (Exception ex) {
                            new DefaultSmallPopup("Fehler",
                                    "Fehler: " + ex.getMessage());
                            throw new RuntimeException(ex);
                        }
                    } else
                        new DefaultSmallPopup("Fehlende Berechtigung", "Dafür fehlt Ihnen die Berechtigung.");

                }
            });

            abbrechenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    frame.dispose();


                }
            });
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }


    }
}
