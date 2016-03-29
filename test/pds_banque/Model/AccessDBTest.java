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
public class AccessDBTest {
    
    public AccessDBTest() {
    }

    /**
     * Test of getAccessDB method, of class AccessDB.
     */
    @Test
    public void testGetAccessDB() {
        System.out.println("getAccessDB");
        AccessDB expResult = null;
        AccessDB result = AccessDB.getAccessDB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnexion method, of class AccessDB.
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
        AccessDB instance = null;
        instance.deconnexion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Infos_Conseiller method, of class AccessDB.
     */
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

    /**
     * Test of insertCustomer method, of class AccessDB.
     */
    @Test
    public void testInsertCustomer() {
        System.out.println("insertCustomer");
        Customer cust = null;
        AccessDB instance = null;
        int expResult = 0;
        int result = instance.insertCustomer(cust);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIDConsultant method, of class AccessDB.
     */
    @Test
    public void testGetIDConsultant() {
        System.out.println("getIDConsultant");
        Customer cust = null;
        AccessDB instance = null;
        int expResult = 0;
        int result = instance.getIDConsultant(cust);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
