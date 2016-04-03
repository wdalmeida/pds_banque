
package pds_banque.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.simple.JSONObject;

/**
 *
 * @author Florian
 */
public class ClientJavaInsert {
    

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
