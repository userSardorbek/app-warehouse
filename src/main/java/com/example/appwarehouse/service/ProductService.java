package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.entity.Product;
import com.example.appwarehouse.pload.ProductDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.AttachmentRepository;
import com.example.appwarehouse.repository.CategoryRepository;
import com.example.appwarehouse.repository.MeasurementRepository;
import com.example.appwarehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDTO productDTO) {
        //Checking product
        Optional<Product> byNameAndCategoryId = productRepository.findByNameAndCategoryId(productDTO.getName(), productDTO.getCategoryId());
        if (byNameAndCategoryId.isPresent())
            return new Result("this product is already exist", false);

        //Checking category
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("There is no such category", false);

        //Checking photo
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDTO.getPhotoId());
        if (!optionalAttachment.isPresent())
            return new Result("There is no such Photo", false);

        //Checking measurement
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("There is no such measurement", false);

        // SAVE
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode("1");
        product.setCategory(optionalCategory.get());
        product.setMeasurement(optionalMeasurement.get());
        product.setPhoto(optionalAttachment.get());
        productRepository.save(product);

        return new Result("Successfully saved", true);
    }

    public Result editProduct(ProductDTO productDTO, Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent())
            return new Result("Product not found", false);

        Product product = optionalProduct.get();
        product.setName(productDTO.getName());
        product.setCode(id.toString());

        // CHECKING CATEGORY
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        if (!optionalCategory.isPresent())
            return new Result("such category not found", false);
        product.setCategory(optionalCategory.get());

        // CHECKING MEASUREMENT
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new Result("such measurement not found", false);
        product.setMeasurement(optionalMeasurement.get());

        // CHECKING MEASUREMENT
        Optional<Attachment> optionalPhoto = attachmentRepository.findById(productDTO.getPhotoId());
        if (!optionalPhoto.isPresent())
            return new Result("such photo not found", false);
        product.setPhoto(optionalPhoto.get());

        productRepository.save(product);
        return new Result("Successfully edited", true);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Result getProductInfo(Integer productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            return new Result("Product not found", false);
        Product product = optionalProduct.get();
        return new Result("Here is info about you searched product", true, product);
    }

    public Result deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return new Result("Successfully deleted", true);
    }
}
