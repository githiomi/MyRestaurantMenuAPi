package models;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonClass {

    public static void mealJsonArray(){
        JSONArray jsonArray = new JSONArray();
    }

    public static void newJsonObject(String name, String description, String imageUrl, JSONArray meals){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("category_name", name);
        jsonObject.put("category_description", description);
        jsonObject.put("category_imageurl", imageUrl);
        jsonObject.put("category-meals", meals);
    }
}
