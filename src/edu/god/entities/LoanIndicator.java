/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package edu.god.entities;

import java.sql.SQLException;

/**
 *
 * @author nabil
 */
public class LoanIndicator {

    private String firstName;
    private String lastName;
    private String percentage_Rate;
    private String monthly_Sim;
    private String duration_Sim;
    private String birthday_Customer;

    public LoanIndicator(String firstName, String lastName, String duration_Sim, String monthly_Sim, String percentage_Rate, String birthday_Customer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.percentage_Rate = percentage_Rate;
        this.monthly_Sim = monthly_Sim;
        this.duration_Sim = duration_Sim;
        this.birthday_Customer = birthday_Customer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDuration_Sim() {
        return duration_Sim;
    }

    public void setDuration_Sim(String duration_Sim) {
        this.duration_Sim = duration_Sim;
    }

    public String getMonthly_Sim() {
        return monthly_Sim;
    }

    public void setMonthly_Sim(String monthly_Sim) {
        this.monthly_Sim = monthly_Sim;
    }

    public String getPercentage_Rate() {
        return percentage_Rate;
    }

    public void setPercentage_Rate(String percentage_Rate) {
        this.percentage_Rate = percentage_Rate;
    }

    public String getBirthday_Customer() {
        return birthday_Customer;
    }

    public void setBirthday_Customer(String birthday_Customer) {
        this.birthday_Customer = birthday_Customer;
    }

}
