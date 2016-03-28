package pds_banque.Json;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

/**
 *
 * @author Florian
 */
public class EncodageBasique {

    public static void main(String[] args) throws IOException {
        JSONObject obj = new JSONObject();

      //obj.put("Nom du conseiller", "Patick");
        //obj.put("id", new Integer(001));
        //obj.put("test", new Double(1000.21));
        //obj.put("est_directeur", new Boolean(true));
        obj.put("id_Customer", 4);
        obj.put("title_Customer", "");
        obj.put("last_Name_Customer", "Mickael");
        obj.put("first_Name_Customer", "Jackson");
        obj.put("salary_Customer", 4500); //! float
        obj.put("street_Customer", "Rue de Paris");
        obj.put("pc_Customer", "94225");
        obj.put("city_Customer", "Charenton le Pont");
        obj.put("phone_Customer", "0125476936");
        obj.put("email_Customer", "mickael.jackson@gmail.com");
        obj.put("birthday_Customer", "1958-08-29");
        obj.put("owner_Customer", 1);
        obj.put("id_Consultant", 2);
        obj.put("id_User", 0);
        obj.put("id_status", 1);

        System.out.print(obj);
        String jsonString = obj.toString();

        try (FileWriter file = new FileWriter("testJson.txt")) {
            file.write(obj.toJSONString());
            System.out.println("Object ecrit dans le fichier avec succes");
            System.out.println("\nObjet ecrit: " + obj);
        }

    }
}
