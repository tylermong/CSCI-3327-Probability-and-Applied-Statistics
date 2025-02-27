package game;
import card.pokemon.*;
import card.pokemon.pokemoncards.Pikachu;
import card.pokemon.pokemoncards.Zapdos;
import card.energy.*;
import card.energy.energycards.DoubleColorlessEnergy;
import card.energy.energycards.LightningEnergy;

public class Main
{
    public static void main(String[] args)
    {
        Game game = new Game();
        game.startGame();
    }
}