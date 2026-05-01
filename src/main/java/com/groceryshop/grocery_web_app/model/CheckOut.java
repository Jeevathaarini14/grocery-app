package com.groceryshop.grocery_web_app.model;

import jakarta.persistence.*;


@Entity
@Table(name = "checkout")
public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String username;
    public String email;
    public String address;
    public String country;
    public String state;
    public String pincode;
    public String payment;
    public String totalamount;

    public CheckOut(String username, String email, String address, String state, String country, String pincode, String payment, String totalamount) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
        this.payment = payment;
        this.totalamount = totalamount;
    }

    public CheckOut() {
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(String totalamount) {
        this.totalamount = totalamount;
    }
}