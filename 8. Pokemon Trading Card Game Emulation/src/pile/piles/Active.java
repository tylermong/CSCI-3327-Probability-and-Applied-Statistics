package pile.piles;

import java.util.Scanner;

import card.Card;
import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;
import pile.Pile;
import java.util.Map;

public class Active extends Pile
{
    private static final Scanner in = new Scanner(System.in);
    public Active()
    {
        super();
    }

    public boolean attack(Active target)
    {
        PokemonCard attacker = (PokemonCard) getCardAtIndex(0);
        PokemonCard defender = (PokemonCard) target.getCardAtIndex(0);

        System.out.println("Attacker: " + attacker.getName() + " (HP: " + attacker.getHP() + ")");
        System.out.println("Defender: " + defender.getName() + " (HP: " + defender.getHP() + ")");

        Move selectedMove = selectMove(attacker, defender);
        applyMoveEffect(selectedMove, attacker, defender);
        int moveDamage = calculateMoveDamage(selectedMove, attacker, defender);
        return applyMoveDamage(moveDamage, attacker, defender);
    }

    private Move selectMove(PokemonCard attacker, PokemonCard defender)
    {
        Move[] moves = attacker.getMoves();

        System.out.println("\nMoves:");
        for (int i = 0; i < moves.length; i++)
        {
            System.out.println("\t" + (i + 1) + ". " + moves[i].getName() + " - Damage: " + moves[i].getDamage() + " - " + moves[i].getEffect() + " - " + moves[i].getEnergyCost());
        }
        System.out.print("Selection: ");
        int selectedMoveIndex = in.nextInt() - 1;

        // Validate within range
        while(selectedMoveIndex < 0 || selectedMoveIndex >= moves.length)
        {
            System.out.println("Invalid selection. Please choose a valid move (1 - " + moves.length + "): ");
            selectedMoveIndex = in.nextInt() - 1;
        }

        // Validate energy cost
        Map<String, Integer> energyCost = moves[selectedMoveIndex].getEnergyCost();
        for (Map.Entry<String, Integer> entry : energyCost.entrySet())
        {
            String energyType = entry.getKey();
            int requiredAmount = entry.getValue();
            int availableAmount = attacker.getTypeEnergy(energyType);
            if (availableAmount < requiredAmount)
            {
                System.out.println("Required energy is not available. Please select a different move or skip the attack.");
                return selectMove(attacker, defender);
            }
        }
        // At this point, the move is valid and the energy cost is met
        return moves[selectedMoveIndex];
    }

    private void applyMoveEffect(Move move, PokemonCard attacker, PokemonCard defender)
    {
        // TODO: Implement effect logic
    }

    private int calculateMoveDamage(Move move, PokemonCard attacker, PokemonCard defender)
    {
        // Move's base damage
        int damage = move.getDamage();
        
        // Factor in weakness and resistance
        if (defender.getWeakness() == attacker.getType())
        {
            damage *= 2;
        }
        else if (defender.getResistance() == attacker.getType())
        {
            damage -= 30;
        }

        // Check for minimum damage
        if (damage < 0)
        {
            damage = 0;
        }
        return damage;
    }

    private boolean applyMoveDamage(int damage, PokemonCard attacker, PokemonCard defender)
    {
        // Apply damage to defender's HP
        int defenderHP = defender.getHP();
        defender.setHP(defenderHP - damage);

        // Check if defender is knocked out
        System.out.println(attacker.getName() + " dealt " + damage + " damage to " + defender.getName() + ".");
        System.out.println(defender.getName() + " has " + Math.max(defender.getHP(), 0) + " HP remaining.");
        if (defender.getHP() <= 0)
        {
            System.out.println(defender.getName() + " has been knocked out.");
            return true;
        }
        return false;
    }
}