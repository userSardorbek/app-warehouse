package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Product;
import com.example.appwarehouse.pload.ProductDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/editProduct/{productId}")
    public Result editProduct(@PathVariable Integer productId, @RequestBody ProductDTO productDTO) {
        Result result = productService.editProduct(productDTO, productId);
        return result;
    }

    @GetMapping
    public List<Product> allProducts() {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/{productId}")
    public Result getProductInfo(@PathVariable Integer productId) {
        Result productInfo = productService.getProductInfo(productId);
        return productInfo;
    }

    @DeleteMapping("/delete/{productId}")
    public Result deleteProduct(@PathVariable Integer productId) {
        Result result = productService.deleteProduct(productId);
        return result;
    }
}
