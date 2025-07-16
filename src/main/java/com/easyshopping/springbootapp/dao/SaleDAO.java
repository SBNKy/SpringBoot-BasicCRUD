package com.easyshopping.springbootapp.dao;

import com.easyshopping.springbootapp.entity.Sale;

import java.util.List;

public interface SaleDAO {

    List<Sale> findAll();
    Sale findById(Integer id);
    Sale save(Sale sale);
    void delete(Sale sale);
}
