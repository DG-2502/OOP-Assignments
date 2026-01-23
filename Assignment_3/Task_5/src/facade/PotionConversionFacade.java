package facade;

import alchemy_library.HealingEssenceConverter;
import alchemy_library.ManaEssenceConverter;
import alchemy_library.StaminaEssenceConverter;
import main.*;

import static main.Type.MANA;
import static main.Type.STAMINA;

public class PotionConversionFacade {
    public String convertPotion(String potionName, String convertTo){
        System.out.println("PotionConversionFacade: conversion started.");
        Potion potion = new Potion(potionName);
        Type type = Type.valueOf(Main.type.toUpperCase());
        Essence sourceEssence = EssenceFactory.extract(potion);
        Essence destinationEssence;
        if (type == MANA) {
            destinationEssence = new ManaEssenceConverter();
        } else if (type == STAMINA) {
            destinationEssence = new StaminaEssenceConverter();
        } else {
            destinationEssence = new HealingEssenceConverter();
        }
        Potion portion = Extractor.extractPortion(potion, sourceEssence);
        Potion intermediateResult = Extractor.convert(portion, destinationEssence);
        Potion result = new Finalizator().fix(intermediateResult);
        System.out.println("Client Code: conversion completed.");

        return result.getName();
    }

}
