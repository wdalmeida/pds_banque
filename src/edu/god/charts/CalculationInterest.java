/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.charts;

/**
 *
 * @author Coline
 */
public class CalculationInterest {
    public double getinfo(){
        double taux = 3.3;
        int mycredit =5000;
        int nbcredit =100;
 /*Calcul  du taux pour consommation*/
               double calcul =mycredit*nbcredit;
               double resultat=((calcul*taux)/2)/12;
        return resultat;
        }
    
    }
