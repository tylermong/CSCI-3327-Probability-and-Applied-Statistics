package game;
import card.pokemon.*;
import card.pokemon.species.Pikachu;
import card.pokemon.species.Zapdos;
import card.energy.*;

public class Main
{
    public static void main(String[] args)
    {
        // Temporary Tests
        
        // Pokemon Cards
        System.out.println("[Testing Pokemon Cards]");
        Pikachu pikachu = new Pikachu();
        Zapdos zapdos = new Zapdos();
        
        System.out.println("\nPikachu:");
        System.out.println("Name: " + pikachu.getName());
        System.out.println("Weakness: " + pikachu.getWeakness());
        System.out.println("Resistance: " + pikachu.getResistance());
        System.out.println("Retreat Cost: " + pikachu.getRetreatCost());
        
        System.out.println("\nZapdos:");
        System.out.println("Name: " + zapdos.getName());
        System.out.println("Weakness: " + zapdos.getWeakness());
        System.out.println("Resistance: " + zapdos.getResistance());
        System.out.println("Retreat Cost: " + zapdos.getRetreatCost());

        // Energy Cards
        System.out.println("\n[Testing Energy Cards]");
        LightningEnergy lightningEnergy = new LightningEnergy();
        DoubleColorlessEnergy doubleColorless = new DoubleColorlessEnergy();
        
        System.out.println("\nEnergy Card:");
        System.out.println(lightningEnergy.getName() + ", Type: " + lightningEnergy.getType() + ", Amount: " + lightningEnergy.energyValue());
        System.out.println(doubleColorless.getName() + " , Type: " + doubleColorless.getType() + ", Amount: " + doubleColorless.energyValue());
    }
}