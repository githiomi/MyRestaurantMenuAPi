package models.DAO;

import models.Constants;
import models.Meals;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.logging.SysOutLogger;

import static org.junit.Assert.*;

public class Sql2oMealsTest {

    private static Connection conn;
    private static Sql2oMeals sql2oMeals;
    private static Sql2ocategory sql2ocategory;

    //        Remove the following 2 lines of code
    private static String username = Constants.getMyUsername();
    private static String password = Constants.getMyPassword();

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/renu_test";
        Sql2o sql2o = new Sql2o(connectionString, username, password);
        sql2ocategory = new Sql2ocategory(sql2o);
        sql2oMeals = new Sql2oMeals(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void canGetASpecificMealUsingTheId(){
        Meals meal = sql2oMeals.getMealById(1);
        assertEquals("Water" , meal.getName());
    }

    @Test
    public void canGetAllMealsRegardlessOfTheCategory() {
        int actualCount = 3;
        int count = sql2oMeals.getAll().size();
        assertEquals(3, count);
        assertTrue(actualCount == count);
    }


    @AfterClass
    public static void tearDown() throws Exception {
        conn.close();
        System.out.println("Database was closed successfully");
    }
}