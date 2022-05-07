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

//TODO WAS MACHT DIESE KLASSE?
public class RMIClientTest {
    static IGeraeteverwaltung geraeteverwaltung;
    static IDienstleistungsverwaltung dienstleistungsverwaltung;
    public static void main(String[] args) {
        try { initializeRMI(); }
        catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void initializeRMI() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);

        geraeteverwaltung = (IGeraeteverwaltung) registry.lookup("Geraeteverwaltung");
        dienstleistungsverwaltung = (IDienstleistungsverwaltung) registry.lookup("Dienstleistungsverwaltung");
    }
}
