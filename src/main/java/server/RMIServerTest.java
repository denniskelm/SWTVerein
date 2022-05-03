package server;
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
import java.net.ServerSocket;
import java.net.Socket;

//TODO WAS MACHT DIESE KLASSE?
public class RMIServerTest {
    private ServerSocket server;

    public RMIServerTest(int port) throws IOException {
        server = new ServerSocket(port);
        server.setSoTimeout(60 * 1000);

        serverLoop();
    }

    public void serverLoop() {
        while (true) {
            System.out.println("Warte auf Client! (Port: " + server.getLocalPort() + ")");

            try {
                // --> mit (nächsten) Client verbinden
                Socket client = server.accept();

                // --> Nachricht vom Nutzer auswerten
                DataInputStream input = new DataInputStream(client.getInputStream());
                String clientMessage = input.readUTF();
                int messageLength = handleMessage(clientMessage);

                // --> Antwort an Nutzer senden
                DataOutputStream output = new DataOutputStream(client.getOutputStream());
                String serverMessage = "" + messageLength;
                output.writeUTF(serverMessage);

                // --> Client wieder trennen
                client.close();
                System.out.println("Client abgefertigt!");
                System.out.println();

            } catch (IOException e) {
                e.getMessage();
                break;
            }
        }
    }

    private int handleMessage(String message) {
        return message.length();
    }


    // SERVER STARTEN UND IN SCHLEIFE GEHEN
    public static void main(String[] args) {
        try {
            RMIServerTest s = new RMIServerTest(1234);
            s.serverLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
