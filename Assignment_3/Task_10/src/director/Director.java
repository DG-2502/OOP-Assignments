package director;

import builders.Builder;

import static main.PizzaType.*;

public class Director {
    public void makeHawaiianPizza(Builder builder){
        builder.setType(HAWAIIAN);
        builder.setDough("обычное");
        builder.setSauce("мягкий");
        builder.setTopping("ветчина + ананас");
    };
    public void makeSpicyPizza(Builder builder){
        builder.setType(SPICY);
        builder.setDough("запечёное");
        builder.setSauce("острый");
        builder.setTopping("пепперони + салями");
    };

}
