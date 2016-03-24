package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequeteClientTCP {

   
    public RequeteClientTCP(String entree) throws IOException {
        String phraseModifiee;
        //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Socket socketClient = new Socket("localhost", 3000);
        DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
        BufferedReader entreeVenantDuServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        sortieVersServeur.writeBytes(entree + '\n');
        phraseModifiee = entreeVenantDuServeur.readLine();
        System.out.println("Reponse du serveur: " + phraseModifiee);
        socketClient.close();
    }

    public static void main(String argv[]) throws Exception {
        Thread monclient = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //TCPClientAvecThread TCPClientAvecThread = new TCPClientAvecThread("Bonjour");
                    new RequeteClientTCP("Bonjour");
                } catch (IOException ex) {
                    Logger.getLogger(RequeteClientTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Thread monclient2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new RequeteClientTCP("Au revoir");
                } catch (IOException ex) {
                    Logger.getLogger(RequeteClientTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        monclient.start();
        monclient2.start();
    }
}
