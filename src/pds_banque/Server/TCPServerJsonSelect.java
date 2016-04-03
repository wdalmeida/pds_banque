package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;
import static pds_banque.Json.JsonDecoding.decodageLoginConsultant;

/**
 *
 * @author Florian
 */
public class TCPServerJsonSelect {

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
          String result = AccessDB_server.envoyerRequeteQuery(decodageLoginConsultant(donneesEntreeClient));

            sortieVersClient.writeBytes(result + '\n');
        }

    }

    public static void main(String argv[]) throws Exception {

        lancerServeur(3000);

    }
}
