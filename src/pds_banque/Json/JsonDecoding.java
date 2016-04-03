package pds_banque.Json;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Florian
 */
public class JsonDecoding {

    public static String decodageCustomer(Object objetjson) throws FileNotFoundException, IOException, ParseException {

        System.out.print("Objet recu par le decodeur:" + objetjson);
        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);

        JSONObject jsonObject = (JSONObject) objetjson;

        String title_Customer = (String) jsonObject.get("title_Customer");
        String last_Name_Customer = (String) jsonObject.get("last_Name_Customer");
        String first_Name_Customer = (String) jsonObject.get("first_Name_Customer");
        double salary_Customer = (double) jsonObject.get("salary_Customer");
        String street_customer = (String) jsonObject.get("street_Customer");
        String postalcode_customer = (String) jsonObject.get("postalcode_Customer");
        String city_customer = (String) jsonObject.get("city_customer");
        String phone_Customer = (String) jsonObject.get("phone_Customer");
        String email_Customer = (String) jsonObject.get("email_Customer");
        String birthday_Customer = (String) jsonObject.get("birthday_Customer"); //DATE
        Boolean owner = (Boolean) jsonObject.get("owner_Customer");
        String nationality_customer = (String) jsonObject.get("nationality_Customer");
        long id_Consultant = (long) jsonObject.get("consultant_Customer");
        long id_User = (long) jsonObject.get("idUser_Customer");
        long id_status = (long) jsonObject.get("id_status");

        System.out.println("title_Customer: " + title_Customer);
        System.out.println("last_Name_Customer: " + last_Name_Customer);
        System.out.println("first_Name_Customer: " + first_Name_Customer);
        System.out.println("salary_Customer: " + salary_Customer);
        System.out.println("street_Customer: " + street_customer);
        System.out.println("postalcode_Customer: " + postalcode_customer);
        System.out.println("city_customer: " + city_customer);
        System.out.println("phone_Customer: " + phone_Customer);
        System.out.println("email_Customer: " + email_Customer);
        System.out.println("birthday_Customer: " + birthday_Customer);
        System.out.println("owner_Customer: " + owner);
        System.out.println("nationality_Customer: " + nationality_customer);
        System.out.println("consultant_Customer: " + id_Consultant);
        System.out.println("idUser_Customer: " + id_User);
        System.out.println("id_status: " + id_status);

        int ownerInt = 0;
        if (owner == true) {
            ownerInt = 1;
        }

        String requete = "INSERT INTO `Customer`(`title_Customer`, `last_Name_Customer`, `first_Name_Customer`, `salary_Customer`, `street_Customer`, `pc_Customer`, `city_Customer`, `phone_Customer`, `email_Customer`, `birthday_Customer`, `owner_Customer`, `nationality_Customer`, `id_Consultant`,`id_User`,`id_status`)  VALUES ('" + title_Customer + "','" + last_Name_Customer + "', '" + first_Name_Customer + "','" + salary_Customer + "','" + street_customer + "', '" + postalcode_customer + "', '" + city_customer + "', '" + phone_Customer + "', '" + email_Customer + "', '" + birthday_Customer + "', '" + ownerInt + "','" + nationality_customer + "','" + id_Consultant + "','" + id_User + "','" + id_status + "')";
        System.out.println("Soit la requete SQL:" + '\n');
        System.out.println(requete);

        return requete;

    }

    public static String decodageLoginConsultant(Object objetjson) throws FileNotFoundException, IOException, ParseException {

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
        //AccessDB_server.envoyerRequeteQuery(requete);
        return requete;
    }

}
