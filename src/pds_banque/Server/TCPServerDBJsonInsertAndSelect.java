package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pds_banque.Json.JsonDecoding.decodageCustomer;
import static pds_banque.Json.JsonDecoding.decodageLoginConsultant;

/**
 *
 * @author Florian
 */
public class TCPServerDBJsonInsertAndSelect {

    /**
     *
     * @param port Defines on which port the server will be listening on.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static void lancerServeur(int portInsert, int portSelect) throws IOException, FileNotFoundException, ParseException {
        ServerSocket socketInsert = new ServerSocket(portInsert);
        String inputClientInsert;
        ServerSocket socketSelect = new ServerSocket(portSelect);
        String donneesEntreeClientSelect;

        while (true) {
            Socket connectionSocketSelect = socketSelect.accept();
            BufferedReader inputFromClientSelect = new BufferedReader(new InputStreamReader(connectionSocketSelect.getInputStream()));
            DataOutputStream outputToClientSelect = new DataOutputStream(connectionSocketSelect.getOutputStream());
            donneesEntreeClientSelect = inputFromClientSelect.readLine();
            System.out.println("Donnees recues par le serveur: " + donneesEntreeClientSelect);
            Object obj = donneesEntreeClientSelect;
            //decodageCustomer(obj);
            String resultFromSelect = AccessDB_server.envoyerRequeteQuery(decodageLoginConsultant(donneesEntreeClientSelect));

            outputToClientSelect.writeBytes(resultFromSelect + '\n');
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            Socket connectionSocketInsert = socketInsert.accept();
            BufferedReader InputFromClientInsert = new BufferedReader(new InputStreamReader(connectionSocketInsert.getInputStream()));
            DataOutputStream sortieVersClient = new DataOutputStream(connectionSocketInsert.getOutputStream());

            inputClientInsert = InputFromClientInsert.readLine();
            System.out.println("Donnees recues par le serveur: " + inputClientInsert);
            Object objectInpuClientInsert = inputClientInsert;

            AccessDB_server.envoyerRequeteUpdate(decodageCustomer(objectInpuClientInsert));

            //sortieVersClient.writeBytes("Requete effectuee" + '\n');
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
        }

    }

    /**
     * Starts the server on the specified port. Has to be the same that the
     * client.
     *
     * @param argv
     * @throws Exception /** Starts the server on the specified port. Has to be
     * the same that the client.
     * @param argv
     * @throws Exception
     */
    public static void main(String argv[]) throws Exception {



        Thread serveur = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.err.println("Lancement du serveur d'insertion:");
                    lancerServeur(3001, 3000);
                    System.err.println("Serveur d'insertion lancé");
                } catch (IOException ex) {
                    Logger.getLogger(RequeteInsertionClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TCPServerDBJsonInsertAndSelect.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        serveur.start();
        
        /* System.out.println("Lancement du serveur de select:");
         lancerServeurSelect(3000);
         System.out.println("Serveur de Select lancé");
         System.err.println("Lancement du serveur d'insertion:");
         lancerServeurInsert(3001);
         System.err.println("Serveur d'insertion lancé");
         */

    }
}
