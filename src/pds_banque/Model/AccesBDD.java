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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pds_banque.Constantes;
import pds_banque.Customer;

public class AccesBDD implements Constantes {

    private Statement declaration;
    private Connection conn;
    private static AccesBDD acces;

    private AccesBDD() {
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

    public static AccesBDD getAccesBDD() {
        if (acces == null) {
            acces = new AccesBDD();
        }
        return acces;
    }

    public int getConnexion(String login, String pwd) throws NoSuchAlgorithmException {
        int tmp = 0;
        String query = "";
        String pwdHash = HashString.sha512(pwd);
        System.out.println("Mot de passe sha512 = "+ pwdHash);
        try {
            query = "SELECT login_User,pwd_User FROM User where login_U1ser='" + login + "' AND pwd_User='" + pwdHash + "';";
            ResultSet rs = this.declaration.executeQuery(query);
            if (rs.first()) {
                if (rs.getString(1).equals(login) && rs.getString(2).equals(pwdHash)) {
                    tmp = 1;
                } else {
                    tmp = 0;
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        if (tmp == 1) {
            return 1;
        } else {
            return 0;
        }
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

    public String[] Infos_Conseiller(String login) {
        String query = "test";
        try {
            String[] tab = new String[2];
            query = "SELECT nom_Conseiller,prenom_Conseiller FROM Conseiller WHERE login='" + login + "';";
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
            query1 = "Insert Into Utilisateur(login,mdp) values('" + cust.getLastName() + "','" + cust.getFirstName() + "')";
            res = this.declaration.executeUpdate(query1);
            try {
                if (res == 1) {
                    query2 = "INSERT INTO `client`(`nom_Client`, `prenom_Client`, `salaire_Client`, `rue_Client`, `cp_Client`, `ville_Client`, `tel_Client`, `email_Client`,`id_Conseiller`) VALUES ('" + cust.getLastName() + "','" + cust.getFirstName() + "','" + cust.getSalary() + "','" + cust.getStreet() + "','" + cust.getPostalCode() + "','" + cust.getCity() + "','" + cust.getPhoneNumber() + "','" + cust.getEmail() + "',2)";
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
            query1 = "Select Id_Conseiller from Client where nom_client ='" + cust.getLastName() + "' AND prenom_client ='" + cust.getFirstName() + "')";

            ResultSet rs = this.declaration.executeQuery(query1);
            if (rs.first()) {
                tmp = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erreur ! La requ\u00EAte" + query1 + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
        }
        return tmp;
    }
}


    /* met à jour la chambre en passant de sale à propr et renvoie vrai ou faux en fonction de la reussite de la mise à jour */
    /*public boolean nettoyage(int chambreID) {
     String query;
     query = "UPDATE Chambre SET `Propre`=" + 1 + " where `Numero`=" + chambreID + ";";
     try {
     this.declaration.executeUpdate(query);
     } catch (SQLException e) {
     System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
     e.printStackTrace();
     return false;
     }
     return true;
     }

     /*recupere la liste des chambres à nettoyer 
     public ChambreN[] tableau_nett(int nettoyeurID) {

     int i = 1;
     String query = "test";
     String query_nb = "test1";
     try {
     query_nb = "SELECT count(*) FROM Chambre WHERE Nettoyeur=" + nettoyeurID + ";";

     ResultSet rs_nett1 = this.declaration.executeQuery(query_nb);
     try {

     query = "SELECT Numero,Libre,Propre FROM Chambre WHERE Nettoyeur=" + nettoyeurID + ";";

     ResultSet rs_nett = this.declaration.executeQuery(query);

     rs_nett1.first();
     ChambreN[] tab = new ChambreN[rs_nett1.getInt(1) + 1];
     if (rs_nett.first()) {
     do {
     tab[i] = new ChambreN(rs_nett.getInt(1), rs_nett.getInt(2), rs_nett.getInt(3));
     i++;
     } while (rs_nett.next());
     }
     rs_nett.close();
     rs_nett = null;
     return tab;

     } catch (SQLException e) {
     System.out.println("Erreur ! La requ\u00EAte" + query_nb + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
     e.printStackTrace();
     }
     } catch (SQLException e) {
     System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
     e.printStackTrace();
     }
     return null;
     }

     public void setPropre(int numero, int propre0) {
     String query = "test";
     try {
     if (propre0 == 0) {
     query = "UPDATE Chambre SET propre = 0 WHERE numero = " + numero + ";";
     } else {
     query = "UPDATE Chambre SET propre = 1 WHERE numero = " + numero + ";";
     }

     declaration.executeUpdate(query);

     } catch (SQLException e) {
     System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
     }
     }

     /*Fonction getPropre
     public int getPropre(int numero) {
     int tmp = 1;
     String query = "test";
     try {
     query = "SELECT propre FROM Chambre WHERE numero = " + numero + ";";

     ResultSet rs = this.declaration.executeQuery(query);
     if (rs.first()) {
     if (rs.getInt(1) == 1) {
     tmp = 1;
     } else {
     tmp = 0;
     }
     }
     } catch (SQLException e) {
     System.out.println("Erreur ! La requ\u00EAte" + query + "n'a pas pu aboutir.\n\nMessage d'erreur :\n");
     }
     if (tmp == 1) {
     return 1;
     } else {
     return 0;
     }

     }*/
