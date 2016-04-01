/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.json.simple.JSONObject;

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
        RequeteTCPJson(obj);
    }

    public static void RequeteTCPJson(JSONObject objetJson) throws IOException {
        String host = "localhost";
        int port = 3000;
        String jsonString = objetJson.toString();
        try (
                Socket socketClient = new Socket(host, port)) {
            DataOutputStream sortieVersServeur = new DataOutputStream(socketClient.getOutputStream());
            sortieVersServeur.writeBytes(jsonString + '\n');
            socketClient.close();
        }
    }

    

}
