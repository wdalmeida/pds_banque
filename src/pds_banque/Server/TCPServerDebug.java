package pds_banque.Server;

import pds_banque.Server.AccessDB_server;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author flesguer
 */
import java.io.*;
import java.net.*;

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