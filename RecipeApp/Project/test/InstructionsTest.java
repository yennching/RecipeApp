package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.RecipeBook;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InstructionsTest {

    public RecipeBook testRecipeBook;

    @BeforeEach
    public void runBefore(){
        testRecipeBook = new RecipeBook("","",new ArrayList<>(),"");
    }

    @Test
    public void testEmptyIngredientsList() {
        //testing that the ingredients list is empty
        String entry = "ham";
        assertFalse(testRecipeBook.hasIngredient(entry));
        assertEquals(0, testRecipeBook.getIngredients().size());
    }
}
