/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Florian
 */
public class TCPServerJsonSelect {

    public static void lancerServeur(int port) throws IOException, FileNotFoundException, ParseException {
        ServerSocket socketAccueil = new ServerSocket(port);
        String donneesEntreeClient;

        while (true) {
            Socket connectionSocket = socketAccueil.accept();
            BufferedReader entreeVenantDuClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream sortieVersClient = new DataOutputStream(connectionSocket.getOutputStream());
            donneesEntreeClient = entreeVenantDuClient.readLine();
            System.out.println("Donnees recues par le serveur: " + donneesEntreeClient);

            //a partir de ce moment le but est de transformer la chaine de caractere lue par le serveur en un objet
            //il faudrait que cet objet soit un JsonObject dans l ideal
            Object obj = donneesEntreeClient;
            decodageCustomer(obj);

            //sortieVersClient.writeBytes("Requete effectuee" + '\n');
            sortieVersClient.writeBytes("cmarin"+ '\n');
        }

    }

    public static void decodageCustomer(Object objetjson) throws FileNotFoundException, IOException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String identifiant_consultant = (String) jsonObject.get("identifiant");
        String mdp_consultant = (String) jsonObject.get("motdepasse");

        System.out.println("identifiant: " + identifiant_consultant);
        System.out.println("motdepasse: " + mdp_consultant);

        String requete = "SELECT login_user FROM user WHERE login_user LIKE '" + identifiant_consultant + "' AND pwd_User LIKE '" + mdp_consultant + "'";

        System.out.println("Soit la requete SQL:" + '\n');
        System.out.println(requete);
        AccessDB_server.envoyerRequeteQuery(requete);
    }

    public static void main(String argv[]) throws Exception {

        lancerServeur(3000);

    }
}