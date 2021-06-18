package com.category.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.category.app.entities.Category;
import com.category.app.repositories.CategoryRepository;
import com.category.app.requests.CreateCategoryDTO;
import com.category.app.service.interfaces.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	CategoryRepository CategoryRepository;

	@Override
	public List<Category> findAll(Pageable pageable) {

		return CategoryRepository.findAll(pageable).getContent();
	}

	@Override
	public Category getCategory(Long categoryId) {
		Optional<Category> optionalCategory = CategoryRepository.findById(categoryId);
		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		}
		return new Category();

	}

	@Override
	public Category saveCategory(CreateCategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);
		return CategoryRepository.save(category);
	}
}
