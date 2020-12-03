package service;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }
}
