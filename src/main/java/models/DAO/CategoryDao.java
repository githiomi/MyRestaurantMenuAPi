package models.DAO;

import models.Category;
import models.Meals;

import java.util.List;

public interface CategoryDao {

    //    Can find all the categories
    List<Category> getAllCategories();

    //    Call the json table


    //    Get a single category
    Category findById(int categoryId);


    //    Get all the meals in a category
    List<Meals> getAllMealsInCategory(int categoryId);

}
