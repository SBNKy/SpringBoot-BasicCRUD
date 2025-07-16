package com.easyshopping.springbootapp.service;

import com.easyshopping.springbootapp.entity.Sale;

import java.util.List;
import java.util.Map;

public interface SaleService {

    List<Sale> findAll();
    Sale findById(Integer id);
    Sale addSale(Sale sale);
    Sale updateSale(Sale sale);
    Sale patchSale(Integer saleId, Map<String, Object>patchPayload);
    void deleteById(Integer id);

}
