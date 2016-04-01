package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequeteInsertionClient {

    public RequeteInsertionClient(String entree, String host, int port) throws IOException {
        String reponseDuServeur;
        try (
                Socket socketClient = new Socket(host, port)) {
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
            BufferedReader entreeVenantDuServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            sortieVersServeur.writeBytes(entree + '\n');
            reponseDuServeur = entreeVenantDuServeur.readLine();
            System.out.println("Reponse du serveur: " + reponseDuServeur);
        }
    }

    
    public static void main(String argv[]) throws Exception {
        Thread monclient = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String requete = "INSERT INTO Customer " + "VALUES (1, '', 'STROH', 'NICOLAS', 3100, 'Edmon Nocard', '94410', 'Saint Maurice', '145659878', 'nicolas.stroh@Yahoo.fr', '1992-08-08', 1, 1, 0, 1)";
                    new RequeteInsertionClient(requete, "localhost", 3000);
                } catch (IOException ex) {
                    Logger.getLogger(RequeteInsertionClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        monclient.start();

    }
}