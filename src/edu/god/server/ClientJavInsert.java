package edu.god.server;

import static edu.god.serialisation.JsonEncoding.encodageCustomer;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Florian
 */
public class ClientJavInsert {

    /**
     *
     * @param objetJson
     * @throws IOException Function for sending an encoded Json object to a
     * server through a TCP socket
     */
    public static void clientTcpInsert(String userPrefix, String numberPrefix,  JSONObject objetJson) throws IOException, ParseException {
        String host = "localhost";
        int port = 3000;
       

    
        Socket socketClient = new Socket(host, port);
        DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
     

        //JSONObject myJsonObject  = encodageCustomer("M", "Test", "Test2", Float.valueOf("100.0"), "Edmond Nocard", "94000", "Creteil", "0784579563", "Test@test.com", "2016-05-25", true, "Francais", 1, -1, 1);
        String jsonString = objetJson.toString();
        int longeurObjet = objetJson.size();

        

        System.out.println("Soit la valeur encodée:" + jsonString);

        sortieVersServeur.writeBytes("L" +"####"+ "1" + "####" + jsonString + '\n');
        socketClient.close();
    }

}
