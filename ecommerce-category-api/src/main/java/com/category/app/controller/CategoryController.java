package com.category.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.category.app.entities.Category;
import com.category.app.requests.CreateCategoryDTO;
import com.category.app.service.interfaces.ICategoryService;

@RestController
@RequestMapping("/category/")
public class CategoryController {

	@Autowired
	ICategoryService categoryService;

	@GetMapping("/all/{page}/{size}")
	public ResponseEntity<List<Category>> getcategorysByPage(@PathVariable(value = "page", required = true) int page,
			@PathVariable(value = "size", required = true) int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
		List<Category> categorys = categoryService.findAll(pageable);
		return new ResponseEntity<>(categorys, HttpStatus.OK);
	}

	@GetMapping("/get/{categoryId}")
	public ResponseEntity<Category> getcategory(@PathVariable("categoryId") Long categoryId) {
		return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<Category> saveCategory(@RequestBody CreateCategoryDTO category) {
		return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
	}
}
