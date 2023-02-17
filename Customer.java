package com.example.servlet_customer;

public class Customer {
    private String firstname;
    private String lastname;
    private String phone;
    private String country;
    private String city;
    private String postcode;
    private String email;
    private String dateofbirth;
    private String instagram;
    private String pet;
    private String kids;
    private  String gender;
    private String maritalstatus;
    private String linkedin;
    private String job;


    public Customer(String firstname,String lastname,String phone,String country,String city,String postcode,String email,String dateofbirth,String instagram,String pet,String kids,String gender,String maritalstatus,String linkedin, String job) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.email = email;
        this.dateofbirth = dateofbirth;
        this.instagram = instagram;
        this.pet = pet;
        this.kids = kids;
        this.gender = gender;
        this.maritalstatus = maritalstatus;
        this.linkedin = linkedin;
        this.job = job;

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getKids() {
        return kids;
    }

    public void setKids(String kids) {
        this.kids = kids;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone=" + phone +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postcode=" + postcode +
                ", email='" + email + '\'' +
                ", dateofbirth=" + dateofbirth +
                ", instagram='" + instagram + '\'' +
                ", pet='" + pet + '\'' +
                ", kids='" + kids + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalstatus='" + maritalstatus + '\'' +
                ", linkedin='" + linkedin + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}

