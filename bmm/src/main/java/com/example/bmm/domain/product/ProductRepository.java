package com.example.bmm.domain.product;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long>, ProductCustomRepository {
}