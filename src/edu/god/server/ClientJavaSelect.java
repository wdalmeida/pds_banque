package edu.god.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import org.json.simple.JSONObject;

//192.168.30.9
/**
 *
 * @author Florian
 */
public class ClientJavaSelect {

    /**
     *
     * @param objetJson
     * @return
     * @throws IOException Send a Json Object to a server through a TCP socket.
     */
    public static String clientTcpSelect(String idDev, String numberQuery, JSONObject objetJson) throws IOException {
        String host = "localhost";
        int port = 3000;
        String jsonString = objetJson.toString();
        String reponseServeur;

        try (Socket socketClient = new Socket(host, port)) {
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
            BufferedReader receptionServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            sortieVersServeur.writeBytes(idDev + "####" + numberQuery + "####" + jsonString + '\n');
            reponseServeur = receptionServeur.readLine();
            System.out.println("Le serveur a repondu: " + reponseServeur);
            socketClient.close();

        }
        return reponseServeur;
    }
}
