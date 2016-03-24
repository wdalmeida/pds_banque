package pds_banque.Json;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

    public class ExempleEncodage {

   public static void main(String[] args) throws IOException{
      JSONObject obj = new JSONObject();

      obj.put("Nom du conseiller", "Patick");
      obj.put("id", new Integer(001));
      obj.put("test", new Double(1000.21));
      obj.put("est_directeur", new Boolean(true));

      System.out.print(obj);
      String jsonString = obj.toString();
      
      try (FileWriter file = new FileWriter("testJson.txt")) {
			file.write(obj.toJSONString());
			System.out.println("Object ecrit dans le fichier avec succes");
			System.out.println("\nObjet ecrit: " + obj);
		}
      
   }
}