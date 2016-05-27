package edu.god.serialisation;

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

    public static String decodeCustomer(Object objetjson) throws FileNotFoundException, IOException, ParseException {

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

        /*System.out.println("title_Customer: " + title_Customer);
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
         */
        int ownerInt = 0;
        if (owner == true) {
            ownerInt = 1;
        }

        String query = "INSERT INTO `Customer`(`title_Customer`, `last_Name_Customer`, `first_Name_Customer`, `salary_Customer`, `street_Customer`, `pc_Customer`, `city_Customer`, `phone_Customer`, `email_Customer`, `birthday_Customer`, `owner_Customer`, `nationality_Customer`, `id_Consultant`,`id_User`,`id_status`)  VALUES ('" + title_Customer + "','" + last_Name_Customer + "', '" + first_Name_Customer + "','" + salary_Customer + "','" + street_customer + "', '" + postalcode_customer + "', '" + city_customer + "', '" + phone_Customer + "', '" + email_Customer + "', '" + birthday_Customer + "', '" + ownerInt + "','" + nationality_customer + "','" + id_Consultant + "','" + id_User + "','" + id_status + "')";
        System.out.println("Soit la requete SQL flo:" + '\n');
        System.out.println(query);

        return query;

    }

    public static String[] decodeLoginConsultant(Object objetjson) throws FileNotFoundException, IOException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String identifiant_consultant = (String) jsonObject.get("identifiant");
        String mdp_consultant = (String) jsonObject.get("motdepasse");

        System.out.println("identifiant : " + identifiant_consultant);
        System.out.println("motdepasse : " + mdp_consultant);

        //  String query = "SELECT id_user FROM User WHERE login_user LIKE '" + identifiant_consultant + "' AND pwd_User LIKE '" + mdp_consultant + "'";
        String data[] = {identifiant_consultant, mdp_consultant};

        return data;
    }

    public static String[] decodingSearchCustomer(Object objetjson) throws FileNotFoundException, IOException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String lastName = (String) jsonObject.get("lastName");
        String firstName = (String) jsonObject.get("firstName");
        String postalCode = (String) jsonObject.get("postalCode");

        System.out.println("lastName : " + lastName);
        System.out.println("firstName : " + firstName);
        System.out.println("postalCode : " + postalCode);

        String data[] = {lastName, firstName, postalCode};

        return data;
    }

    /**
     *
     * @param objetjson
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static String decodingInfoSimCust(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String idCust = (String) jsonObject.get("idCust");

        System.out.println("idCust : " + idCust);

        return idCust;
    }

    /**
     *
     * @param objetjson
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static String[] decodingCustomerId(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        System.out.print("Objet recu par le decodeur:" + objetjson);
        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String title_Customer = (String) jsonObject.get("title_Customer");
        String last_Name_Customer = (String) jsonObject.get("last_Name_Customer");
        String first_Name_Customer = (String) jsonObject.get("first_Name_Customer");
        String salary_Customer = (String) jsonObject.get("salary_Customer");
        String street_customer = (String) jsonObject.get("street_Customer");
        String postalcode_customer = (String) jsonObject.get("postalcode_Customer");
        String city_customer = (String) jsonObject.get("city_customer");
        String phone_Customer = (String) jsonObject.get("phone_Customer");
        String email_Customer = (String) jsonObject.get("email_Customer");
        String birthday_Customer = (String) jsonObject.get("birthday_Customer"); //DATE
        Boolean owner = (Boolean) jsonObject.get("owner_Customer");
        String nationality_customer = (String) jsonObject.get("nationality_Customer");
        String id_Consultant = (String) jsonObject.get("consultant_Customer");
        String id_status = (String) jsonObject.get("id_status");

        String ownerString = "0";
        if (owner == true) {
            ownerString = "1";
        }
        String[] data = {title_Customer, last_Name_Customer, first_Name_Customer, salary_Customer, street_customer, postalcode_customer, city_customer, phone_Customer, email_Customer, birthday_Customer, ownerString, nationality_customer, id_Consultant, id_status};

        return data;

    }

    public static String decodingLastFirstCustomer(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String idCustomer = (String) jsonObject.get("id_Customer");

        System.out.println("id_Customer : " + idCustomer);

        return idCustomer;
    }
    public static String decodingSimCust(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String idCustomer = (String) jsonObject.get("idCustomer");

        System.out.println("idCustomer : " + idCustomer);

        return idCustomer;
    }
    public static String decodingSimCustType(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String idCustomer = (String) jsonObject.get("description_LoanRef");

        System.out.println("idCustomer : " + idCustomer);

        return idCustomer;
    }

    public static String decodingSimId(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String idSim = (String) jsonObject.get("id_sim");

        System.out.println("id_sim : " + idSim);

        return idSim;
    }

    public static Object[] decodingUpLoanSim(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String capital = (String) jsonObject.get("capital_Sim");
        String amount = (String) jsonObject.get("amount_Sim");
        String monthly = (String) jsonObject.get("monthly_Sim");
        String duration = (String) jsonObject.get("duration_Sim");
        String date = (String) jsonObject.get("date_Sim");
        String statut = (String) jsonObject.get("statut_Sim");
        long idConsultant = (long) jsonObject.get("id_Consultant");
        String idInsurance = (String) jsonObject.get("id_Insurance");
        String idRate = (String) jsonObject.get("id_Rate");
        String idLoanRef = (String) jsonObject.get("id_LoanRef");
        String idSim = (String) jsonObject.get("id_Sim");

        System.out.println("id_sim : " + idSim);

        Object[] data = {capital, amount, monthly, duration, date, statut, idConsultant, idInsurance, idRate, idLoanRef, idSim};
        return data;
    }

    public static String[] decodingInLoanSim(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String capital = (String) jsonObject.get("capital_Sim");
        String amount = (String) jsonObject.get("amount_Sim");
        String monthly = (String) jsonObject.get("monthly_Sim");
        String duration = (String) jsonObject.get("duration_Sim");
        String date = (String) jsonObject.get("date_Sim");
        String statut = (String) jsonObject.get("statut_Sim");
        String idConsultant = (String) jsonObject.get("id_Consultant");
        String idCustomer = (String) jsonObject.get("id_Customer");
        String idInsurance = (String) jsonObject.get("id_Insurance");
        String idRate = (String) jsonObject.get("id_Rate");
        String idLoanRef = (String) jsonObject.get("id_LoanRef");

        String[] data = {capital, amount, monthly, duration, date, statut, idConsultant, idCustomer,idInsurance, idRate, idLoanRef};
        return data;
    }

    public static String decodingIdLoanType(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String loanType = (String) jsonObject.get("description_Loan");

        System.out.println("description_Loan : " + loanType);

        return loanType;
    }

    public static String decodingIdCustInSim(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String idSim = (String) jsonObject.get("id_Sim");

        System.out.println("id_Sim : " + idSim);

        return idSim;
    }

    public static String decodingParamById(Object objetjson) throws IOException, FileNotFoundException, ParseException {

        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;

        String idType = (String) jsonObject.get("id_LoanType");

        System.out.println("id_LoanType : " + idType);

        return idType;
    }
}
