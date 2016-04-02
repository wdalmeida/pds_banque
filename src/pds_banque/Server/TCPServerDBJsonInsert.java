package pds_banque.Server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Florian
 */
public class TCPServerDBJsonInsert {

    public static void lancerServeur(int port) throws IOException, FileNotFoundException, ParseException {
        ServerSocket socketAccueil = new ServerSocket(port);
        String donneesEntreeClient;

        while (true) {
            Socket connectionSocket = socketAccueil.accept();
            BufferedReader entreeVenantDuClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream sortieVersClient = new DataOutputStream(connectionSocket.getOutputStream());
            
            donneesEntreeClient = entreeVenantDuClient.readLine();
            System.out.println("Donnees recues par le serveur: " + donneesEntreeClient);
            Object obj = donneesEntreeClient;
            
            AccessDB_server.envoyerRequeteUpdate(decodageCustomer(obj));
          
            //sortieVersClient.writeBytes("Requete effectuee" + '\n');
        }

    }

    public static String decodageCustomer(Object objetjson) throws FileNotFoundException, IOException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        //String Newligne=System.getProperty("line.separator"); 
        //System.out.print(objetjson + Newligne);
        //System.out.print("Recu cinq sur cinq");

        JSONObject jsonObject = (JSONObject) objetjson;
        long id_Customer = (long) jsonObject.get("id_Customer");
        String title_Customer = (String) jsonObject.get("title_Customer");
        String last_Name_Customer = (String) jsonObject.get("last_Name_Customer");
        String first_Name_Customer = (String) jsonObject.get("first_Name_Customer");
        double salary_Customer = (double) jsonObject.get("salary_Customer");
        String street_Customer = (String) jsonObject.get("street_Customer");
        String pc_Customer = (String) jsonObject.get("pc_Customer");
        String city_Customer = (String) jsonObject.get("city_Customer");
        String phone_Customer = (String) jsonObject.get("phone_Customer");
        String email_Customer = (String) jsonObject.get("email_Customer");
        String birthday_Customer = (String) jsonObject.get("birthday_Customer");
        long owner_Customer = (long) jsonObject.get("owner_Customer");
        long id_Consultant = (long) jsonObject.get("id_Consultant");
        long id_User = (long) jsonObject.get("id_User");
        long id_status = (long) jsonObject.get("id_status");
        System.out.println("id_Customer: " + id_Customer);
        System.out.println("title_Customer: " + title_Customer);
        System.out.println("last_Name_Customer: " + last_Name_Customer);
        System.out.println("first_Name_Customer: " + first_Name_Customer);
        System.out.println("salary_Customer: " + salary_Customer);
        System.out.println("street_Customer: " + street_Customer);
        System.out.println("pc_Customer: " + pc_Customer);
        System.out.println("city_Customer: " + city_Customer);
        System.out.println("phone_Customer: " + phone_Customer);
        System.out.println("email_Customer: " + email_Customer);
        System.out.println("birthday_Customer: " + birthday_Customer);
        System.out.println("owner_Customer: " + owner_Customer);
        System.out.println("id_Consultant: " + id_Consultant);
        System.out.println("id_User: " + id_User);
        System.out.println("id_status: " + id_status);

        String requete = "INSERT INTO Customer " + "VALUES (" + id_Customer + ",'" + title_Customer + "', '" + last_Name_Customer + "','"
                + first_Name_Customer + "'," + salary_Customer + ", '" + street_Customer + "', '" + pc_Customer + "', '" + city_Customer + "', '" + phone_Customer + "', '"
                + email_Customer + "', '" + birthday_Customer + "'," + owner_Customer + "," + id_Consultant + "," + id_User + "," + id_status + ")";
        System.out.println("Soit la requete SQL:" + '\n');
        System.out.println(requete);
        //AccessDB_server.envoyerRequeteUpdate(requete);
        return requete;
    }

    public static void main(String argv[]) throws Exception {

        lancerServeur(3000);

    }
}
