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
    private String salary;
    private String phoneNumber;

    public Customer(String lastName, String firstName, String email, String street, String city, String postalCode, String salary, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
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

    public String getSalary() {
        return salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    
}
