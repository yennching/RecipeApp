package model;

public class FamousRecipes extends CookBook {

    public FamousRecipes(String name, String description, String author){
        super(description, author);
        this.name = name;
    }


    @Override
    public void deserialize(String from) {
        String[] parts = from.split(":", 2);
        this.name = parts[0];
        this.description = parts[1];
    }

    @Override
    public String serialize() {
        return "F-"+ name+":"+description;
    }

    @Override
    public String toString() {
        return "<html>"  + toSimpleString() + "</html>";
    }

    @Override
    public String toSimpleString() {
        return "Food: " + getName() +"<br>" +
                "Description: " + getDescription();
    }
}
