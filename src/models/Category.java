package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private Integer categoryId;
    private String categoryName;
    private ArrayList<Category> listOfChildrensCategory;

    public static Category mainCategory = new Category(0, "CATEGORIES");

    public Integer getCategoryId() {
        return categoryId;
    }

    private Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        listOfChildrensCategory = new ArrayList<>();
    }

    private Category(Integer categoryId, String categoryName, Category parent) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        listOfChildrensCategory = new ArrayList<>();
    }

    public Category() {
        if (mainCategory.listOfChildrensCategory.isEmpty()) mainCategory = createCategoryTree(mainCategory);
    }

    private String getCategoryName() {
        return categoryName;
    }

    public ArrayList<Category> getListOfChildrensCategory() {
        return listOfChildrensCategory;
    }

    private void addChildrenCategory(Category category) {
        listOfChildrensCategory.add(category);
    }


    private void addChildrensCategory(Category category, Category childrenCategory) {
        category.addChildrenCategory(childrenCategory);
    }

    private Category createCategory(Integer categoryId, String categoryName, Category parent) {
        return new Category(categoryId, categoryName, parent);
    }

    @Override
    public String toString() {
        return this.getCategoryId() + " " + this.getCategoryName();
    }

    private Category createCategoryTree(Category mainCategory) {
        Category vehicles = createCategory(1, "VEHICLES", mainCategory); // zeby od niej zaczynac wyswietlanie i tak dalej
        Category clothes = createCategory(2, "CLOTHES", mainCategory);
        Category underwear = createCategory(3, "UNDERWEAR", clothes);
        Category tshirts = createCategory(4, "T-SHIRTS", clothes);
        Category cars = createCategory(5, "CARS", vehicles);
        Category tires = createCategory(6, "TIRES", vehicles);

        addChildrensCategory(mainCategory, vehicles);
        addChildrensCategory(mainCategory, clothes);
        addChildrensCategory(vehicles, cars);
        addChildrensCategory(vehicles, tires);
        addChildrensCategory(clothes, underwear);
        addChildrensCategory(clothes, tshirts);

        return mainCategory;
    }
}
