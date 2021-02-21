package com.oopclass.breadapp.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OOP Class 20-21
 *
 * @author Gerald Villaran
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private String product_name;
    
    private LocalDate product_dop;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return product_name;
    }
    public void setName(String product_name) {
        this.product_name = product_name;
    }
    
     public void setDop(LocalDate product_dop) {
	this.product_dop = product_dop;
    }
     
    public LocalDate getDop() {
	return product_dop;
    }

   


    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + product_name + ", dop=" +"]"; //+ product_dop + "]";
    }

}