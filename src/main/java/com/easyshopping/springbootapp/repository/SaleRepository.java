package com.easyshopping.springbootapp.repository;

import com.easyshopping.springbootapp.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
