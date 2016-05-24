package edu.god.server;

import static edu.god.serialisation.JsonEncoding.encodageCustomer;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Florian
 */
public class ClientJavaInsertTest {

    /**
     *
     * @param objetJson
     * @throws IOException Function for sending an encoded Json object to a
     * server through a TCP socket
     */
    public static void clientTcpInsert() throws IOException, ParseException {
        String host = "localhost";
        int port = 3000;

        try (
                Socket socketClient = new Socket(host, port)) {
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());

            JSONObject myJsonObject = encodageCustomer("M", "Philipe", "Morin", Float.valueOf("100.0"), "Edmond Nocard", "94000", "Creteil", "0784579563", "Test@test.com", "2016-05-25", true, "Francais", 1, -1, 1);

            String jsonString = myJsonObject.toString();

            System.out.println("Soit la valeur encodée:" + jsonString);

            sortieVersServeur.writeBytes("L" + "####" + jsonString + '\n');
            socketClient.close();
        }
    }

    public static void main(String argv[]) throws Exception {
        clientTcpInsert();
    }

}
