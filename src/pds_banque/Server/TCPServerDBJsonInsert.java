package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import static pds_banque.Json.JsonDecoding.decodageCustomer;

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
    public static void lancerServeur(int port) throws IOException, FileNotFoundException, ParseException {
        ServerSocket socketAccueil = new ServerSocket(port);
        String donneesEntreeClient;

        while (true) {
            Socket connectionSocket = socketAccueil.accept();
            BufferedReader entreeVenantDuClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream sortieVersClient = new DataOutputStream(connectionSocket.getOutputStream());

            donneesEntreeClient = entreeVenantDuClient.readLine();
            System.out.println("Donnees recues par le serveur: " + donneesEntreeClient);
            Object obj = donneesEntreeClient;
            
            
            //decodageCustomer(obj);
            AccessDB_server.envoyerRequeteUpdate(decodageCustomer(obj));

            //sortieVersClient.writeBytes("Requete effectuee" + '\n');
        }

    }
    /**
     *  Starts the server on the specified port. Has to be the same that the client.
     * @param argv
     * @throws Exception 
     */
   
    public static void main(String argv[]) throws Exception {

        lancerServeur(3000);

    }
}
