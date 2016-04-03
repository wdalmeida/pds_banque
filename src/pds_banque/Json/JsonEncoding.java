package pds_banque.Json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import pds_banque.Model.HashString;

/**
 *
 * @author Florian
 */
public class JsonEncoding {
    
    public static JSONObject encodageCustomer(int id_Customer, String title_Customer, String last_Name_Customer, String first_Name_Customer, Float salary_Customer, String street_Customer, String pc_Customer, String city_Customer, String phone_Customer, String email_Customer, String birthday_Customer, int owner_Customer, int id_Consultant, int id_User, int id_status) throws IOException {

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
        //RequeteTCPJson(obj);
        return obj;
    }
    
    
    public static JSONObject encodageLoginConsultant(String identifiant, String motDePasse) throws IOException, FileNotFoundException, ParseException, NoSuchAlgorithmException {

        JSONObject obj = new JSONObject();

        obj.put("identifiant", identifiant);
        obj.put("motdepasse", HashString.sha512(motDePasse));

        System.out.print("Objet encodé:" + obj);
        return obj;
    }
    
    
}
