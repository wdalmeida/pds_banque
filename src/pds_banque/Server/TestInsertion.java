package pds_banque.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.simple.JSONObject;
import static pds_banque.Json.JsonEncoding.encodageCustomer;

/**
 *
 * @author Florian
 */
public class TestInsertion {

    public static void main(String[] args) throws IOException {

        //je balance dans l'encodeur Json
        //l'encodeur Json balance dans le client
        //le client balance l'objet Json dans le serveur java
        //le serveur Java balance dans le decodeur Json
        //le decodeur Json balance dans la classe d'accès à la base de données
        float salaire = (float) 4500;

        //encodage(1, "", "Mickael", "Json", salaire, "18 Rue de Paris", "94225", "Charenton le Pont", "0125476936", "mickael.jackson@gmail.com", "1958-08-29", 1, 1, 0, 1);
        RequeteTCPJson(encodageCustomer(1, "", "Mickael", "Json", salaire, "18 Rue de Paris", "94225", "Charenton le Pont", "0125476936", "mickael.jackson@gmail.com",
                "1958-08-29", 1, 1, 0, 1));

    }

    public static void RequeteTCPJson(JSONObject objetJson) throws IOException {
        String host = "localhost";
        int port = 3000;
        String jsonString = objetJson.toString();

        try (
                Socket socketClient = new Socket(host, port)) {
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
            sortieVersServeur.writeBytes(jsonString + '\n');
            socketClient.close();
        }
    }

}
