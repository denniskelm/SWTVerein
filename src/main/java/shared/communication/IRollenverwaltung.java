package shared.communication;

import server.users.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
@author
Raphael Kleebaum
TODO Jonny Schlutter
Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
TODO Dennis Kelm
*/

// Interface, um RMI für Klasse Rollenverwaltung zu ermöglichen
public interface IRollenverwaltung extends Remote {

    Object gastListeAnzeigen() throws RemoteException;    //Object[]
    Object mitgliedListeAnzeigen() throws RemoteException;    //Object[]
    Object mitarbeiterListeAnzeigen() throws RemoteException; //Object[]
    Object vorsitzListeAnzeigen() throws RemoteException; //Object[]
    void rolleAendern(String mitgliedsID, Rolle rolle) throws RemoteException, Exception;
    void nutzereintragAendern(String mitgliedsID, Personendaten attr, String wert) throws RemoteException;
    Object mahnungsverwaltungAnzeigen() throws RemoteException;   //Object[]
    void gastHinzufuegen(String nachname, String vorname, String email, String password, String anschrift, String mitgliedsnr, int telefonnummer, boolean spender) throws RemoteException;
    boolean login(String email, int password) throws Exception;
    Object getGaeste() throws RemoteException;  //ArrayList<Gast>
    Object getMitglieder() throws RemoteException;  //ArrayList<Mitglied>
    Object getMitarbeiter() throws RemoteException; //ArrayList<Mitarbeiter>
    Object getVorsitze() throws RemoteException;    //ArrayList<Vorsitz>
    Object getMahnungen() throws RemoteException;   //ArrayList<Mahnung>
    int getIdCounter() throws RemoteException;

    public String getMitgliedsNamen(String MitgliedsID) throws Exception;

}
