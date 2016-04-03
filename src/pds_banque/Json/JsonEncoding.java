package pds_banque.Json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import static pds_banque.Json.JsonDecoding.decodageCustomer;

/**
 *
 * @author Florian
 */
public class JsonEncoding {
    

    
    public static JSONObject encodageCustomer(String title_Customer, String last_Name_Customer, String first_Name_Customer, Float salary_Customer, String street_customer,  
            String postalcode_customer, String city_customer, String phone_Customer, String email_Customer, String birthday_Customer, Boolean owner, String nationality_customer, 
            int idconsultant, int idUser, int idStatus) throws IOException, FileNotFoundException, ParseException {

        JSONObject obj = new JSONObject();

        obj.put("title_Customer", title_Customer);
        obj.put("last_Name_Customer", last_Name_Customer);
        obj.put("first_Name_Customer", first_Name_Customer);
        obj.put("salary_Customer", salary_Customer);
        obj.put("street_Customer", street_customer);
        obj.put("postalcode_Customer", postalcode_customer);
        obj.put("city_customer", city_customer);
        obj.put("phone_Customer", phone_Customer); 
        obj.put("email_Customer", email_Customer);
        obj.put("birthday_Customer", birthday_Customer); //! date
        obj.put("owner_Customer", owner);
        obj.put("nationality_Customer", nationality_customer);
        obj.put("consultant_Customer", idconsultant);
        obj.put("idUser_Customer", idUser);
        obj.put("id_status", idStatus);
        
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
        System.out.println("consultant_Customer: " + idconsultant);
        System.out.println("idUser_Customer: " + idUser);
        System.out.println("id_status: " + idStatus);

        
        System.out.print("Objet encodé:" + obj);
        //RequeteTCPJson(obj);
        decodageCustomer(obj);
        return obj;
        
        
    }
    
    
    public static JSONObject encodageLoginConsultant(String identifiant, String motDePasse) throws IOException {

        JSONObject obj = new JSONObject();

        obj.put("identifiant", identifiant);
        obj.put("motdepasse", motDePasse);

        System.out.print("Objet encodé:" + obj);

        return obj;
    }
    
    
}
