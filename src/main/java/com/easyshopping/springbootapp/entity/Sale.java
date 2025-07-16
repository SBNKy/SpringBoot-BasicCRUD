package com.easyshopping.springbootapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name="sales")
public class Sale {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="company_name")
    private String companyName;

    @Column(name="product_name")
    private String productName;

    @Column(name="price")
    private double price;

    public Sale() {
    }

    public Sale(String companyName, String productName, double price) {
        this.companyName = companyName;
        this.productName = productName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
