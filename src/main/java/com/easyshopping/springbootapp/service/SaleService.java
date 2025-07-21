package com.easyshopping.springbootapp.service;

import com.easyshopping.springbootapp.model.Sale;
import com.easyshopping.springbootapp.exception.InvalidRequestException;
import com.easyshopping.springbootapp.exception.SaleNotFoundException;
import com.easyshopping.springbootapp.repository.SaleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SaleService {

    SaleRepository saleRepository;
    ObjectMapper objectMapper;

    @Autowired
    public SaleService(SaleRepository saleDAO, ObjectMapper objectMapper) {
        this.saleRepository = saleDAO;
        this.objectMapper = objectMapper;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Integer id) {
        Optional<Sale> tempSale = saleRepository.findById(id);

        if (tempSale.isPresent())
            return tempSale.get();
        else
            throw new SaleNotFoundException("Sale with id not found - " + id);
    }

    @Transactional
    public Sale addSale(Sale sale) {
        if (sale.getId() != null)
            throw new InvalidRequestException("New sale cannot have an id " + sale.getId());

        return saleRepository.save(sale);
    }

    @Transactional
    public Sale updateSale(Sale sale) {
        // validates if sale exists
        findById(sale.getId());

        return saleRepository.save(sale);
    }

    @Transactional
    public Sale patchSale(Integer saleId, Map<String, Object> patchPayload) {
        Sale tempSale = findById(saleId);

        if (patchPayload.containsKey("id"))
            throw new RuntimeException("Sale id not allowed in request body - " + patchPayload.get("id"));

        Sale patchedSale = apply(tempSale, patchPayload);
        return saleRepository.save(patchedSale);
    }

    private Sale apply(Sale sale, Map<String, Object> patchPayload) {
        ObjectNode saleNode = objectMapper.convertValue(sale, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        saleNode.setAll(patchNode);

        return objectMapper.convertValue(saleNode, Sale.class);
    }

    @Transactional
    public void deleteById(Integer id) {
        Sale tempSale = findById(id);

        saleRepository.delete(tempSale);
    }
}
