/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Server;

/**
 *
 * @author Florian
 */
public class supressionCustomer {

    public static void main(String argv[]) throws Exception {

        String supressionCustomer = "DELETE FROM customer WHERE last_Name_Customer='Mickael';";
        String supressionUser = "DELETE FROM user WHERE login_User LIKE 'jmickael';";
        System.out.println("Soit la requete SQL:" + '\n');
        System.out.println(supressionCustomer);
        System.out.println(supressionUser);
        AccessDB_server.envoyerRequeteUpdate(supressionCustomer);
        AccessDB_server.envoyerRequeteUpdate(supressionUser);

    }

}
