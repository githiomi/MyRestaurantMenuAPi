import models.DAO.Sql2oMeals;
import models.DAO.Sql2ocategory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
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

        String connectionString = "jdbc:postgresql://localhost:5432/renu";
        Sql2o sql2o = new Sql2o(connectionString, "dhosio", "MaFaD@niel2019");
        sql2oMeals = new Sql2oMeals(sql2o);
        sql2ocategory = new Sql2ocategory(sql2o);
        connection = sql2o.open();

        get("/", "application/json", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            return new ModelAndView(model, "Home.hbs");
        }, new HandlebarsTemplateEngine());

        get("/meals", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(sql2oMeals.getAll());
        });

        get("/categories", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(sql2ocategory.getAllCategories());
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
