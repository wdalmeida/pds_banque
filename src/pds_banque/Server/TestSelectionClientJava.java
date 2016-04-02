package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import static pds_banque.Json.JsonEncoding.encodageLoginConsultant;
import pds_banque.Model.HashString;
import pds_banque.View.ScreenHome;

/**
 *
 * @author Florian
 */
public class TestSelectionClientJava extends Thread {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        Thread monclient = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    RequeteTCPJson(encodageLoginConsultant("cmarin", HashString.sha512("pass")));
                } catch (IOException ex) {

                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(TestSelectionClientJava.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        monclient.start();
    }

    public static void RequeteTCPJson(JSONObject objetJson) throws IOException {
        String host = "localhost";
        int port = 3000;
        String jsonString = objetJson.toString();
        String reponseServeur;

        try (Socket socketClient = new Socket(host, port)) {
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
            BufferedReader receptionServeur = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            sortieVersServeur.writeBytes(jsonString + '\n');
            reponseServeur = receptionServeur.readLine();
            System.out.println("Le serveur a repondu: " + reponseServeur);
            socketClient.close();

            if (reponseServeur.equals("cmarin")) {
                ScreenHome fen2 = new ScreenHome(1);
                fen2.setVisible(true);
                System.out.println("La fenetre devrai etre creee");
            }

        }

    }

}
