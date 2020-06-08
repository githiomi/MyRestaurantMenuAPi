import exceptions.ApiException;
import models.Category;
import models.Constants;
import models.DAO.Sql2oMeals;
import models.DAO.Sql2ocategory;
import models.Meals;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        Sql2oMeals sql2oMeals;
        Sql2ocategory sql2ocategory;
        Connection connection;
        Gson gson = new Gson();

//      Heroku database
        String connectionString = "jdbc:postgresql://ec2-3-222-30-53.compute-1.amazonaws.com/da14ef8b93cdec";
        String username = "dliwymnwsjslcc";
        String password = "b1b526b4235fb4a3c882eebb2d1c8f936a25e0c7d720f61f17d65aec34a6c7fb";

//        Remove the following 2 lines of code
        // Local postgresql
        String connectionString = "jdbc:postgresql://localhost:5432/renu";
        String username = Constants.getMyUsername();
        String password = Constants.getMyPassword();

        Sql2o sql2o = new Sql2o(connectionString, username, password);
        sql2oMeals = new Sql2oMeals(sql2o);
        sql2ocategory = new Sql2ocategory(sql2o);
        connection = sql2o.open();

        get("/", "application/json", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "Home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/meals", "application/json", (req, res) -> {
            res.type("application/json");

            List<Meals> allMeals = sql2oMeals.getAll();

            if ( allMeals.size() > 0) {
                res.status(201);
                return gson.toJson(allMeals);
            }
            else {
                throw new ApiException(404, String.format("There are no meals in the database"));
            }
        });

        get("/categories", "application/json", (req, res) -> {
            res.type("application/json");

            List<Category> allCategories= sql2ocategory.getAllCategories();

            if ( allCategories.size() > 0 ) {
                return gson.toJson(allCategories);
            }else {
                throw new ApiException(404, String.format("There are no categories in the database"));
            }
        });

        get("/categories/:id", "application/json", (req, res) -> {
            int categoryId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(sql2ocategory.findById(categoryId));
        });

        get("/categories/:id/meals", "application/json", (req, res) -> {
            int categoryId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(sql2ocategory.getAllMealsInCategory(categoryId));
        });

        get("/categories/:ic/meals/:id", "application/json", (req, res) -> {
            int categoryId = Integer.parseInt(req.params("ic"));
            int mealId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(sql2oMeals.getMealById(mealId));
        });

        //FILTERS
        after((req, res) ->{

        });
    }
}
