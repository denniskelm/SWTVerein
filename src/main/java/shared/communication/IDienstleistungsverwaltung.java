package shared.communication;

/*
@author
Gabriel Kleebaum
Dennis Kelm
Bastian Reichert
Ole Adelmann
*/

import server.dienstleistungsmodul.Dienstleistungsangebotdaten;
import server.dienstleistungsmodul.Dienstleistungsgesuchdaten;

import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

// Interface, um RMI für Klasse Dienstleistungsverwaltung zu ermöglichen
public interface IDienstleistungsverwaltung extends Remote {


    String gesuchErstellen(String titel, String beschreibung, String kategorie, String imageUrl, String ersteller) throws RemoteException, Exception;

    String angebotErstellen(String titel, String beschreibung, String kategorie, LocalDateTime ab, LocalDateTime bis, String imageUrl, String ersteller) throws RemoteException, Exception;

    void gesuchLoeschen(String gesuchsID) throws RemoteException;
    void angebotLoeschen(String angebotsID) throws RemoteException;
    void gesuchAendern(String gesuchsID, Dienstleistungsgesuchdaten attr, Object wert) throws RemoteException;
    void angebotAendern(String angebotsID, Dienstleistungsangebotdaten attr, Object wert) throws RemoteException;
    void gesuchAnnehmen(String gesuchsID, String ersteller, String nutzer, int stunden) throws RemoteException, Exception;
    void angebotAnnehmen(String gesuchsID, String ersteller, String nutzer, int stunden) throws RemoteException, Exception;

    Object[] getAngeboteInformationen(String geraeteID) throws RemoteException;

    Object[] getGesucheInformationen(String geraeteID) throws RemoteException;

    Object[][] OmniAngebotDaten() throws NoSuchObjectException, RemoteException;

    Object[][] OmniGesuchDaten() throws NoSuchObjectException, RemoteException;

}
