package com.prs.business.product;

import org.springframework.data.repository.CrudRepository;

import com.prs.business.product.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}