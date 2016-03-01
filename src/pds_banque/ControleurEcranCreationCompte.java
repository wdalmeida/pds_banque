package pds_banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author flesguer
 */
public class ControleurEcranCreationCompte extends EcranCreationCompte {    
    String resultat;
    
    public ControleurEcranCreationCompte (String Nom, String Prenom, String Telephone, String MDP, String Directeur, String Agence, String idAgence ){  
         try
    {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pds_banque_old", "root", "");
      Statement stmt = con.createStatement();
     
      String request = "INSERT INTO `conseiller`(`Id_Conseiller`, `Nom_Conseiller`, `Prenom_Conseiller`, `Tel_Conseiller`, `Mdp_Conseiller`, `Directeur`, `Id_Agence`) "
              + "VALUES (3,'"+Nom+"','"+Prenom+"','"+Telephone+"','"+MDP+"',"+Directeur+","+idAgence+")";
      stmt.executeQuery(request);
      System.out.println(request);
        
      ResultSet rs = stmt.executeQuery(request);
      while (rs.next()) {
        this.resultat = rs.getString(1);
      }
      con.close();
    }
    catch (Exception e)
    {
    }
  }
}