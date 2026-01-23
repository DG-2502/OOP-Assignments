package builders;

import main.PizzaType;

public interface Builder {
    void setType(PizzaType type);
    void setDough(String dough);
    void setSauce(String sauce);
    void setTopping(String topping);
}
