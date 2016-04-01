package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import org.json.simple.JSONObject;
import pds_banque.Model.HashString;
import pds_banque.View.ScreenHome;

/**
 *
 * @author Florian
 */
public class TestSelection {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        String hashedPass = HashString.sha512("pass");
        encodage("cmarin", hashedPass);
        System.out.println(hashedPass);

    }

    public static void encodage(String identifiant, String motDePasse) throws IOException {

        JSONObject obj = new JSONObject();

        obj.put("identifiant", identifiant);
        obj.put("motdepasse", motDePasse);

        System.out.print("Objet encodé:" + obj);
        RequeteTCPJson(obj);
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
            
            if(reponseServeur.equals("cmarin")){
                ScreenHome fen2 = new ScreenHome(1);
                fen2.setVisible(true);
                System.out.println("La fenetre devrai etre creee");
            }
            
        }

    }

}
