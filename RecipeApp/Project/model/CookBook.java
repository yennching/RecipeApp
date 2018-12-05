package model;

import ObserverPattern.RecipeConsumers;
import ObserverPattern.Subject;

import java.util.ArrayList;
import java.util.List;

public abstract class CookBook extends Subject {

    protected String description;
    protected String name;
    protected List<String> ingredients;
    protected List<RecipeAuthor> authors;
    protected List<RecipeConsumers> consumers;

    public CookBook(String description, String author) {
        this.description = description;
        this.authors = new ArrayList<>();
        this.authors.add(new RecipeAuthor(author, this));
        consumers = new ArrayList<>();
    }

    public void addAuthor(RecipeAuthor author){
        if (!this.authors.contains(author)){
            this.authors.add(author);
            author.addCookBook(this);
        }
    }

    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void deserialize(String from);

    public abstract String toString();

    public abstract String toSimpleString();

    public abstract String serialize();
}
