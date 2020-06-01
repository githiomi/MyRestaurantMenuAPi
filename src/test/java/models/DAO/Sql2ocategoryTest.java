package models.DAO;

import models.Category;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2ocategoryTest {

    private static Connection conn;
    private static Sql2ocategory sql2ocategory;
    private static Sql2oMeals sql2oMeals;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/renu_test";
        Sql2o sql2o = new Sql2o(connectionString, "dhosio", "MaFaD@niel2019");
        sql2ocategory = new Sql2ocategory(sql2o);
        sql2oMeals = new Sql2oMeals(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void canGetAllCategoriesInDatabase(){
        int actualCount = 7;
        int count = sql2ocategory.getAllCategories().size();
        assertTrue(actualCount == count);
        assertEquals(7, count);
    }

    @Test
    public void canFindAllTheMealsInsideACategory(){
        int actualCount = 3;
        int count = sql2ocategory.getAllMealsInCategory(1).size();
        assertTrue(actualCount == count);
        assertEquals(3, count);
    }

    @Test
    public void canGetASpecificCategory() {
        Category found = sql2ocategory.findById(2);
        String categoryName = "Dessert";
        assertEquals(categoryName, found.getName());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        conn.close();
        System.out.println("Database has been closed successfully");
    }
}