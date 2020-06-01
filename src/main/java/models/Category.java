package models;


public class Category {

    public int id;
    public String name;
    public String description;
    public Meals[] meals;

    public Category(String name, String description, Meals[] meals) {
        this.name = name;
        this.description = description;
        this.meals=meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Meals[] getMeals() {
        return meals;
    }

    public void setMeals(Meals[] meals) {
        this.meals = meals;
    }
}
