package models.DAO;

import models.Meals;

import java.util.List;

public interface MealsDao {

//    Get a single meal
    Meals getMealById(int mealId);

//    Can get all meals regardless of the category
    List<Meals> getAll();

}
