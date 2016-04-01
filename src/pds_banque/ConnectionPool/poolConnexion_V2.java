package PoolConnection;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pds_banque.Model.AccessDB;

/**
 *
 * @author nabil
 */
public class poolConnexion_V2 {
    
    ArrayList<AccessDB> listConnexion = new ArrayList<>();
    
    public poolConnexion_V2(){
       for(int i =0; i<10; i++){
           listConnexion.add(i,AccessDB.getAccessDB());
       } 
}
    public synchronized AccessDB getConnexion(){
        AccessDB connexion = null;
        if(!listConnexion.isEmpty()){
             connexion = listConnexion.get(0);
             listConnexion.remove(0);
        }else if(listConnexion.isEmpty()){
            try {    
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(poolConnexion_V2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connexion;
    }
    
    public synchronized void closeConnexion(AccessDB connexion){
        listConnexion.add(connexion);
        notify();
    }
       
    public boolean poolIsEmpty(){
        return listConnexion.isEmpty();
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException{
       poolConnexion_V2 p = new poolConnexion_V2();
       AccessDB connexion = p.getConnexion();
       AccessDB connexion2 = p.getConnexion();
       int result = connexion2.getConnexion("Nsadiki","pass");
       System.out.println(result);
       AccessDB connexion3 = p.getConnexion();
       int result2 = connexion3.getConnexion("Nsadiki","pass");
       System.out.println(result2);
       int result3 = connexion2.getConnexion("Cmarin","pass");
       System.out.println(result3);
       p.closeConnexion(connexion2);
       AccessDB connexion4 = p.getConnexion();
       int result4 = connexion4.getConnexion("Cmarin","pass");
       System.out.println(result4);

       
    }
}