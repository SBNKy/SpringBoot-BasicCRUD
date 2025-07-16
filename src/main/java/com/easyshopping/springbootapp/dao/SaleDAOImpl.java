package com.easyshopping.springbootapp.dao;

import com.easyshopping.springbootapp.entity.Sale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleDAOImpl implements SaleDAO {

    EntityManager entityManager;

    @Autowired
    public SaleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Sale> findAll() {
        TypedQuery<Sale> query = entityManager.createQuery("FROM Sale", Sale.class);

        return query.getResultList();
    }

    @Override
    public Sale findById(Integer id) {
        return entityManager.find(Sale.class, id);
    }


    @Override
    public Sale save(Sale sale) {
        Sale dbSale = entityManager.merge(sale);

        return dbSale;
    }

    @Override
    public void delete(Sale sale) {
        entityManager.remove(sale);
    }
}
