package edu.god.serialisation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import edu.god.models.HashString;

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
        //decodeCustomer(obj);
        return obj;

    }

    public static JSONObject encodageLoginConsultant(String identifiant, String motDePasse) throws IOException, FileNotFoundException, ParseException, NoSuchAlgorithmException {

        JSONObject obj = new JSONObject();

        obj.put("identifiant", identifiant);
        obj.put("motdepasse", HashString.sha512(motDePasse));

        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    /**
     *
     * @param aLastName
     * @param aFirstName
     * @param aPostalCode
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    public static JSONObject encodingSearchCustomer(String aLastName, String aFirstName, String aPostalCode) throws IOException, FileNotFoundException, ParseException, NoSuchAlgorithmException {

        JSONObject obj = new JSONObject();

        obj.put("lastName", aLastName);
        obj.put("firstName", aFirstName);
        obj.put("postalCode", aPostalCode);

        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    /**
     *
     * @param idCust
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     * @throws NoSuchAlgorithmException
     */
    public static JSONObject encodingInfoSimCust(String idCust) throws IOException, FileNotFoundException, ParseException, NoSuchAlgorithmException {

        JSONObject obj = new JSONObject();

        obj.put("idCust", idCust);

        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    /**
     *
     * @param title_Customer
     * @param last_Name_Customer
     * @param first_Name_Customer
     * @param salary_Customer
     * @param street_customer
     * @param postalcode_customer
     * @param city_customer
     * @param phone_Customer
     * @param email_Customer
     * @param birthday_Customer
     * @param owner
     * @param nationality_customer
     * @param idconsultant
     * @param idStatus
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public static JSONObject encodingCustomerId(String title_Customer, String last_Name_Customer, String first_Name_Customer, Float salary_Customer, String street_customer,
            String postalcode_customer, String city_customer, String phone_Customer, String email_Customer, String birthday_Customer, Boolean owner, String nationality_customer,
            int idconsultant, int idStatus) throws IOException, FileNotFoundException, ParseException {

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
        obj.put("birthday_Customer", birthday_Customer);
        obj.put("owner_Customer", owner);
        obj.put("nationality_Customer", nationality_customer);
        obj.put("consultant_Customer", idconsultant);
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
        System.out.println("id_status: " + idStatus);

        System.out.print("Objet encodé:" + obj);

        return obj;

    }

    public static JSONObject encodingLastFirstNameCustomer(String idCustomer) throws IOException, FileNotFoundException, ParseException {

        JSONObject obj = new JSONObject();

        obj.put("id_Customer", idCustomer);

        System.out.print("Objet encodé:" + obj);

        return obj;
    }

    public static JSONObject encodingSimId(String idSim) throws IOException, FileNotFoundException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("id_sim", idSim);
        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    public static JSONObject encodingUpLoanSim(String idSim, String capital, String amount, String monthly, String duration, String date, String statut, int idCons, String idInsurance, String idRate, String idLoanRef) {
        JSONObject obj = new JSONObject();

        obj.put("capital_Sim", capital);
        obj.put("amount_Sim", amount);
        obj.put("monthly_Sim", monthly);
        obj.put("duration_Sim", duration);
        obj.put("date_Sim", date);
        obj.put("statut_Sim", statut);
        obj.put("id_Consultant", idCons);
        obj.put("id_Insurance", idInsurance);
        obj.put("id_Rate", idRate);
        obj.put("id_LoanRef", idLoanRef);
        obj.put("id_Sim", idSim);

        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    public static JSONObject encodingInLoanSim(String capital, String amount, String monthly, String duration, String date, String statut, String idCons,String idCust, String idInsurance, String idRate, String idLoanRef) {
        JSONObject obj = new JSONObject();

        obj.put("capital_Sim", capital);
        obj.put("amount_Sim", amount);
        obj.put("monthly_Sim", monthly);
        obj.put("duration_Sim", duration);
        obj.put("date_Sim", date);
        obj.put("statut_Sim", statut);
        obj.put("id_Consultant", idCons);
        obj.put("id_Customer", idCust);
        obj.put("id_Insurance", idInsurance);
        obj.put("id_Rate", idRate);
        obj.put("id_LoanRef", idLoanRef);

        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    public static JSONObject encodingIdLoanType(String loanType) throws IOException, FileNotFoundException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("description_Loan", loanType);
        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    public static JSONObject encodingIdCustInSim(String idSim) throws IOException, FileNotFoundException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("id_Sim", idSim);
        System.out.print("Objet encodé:" + obj);
        return obj;
    }

    public static JSONObject encodingParambyID(String idType) throws IOException, FileNotFoundException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("id_LoanType", idType);
        System.out.print("Objet encodé:" + obj);
        return obj;
    }
    
    public static JSONObject encodingLoanType(String idCustomer) throws IOException, FileNotFoundException, ParseException {
        JSONObject obj = new JSONObject();
        obj.put("idCustomer", idCustomer);
        System.out.print("Objet encodé:" + obj);
        return obj;
    }
    
}
