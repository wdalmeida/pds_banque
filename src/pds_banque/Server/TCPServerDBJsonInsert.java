package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import static pds_banque.Json.JsonDecoding.decodeCustomer;

/**
 *
 * @author Florian
 */
public class TCPServerDBJsonInsert {
/**
 * 
 * @param port Defines on which port the server will be listening on.
 * @throws IOException 
 * @throws FileNotFoundException
 * @throws ParseException 
 */
    public static void launchServer(int port) throws IOException, FileNotFoundException, ParseException {
        ServerSocket hostingSocket = new ServerSocket(port);
        String dataFromClient;

        while (true) {
            Socket socketConnection = hostingSocket.accept();
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
            DataOutputStream outputToClient = new DataOutputStream(socketConnection.getOutputStream());

            dataFromClient = inputFromClient.readLine();
            System.out.println("Donnees recues par le serveur: " + dataFromClient);
            Object obj = dataFromClient;
            
            
            //decodageCustomer(obj);
            AccessDB_server.sendUpdateRequest(decodeCustomer(obj));

            //sortieVersClient.writeBytes("Requete effectuee" + '\n');
        }

    }
    /**
     *  Starts the server on the specified port. Has to be the same that the client.
     * @param argv
     * @throws Exception
     *  Starts the server on the specified port. Has to be the same that the client. 
     */
   
    public static void main(String argv[]) throws Exception {

        launchServer(3000);

    }
}