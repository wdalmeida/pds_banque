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

    /* public int insertCustomer(Customer cust, int idConsultant) throws NoSuchAlgorithmException {
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
            query2 = "INSERT INTO `Customer`(`title_Customer`, `last_Name_Customer`, `first_Name_Customer`, `salary_Customer`, `street_Customer`, `pc_Customer`, `city_Customer`, `phone_Customer`, `email_Customer`, `birthday_Customer`, `owner_Customer`, `nationality_Customer`, `id_Consultant`,`id_User`,`id_status`) VALUES ('" + cust.getTitle() + "','" + cust.getLastName() + "','" + cust.getFirstName() + "','" + cust.getSalary() + "','" + cust.getStreet() + "','" + cust.getPostalCode() + "','" + cust.getCity() + "','" + cust.getPhoneNumber() + "','" + cust.getEmail() + "','" + sqlDate + "','" + owner + "','" + cust.getNationality() + "','" + idConsultant + "','" + cust.getIdUser() + "','" + cust.getIdstatus() + "'); commit;";
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
            System.out.println("Erreur ! La requ\u00EAte insert " + query2 + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return tmp;
    }
     */
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

    public ArrayList<String> getTypeLoanCustomer(int idC) {
        String query = "SELECT description_LoanRef FROM LoanRef Natural Join LoanSimulation Natural Join Rate where id_Customer=" + idC + ";";
        try {
            ArrayList<String> tab = new ArrayList<>();
            try (ResultSet rs = this.declaration.executeQuery(query)) {
                if (rs.first()) {
                    tab.add(rs.getString(1));
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

    public float getSalaryOfCustomer(String idCustomer) {
        String query = query = "select salary_Customer From Customer where id_Customer=?;";
        float res = 0;
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idCustomer);
            try (ResultSet rs = queryPrep.executeQuery()) {
                if (rs.first()) {
                    res = Float.parseFloat(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requete " + query + " n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return res;
    }

    /**
     * return all simulation of a customer
     *
     * @param idCustomer
     * @return res ArrayList<String>
     */
    public ArrayList<String[]> getSimulationsLoanOfCustomer(String idCustomer, String type) {
        String query = query = "select description_LoanRef,capital_Sim,percentage_Rate,percentage_Insurance,duration_Sim "
                + "From LoanRef Natural Join LoanSimulation where id_Customer=? AND description_LoanRef=? "
                + "AND now() > DATE_SUB(now(),INTERVAL 6 MONTH);";
        ArrayList<String[]> res = new ArrayList();
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        double monthlyInterestRate = 0, monthlyPayment = 0, annualPayment = 0;
        int cpt = 1;
        try {
            PreparedStatement queryPrep = conn.prepareStatement(query);
            queryPrep.setString(1, idCustomer);
            queryPrep.setString(2, type);
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
        String query = "SELECT id_Sim,capital_Sim,amount_Sim,monthly_Sim,duration_Sim,date_Sim,statut_Sim,percentage_Insurance, description_LoanRef,percentage_Rate, id_Customer "
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

    /* public String getRateAverage() throws SQLException {
        String rate= null;
        String query = "SELECT AVG(percentage_Rate) FROM Rate";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        //queryPrep.setString(1, agency);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            rate = rs.getString("AVG(percentage_Rate)");
        }
        return rate ;
    }*/
    public String getLoanNumber(int year) throws SQLException {
        int nbrLoan = 0;
        String loanCount = "";
        String query = "SELECT distinct Count(*) as loan_Number from Loan where start_Date like ?";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setString(1, Integer.toString(year) + "%");
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            System.out.println("TEST GETLOANNUMBER");
            System.out.println(" numberloan = " + rs.getInt("loan_Number"));
            nbrLoan = rs.getInt("loan_Number");
        }

        return loanCount = Integer.toString(nbrLoan);
    }

    public String getAgency() throws SQLException {
        int nbrLoan = 0;
        String loanCount = "";
        String query = "SELECT distinct Count(*) as loan_Number from Loan";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        //queryPrep.setString(1, agency);
        ResultSet rs = queryPrep.executeQuery();
        if (rs.first()) {
            nbrLoan = rs.getInt("loan_Number");
        }
        return loanCount = Integer.toString(nbrLoan);
    }

    /*For Coline
    ScreenModifyRatesConsumption*/
 /*Get Parent Agency rates Consumption */
    public String getRatesConsumptionParent() throws SQLException {
        String chiff = null;
        String query = "SELECT ParentRatesConsumption from Rate_Parent_company ;";
        Statement stmt = conn.prepareStatement(query);
        ResultSet resultat = stmt.executeQuery(query);
        if (resultat.first()) {
            chiff = resultat.getString("ParentRatesConsumption");
        }
        System.out.println("requete = " + stmt.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Consumption */
    public String getratesConsumption(int idAgency) throws SQLException {
        String taux = null;
        String query = "SELECT RatesConsumption from Agency where id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        System.out.println("getratesConsumption se lance");
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            taux = resultat.getString("RatesConsumption");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(taux);
        return taux;
    }

    /*update rates Consumption Agency*/
    public int updateRatesConsumption(int idAgency, float RatesConsumption) throws SQLException {
        String query = "UPDATE Agency set RatesConsumption=? WHERE id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        System.out.println("updateratesConsumption se lance");
        queryPrep.setFloat(1, RatesConsumption);
        queryPrep.setInt(2, idAgency);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();
    }

    /*For Coline
    ScreenModifyRatesPersonal*/

 /*Get Parent Agency rates Personal */
    public String getRatesPesonalParent() throws SQLException {
        String chiff = null;
        String query = "SELECT ParentRatesPersonel FROM Rate_Parent_company ;";
        Statement stmt = conn.prepareStatement(query);
        ResultSet resultat = stmt.executeQuery(query);
        if (resultat.first()) {
            chiff = resultat.getString("ParentRatesPersonel");
        }
        System.out.println("requete = " + stmt.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Personal */
    public String getratesPersonal(int idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesPersonel FROM Agency where id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesPersonel");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Update Rates Personal*/
    public int updateRatesPersonal(int idAgency, float RatesPersonel) throws SQLException {
        String query = "UPDATE Agency set RatesPersonel=? WHERE id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setFloat(1, RatesPersonel);
        queryPrep.setInt(2, idAgency);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();

    }

    /*For Coline
    ScreenModifyRatesProperty*/

 /*Get Parent Agency rates Property */
    public String getRatesPropertyParent() throws SQLException {
        String chiff = null;
        String query = "SELECT ParentRatesProperty from Rate_Parent_company ;";
        Statement stmt = conn.prepareStatement(query);
        ResultSet resultat = stmt.executeQuery(query);
        if (resultat.first()) {
            chiff = resultat.getString("ParentRatesProperty");
        }
        System.out.println("requete = " + stmt.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Property */
    public String getratesProperty(int idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesProperty from Agency where id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesProperty");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Update Rates Property*/
    public int updateRatesProperty(int idAgency, float RatesProperty) throws SQLException {
        String query = "UPDATE Agency set RatesProperty=? WHERE id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setFloat(1, RatesProperty);
        queryPrep.setInt(2, idAgency);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();

    }

    /*For Coline
    ScreenModifyRatesProject*/

 /*Get Parent Agency rates Project */
    public String getRatesProjectParent() throws SQLException {
        String chiff = null;
        String query = "SELECT ParentRatesProject from Rate_Parent_company ;";
        Statement stmt = conn.prepareStatement(query);
        ResultSet resultat = stmt.executeQuery(query);
        if (resultat.first()) {
            chiff = resultat.getString("ParentRatesProject");
        }
        System.out.println("requete = " + stmt.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Project */
    public String getratesProj(int idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesProject from Agency where id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesProject");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Update Rates Project*/
    public int updateRatesProject(int idAgency, float RatesProject) throws SQLException {
        String query = "UPDATE Agency set RatesProject=? WHERE id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setFloat(1, RatesProject);
        queryPrep.setInt(2, idAgency);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();

    }

    /*For Coline
    ScreenModifyRatesRepurchase*/

 /*Get Parent Agency rates Repurchase */
    public String getRatesRepurchaseParent() throws SQLException {
        String chiff = null;
        String query = "SELECT ParentRatesRepurchase from Rate_Parent_company ;";
        Statement stmt = conn.prepareStatement(query);
        ResultSet resultat = stmt.executeQuery(query);
        if (resultat.first()) {
            chiff = resultat.getString("ParentRatesRepurchase");
        }
        System.out.println("requete = " + stmt.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Repurchase */
    public String getratesRepurchase(int idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesRepurchase from Agency where id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesRepurchase");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Update Rates Repurchase*/
    public int updateRatesRepurchase(int idAgency, float RatesRepurchase) throws SQLException {
        String query = "UPDATE Agency set RatesRepurchase=? WHERE id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setFloat(1, RatesRepurchase);
        queryPrep.setInt(2, idAgency);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();

    }

    /*For Coline
    ScreenModifyRatesVehicles*/
 /*Get Parent Agency rates Vehicles */
    public String getRatesVehiclesParent() throws SQLException {
        String chiff = null;
        String query = "SELECT ParentRatesVehicles from Rate_Parent_company ;";
        Statement stmt = conn.prepareStatement(query);
        ResultSet resultat = stmt.executeQuery(query);
        if (resultat.first()) {
            chiff = resultat.getString("ParentRatesVehicles");
        }
        System.out.println("requete = " + stmt.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Get rates Vehicles */
    public String getratesVehicles(int idAgency) throws SQLException {
        String chiff = null;
        String query = "SELECT RatesVehicles from Agency where id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getString("RatesVehicles");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    /*Update Rates Vehicles*/
    public int updateRatesVehicles(int idAgency, float RatesVehicles) throws SQLException {
        String query = "UPDATE Agency set RatesVehicles=? WHERE id_Agency=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setFloat(1, RatesVehicles);
        queryPrep.setInt(2, idAgency);
        System.out.println(queryPrep.toString());
        return queryPrep.executeUpdate();

    }

    /*Get id Agency user*/
    public int getidAgency(int idConsultant) throws SQLException {
        int chiff = 0;
        String query = "SELECT id_Agency FROM Consultant where id_Consultant=? ;";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idConsultant);
        ResultSet resultat = queryPrep.executeQuery();
        if (resultat.first()) {
            chiff = resultat.getInt("id_Agency");
        }
        System.out.println("requete = " + queryPrep.toString());
        System.out.println(chiff);
        return chiff;
    }

    public String getAgencyCity(int idC0) throws SQLException {
        String city = "";
        String query = "SELECT city_Agency "
                + "FROM Consultant c,Agency a "
                + "WHERE c.id_Agency = a.id_Agency "
                + "AND c.id_Consultant = ? ";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idC0);
        ResultSet rs = queryPrep.executeQuery();
        System.out.println("city = " + city);
        if (rs.first()) {
            //city = rs.getString("city_Agency");
            city = rs.getString(1);

        }
        //System.out.println("city2 = " + city);
        return city;

    }

    public ArrayList<LoanIndicator> getLaonInformation(int idAgency) throws SQLException {
        ArrayList<LoanIndicator> loanObject = new ArrayList();
        String query = "SELECT first_Name_Customer,last_Name_Customer, percentage_Rate, monthly_Sim, duration_Sim,birthday_Customer "
                + "FROM LoanSimulation ls, Loan l, Customer c, Agency a,Consultant ct  "
                + "WHERE l.id_Sim = ls.id_Sim "
                + "AND c.id_Customer = ls.id_Customer "
                + "AND ct.id_Consultant = ls.id_Consultant "
                + "AND a.id_Agency = ct.id_Agency "
                + "AND a.id_Agency = ? ";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet rs = queryPrep.executeQuery();
        while (rs.next()) {
            loanObject.add(new LoanIndicator(rs.getString("first_Name_Customer"), rs.getString("last_Name_Customer"), rs.getString("percentage_Rate"), rs.getString("monthly_Sim"), rs.getString("duration_Sim"), rs.getString("birthday_Customer")));
        }
        return loanObject;

    }

    public String getNumberCustomer(int idAgency) throws SQLException {
        String numberCust = "";
        String query = "SELECT count( * ) "
                + "FROM Customer c, Agency a, Consultant ct "
                + "WHERE a.id_Agency = ct.id_Agency "
                + "AND ct.id_Consultant = c.id_Consultant "
                + "AND ct.id_Agency = ? ";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet rs = queryPrep.executeQuery();
        System.out.println("number of cust = " + numberCust);
        if (rs.first()) {
            //city = rs.getString("city_Agency");
            numberCust = rs.getString(1);

        }
        //System.out.println("city2 = " + city);
        return numberCust;

    }

    public String getNumberSimulation(int idAgency) throws SQLException {
        String numberSim = "";
        String query = "Select count(*) "
                        +"From LoanSimulation ls, Consultant ct, Agency a "
                        +"Where ls.id_Consultant = ct.id_Consultant "
                        +"AND ct.id_Agency = a.id_Agency "
                        +"AND a.id_Agency = ? ";
        PreparedStatement queryPrep = conn.prepareStatement(query);
        queryPrep.setInt(1, idAgency);
        ResultSet rs = queryPrep.executeQuery();
        System.out.println("number of sim = " + numberSim);
        if (rs.first()) {
            //city = rs.getString("city_Agency");
            numberSim = rs.getString(1);

        }
        //System.out.println("city2 = " + city);
        return numberSim;

    }
    
 

}
