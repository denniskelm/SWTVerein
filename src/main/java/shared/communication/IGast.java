package shared.communication;

import server.users.Personendaten;

import java.rmi.Remote;
import java.time.LocalDateTime;

public interface IGast extends Remote {

    String getPersonenID();

    String getNachname();

    String getAnschrift();

    String getVorname();

    String getEmail();

    int getPassword();

    String getMitgliedsNr();

    String getTelefonNr();

    boolean getSpenderStatus();
    void datenVerwalten(Personendaten attr, String wert);

}
