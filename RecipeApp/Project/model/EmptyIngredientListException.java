package model;

public class EmptyIngredientListException extends InvalidInputException {

    private static final long serialVersionUID = 1L;

    public EmptyIngredientListException(String msg) {
        super(msg);
    }
}
