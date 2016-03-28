/**
 *
 * @author flesguer
 */
package pds_banque.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExempleDecodageAmeliore {

    public static void decodageCustomer(Object objetjson) throws FileNotFoundException, IOException, ParseException {

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

    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj;
        obj = parser.parse(new FileReader(
                "encodageCustomer.txt"));
        decodageCustomer(obj);
    }

}
