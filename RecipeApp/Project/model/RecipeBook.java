package model;

import ObserverPattern.RecipeConsumers;

import java.util.*;

public class RecipeBook extends CookBook {
    private Map<CookingRoutine, List<String>> cooking = new HashMap<>();

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: changes the name, description and list of ingredients in Name
    public RecipeBook(String name, String description, List<String> ingredients, String author){
        super(description, author);
        this.ingredients = ingredients;
        this.name = name;


    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String serialize() {
        StringBuilder builder = new StringBuilder();
        String sep = "";
        for(String i : ingredients) {
            builder.append(sep);
            builder.append(i);
            sep = ";";
        }
        return name+":"+builder.toString()+":"+description;
    }

    @Override
    public void deserialize(String from) {
        String[] parts = from.split(":");
        this.name = parts[0];
        String[] ingredients = parts[1].split(";");
        for(String i : ingredients) {
            this.ingredients.add(i);
        }
        this.description = parts[2];
        for(int i = 3 ; i < parts.length ; i++){
            authors.add(new RecipeAuthor(parts[i], this));
        }
        //calculateDescription();
    }

    //REQUIRES: The ListofIngredients not to be empty
    //MODIFIES: nothing
    //EFFECTS: return true if the ingredients entered (user input) equals to one of the ingredients in CookBook of
    //         ingredients, false otherwise.
   public boolean hasIngredient(String ingrid) {
        for (String s : ingredients) {
            if (s.equals(ingrid)) {
                return true;
            }
        }
        return false;
    }

    //REQUIRES: the ingredients list not to be empty
    //MODIFIES: this
    //EFFECTS: adds an ingredient to the ingredients list
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
        //notifyObservers(rc);

    }

    public void removeIngredient(String ingredient){
        ingredients.remove(ingredient);
    }

    public void checkIngredient (String rep, String ing) throws EmptyIngredientListException {
        if (hasIngredient(ing)) {
            if (rep.equals(getName())){
                removeIngredient(ing);
                System.out.println("The ingredient has been removed successfully!");
            }
        }

        else if (getIngredients().size()<2){
            throw new EmptyIngredientListException("");
        }
    }

    public void addNewCookRoutine(CookingRoutine cookRoutine) {
        ingredients = new ArrayList<>();
        cooking.put(cookRoutine, ingredients);
    }

    public void addToCookRoutine(CookingRoutine cookRoutine, String ingredient) {
        List<String> ingredients = cooking.get(cookRoutine);
        ingredients.add(ingredient);
    }

    /*public void calculateDescription() {
        if(this.name != null && this.name.length() > 1) {
            this.description = "Recommended serving size of " + NutritionsAPI.getRecommendedServingQuantity(this.name);
        }
    }*/

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: prints out a line of Name ith food, description and ingredients required.
    @Override
    public String toString() {
        return "<html>" + toSimpleString() + "</html>";
    }

    @Override
    public String toSimpleString() {
        return "Food: " + getName() +"<br>" +
                "Description: " + getDescription() + "<br>" +
                "Recipes required: " + getIngredients();
    }


}
