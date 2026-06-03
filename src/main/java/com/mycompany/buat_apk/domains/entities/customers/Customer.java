package com.mycompany.buat_apk.domains.entities.customers;
import java.util.Date;

public class Customer {

    private Long id;
    private String name;
    private Date dob;
    private String subdistrict;
    private String phone;
    private Date createdAt;

    public Customer() {
    }

    public Customer(Long id, String name, Date dob, String subdistrict, String phone, Date createdAt) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.subdistrict = subdistrict;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
