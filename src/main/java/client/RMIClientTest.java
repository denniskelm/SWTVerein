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


import server.Dienstleistungsverwaltung;
import server.Geraeteverwaltung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//TODO WAS MACHT DIESE KLASSE?
public class RMIClientTest {
    static Geraeteverwaltung geraeteverwaltung;
    static Dienstleistungsverwaltung dienstleistungsverwaltung;
    public static void main(String[] args) {
        try { initializeRMI(); }
        catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void initializeRMI() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1234);

        geraeteverwaltung = (Geraeteverwaltung) registry.lookup("Geraeteverwaltung");
        dienstleistungsverwaltung = (Dienstleistungsverwaltung) registry.lookup("Dienstleistungsverwaltung");
    }
}
