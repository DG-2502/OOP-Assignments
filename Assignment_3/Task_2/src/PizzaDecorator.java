public abstract class PizzaDecorator implements Pizza{
    private final Pizza decoratedPizza;
    public PizzaDecorator(Pizza pizza){
        this.decoratedPizza = pizza;
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost();
    }

    @Override
    public String getIngredients() {
        return decoratedPizza.getIngredients();
    }
}