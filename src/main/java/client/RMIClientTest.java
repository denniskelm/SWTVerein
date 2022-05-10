package client;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import shared.communication.IDienstleistungsverwaltung;
import shared.communication.IGeraeteverwaltung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

//TODO WAS MACHT DIESE KLASSE?
public class RMIClientTest {
    static IGeraeteverwaltung geraeteverwaltung;
    static IDienstleistungsverwaltung dienstleistungsverwaltung;

    public static void main(String[] args) {

        //RMI ermöglichen
        try {
            initializeRMI();
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void initializeRMI() throws RemoteException, NotBoundException {
        //Registry registry = LocateRegistry.getRegistry("meta.informatik.uni-rostock.de", 1234);
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);

        geraeteverwaltung = (IGeraeteverwaltung) registry.lookup("Geraeteverwaltung");
        dienstleistungsverwaltung = (IDienstleistungsverwaltung) registry.lookup("Dienstleistungsverwaltung");

        // ZUM TESTEN
        /* geraeteverwaltung.geraetHinzufuegen("Schaufel", "Gabriel", 2, "Schaufeln", "Dies ist eine tolle Schaufel!", "Raum 1");
        System.out.println(geraeteverwaltung.geraeteDatenAusgeben("0")); */

        /* try {
            dienstleistungsverwaltung.angebotErstellen("Zaun streichen", "Ich biete an, deinen Zaun zu streichen.", "Malern", LocalDateTime.now(), LocalDateTime.of(2022, 5, 26, 15, 10), "Gabriel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } */

    }
}
