package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Category;
import nl.tipsntricks.games.domain.Post;
import nl.tipsntricks.games.domain.Tag;
import nl.tipsntricks.games.exception.CategoryException;
import nl.tipsntricks.games.exception.PostNotFoundException;
import nl.tipsntricks.games.repository.CategoryRepository;
import nl.tipsntricks.games.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    private PostRepository postRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category getCategoryById(long categoryid) {
        return categoryRepository.findById(categoryid)
                .orElseThrow(()-> new CategoryException("categorie bestaat niet"));
    }

    @Override
    public Category updateCategoryById(long categoryid, Category updatedCategory) {
        return categoryRepository.findById(categoryid).map(
                category -> {
                    category.setCategoryName(updatedCategory.getCategoryName());
                    return categoryRepository.save(category);
                }).orElseGet(() -> {
            updatedCategory.setCategoryid(categoryid);
            return categoryRepository.save(updatedCategory);
        });
    }

    @Override
    public Category addCategoryToPost(long postid, Category newCategory) {
        Optional<Post> post = postRepository.findById(postid);
        if (post.isPresent()) {
                Post postFromDb = post.get();
                Set<Category> categories = postFromDb.getCategories();

                categories.add(newCategory);
                postFromDb.setCategories(categories);

                return categoryRepository.save(newCategory);
            }
        throw new PostNotFoundException("post bestaat niet");
    }

    @Override
    public String deleteCategoryById(long categoryid) {
        Optional<Category> category = categoryRepository.findById(categoryid);
        if (category.isPresent()){
            categoryRepository.deleteById(categoryid);
            return "Categorie met id " +category.get().getCategoryid() + " is verwijderd";
        }
        throw new CategoryException("categorie niet gevonden");
    }
}
