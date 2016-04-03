/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque;

import java.util.*;

/**
 *
 * @author florent
 */
public class Customer {
    private String title;
    private String lastName;
    private String firstName;
    private Date birthday;
    private String nationality;
    private String phoneNumber;
    private String email;
    private boolean owner;
    private float salary;
    private int idstatus;
    private String street;
    private String city;
    private String postalCode;
    private int idConsultant;
    private int idUser;

    public Customer(String title, String lastName, String firstName, Date birthday, String nationality, String phoneNumber, String email, boolean owner, float salary, int idstatus0, String street, String city, String postalCode,int idConsultant0,int idUser0) {
        this.title = title;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.owner = owner;
        this.salary = salary;
        this.idstatus = idstatus0;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.idConsultant = idConsultant0;
        this.idUser = idUser0;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    

    public int getIdstatus() {
        return idstatus;
    }

    public int getIdConsultant() {
        return idConsultant;
    }

    public String getTitle() {
        return title;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public boolean isOwner() {
        return owner;
    }

    public float getSalary() {
        return salary;
    }

    public int getidStatus() {
        return idstatus;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
   
}
