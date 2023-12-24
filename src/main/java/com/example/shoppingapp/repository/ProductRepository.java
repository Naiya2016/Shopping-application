package com.example.shoppingapp.repository;

import com.example.shoppingapp.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface  ProductRepository extends JpaRepository<ProductModel,String> {
    Optional<ProductModel> findByproName(String pname);
    Optional<List<ProductModel>> findAllBytype(String pname);
    void deleteByproName(String pname);

}
