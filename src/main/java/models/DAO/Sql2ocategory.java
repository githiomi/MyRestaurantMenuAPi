package models.DAO;

import models.Category;
import models.Meals;
import org.sql2o.*;

import java.util.List;

public class Sql2ocategory implements CategoryDao{

    private final Sql2o sql2o;

    public Sql2ocategory(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public List<Category> getAllCategories(){
        String sql = "SELECT * FROM categories";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Category.class);
        }
    }

    @Override
    public Category findById(int categoryId){
        String sql = "SELECT * FROM categories WHERE id = :id";
        try (Connection conn = sql2o.open()){
            Category foundCategory = conn.createQuery(sql)
                                        .throwOnMappingFailure(false)
                                        .addParameter("id", categoryId)
                                        .executeAndFetchFirst(Category.class);
            return foundCategory;
        }
    }

    @Override
    public List<Meals> getAllMealsInCategory(int categoryId){
        String sql = "SELECT * FROM meals WHERE categoryid = :categoryId";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("categoryId", categoryId)
                    .executeAndFetch(Meals.class);
        }
    }
}
