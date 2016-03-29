package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

public class RequeteClientTCPJson {

    public RequeteClientTCPJson(JSONObject objetJson, String host, int port) throws IOException {
        String reponseDuServeur;
        try (
                Socket socketClient = new Socket(host, port)) {
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
            BufferedReader entreeVenantDuServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            sortieVersServeur.writeBytes(objetJson.toString() + '\n');
            reponseDuServeur = entreeVenantDuServeur.readLine();
            System.out.println("Reponse du serveur: " + reponseDuServeur);
        }
    }
}
