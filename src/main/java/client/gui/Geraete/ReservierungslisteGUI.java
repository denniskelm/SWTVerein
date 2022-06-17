package client.gui.Geraete;

import client.ClientDefaults;
import javax.swing.*;
import java.rmi.RemoteException;


public class ReservierungslisteGUI {
    private JPanel Reservierungsliste;
    private JTable table1;
    private static JFrame frame;

    public ReservierungslisteGUI() throws RemoteException {

        frame = new JFrame("Reservierungsliste");
        frame = ClientDefaults.standardizeFrame(frame, Reservierungsliste);
        createTable();
    }

    private void createTable() throws RemoteException {
        String[][] data = null;
        String[] columns = {"MitgliedsID", "Reservierungsdatum", "FristBeginn", "istAbgegeben"};
    }
}
