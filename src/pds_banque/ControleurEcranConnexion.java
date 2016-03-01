package pds_banque;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ControleurEcranConnexion
  extends EcranConnexion
{
  String resultat;
  
  public ControleurEcranConnexion(String identifiant, String motDePasse)
  {
    try
    {
      Class.forName("com.mysql.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pds_banque_old", "root", "");
      Statement stmt = con.createStatement();
      
      String request = "select Nom_Conseiller from conseiller WHERE Nom_Conseiller LIKE '" + identifiant + "' AND Mdp_Conseiller LIKE '" + motDePasse + "'";
      ResultSet rs = stmt.executeQuery(request);
      while (rs.next()) {
        this.resultat = rs.getString(1);
      }
      con.close();
      if (this.resultat.equals(identifiant))
      {
        String[] arguments = { "" };
        EcranSimulation.main(arguments);
        dispose();
        setVisible(false);
      }
    }
    catch (Exception e)
    {
      String[] arguments = { "" };
      EcranErreurConnexion.main(arguments);
    }
  }
}