package shared.communication;


import server.users.Personendaten;

public interface IMitglied extends IGast {

    void reservierungenErhoehen();

    void reservierungenVerringern();
    int getReservierungen();
    void veraendereStundenkonto(int change);
    boolean isGesperrt();
    IAnfragenliste getAnfragenliste();
    void datenVerwalten(Personendaten attr, String wert);

}
