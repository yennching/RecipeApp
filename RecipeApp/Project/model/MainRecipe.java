package model;

import ObserverPattern.RecipeConsumers;

import java.io.IOException;
import java.lang.NumberFormatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainRecipe {
    ArrayList<RecipeBook> recipeBook = new ArrayList<>();
    ArrayList<FamousRecipes> fRecipe = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    public MainRecipe() {
        try {
            setUp();
        } catch (IOException e) {

        }
    }

    public void run() throws NumberFormatException {
        String entry;
        while(true){
            System.out.println("Please select an option:");
            System.out.println("1. All Recipes\n" + "2. Famous Recipes\n");
            entry = scanner.nextLine();
            if(entry.equals("1")) {
                for(RecipeBook r : recipeBook) {
                    System.out.println(r.toString());
                }
                System.out.println("You can also:\n" + "1. add ingredient\n" + "2. remove ingredient\n" + "3. add recipe");
                String enter;
                enter = scanner.nextLine();
                if(enter.equals("1")) { //add ingredient
                    System.out.println("Please add an ingredient");
                    String add = scanner.nextLine();
                    System.out.println("Please enter your name.");
                    String name = scanner.nextLine();
                    RecipeConsumers rc = new RecipeConsumers(name);
                    try {
                        Double.parseDouble(add);
                        System.out.println("That was a number. Please enter an ingredient.");
                    }
                    catch (NumberFormatException e) {
                        RecipeBook r = new RecipeBook("","", new ArrayList<>(), "");
                        recipeBook.add(r);
                        //r.addObserver(rc);
                        r.addIngredient(add);
                    }
                    finally {
                        System.out.println("Just ingredients!");
                    }
                }
                if(enter.equals("2")) { //remove ingredient
                    System.out.println("Which recipe would you like to remove the ingredient from?");
                    String rep = scanner.nextLine();
                    System.out.println("Enter the ingredient you would like to remove.");
                    String ing = scanner.nextLine();
                    for (RecipeBook r : recipeBook) {
                        try {
                            r.checkIngredient(rep, ing);
                        } catch (EmptyIngredientListException e) {
                            System.out.println("Empty Ingredients List!");
                        }
                        }
                }
            }

            else if (entry.equals("2")) {
                for(FamousRecipes r : fRecipe) {
                    System.out.println(r.getName());
                    System.out.println(r.getDescription());
                }
            }
            else if (entry.equals("search")) {
                System.out.println("Please enter an ingredient");
                String search = scanner.nextLine();
                for (RecipeBook r: recipeBook){
                    if (r.hasIngredient(search)) {
                        System.out.println(r.toString());
                    }
                }
            }
            else if (entry.equals("finish")){
                try {
                    save();
                } catch (IOException e) {
                }
                break;
            }
        }
        System.out.println("Enjoy your meal!");
    }


    public void setUp() throws IOException {
        recipeBook.clear();
        fRecipe.clear();
        load();
    }

    public static void main(String[] args) throws IOException, NumberFormatException, EmptyIngredientListException {
        MainRecipe recipe = new MainRecipe();
        recipe.run();
    }

    public void load() throws IOException {
        List<String> recipes = Files.readAllLines(Paths.get("Recipes.txt"));
        for(String s : recipes) {
            RecipeBook r = new RecipeBook(null, null, new ArrayList<>(), null);
            r.deserialize(s);
            recipeBook.add(r);
        }

        List<String> fRecipes = Files.readAllLines(Paths.get("Famous.txt"));
        for(String s : fRecipes) {
            FamousRecipes fr = new FamousRecipes(null,null, null);
            fr.deserialize(s);
            fRecipe.add(fr);
        }
    }

    public void save() throws IOException {
        List<String> recipes = new ArrayList<>();
        for(RecipeBook r : recipeBook) {
            recipes.add(r.serialize());
        }
        Files.write(Paths.get("Recipes.txt"), recipes);

        List<String> fRecipes = new ArrayList<>();
        for(FamousRecipes fr : fRecipe) {
            fRecipes.add(fr.serialize());
        }
        Files.write(Paths.get("Famous.txt"), fRecipes);
    }

}
