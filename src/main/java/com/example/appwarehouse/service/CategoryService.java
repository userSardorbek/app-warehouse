package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.pload.CategoryDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParentId() != null) {
            Optional<Category> optionalParent = categoryRepository.findById(categoryDTO.getParentId());
            if (!optionalParent.isPresent())
                return new Result("There is no such parent category", false);
            category.setParentCategory(optionalParent.get());
        }
        categoryRepository.save(category);
        return new Result("Successfully saved", true);
    }

//problem
    //There is a problem
//    public Result editCategory(CategoryDTO categoryDTO, Integer id) {
//        Optional<Category> byId = categoryRepository.findById(id);
//        if (!byId.isPresent())
//            return new Result("Such category not found", false);
//        Category category = byId.get();
//        category.setName(categoryDTO.getName());
//        category.setActive(categoryDTO.isActive());
//        if (categoryDTO.getParentId() == null) {
//            category.setParentCategory(null);
//        } else {
//            Category parent = categoryRepository.findByParentCategoryId(categoryDTO.getParentId());
//            if (parent==null)
//                return new Result("parent not found", false);
//            category.setParentCategory(parent);
//        }
//
//        categoryRepository.save(category);
//        return new Result("Successfully edited", true);
//    }

    public Result deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
        return new Result("Successfully deleted", true);
    }

}
