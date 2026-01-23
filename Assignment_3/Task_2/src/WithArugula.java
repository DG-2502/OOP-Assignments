public class WithArugula extends PizzaDecorator {
    public WithArugula(Pizza pizza){
        super(pizza);
    }

    @Override
    public double getCost() {
        return super.getCost() + 15;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", arugula";
    }
}
