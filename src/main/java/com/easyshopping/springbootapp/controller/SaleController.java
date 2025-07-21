package com.easyshopping.springbootapp.controller;

import com.easyshopping.springbootapp.model.Sale;
import com.easyshopping.springbootapp.service.SaleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SaleController {

    SaleService saleService;
    ObjectMapper objectMapper;

    @Autowired
    public SaleController(SaleService saleService, ObjectMapper objectMapper) {
        this.saleService = saleService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/sales")
    public List<Sale> getAllSales() {
        return saleService.findAll();
    }

    @GetMapping("/sales/{saleId}")
    public Sale getSaleById(@PathVariable int saleId) {
        return saleService.findById(saleId);
    }

    @PostMapping("/sales")
    public Sale addSale(@RequestBody Sale sale) {
        sale.setId(null);

        return saleService.addSale(sale);
    }

    @PatchMapping("/sales/{saleId}")
    public Sale patchSale(@PathVariable int saleId,
                          @RequestBody Map<String, Object> patchPayload) {

        return saleService.patchSale(saleId, patchPayload);
    }

    @PutMapping("/sales")
    public Sale updateSale(@RequestBody Sale sale) {
        return saleService.updateSale(sale);
    }

    @DeleteMapping("/sales/{saleId}")
    public void deleteById(@PathVariable Integer saleId) {
        saleService.deleteById(saleId);
    }
}
