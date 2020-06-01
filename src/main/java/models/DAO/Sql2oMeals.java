package models.DAO;

import models.Meals;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oMeals implements MealsDao{

    private final Sql2o sql2o;

    public Sql2oMeals(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public Meals getMealById(int mealId) {
        String sql = "SELECT * FROM meals WHERE id = :id";
        try (Connection conn = sql2o.open()) {
            Meals meal = conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("id", mealId)
                    .executeAndFetchFirst(Meals.class);
            return meal;
        }
    }

    @Override
    public List<Meals> getAll(){
        String sql = "SELECT * FROM meals";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Meals.class);
        }
    }

}
