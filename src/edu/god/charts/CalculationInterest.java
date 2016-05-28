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
    
    /*For consummption*/
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
    /*For Personal*/
    
    public String getInfoRatesParentPersonal() throws SQLException{
        String ratescompany = null;
        ratescompany= (AccessDB.getAccessDB().getRatesPesonalParent());
        int averageamount =9000;
        int numbercredit =30;//Estimate
        int duration=3;
 /*Calculation personal rates Parent Company*/
               float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratescompany))/duration)/12;
               String stringResult = ""+result;
               //Calculation interest per months
        return stringResult;
        
        }
    public float getNumberCreditPersonal() throws SQLException{
        float number=30; // number average customer
        String ratesagency= null;
        String ratescompany= null;
        ratesagency = AccessDB.getAccessDB().getratesPersonal(AccessDB.getAccessDB().getidAgency(idConsultant));//take Personal Ratesagency in DB
        ratescompany= AccessDB.getAccessDB().getRatesPesonalParent();//take Personal RatesParent in DB
     
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
    
    public String getInfoRatesPersonalCalc(int idConsultant) throws SQLException{
        this.idConsultant=idConsultant;
        String ratesagency=null;
        ratesagency=AccessDB.getAccessDB().getratesPersonal(AccessDB.getAccessDB().getidAgency(idConsultant));
        int averageamount =9000;
        int duration =3;
        float numbercredit = (getNumberCreditPersonal());
 /*Calculation personal rates Agency*/
            float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratesagency))/duration)/12;
               //Calculation interest per months
               String stringResult = ""+result;
        return stringResult;
        
        }
    /*For Project*/
    
    public String getInfoRatesParentProject() throws SQLException{
        String ratescompany = null;
        ratescompany= (AccessDB.getAccessDB().getRatesProjectParent());
        int averageamount =17000;
        int numbercredit =10;//Estimate
        int duration=5;
 /*Calculation project rates Parent Company*/
               float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratescompany))/duration)/12;
               String stringResult = ""+result;
               //Calculation interest per months
        return stringResult;
        
        }
    public float getNumberCreditProject() throws SQLException{
        float number=10; // number average customer
        String ratesagency= null;
        String ratescompany= null;
        ratesagency = AccessDB.getAccessDB().getratesProj(AccessDB.getAccessDB().getidAgency(idConsultant));//take Project Ratesagency in DB
        ratescompany= AccessDB.getAccessDB().getRatesProjectParent();//take Project RatesParent in DB
     
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
    
    public String getInfoRatesProjectCalc(int idConsultant) throws SQLException{
        this.idConsultant=idConsultant;
        String ratesagency=null;
        ratesagency=AccessDB.getAccessDB().getratesProj(AccessDB.getAccessDB().getidAgency(idConsultant));
        int averageamount =17000;
        int duration =5;
        float numbercredit = (getNumberCreditProject());
 /*Calculation project rates Agency*/
            float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratesagency))/duration)/12;
               //Calculation interest per months
               String stringResult = ""+result;
        return stringResult;
        
        }
     /*For Property*/
    
    public String getInfoRatesParentProperty() throws SQLException{
        String ratescompany = null;
        ratescompany= (AccessDB.getAccessDB().getRatesPropertyParent());
        int averageamount =250000;
        int numbercredit =3;//Estimate
        int duration=20;
 /*Calculation property rates Parent Company*/
               float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratescompany))/duration)/12;
               String stringResult = ""+result;
               //Calculation interest per months
        return stringResult;
        
        }
    public float getNumberCreditProperty() throws SQLException{
        float number=3; // number average customer
        String ratesagency= null;
        String ratescompany= null;
        ratesagency = AccessDB.getAccessDB().getratesProperty(AccessDB.getAccessDB().getidAgency(idConsultant));//take property Ratesagency in DB
        ratescompany= AccessDB.getAccessDB().getRatesPropertyParent();//take Project RatesParent in DB
     
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
    
    public String getInfoRatesPropertyCalc(int idConsultant) throws SQLException{
        this.idConsultant=idConsultant;
        String ratesagency=null;
        ratesagency=AccessDB.getAccessDB().getratesProperty(AccessDB.getAccessDB().getidAgency(idConsultant));
        int averageamount =250000;
        int duration =20;
        float numbercredit = (getNumberCreditProperty());
 /*Calculation property rates Agency*/
            float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratesagency))/duration)/12;
               //Calculation interest per months
               String stringResult = ""+result;
        return stringResult;
        
        }
        /*For Repurchase*/
    
    public String getInfoRatesParentRepurchase() throws SQLException{
        String ratescompany = null;
        ratescompany= (AccessDB.getAccessDB().getRatesRepurchaseParent());
        int averageamount =4000;
        int numbercredit =10;//Estimate
        int duration=4;
 /*Calculation repurchase rates Parent Company*/
               float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratescompany))/duration)/12;
               String stringResult = ""+result;
               //Calculation interest per months
        return stringResult;
        
        }
    public float getNumberCreditRepurchase() throws SQLException{
        float number=10; // number average customer
        String ratesagency= null;
        String ratescompany= null;
        ratesagency = AccessDB.getAccessDB().getratesRepurchase(AccessDB.getAccessDB().getidAgency(idConsultant));//take repurchase Ratesagency in DB
        ratescompany= AccessDB.getAccessDB().getRatesRepurchaseParent();//take Repurchase RatesParent in DB
     
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
    
    public String getInfoRatesRepurchaseCalc(int idConsultant) throws SQLException{
        this.idConsultant=idConsultant;
        String ratesagency=null;
        ratesagency=AccessDB.getAccessDB().getratesRepurchase(AccessDB.getAccessDB().getidAgency(idConsultant));
        int averageamount =4000;
        int duration =4;
        float numbercredit = (getNumberCreditRepurchase());
 /*Calculation Repurchase rates Agency*/
            float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratesagency))/duration)/12;
               //Calculation interest per months
               String stringResult = ""+result;
        return stringResult;
        
        }
         /*For Vehicles*/
    
    public String getInfoRatesParentVehicles() throws SQLException{
        String ratescompany = null;
        ratescompany= (AccessDB.getAccessDB().getRatesVehiclesParent());
        int averageamount =14500;
        int numbercredit =20;//Estimate
        int duration=4;
 /*Calculation vehicles rates Parent Company*/
               float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratescompany))/duration)/12;
               String stringResult = ""+result;
               //Calculation interest per months
        return stringResult;
        
        }
    public float getNumberCreditVehicles() throws SQLException{
        float number=20; // number average customer
        String ratesagency= null;
        String ratescompany= null;
        ratesagency = AccessDB.getAccessDB().getratesVehicles(AccessDB.getAccessDB().getidAgency(idConsultant));//take Vehicles Ratesagency in DB
        ratescompany= AccessDB.getAccessDB().getRatesVehiclesParent();//take Vehicles RatesParent in DB
     
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
    
    public String getInfoRatesVehiclesCalc(int idConsultant) throws SQLException{
        this.idConsultant=idConsultant;
        String ratesagency=null;
        ratesagency=AccessDB.getAccessDB().getratesVehicles(AccessDB.getAccessDB().getidAgency(idConsultant));
        int averageamount =14500;
        int duration =4;
        float numbercredit = (getNumberCreditVehicles());
 /*Calculation Vehicles rates Agency*/
            float calcul =averageamount*numbercredit;
               float result=((calcul*Float.parseFloat(ratesagency))/duration)/12;
               //Calculation interest per months
               String stringResult = ""+result;
        return stringResult;
        
        }
    }
