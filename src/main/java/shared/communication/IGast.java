package shared.communication;

import server.users.Personendaten;

public interface IGast {

    String getPersonenID();
    String getNachname();
    String getAnschrift();
    String getVorname();
    void datenVerwalten(Personendaten attr, String wert);

}
