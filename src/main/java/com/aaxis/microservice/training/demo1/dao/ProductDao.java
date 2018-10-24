package com.aaxis.microservice.training.demo1.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.aaxis.microservice.training.demo1.domain.Product;

public interface ProductDao extends JpaRepository<Product,String>,JpaSpecificationExecutor<Product> {
    public List<Product> findProductsByCategory_Id(String categoryId);
    @Query("select p.id from Product p")
    public Page<String> findProductId(Specification<Product> arg0, Pageable arg1);
}
