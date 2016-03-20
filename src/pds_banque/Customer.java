/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque;

/**
 *
 * @author florent
 */
public class Customer {
    private String lastName;
    private String firstName;
    private String email;
    private String street;
    private String city;
    private String postalCode;
    private float salary;
    private String phoneNumber;
    private String birthday;
    private String title;
    private String profStatus;
    private boolean owner;

    public Customer(String lastName, String firstName, String email, String street, String city, String postalCode, float salary, String phoneNumber, String birthday, String title, String profStatus, boolean owner) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.title = title;
        this.profStatus = profStatus;
        this.owner = owner;
    }   
    

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfStatus() {
        return profStatus;
    }

    public void setProfStatus(String profStatus) {
        this.profStatus = profStatus;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    } 
    
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
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
    
    public String getPhoneNumber() {
        return phoneNumber;
    }


    
}
