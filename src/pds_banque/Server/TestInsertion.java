/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Server;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Florian
 */
public class TestInsertion {

    public static void main(String[] args) throws IOException {

        //je balance dans l'encodeur Json
        //l'encodeur Json balance dans le client
        //le client balance l'objet Json dans le serveur java
        //le serveur Java balance dans le decodeur Json
        //le decodeur Json balance dans la classe d'accès à la base de données
        float salaire = (float) 4500;

        encodage(1, "", "Mickael", "Json", salaire, "18 Rue de Paris", "94225", "Charenton le Pont", "0125476936", "mickael.jackson@gmail.com",
                "1958-08-29", 1, 1, 0, 1);

    }

    public static void encodage(int id_Customer, String title_Customer, String last_Name_Customer, String first_Name_Customer, Float salary_Customer, String street_Customer, String pc_Customer, String city_Customer, String phone_Customer, String email_Customer, String birthday_Customer, int owner_Customer, int id_Consultant, int id_User, int id_status) throws IOException {

        JSONObject obj = new JSONObject();

        obj.put("id_Customer", id_Customer);
        obj.put("title_Customer", title_Customer);
        obj.put("last_Name_Customer", last_Name_Customer);
        obj.put("first_Name_Customer", first_Name_Customer);
        obj.put("salary_Customer", salary_Customer); //! float
        obj.put("street_Customer", street_Customer);
        obj.put("pc_Customer", pc_Customer);
        obj.put("city_Customer", city_Customer);
        obj.put("phone_Customer", phone_Customer);
        obj.put("email_Customer", email_Customer);
        obj.put("birthday_Customer", birthday_Customer); //! date
        obj.put("owner_Customer", owner_Customer);
        obj.put("id_Consultant", id_Consultant);
        obj.put("id_User", id_User);
        obj.put("id_status", id_status);

        System.out.print("Objet encodé:" + obj);
        System.out.println("1");
        RequeteTCPJson(obj);
        System.out.println("2");
    }

    public static void RequeteTCPJson(JSONObject objetJson) throws IOException {
        String host = "localhost";
        int port = 3000;
        String jsonString = objetJson.toString();
        try (
            Socket socketClient = new Socket(host, port)) {
            System.out.println("3");
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
            sortieVersServeur.writeBytes(jsonString + '\n');
            socketClient.close();
        }
    }

    public static void decodageCustomer(JSONObject objetjson) throws FileNotFoundException, IOException, ParseException {

        
        long id_Customer = (long) objetjson.get("id_Customer");
        String title_Customer = (String) objetjson.get("title_Customer");
        String last_Name_Customer = (String) objetjson.get("last_Name_Customer");
        String first_Name_Customer = (String) objetjson.get("first_Name_Customer");
        double salary_Customer = (double) objetjson.get("salary_Customer");
        String street_Customer = (String) objetjson.get("street_Customer");
        String pc_Customer = (String) objetjson.get("pc_Customer");
        String city_Customer = (String) objetjson.get("city_Customer");
        String phone_Customer = (String) objetjson.get("phone_Customer");
        String email_Customer = (String) objetjson.get("email_Customer");
        String birthday_Customer = (String) objetjson.get("birthday_Customer");
        long owner_Customer = (long) objetjson.get("owner_Customer");
        long id_Consultant = (long) objetjson.get("id_Consultant");
        long id_User = (long) objetjson.get("id_User");
        long id_status = (long) objetjson.get("id_status");
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

    }

}
