/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Model;

import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import pds_banque.Customer;
import java.util.Date;

/**
 *
 * @author Warren
 */
public class AccessDBTest {
    
    public AccessDBTest() {
    }

    /**
     * Test of getAccessDB method, of class AccessDB.
     */
    
    @Test
    public void testGetAccessDB() {
        System.out.println("getAccessDB");
       // AccessDB expResult = null;
        AccessDB result = AccessDB.getAccessDB();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getConnexion method, of class AccessDB.
     * @throws java.security.NoSuchAlgorithmException
     */
    @Test
    public void testGetConnexion() throws NoSuchAlgorithmException {
        System.out.println("getConnexion");
        AccessDB instance = AccessDB.getAccessDB();
        int result = instance.getConnexion("CMarin", "pass");
        assertNotNull(result);
    }

    /**
     * Test of deconnexion method, of class AccessDB.
     */
    @Test
    public void testDeconnexion() {
        System.out.println("deconnexion");
        AccessDB instance = AccessDB.getAccessDB();;
        instance.deconnexion();
        assertNotNull(instance);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Infos_Conseiller method, of class AccessDB.
     
    @Test
    public void testInfos_Conseiller() {
        System.out.println("Infos_Conseiller");
        String login = "";
        String pwd ="";
        AccessDB instance = null;
        String[] expResult = null;
        String[] result = instance.Info_Consultant(login,pwd);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of insertCustomer method, of class AccessDB.
     */
    @Test
    public void testInsertCustomer() throws NoSuchAlgorithmException {
        System.out.println("insertCustomer");
        //Customer cust = new Customer(2147483646,"test", "testp", "@", "pari", "par", "12345", 0,"908765453", 24-04-2015, "tt", "Cadre", true);
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	String dateInString = "7-08-1994";
        Date birthDate = null;
	try {

		birthDate = formatter.parse(dateInString);
		System.out.println(birthDate);
		System.out.println(formatter.format(birthDate));

	} catch (ParseException e) {
	}
        Customer cust2 = new Customer("1", "M", "Patrick","Raison", birthDate, "Francais", "0105060807", "patrick@hotmail.com", true, 3500.00f, 1, "Rue de Paris", "Creteil", "94000", 1, 1);
        
        
        AccessDB instance = AccessDB.getAccessDB();
        int result;
        int epResult =1;
        result = instance.insertCustomer(cust2,1);
        assertEquals(epResult,result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getIDConsultant method, of class AccessDB.
     */
    @Test
    public void testGetIDConsultant() {
        System.out.println("getIDConsultant");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	String dateInString = "7-08-1994";
        Date birthDate = null;
	try {

		birthDate = formatter.parse(dateInString);
		System.out.println(birthDate);
		System.out.println(formatter.format(birthDate));

	} catch (ParseException e) {
	}
        Customer cust2 = new Customer("1", "M", "Patrick","Raison", birthDate, "Francais", "0105060807", "patrick@hotmail.com", true, 3500.00f, 1, "Rue de Paris", "Creteil", "94000", 1, 1);
        
        
        //Customer cust = new Customer(2147483647,"test", "testp", "@", "pari", "par", "12345", 0,"908765453", "24/04/2015", "tt", "Cadre", true);
        AccessDB instance = AccessDB.getAccessDB();
        int expResult = 1;
        int result = instance.getIDConsultant(cust2);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
