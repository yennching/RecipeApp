package model;

import java.util.ArrayList;
import java.util.List;

public class RecipeAuthor {

    List<CookBook> cookBooks; // everything this author has written
    String name;

    public RecipeAuthor(String name, CookBook cookBook){
        cookBooks = new ArrayList<>();
        this.name = name;
        cookBooks.add(cookBook);
    }

    public void addCookBook(CookBook cookbook){
        if (!this.cookBooks.contains(cookbook)){
            this.cookBooks.add(cookbook);
            cookbook.addAuthor(this);
        }
    }

}
