package com.example.shoppingapp.controller;

import com.example.shoppingapp.model.ProductModel;
import com.example.shoppingapp.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository  productrespository;
    @GetMapping("/Dunkin")
    public List<ProductModel> getAllproducts(){
        return productrespository.findAll();
    }
    @GetMapping("/Dunkin/name/{pname}")
    public Optional<ProductModel> getbyproductname(@PathVariable(value ="pname")String productname){
        //return emplyoeeRespository.findByFirstName(emplyoeename);
        return productrespository.findByproName(productname);
    }
    @GetMapping("/Dunkin/type/{ptype}")
    public Optional<List<ProductModel>> getbyproducttype(@PathVariable(value ="ptype")String productype){
        //return emplyoeeRespository.findByFirstName(emplyoeename);
        return productrespository.findAllBytype(productype);
    }
    @PostMapping("/Dunkin/add")
    public void add_prod(@RequestParam(value = "pname" )String name,@RequestParam(value ="price")int price, @RequestParam(value ="type")String type){
       // productrespository.save(new ProductModel(price,name,type));
        ProductModel pmodel = new ProductModel(price,name,type);
        productrespository.save(pmodel);
    }

    @DeleteMapping("/Dunkin/delete/{pname}")
    @Transactional
    public String dele_prod(@PathVariable(value ="pname")String name){
        productrespository.deleteByproName(name);
        return "Product delete successfully";

    }
    @PatchMapping("/Dunkin/update")
    public String upd_pro(@RequestParam(value = "pname")String name,@RequestParam(value= "price")int price){
        Optional<ProductModel> myproduct = productrespository.findByproName(name);
        if(myproduct.isPresent()){
            myproduct.get().setPro_price(price);
            productrespository.save(myproduct.get());
            return "Update Successfully";
        }else{
            return "can't update";
        }
    }
}

