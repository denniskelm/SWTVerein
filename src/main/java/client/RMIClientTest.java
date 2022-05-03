package client;
/*
@author
TODO Raphael Kleebaum
TODO Jonny Schlutter
TODO Gabriel Kleebaum
TODO Mhd Esmail Kanaan
TODO Gia Huy Hans Tran
TODO Ole Björn Adelmann
TODO Bastian Reichert
Dennis Kelm
*/

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//TODO WAS MACHT DIESE KLASSE?
public class RMIClientTest {
    private final String address = "meta.informatik.uni-rostock.de";
    private int port;

    public RMIClientTest(int port) {
        this.port = port;
    }

    public void sendMessage(String message) throws IOException {
        Socket server = new Socket(address, port);

        // --> Nachricht zum Server senden
        DataOutputStream output = new DataOutputStream(server.getOutputStream());
        output.writeUTF(message);

        // --> Antwort vom Server empfangen & verarbeiten
        DataInputStream input = new DataInputStream(server.getInputStream());
        String serverResponse = input.readUTF();
        String handledResponse = handleResponse(serverResponse);

        // --> Server schließen & Nachricht am Client ausgeben
        server.close();
        System.out.println(handledResponse);
    }

    public static String handleResponse(String message) {
        return "lalala: " + message + " Zeichen";
    }


    // CLIENT STARTEN UND NACHRICHT SENDEN
    public static void main(String[] args) {
        try {
            RMIClientTest client = new RMIClientTest(1234);
            client.sendMessage("Dies ist eine Testnachricht!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
