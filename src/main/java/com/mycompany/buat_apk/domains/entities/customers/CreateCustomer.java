package com.mycompany.buat_apk.domains.entities.customers;

import java.util.Date;

public class CreateCustomer {

    private String name;
    private Date dob;
    private String subdistrict;
    private String phone;

    public CreateCustomer() {
    }

    public CreateCustomer(String name, Date dob, String subdistrict, String phone) {
        this.name = name;
        this.dob = dob;
        this.subdistrict = subdistrict;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
