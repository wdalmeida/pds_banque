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
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

    /**
     * Return customers by the last and first name and the postal code
     *
     * @param lastN String
     * @param firstN String
     * @param pc String
     * @return res ArrayList<String>
     * @throws SQLException
     */
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
                    rs.beforeFirst();
                    while (rs.next()) {
                        String test[] = new String[nbColumn];
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
 /**
  * return all simulation of a customer
  * @param idCustomer
  * @return res ArrayList<String>
  */
    public ArrayList<String[]> getSimulationsLoanOfCustomer(int idCustomer) {

        String query = query = "select description_LoanRef,capital_Sim,percentage_Rate,amount_Insurance,duration_Sim From LoanRef Natural Join LoanSimulation Natural Join Rate Natural Join Insurance where id_Customer=?;";;
        ArrayList<String[]> res = new ArrayList();
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        double monthlyInterestRate = 0, monthlyPayment = 0, annualPayment = 0;
        int cpt = 1;
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setInt(1, idCustomer);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount() + 3;

                    rs.beforeFirst();
                    while (rs.next()) {
                        String test[] = new String[nbColumn];
                        test[0] = Integer.toString(cpt);
                        test[1] = rs.getString(1);
                        test[2] = nf.format(Integer.parseInt(rs.getString(2)));
                        test[3] = rs.getString(3);
                        monthlyInterestRate = Integer.parseInt(rs.getString(3)) / 12.0;
                        monthlyPayment = Integer.parseInt(rs.getString(2)) * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -Integer.parseInt(rs.getString(5))))) + Integer.parseInt(rs.getString(4));
                        test[4] = nf.format(Double.parseDouble(Double.toString(monthlyPayment)));
                        test[5] = rs.getString(4);
                        test[6] = rs.getString(5);

                        annualPayment = monthlyPayment * Double.parseDouble(rs.getString(5));
                        test[7] = nf.format(Double.parseDouble(Double.toString(annualPayment)));
                        res.add(test);
                        cpt++;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    /**
     * Return the customer id with all the parameter required to insert him
     *
     * @param title String
     * @param lastN String
     * @param firstN String
     * @param salary Float
     * @param street String
     * @param pc String
     * @param city String
     * @param phone String
     * @param email String
     * @param birthday String
     * @param owner boolean
     * @param nation String
     * @param idConsultant int
     * @param status int
     * @return res String
     */
    public String getIDCustomer(String title, String lastN, String firstN, Float salary, String street, String pc, String city, String phone, String email, String birthday, boolean owner, String nation, int idConsultant, int status) {
        String query = "SELECT id_Customer"
                + " FROM Customer WHERE title_Customer=? AND last_Name_Customer=? AND first_Name_Customer=? AND salary_Customer=? AND street_Customer=?"
                + " AND pc_Customer=? AND city_Customer=? AND phone_Customer=? AND email_Customer=? AND birthday_Customer=? AND nationality_Customer=? "
                + " AND nationality_Customer=? AND id_Consultant=? AND statut=? ; ";
        String res = "";
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, title);
            queryPrep.setString(2, lastN);
            queryPrep.setString(3, firstN);
            queryPrep.setFloat(4, salary);
            queryPrep.setString(5, street);
            queryPrep.setString(6, pc);
            queryPrep.setString(7, city);
            queryPrep.setString(8, phone);
            queryPrep.setString(9, email);
            queryPrep.setString(10, birthday);
            queryPrep.setBoolean(11, owner);
            queryPrep.setString(12, nation);
            queryPrep.setInt(13, idConsultant);
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

    /**
     * Return the last and the first name of a customer
     *
     * @param idCustomer String
     * @return res Strin[]
     */
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

    /**
     * Return the id, the type, the date, the rate,the monthly and the duration
     * for all the sim of the given customer
     *
     * @param idCustomer String
     * @return res ArrayList<String>
     */
    public ArrayList<String[]> getDateTypeSims(String idCustomer) {
        String query = "SELECT id_Sim, description_LoanRef, date_Sim,percentage_Rate,monthly_Sim, duration_Sim "
                + " FROM LoanSimulation NATURAL JOIN LoanRef,Rate WHERE id_Customer =? AND now() > DATE_SUB(now(), INTERVAL 6 MONTH); ";
        ArrayList<String[]> res = new ArrayList();
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idCustomer);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    ResultSetMetaData metadata = rs.getMetaData();
                    int nbColumn = metadata.getColumnCount();
                    rs.beforeFirst();
                    while (rs.next()) {
                        String test[] = new String[nbColumn];
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

    /**
     * Return all the simulation for a simulation
     *
     * @param idSim String
     * @return res ArrayList<String>
     */
    public ArrayList<String> getSimByID(String idSim) {
        String query = "SELECT id_Sim,capital_Sim,amount_Sim,monthly_Sim,duration_Sim,date_Sim,statut_Sim,amount_Insurance, description_LoanRef,percentage_Rate, id_Customer "
                + " FROM LoanSimulation NATURAL JOIN LoanRef,Rate,Insurance WHERE id_Sim=? ;";
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

    /**
     * Return all the type of loan
     *
     * @return res ArrayList<String>
     * @throws SQLException
     */
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

    /**
     * The method insert a new simulation in the database
     *
     * @param capital String
     * @param amount String
     * @param monthly String
     * @param duration String
     * @param date String
     * @param statut String
     * @param idConsultant int
     * @param idCustomer String
     * @param idInsurance String
     * @param idRate String
     * @param loanType String
     * @return
     * @throws SQLException
     */
    public int insertLoanSim(String capital, String amount, String monthly, String duration, String date, String statut, int idConsultant, String idCustomer, String idInsurance, String idRate, String loanType) throws SQLException {
        String query = "INSERT INTO LoanSimulation (capital_Sim,amount_Sim,monthly_Sim,duration_Sim,date_Sim,statut_Sim,id_Consultant,id_Customer,id_Insurance,id_Rate,id_LoanRef) VALUES (?,?,?,?,?,?,?,?,?,?) ; ";
        System.out.println(query);
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, capital);
        queryPrep.setString(2, amount);
        queryPrep.setString(3, monthly);
        queryPrep.setString(4, duration);
        queryPrep.setString(5, date);
        queryPrep.setString(6, statut);
        queryPrep.setInt(7, idConsultant);
        queryPrep.setString(8, idCustomer);
        queryPrep.setString(9, idInsurance);
        queryPrep.setString(10, idRate);
        queryPrep.setString(11, loanType);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();
    }

    /**
     * The method update a simulation in the table LoanSimulation with the given
     * parameter
     *
     * @param idSim String
     * @param capital String
     * @param amount String
     * @param monthly String
     * @param duration String
     * @param date String
     * @param statut String
     * @param idConsultant int
     * @param idInsurance String
     * @param idRate String
     * @param loanType String
     * @return int
     * @throws SQLException
     */
    public int updateLoanSim(String idSim, String capital, String amount, String monthly, String duration, String date, String statut, int idConsultant, String idInsurance, String idRate, String loanType) throws SQLException {
        String query = "UPDATE LoanSimulation set capital_Sim=?,amount_Sim=?,monthly_Sim=?, duration_Sim=?, date_Sim=?, statut_Sim=?, id_Consultant=?, id_Insurance=? ,id_Rate=?, id_LoanRef=? WHERE id_Sim=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, capital);
        queryPrep.setString(2, amount);
        queryPrep.setString(3, monthly);
        queryPrep.setString(4, duration);
        queryPrep.setString(5, date);
        queryPrep.setString(6, statut);
        queryPrep.setInt(7, idConsultant);
        queryPrep.setString(8, idInsurance);
        queryPrep.setString(9, idRate);
        queryPrep.setString(10, loanType);
        queryPrep.setString(11, idSim);

        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();
    }

    /**
     * Return the customer id which match with simulation
     *
     * @param id String id of a simulation
     * @return res String
     * @throws SQLException
     */
    public String getIdCustInSim(String id) throws SQLException {
        String res = null;
        String query = "SELECT id_Customer from LoanSimulation where id_Sim=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, id);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            res = rs.getString("id_Customer");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(res);
        return res;
    }

    /*Get rates Consumption */
    public String getratesConsumption(String idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesConsumption from Agency where id_agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesConsumption");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Personnel */
    public String getratesPersonal(String idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesPersonel from Agency where id_agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesPersonel");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Property */
    public String getratesProperty(String idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesProperty from Agency where id_agency=5 ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesProperty");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Project */
    public String getratesProj(String idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesProject from Agency where id_agency=5 ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesProject");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Repurchase */
    public String getratesRepurchase(String idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesRepurchase from Agency where id_agency=5 ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesRepurchase");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Vehicles */
    public String getratesVehicles(String idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesVehicles from Agency where id_agency=5 ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesVehicles");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }
 /*Get id agency user*/
    public String getidAgency(int idConsultant) throws SQLException {
        String chiff = null;
        String query = "SELECT idAgency FROM Consultant where id_Consultant=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idConsultant);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("id_Agency");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }
    /**
     * Return the id of the description
     *
     * @param selectedItem String
     * @return res String
     * @throws SQLException
     */
    public String getIdLoanType(String selectedItem) throws SQLException {
        String res = null;
        String query = "SELECT id_LoanRef from LoanRef where description_LoanRef=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, selectedItem);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            res = rs.getString("id_LoanRef");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(res);

        return res;
    }

    /**
     * Return a table which contains the delimeter values for a simulation's
     * type
     *
     * @param idType String
     * @return res int[]
     */
    public int[] getParambyID(String idType) {
        String query = "SELECT min_Amount_LoanRef, max_Amount_LoanRef, min_Duration_LoanRef, max_Duration_LoanRef"
                + " FROM LoanRef WHERE id_LoanRef =? ; ";
        int res[] = new int[4];
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idType);

            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res[0] = rs.getInt(1);
                    res[1] = rs.getInt(2);
                    res[2] = rs.getInt(3);
                    res[3] = rs.getInt(4);
                }
                System.out.println("requete = " + queryPrep.toString());
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }
    
     public String getRateAverage() throws SQLException {
        String rateStr = null;
        Double rate= 0.0;
        String query = "SELECT percentage_Rate from Rate";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        //queryPrep.setString(1, agency);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            rate = rs.getDouble("percentage_Rate");
        }
        return rateStr = rate.toString();
    }
     
     public String getLoanNumber() throws SQLException {
        int nbrLoan = 0;
        String loanCount = null;
        String query = "SELECT distinct Count(*) as loan_Number from Loan";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        //queryPrep.setString(1, agency);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            nbrLoan = rs.getInt("loan_Number");
        }
        return loanCount = Integer.toString(nbrLoan);
    }
}
