package com.oopclass.breadapp.repository;


import com.oopclass.breadapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 * OOP Class 20-21
 * @author Gerald Villaran
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	//User findByEmail(String email);
}
