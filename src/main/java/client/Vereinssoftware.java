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

import client.gui.StartseiteGUI;
import shared.communication.IDienstleistungsverwaltung;
import shared.communication.IGeraeteverwaltung;
import shared.communication.IMahnungsverwaltung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Klasse, die das Programm startet
public class Vereinssoftware {
    static IGeraeteverwaltung geraeteverwaltung;
    static IDienstleistungsverwaltung dienstleistungsverwaltung;
    static IMahnungsverwaltung mahnungsverwaltung;

    public static void main(String[] args) {

        //RMI ermöglichen
        try {
            initializeRMI();
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

        //TODO: GUI starten
        //StartseiteGUI startseiteGUI = new StartseiteGUI();

    }

    public static void initializeRMI() throws RemoteException, NotBoundException {
        //Registry registry = LocateRegistry.getRegistry("meta.informatik.uni-rostock.de", 1234);
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);

        geraeteverwaltung = (IGeraeteverwaltung) registry.lookup("Geraeteverwaltung");
        dienstleistungsverwaltung = (IDienstleistungsverwaltung) registry.lookup("Dienstleistungsverwaltung");
        mahnungsverwaltung = (IMahnungsverwaltung) registry.lookup("Mahnungsverwaltung");
    }
}
