package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.CategoryDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        Result result = categoryService.addCategory(categoryDTO);
        return result;
    }

//    @PutMapping("/{categoryId}")
//    public Result editCategory(@PathVariable Integer categoryId, @RequestBody CategoryDTO categoryDTO) {
//        Result result = categoryService.editCategory(categoryDTO, categoryId);
//        return result;
//    }

    @DeleteMapping("/{categoryId}")
    public Result deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new Result("Category deleted", true);
    }
}
