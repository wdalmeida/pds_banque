/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Model;

/**
 *
 * @author florent
 */
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import pds_banque.Constantes;
import pds_banque.Customer;

public class AccessDB implements Constantes {

    private Statement declaration;
    private Connection conn;
    private static AccessDB acces;

    private AccessDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//com.mysql.jdbc.Driver
            try {
                this.conn = DriverManager.getConnection(URL_INT, USER_INT, PASSWD_INT);
                try {
                    this.declaration = conn.createStatement();
                } catch (SQLException e) {
                    System.out.println("Erreur ! La cr\u00E9ation de l'objet Statement interne a \u00E9chou\u00E9.\n\nMessage d'erreur :\n");
                    e.printStackTrace();
                    deconnexion();
                }
            } catch (SQLException e) {
                System.out.println("Erreur ! La cr\u00E9ation de la connexion interne a la base projetihm a \u00E9chou\u00E9.\n\nMessage d'erreur :\n");
                e.printStackTrace();
                deconnexion();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur ! Le pilote n'a pas \u00E9t\u00E9 trouv\u00E9.\n\nMessage d'erreur :\n");
            e.printStackTrace();
            deconnexion();
        }
    }

    public static AccessDB getAccessDB() {
        if (acces == null) {
            acces = new AccessDB();
        }
        return acces;
    }

    public int getConnexion(String login, String pwd) throws NoSuchAlgorithmException {
        int tmp = 0;
        String query = "";
        pwd = HashString.sha512(pwd); // use for hash the password
        try {
            query = "SELECT id_User FROM User Natural Join Consultant where login_User='" + login + "' AND pwd_User='" + pwd + "';";
            ResultSet rs = this.declaration.executeQuery(query);
            if (rs.first()) {
                tmp = rs.getInt(1);                
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return tmp;
    }

    public void deconnexion() {
        try {
            if (this.declaration != null) {
                if (!this.declaration.isClosed()) {
                    this.declaration.close();
                }
                this.declaration = null;
            }
            if (this.conn != null) {
                if (!this.conn.isClosed()) {
                    this.conn.close();
                }
                this.conn = null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La deconnexion à la BDD interne n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
    }

    public String[] Info_Consultant(String login, String pwd) throws NoSuchAlgorithmException {
        String query = "test";
        pwd = HashString.sha512(pwd); // use for crypt the password
        System.out.println("Mot de passe sha512 = " + pwd);
        try {
            String[] tab = new String[2];
            query = "SELECT last_Name_Consultant,first_Name_Consultant FROM Consultant Natural Join User WHERE login_User='" + login + "' AND pwd_User='" + pwd + "';";
            ResultSet rs = this.declaration.executeQuery(query);
            if (rs.first()) {
                tab[0] = rs.getString(1);
                tab[1] = rs.getString(2);
            }
            rs.close();
            return tab;
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
            e.printStackTrace();
        }
        return null;
    }

    public int insertCustomer(Customer cust) {
        String query1 = "test";
        String query2 = "test";
        int res;
        try {
            query1 = "Insert Into User(login,pwd) values('" + cust.getLastName() + "','" + cust.getFirstName() + "')";
            res = this.declaration.executeUpdate(query1);
            try {
                if (res == 1) {
                    query2 = "INSERT INTO `Customer`(`last_Name_Customer`, `first_Name_Customer`, `salary_Customer`, `street_Customer`, `pc_Customer`, `city_Customer`, `phone_Customer`, `email_Customer`,`id_Consultant`) VALUES ('" + cust.getLastName() + "','" + cust.getFirstName() + "','" + cust.getSalary() + "','" + cust.getStreet() + "','" + cust.getPostalCode() + "','" + cust.getCity() + "','" + cust.getPhoneNumber() + "','" + cust.getEmail() + "',2)";
                    res = this.declaration.executeUpdate(query2);
                    if (res == 1) {
                        return 1;
                    } else {
                        System.out.println("Erreur dans l'insertion du nouveau Client");
                        return 0;
                    }
                } else {
                    System.out.println("Erreur dans l'insertion du nouveau Utilisateur");
                }
            } catch (SQLException e) {
                System.out.println("Erreur ! La requ\u00EAte" + query2 + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query1 + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
            e.printStackTrace();
        }
        return 0;
    }

    public int getIDConsultant(Customer cust) {
        int tmp = 0;
        String query1 = "test";
        try {
            System.out.println("bdd getIDConsultant");
            query1 = "Select Id_Consultant from Customer where last_Name_Customer ='" + cust.getLastName() + "' AND first_Name_Customer ='" + cust.getFirstName() + "')";

            ResultSet rs = this.declaration.executeQuery(query1);
            if (rs.first()) {
                tmp = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query1 + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return tmp;
    }

    public String[] getConsultantInfo(int idC) {
        String query = "test";
        try {
            String[] tab = new String[2];
            query = "SELECT last_Name_Consultant,first_Name_Consultant FROM Consultant Natural Join User WHERE id_User='" + idC + "';";
            ResultSet rs = this.declaration.executeQuery(query);
            if (rs.first()) {
                tab[0] = rs.getString(1);
                tab[1] = rs.getString(2);
            }
            rs.close();
            return tab;
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<String> getStatus() throws SQLException{
        String query ="test";
        int i=0;
        try{
            ArrayList<String> tab = new ArrayList();
            query = "SELECT id_status,description_status FROM statusRef;";
            ResultSet rs = this.declaration.executeQuery(query);
            if (rs.first()) {
                do{
                    tab.add(rs.getString(2));
                }while(rs.next());
            }
            rs.close();
            return tab;
        }catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
            e.printStackTrace();
        }
        return null;
    }
    
}