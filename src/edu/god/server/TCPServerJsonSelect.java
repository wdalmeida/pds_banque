package etu.god.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;
import static etu.god.serialisation.JsonDecoding.decodeLoginConsultant;

/**
 *
 * @author Florian
 */
public class TCPServerJsonSelect {

    /**
     *
     * @param port
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException Launches the server. If the buffered reader
     * detects an input it will decode it and send it as an SQL query.
     */
    public static void launchServer(int port) throws IOException, FileNotFoundException, ParseException {
        ServerSocket hostingSocket = new ServerSocket(port);
        String dataFromClient;

        while (true) {
            Socket connectionSocket = hostingSocket.accept();
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outputToClient = new DataOutputStream(connectionSocket.getOutputStream());
            dataFromClient = inputFromClient.readLine();
            System.out.println("Donnees recues par le serveur: " + dataFromClient);
            Object obj = dataFromClient;
            //decodageCustomer(obj);
            String result = AccessDB_server.sendQueryRequest(decodeLoginConsultant(dataFromClient));

            outputToClient.writeBytes(result + '\n');
        }

    }

    public static void main(String argv[]) throws Exception {

        launchServer(3001);

    }
}
