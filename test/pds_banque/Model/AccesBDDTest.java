/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Model;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import static org.junit.Assert.*;
import pds_banque.Customer;

/**
 *
 * @author Warren
 */
public class AccesBDDTest {
    
    public AccesBDDTest() {
    }

    /**
     * Test of getAccesBDD method, of class AccesBDD.
     */
    @Test
    public void testGetAccesBDD() {
        System.out.println("getAccesBDD");
        AccesBDD expResult = null;
        AccesBDD result = AccesBDD.getAccesBDD();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnexion method, of class AccesBDD.
     */
    @Test
    public void testGetConnexion() throws NoSuchAlgorithmException {
        System.out.println("getConnexion");
        AccesBDD instance = AccesBDD.getAccesBDD();
        int result = instance.getConnexion("CMarin", "pass");
        assertNotNull(result);
    }

    /**
     * Test of deconnexion method, of class AccesBDD.
     */
    @Test
    public void testDeconnexion() {
        System.out.println("deconnexion");
        AccesBDD instance = null;
        instance.deconnexion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Infos_Conseiller method, of class AccesBDD.
     */
    @Test
    public void testInfos_Conseiller() {
        System.out.println("Infos_Conseiller");
        String login = "";
        AccesBDD instance = null;
        String[] expResult = null;
        String[] result = instance.Infos_Conseiller(login);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertCustomer method, of class AccesBDD.
     */
    @Test
    public void testInsertCustomer() {
        System.out.println("insertCustomer");
        Customer cust = null;
        AccesBDD instance = null;
        int expResult = 0;
        int result = instance.insertCustomer(cust);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIDConsultant method, of class AccesBDD.
     */
    @Test
    public void testGetIDConsultant() {
        System.out.println("getIDConsultant");
        Customer cust = null;
        AccesBDD instance = null;
        int expResult = 0;
        int result = instance.getIDConsultant(cust);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
