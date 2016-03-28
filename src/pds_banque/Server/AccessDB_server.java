package pds_banque.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*query:
 INSERT INTO `Customer` (`id_Customer`, `title_Customer`, `last_Name_Customer`, `first_Name_Customer`, `salary_Customer`, `street_Customer`, `pc_Customer`, `city_Customer`, `phone_Customer`, `email_Customer`, `birthday_Customer`, `owner_Customer`, `id_Consultant`, `id_User`, `id_status`) VALUES
 (1, '', 'STROH', 'NICOLAS', 3100, 'Edmon Nocard', '94410', 'Saint Maurice', '145659878', 'nicolas.stroh@Yahoo.fr', '1992-08-08', 1, 1, 0, 1),
 (2, '', 'GUIDEAU', 'FRANCOIS', 2900, 'Gabrielle', '94220', 'Charenton', '145651236', 'francois.guideau@gmail.com', '1990-07-02', 1, 1, 0, 1);
 */
public class AccessDB_server {

    public static void insertionCustomer(String request) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/god_banque", "root", "");
            Statement stmt = con.createStatement();
            //String request = "select Nom_Conseiller from conseiller WHERE Nom_Conseiller LIKE '" + identifiant + "' AND Mdp_Conseiller LIKE '" + motDePasse + "'";
            //String request = "INSERT INTO Customer " + "VALUES (1, '', 'STROH', 'NICOLAS', 3100, 'Edmon Nocard', '94410', 'Saint Maurice', '145659878', 'nicolas.stroh@Yahoo.fr', '1992-08-08', 1, 1, 0, 1)";
            System.out.println(request);
             stmt.executeUpdate(request);
            con.close();

        } catch (Exception e) {
            System.out.println("Erreur lors de l execution de la requete");
        }
    }

    /*public static void main(String[] args) {
        insertionCustomer();
    }
    */
    
}
