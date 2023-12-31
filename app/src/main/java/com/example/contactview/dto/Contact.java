/*
 * madushan joel 2023.
 */

package com.example.contactview.dto;
public class Contact {
    private long id;
    private String name;


    private String email;
    private String phoneNumber;
    private String lastname;
    private String phoneNumber2;
    public Contact(long id, String name, String email, String phoneNumber) {
        this.id=id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Contact(long id, String name, String email, String phoneNumber, String lastname, String phoneNumber2) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastname = lastname;
        this.phoneNumber2 = phoneNumber2;
    }
    public Contact(String name, String email, String phoneNumber, String lastname, String phoneNumber2) {

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.lastname = lastname;
        this.phoneNumber2 = phoneNumber2;
    }
    public Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public Contact() {

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
