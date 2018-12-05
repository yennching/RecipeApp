package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.EmptyIngredientListException;
import model.MainRecipe;
import model.RecipeBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {
    public RecipeBook testRecipeBook;

    @BeforeEach
    public void runBefore(){
        testRecipeBook = new RecipeBook("","",new ArrayList<>(),"");
    }

    @Test
    public void testIngredientsList() {
        // testing if it searches an ingredient in an ingredients list
        List<String> list1 = new ArrayList<>();
        list1.add("eggs");
        list1.add("milk");
        list1.add("flour");
        RecipeBook myRecipeBook = new RecipeBook("pancake", "a sweet treat for your sweet tooth", list1, "Gordon");
        String entry = "eggs";
        assertTrue(myRecipeBook.hasIngredient(entry));
    }

    @Test
    public void testIngredientNotThere() {
        // testing an ingredient that is not there
        List<String> list1 = new ArrayList<>();
        list1.add("eggs");
        list1.add("milk");
        list1.add("flour");
        RecipeBook myRecipeBook = new RecipeBook("pancake","a sweet treat for your sweet tooth", list1, "Christina");
        String entry = "cheese";
        assertFalse(myRecipeBook.hasIngredient(entry));
    }

    @Test
    public void testIngredientsListSize() {
        List<String> list1 = new ArrayList<>();
        list1.add("chocolate");
        list1.add("flour");
        list1.add("eggs");
        list1.add("vanilla extract");
        RecipeBook myRecipeBook = new RecipeBook("Chocolate Cake", "dessert time!",list1,"Gordon");
        String entry = "flour";
        assertTrue(myRecipeBook.hasIngredient(entry));
    }

    @Test
    public void testIngredientinTwoIngredientList() {
        List<String> list1 = new ArrayList<>();
        list1.add("chocolate");
        list1.add("flour");
        list1.add("eggs");
        list1.add("vanilla extract");
        RecipeBook myRecipeBook = new RecipeBook("Chocolate Cake", "dessert time!",list1, "Gordon");

        List<String> list2 = new ArrayList<>();
        list2.add("flour");
        list2.add("eggs");
        list2.add("milk");
        RecipeBook myRecipeBook2 = new RecipeBook("Pancake", "yummy!",list2,"Gordon");

        String entry = "eggs";
        assertTrue(myRecipeBook.hasIngredient(entry));
        assertTrue(myRecipeBook2.hasIngredient(entry));
    }

    @Test
    public void testIngredientinOneOfTheLists() {
        List<String> list1 = new ArrayList<>();
        list1.add("chocolate");
        list1.add("flour");
        list1.add("eggs");
        list1.add("vanilla extract");
        list1.add("butter");
        RecipeBook myRecipeBook = new RecipeBook("Chocolate Cake", "dessert time!",list1, "Kris");

        List<String> list2 = new ArrayList<>();
        list2.add("beef steak");
        list2.add("salt & pepper");
        list2.add("olive oil");
        RecipeBook myRecipeBook2 = new RecipeBook("Steak", "good food",list2,"Krissy");

        String entry = "butter";
        assertTrue(myRecipeBook.hasIngredient(entry));
        assertFalse(myRecipeBook2.hasIngredient(entry));
    }

    @Test
    public void testLoad() {
        testRecipeBook.deserialize("sandwich:bread;ham;cheese;lettuce;mayo;butter:A tasty snack.");
        String entry = "cheese";
        assertTrue(testRecipeBook.hasIngredient(entry));
    }

    @Test
    public void testSave() throws IOException {
        MainRecipe mr = new MainRecipe();
        mr.save();
        List<String> recipes = Files.readAllLines(Paths.get("Recipes.txt"));
        assertEquals("Sandwich:bread;ham;cheese;lettuce;mayo:A tasty snack.",recipes.get(0));
    }

    @Test
    public void testEmptyIngredientsException() throws EmptyIngredientListException {
        testRecipeBook = new RecipeBook("Butter Chicken","Tasty Indian Recipe!", new ArrayList<>(),"David");
        String rep = "Butter Chicken";
        String ing = "chicken";
        try {
            testRecipeBook.checkIngredient(rep, ing);
            fail("Failed!");
        } catch (EmptyIngredientListException e) {

        }
    }


}
