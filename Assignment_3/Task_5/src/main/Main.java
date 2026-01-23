package main;

import facade.PotionConversionFacade;

public class Main {

    public static String potionName = "Minor Healing Potion";
    public static String type = "stamina";

    public static void main(String[] args) {
        PotionConversionFacade facade = new PotionConversionFacade();
        String result = facade.convertPotion(Main.potionName, Main.type);
        System.out.println(result);
    }
}
