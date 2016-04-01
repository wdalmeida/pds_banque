package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import pds_banque.Server.AccessDB_server;

public class TCPServerDB {

    /**
     *
     * @author flesguer
     */
    public static void lancerServeur(int port) throws IOException {
        ServerSocket socketAccueil = new ServerSocket(port);
        String donneesEntreeClient;
        //String donneesModifiees;

        while (true) {
            Socket connectionSocket = socketAccueil.accept();
            BufferedReader entreeVenantDuClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream sortieVersClient = new DataOutputStream(connectionSocket.getOutputStream());
            donneesEntreeClient = entreeVenantDuClient.readLine();
            System.out.println("Donnees recues: " + donneesEntreeClient);
            AccessDB_server.envoyerRequeteQuery(donneesEntreeClient);
            sortieVersClient.writeBytes("Requete effectuee");

        }

    }

    public static void main(String argv[]) throws Exception {

        lancerServeur(3000);

    }
}
