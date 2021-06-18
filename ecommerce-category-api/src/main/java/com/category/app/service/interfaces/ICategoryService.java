package com.category.app.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.category.app.entities.Category;
import com.category.app.requests.CreateCategoryDTO;

public interface ICategoryService {

	public List<Category> findAll(Pageable pageable);

	public Category getCategory(Long categoryId);

	Category saveCategory(CreateCategoryDTO categoryDTO);

}
