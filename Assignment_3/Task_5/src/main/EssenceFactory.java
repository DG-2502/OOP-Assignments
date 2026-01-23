package main;

import alchemy_library.*;

import static main.Type.*;

public class EssenceFactory {
    public static Essence extract(Potion file) {
        Type type = file.getType();
        if (type == MANA) {
            System.out.println("EssenceFactory: extracting mana essence...");
            return new ManaEssenceConverter();
        } else if (type == STAMINA) {
            System.out.println("EssenceFactory: extracting stamina essence...");
            return new StaminaEssenceConverter();
        } else {
            System.out.println("EssenceFactory: extracting healing essence...");
            return new HealingEssenceConverter();
        }
    }
}
