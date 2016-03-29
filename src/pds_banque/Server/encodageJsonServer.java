package pds_banque.Server;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;


/**
 *
 * @author Florian
 */
public class encodageJsonServer {
    

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

        System.out.print(obj);
        String jsonString = obj.toString();

        try (FileWriter file = new FileWriter("encodageCustomer.txt")) {
            file.write(obj.toJSONString());
            System.out.println("Object ecrit dans le fichier avec succes");
            System.out.println("\nObjet ecrit: " + obj);
            
            pds_banque.Server.RequeteClientTCPJson.RequeteClientTCPJson(obj, "localhost", 3000);
        }
    }


}
