package com.easyshopping.springbootapp.service;

import com.easyshopping.springbootapp.dao.SaleDAO;
import com.easyshopping.springbootapp.dao.SaleDAOImpl;
import com.easyshopping.springbootapp.entity.Sale;
import com.easyshopping.springbootapp.exception.InvalidRequestException;
import com.easyshopping.springbootapp.exception.SaleNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService {

    SaleDAO saleDAO;
    ObjectMapper objectMapper;

    @Autowired
    public SaleServiceImpl(SaleDAO saleDAO, ObjectMapper objectMapper) {
        this.saleDAO = saleDAO;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Sale> findAll() {
        return saleDAO.findAll();
    }

    @Override
    public Sale findById(Integer id) {
        Sale tempSale = saleDAO.findById(id);

        if (tempSale == null)
            throw new SaleNotFoundException("Sale with id not found - " + id);

        return tempSale;
    }

    @Transactional
    @Override
    public Sale addSale(Sale sale) {
        if (sale.getId() != null)
            throw new InvalidRequestException("New sale cannot have an id " + sale.getId());

        return saleDAO.save(sale);
    }

    @Transactional
    @Override
    public Sale updateSale(Sale sale) {
        // validates if sale exists
        findById(sale.getId());

        return saleDAO.save(sale);
    }

    @Transactional
    @Override
    public Sale patchSale(Integer saleId, Map<String, Object> patchPayload) {
        Sale tempSale = findById(saleId);

        if (tempSale == null)
            throw new RuntimeException("Sale with id not found - " + saleId);

        if (patchPayload.containsKey("id"))
            throw new RuntimeException("Sale id not allowed in request body - " + patchPayload.get("id"));

        Sale patchedSale = apply(tempSale, patchPayload);
        return saleDAO.save(patchedSale);
    }

    private Sale apply(Sale sale, Map<String, Object> patchPayload) {
        ObjectNode saleNode = objectMapper.convertValue(sale, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        saleNode.setAll(patchNode);

        return objectMapper.convertValue(saleNode, Sale.class);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        Sale tempSale = findById(id);

        saleDAO.delete(tempSale);
    }
}
