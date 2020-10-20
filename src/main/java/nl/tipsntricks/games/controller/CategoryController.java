package nl.tipsntricks.games.controller;

import nl.tipsntricks.games.domain.Category;
import nl.tipsntricks.games.repository.CategoryRepository;
import nl.tipsntricks.games.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin (origins = "*", maxAge = 3600)
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = "api/categories")
    List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @GetMapping (value = "api/category/{categoryid}")
    Category getCategoryById(@PathVariable long categoryid){
        return categoryService.getCategoryById(categoryid);
    }

    @PutMapping (value = "api/category/{categoryid}")
    Category updateCategoryById(@PathVariable long categoryid, @RequestBody Category updatedCategory){
        return categoryService.updateCategoryById(categoryid, updatedCategory);
    }

    @PutMapping(value = "api/post/category/{postid}")
    Category addCategoryToPost(@PathVariable long postid,@RequestBody Category newCategory){
        return categoryService.addCategoryToPost(postid, newCategory);
    }

    @DeleteMapping(value = "api/category/{categoryid}")
    String deleteCategoryById (long categoryid){
        return categoryService.deleteCategoryById(categoryid);
    }
}
