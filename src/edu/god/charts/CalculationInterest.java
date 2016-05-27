/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.charts;

import edu.god.models.AccessDB;
import java.sql.SQLException;

/**
 *
 * @author Coline
 */
public class CalculationInterest {
    public int idConsultant;
    float lastcalcul;
    public String getInfoRatesParentConsumption() throws SQLException{
        String ratescompany = null;
        ratescompany= (AccessDB.getAccessDB().getRatesConsumptionParent());
        int averageconsump =2000;
        int numbercreditconsump =50;//Estimate
        int duration=2;
 /*Calculation consumption rates Parent Company*/
               float calcul =averageconsump*numbercreditconsump;
               float result=((calcul*Float.parseFloat(ratescompany))/duration)/12;
               String stringResult = ""+result;
               //Calculation interest per months
        return stringResult;
        
        }
    public float getNumberCreditConsump() throws SQLException{
        float number=50; // number average customer
        String ratesagency= null;
        String ratescompany= null;
        ratesagency = AccessDB.getAccessDB().getratesConsumption(AccessDB.getAccessDB().getidAgency(idConsultant));//take Consumption Ratesagency in DB
        ratescompany= (AccessDB.getAccessDB().getRatesConsumptionParent());//take Consumption RatesParent in DB
     
        if (Float.parseFloat(ratesagency) > Float.parseFloat(ratescompany)){   //tranform float
            float calcul =((Float.parseFloat(ratesagency) - Float.parseFloat(ratescompany))/2); // 4,4 -4,2= 0,2 /2 =0,1
            float calcul2 =number*calcul; //50*0,1=5
            lastcalcul=number-calcul2;//50+5=55
            //45
        }
        
       else if (Float.parseFloat(ratesagency) < Float.parseFloat(ratescompany)){
             float calcul =(Float.parseFloat(ratescompany)- Float.parseFloat(ratesagency))/2;//4,2-4=0,2/2=0,1
            float calcul2 =number*calcul;//50*0,1=5
            lastcalcul=number+calcul2;//50-5=45
           //55
        }
       else if(Float.parseFloat(ratesagency) == Float.parseFloat(ratescompany)){
            lastcalcul = number;
       }
        return lastcalcul;//return true result
    }
    
    public String getInfoRatesConsumptionCalc(int idConsultant) throws SQLException{
        this.idConsultant=idConsultant;
        String ratesagency=null;
        ratesagency=AccessDB.getAccessDB().getratesConsumption(AccessDB.getAccessDB().getidAgency(idConsultant));
        int averageconsump =2000;
        int duration =2;
        float numbercreditconsump = (getNumberCreditConsump());
 /*Calculation consumption rates Agency*/
            float calcul =averageconsump*numbercreditconsump;
               float result=((calcul*Float.parseFloat(ratesagency))/duration)/12;
               //Calculation interest per months
               String stringResult = ""+result;
        return stringResult;
        
        }
    
    }
