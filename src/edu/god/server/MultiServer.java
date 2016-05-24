package edu.god.server;

import static edu.god.serialisation.JsonDecoding.decodeCustomer;
import static edu.god.serialisation.JsonDecoding.decodeLoginConsultant;
import java.io.BufferedReader;
import java.io.*;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Florian
 */
public class MultiServer {

    public static void launchServer(int port) throws IOException, FileNotFoundException, ParseException {
        ServerSocket hostingSocket = new ServerSocket(port);
        String dataFromClient;

        while (true) {
            Socket connectionSocket = hostingSocket.accept();
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());
            dataFromClient = inputFromClient.readLine();
            System.out.println(dataFromClient);

            String userPrefix = dataFromClient.split("\\####")[0]; // prefixe, inidiquant le type de requete

            String numberPrefix = dataFromClient.split("####")[1]; // prefixe, inidiquant le type de requete

            String encodedRequest = dataFromClient.split("\\####")[2]; //la requete en question


            /*switch (requestType) {
             case "i":
             //System.out.println("Le serveur a recu une requete d'update" + '\n');
             outputToClient.writeBytes("Le serveur a recu une requete d'insert" + '\n');

             break;
             case "u":
             System.out.println("Le serveur a recu une requete d'update" + '\n');
             outputToClient.writeBytes("Le serveur a recu une requete d'update" + '\n');
             break;
             case "s":
             System.out.println("Le serveur a reçu une requete de select");
             outputToClient.writeBytes("Le serveur a recu une requete de select" + '\n');
             break;

             case "d":
             System.out.println("Le serveur a reçu une requete de delete");
             outputToClient.writeBytes("Le serveur a recu une requete de delete" + '\n');
             break;

             default:
             System.out.println("Le serveur a reçu une requete non valide");
             outputToClient.writeBytes("Le serveur a recu une requete non valide" + '\n');
             break;
             }
             */
            if (userPrefix.startsWith("P")) {
                //Florent's  requests
            } //////////////////////////////////////////
            else if (userPrefix.startsWith("D")) {
                //Warren's requests
            } //////////////////////////////////////////
            else if (userPrefix.startsWith("M")) {
                //Coline's requests
            } //////////////////////////////////////////
            else if (userPrefix.startsWith("L")) {
                //Florian's requests
                //the request is a Json Object, it needs to be decoded
                if (numberPrefix.startsWith("1")) {

                    AccessDB_server.sendUpdateRequest(decodeCustomer(encodedRequest));
                }
            } //////////////////////////////////////////
            else if (userPrefix.startsWith("S")) {
                //Nabil's requests

            }

            Object obj = dataFromClient;
            //decodageCustomer(obj);
            // String result = AccessDB_server.sendQueryRequest(decodeLoginConsultant(dataFromClient));

            //   outputToClient.writeBytes(result + '\n');
        }

    }

    public static void main(String argv[]) throws Exception {

        launchServer(3000);

    }
}
