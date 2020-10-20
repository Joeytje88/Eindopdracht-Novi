package nl.tipsntricks.games.service;

import nl.tipsntricks.games.domain.Category;

public interface ICategoryService {
    Category getCategoryById (long categoryid);
    Category updateCategoryById (long categoryid, Category updatedCategory);
    Category addCategoryToPost (long postid, Category newCategory);
    String deleteCategoryById (long categoryid);
}
