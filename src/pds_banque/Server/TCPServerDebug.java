package pds_banque.Server;

import pds_banque.Server.AccessDB_server;

/**
 *
 * @author flesguer
 */
import java.io.*;
import java.net.*;

/**
 *
 * This whole class is only meant to be used for debug purposes. Very useful
 * when it comes to creating the TCP client and the object that has to be sent
 * at the same time.
 */
class TCPServerDebug {

    public static void main(String argv[]) throws Exception {
        String donneesEntreeClient;
        String donneesModifiees;
        ServerSocket socketAccueil = new ServerSocket(3000);
        while (true) {
            Socket connectionSocket = socketAccueil.accept();
            BufferedReader entreeVenantDuClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream sortieVersClient = new DataOutputStream(connectionSocket.getOutputStream());
            donneesEntreeClient = entreeVenantDuClient.readLine();
            System.out.println("Donnees recues: " + donneesEntreeClient);
            donneesModifiees = donneesEntreeClient.toUpperCase() + '\n';
            sortieVersClient.writeBytes(donneesModifiees);
        }
    }
}