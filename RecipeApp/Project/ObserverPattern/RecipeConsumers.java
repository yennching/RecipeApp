package ObserverPattern;

public class RecipeConsumers implements Observer {
    private String name;

    public RecipeConsumers(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void update(RecipeConsumers rc) {
        System.out.println("Hello " + getName() + "! " + "A new recipe has been added!");
    }
}
