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
    private String title;
    private String lastName;
    private String firstName;
    private String birthday;
    private String nationality;
    private String phoneNumber;
    private String email;
    private boolean owner;
    private float salary;
    private String statut;
    private String street;
    private String city;
    private String postalCode;

    public Customer(String title, String lastName, String firstName, String birthday, String nationality, String phoneNumber, String email, boolean owner, float salary, String statut, String street, String city, String postalCode) {
        this.title = title;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.nationality = nationality;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.owner = owner;
        this.salary = salary;
        this.statut = statut;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
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

    public String getBirthday() {
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

    public String getStatut() {
        return statut;
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
