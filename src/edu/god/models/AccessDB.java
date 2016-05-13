/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.models;

/**
 *
 * @author florent
 */
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import edu.god.common.contents.*;
import edu.god.entities.*;

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
                    deconnexion();
                }
            } catch (SQLException e) {
                System.out.println("Erreur ! La cr\u00E9ation de la connexion interne a la base projetihm a \u00E9chou\u00E9.\n\nMessage d'erreur :\n");
                deconnexion();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erreur ! Le pilote n'a pas \u00E9t\u00E9 trouv\u00E9.\n\nMessage d'erreur :\n");
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
            try (ResultSet rs = this.declaration.executeQuery(query)) {
                if (rs.first()) {
                    tmp = rs.getInt(1);
                }
            }
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
        try {
            String[] tab = new String[2];
            query = "SELECT last_Name_Consultant,first_Name_Consultant FROM Consultant Natural Join User WHERE login_User='" + login + "' AND pwd_User='" + pwd + "';";
            try (ResultSet rs = this.declaration.executeQuery(query)) {
                if (rs.first()) {
                    tab[0] = rs.getString(1);
                    tab[1] = rs.getString(2);
                }
            }
            return tab;
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return null;
    }

    /*
    public int getIdUser(String login, String pwd) {
        int idUser = 0;
        String query1 = "test";
        try {
            query1 = "select id_User from User where login_User='" + login + "' AND pwd_User='" + pwd + "';";
            System.out.println("requete iduser = "+ query1);
            ResultSet rs = this.declaration.executeQuery(query1);
            if (rs.first()) {
                idUser = rs.getInt(1);
                System.out.println("idUser ="+idUser);
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query1 + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return idUser;
    }
     */
    public int insertCustomer(Customer cust, int idConsultant) throws NoSuchAlgorithmException {
        String query2 = "test";
        String pwd = HashString.sha512(cust.getBirthday().toString()); // use for crypt the password
        int res;
        int tmp = 0;
        int owner = 0;
        if (cust.isOwner()) {
            owner = 1;
        }
        //res = this.getIdUser(cust.getLastName(), pwd);
        try {
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(cust.getBirthday().getTime());
            query2 = "INSERT INTO `Customer`(`title_Customer`, `last_Name_Customer`, `first_Name_Customer`, `salary_Customer`, `street_Customer`, `pc_Customer`, `city_Customer`, `phone_Customer`, `email_Customer`, `birthday_Customer`, `owner_Customer`, `nationality_Customer`, `id_Consultant`,`id_User`,`id_status`) VALUES ('" + cust.getTitle() + "','" + cust.getLastName() + "','" + cust.getFirstName() + "','" + cust.getSalary() + "','" + cust.getStreet() + "','" + cust.getPostalCode() + "','" + cust.getCity() + "','" + cust.getPhoneNumber() + "','" + cust.getEmail() + "','" + sqlDate + "','" + owner + "','" + cust.getNationality() + "','" + idConsultant + "','" + cust.getIdUser() + "','" + cust.getIdstatus() + "')";
            System.out.println(query2);
            res = this.declaration.executeUpdate(query2);
            System.out.println("res =" + res);
            if (res == 1) {
                tmp = 1;
                System.out.println("Insertion du nouveau Client");
            } else {
                tmp = 0;
                System.out.println("Erreur dans l'insertion du nouveau Client");
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query2 + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return tmp;
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
            try (ResultSet rs = this.declaration.executeQuery(query)) {
                if (rs.first()) {
                    tab[0] = rs.getString(1);
                    tab[1] = rs.getString(2);
                }
            }
            return tab;
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return null;
    }

    public ArrayList<String> getStatus() throws SQLException {
        String query = "test";
        int i = 0;
        try {
            ArrayList<String> tab = new ArrayList();
            query = "SELECT id_status,description_status FROM StatusRef";
            try (ResultSet rs = this.declaration.executeQuery(query)) {
                if (rs.first()) {
                    do {
                        tab.add(rs.getString(2));
                    } while (rs.next());
                }
            }
            return tab;
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return null;
    }

    public ArrayList<String[]> getCustomer(String lastN, String firstN, String pc) throws SQLException {
        String query = "SELECT id_Customer,title_Customer,last_Name_Customer,first_Name_Customer,street_Customer"
                + ",pc_Customer,city_Customer,phone_Customer,email_Customer,birthday_Customer,nationality_Customer"
                + " FROM Customer WHERE last_Name_Customer =? AND first_Name_Customer=? AND pc_Customer=? ; ";
        ArrayList<String[]> res = new ArrayList();
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, lastN);
            queryPrep.setString(2, firstN);
            queryPrep.setString(3, pc);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount();
                    String test[] = new String[nbColumn];
                    rs.beforeFirst();
                    while (rs.next()) {
                        for (int i = 0; i < nbColumn; i++) {
                            test[i] = rs.getString(i + 1);
                        }
                        res.add(test);
                    }
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    public String getIDCustomer(String title, String lastN, String firstN, Float salary, String street, String pc, String city, String phone, String email, String birthday, boolean owner, String nation, int idConsultant, int user, int status) {
        String query = "SELECT id_Customer"
                + " FROM Customer WHERE last_Name_Customer =? AND first_Name_Customer=? AND pc_Customer=? ; ";
        String res = "";
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, title);
            queryPrep.setString(2, lastN);
            queryPrep.setString(3, firstN);
            queryPrep.setString(4, salary.toString());
            queryPrep.setString(5, street);
            queryPrep.setString(6, pc);
            queryPrep.setString(7, city);
            queryPrep.setString(8, phone);
            queryPrep.setString(9, email);
            queryPrep.setString(10, birthday);
            queryPrep.setBoolean(11, owner);
            queryPrep.setString(12, nation);
            queryPrep.setInt(13, idConsultant);
            queryPrep.setInt(14, user);
            queryPrep.setInt(15, status);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res = rs.getString(1);
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    public String[] getLastFirstNameCustomer(String idCustomer) {
        String query = "SELECT title_Customer, last_Name_Customer, first_Name_Customer"
                + " FROM Customer WHERE id_Customer =? ; ";
        String res[] = new String[3];
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idCustomer);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res[0] = rs.getString(1);
                    res[1] = rs.getString(2);
                    res[2] = rs.getString(3);
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    public ArrayList<String[]> getDateTypeSims(String idCustomer) {
        String query = "SELECT id_Sim, description_LoanRef, date_Sim,id_Rate,monthly_Sim, duration_Sim "
                + " FROM LoanSimulation NATURAL JOIN LoanRef WHERE id_Customer =? ; ";
        ArrayList<String[]> res = new ArrayList();
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idCustomer);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount();
                    String test[] = new String[nbColumn];
                    rs.beforeFirst();
                    while (rs.next()) {
                        for (int i = 0; i < nbColumn; i++) {
                            test[i] = rs.getString(i + 1);
                        }
                        res.add(test);
                    }
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    public ArrayList<String> getSimByID(String idSim) {
        String query = "SELECT LoanSimulation.*, description_LoanRef "
                + " FROM LoanSimulation NATURAL JOIN LoanRef WHERE id_Sim =? ;";
        ArrayList<String> res = new ArrayList();
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idSim);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount();
                    for (int i = 0; i < nbColumn; i++) {
                        res.add(rs.getString(i + 1));
                    }
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    public ArrayList<String> getLoanType() throws SQLException {
        ArrayList<String> res = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT description_LoanRef FROM LoanRef ;");
        if (rs.first()) {
            rs.beforeFirst();
            while (rs.next()) {
                res.add(rs.getString(1));
            }
        }

        return res;
    }

    public int insertLoanSim(String capital, String amount, String duration, String date, String statut, String idConsultant, String idCustomer, String idInsurance, String idRate ,String loanType) throws SQLException {
        //  java.util.Date utilDate = new java.util.Date();
        //  java.sql.Date sqlDate = new java.sql.Date(cust.getBirthday().getTime());
        String query = "INSERT INTO LoanSimulation ('capital_Sim','total_Amount_Sim','duration_Sim','date_Sim','statut_Sim','id_Consultant,'id_Customer','id_Insurance','id_Rate','id_LoanRef') VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, capital);
        queryPrep.setString(2, amount);
        queryPrep.setString(3, duration);
        queryPrep.setString(4, date);
        queryPrep.setString(5, statut);
        queryPrep.setString(6, idConsultant);
        queryPrep.setString(7, idCustomer);
        queryPrep.setString(8, idInsurance);
        queryPrep.setString(9, idRate);
        queryPrep.setString(10, loanType);
        return queryPrep.executeUpdate();
    }
    public int updateLoanSim(String idSim, String capital, String amount, String duration, String date, String statut, String idConsultant, String idInsurance, String idRate ,String loanType) throws SQLException {
        //  java.util.Date utilDate = new java.util.Date();
        //  java.sql.Date sqlDate = new java.sql.Date(cust.getBirthday().getTime());
        String query = "UPDATE LoanSimulation set capital_Sim=?, total_Amount_Sim=?, duration_Sim=?, date_Sim=?, statut_Sim=?, idConsultant=?, id_Insurance=? ,id_Rate=? WHERE id_Sim=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, capital);
        queryPrep.setString(2, amount);
        queryPrep.setString(3, duration);
        queryPrep.setString(4, date);
        queryPrep.setString(5, statut);
        queryPrep.setString(6, idConsultant);
        queryPrep.setString(8, idInsurance);
        queryPrep.setString(9, idRate);
        queryPrep.setString(10, loanType);
        return queryPrep.executeUpdate();
    }
}
