package edu.god.server;

import edu.god.server.AccessDB_server;

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
        String dataFromClient;
        String modifiedData;
        ServerSocket hostingSocket = new ServerSocket(3000);
        while (true) {
            Socket connectionSocket = hostingSocket.accept();
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());
            dataFromClient = inputFromClient.readLine();
            System.out.println("Donnees recues: " + dataFromClient);
            modifiedData = dataFromClient.toUpperCase() + '\n';
            outputToClient.writeBytes(modifiedData);
        }
    }
}