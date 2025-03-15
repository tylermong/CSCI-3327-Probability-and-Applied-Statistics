package pile.piles;

import java.util.Scanner;

import card.pokemon.PokemonCard;
import card.pokemon.helper.Move;
import pile.Pile;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the active pile in the Pokémon Trading Card Game, where the player's active Pokémon is placed.
 * This class extends the Pile class and provides methods for attacking with the active Pokémon.
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Active extends Pile
{
    // Scanner for user input, used to select moves during the attack phase.
    private static final Scanner in = new Scanner(System.in);
    
    /**
     * Constructor for the Active class. Initializes the active pile for the player.
     */
    public Active()
    {
        super();
    }

    /**
     * Performs an attack with the active Pokémon on the target Pokémon.
     * Displays the attacker's and defender's HP, displays the moves, prompts for move selection, applies the move's damage, and returns true if the defender is knocked out.
     * 
     * @param target    The target Pokémon (defender) to be attacked.
     * @return          True if the defender is knocked out, false otherwise.
     */
    public boolean attack(Active target)
    {
        // Get the atacker and defender Pokémon.
        PokemonCard attacker = (PokemonCard) getCardAtIndex(0);
        PokemonCard defender = (PokemonCard) target.getCardAtIndex(0);

        // Display the attacker's and defender's HP.
        System.out.println("Attacker: " + attacker.getName() + " (HP: " + attacker.getHP() + ")");
        System.out.println("Defender: " + defender.getName() + " (HP: " + defender.getHP() + ")");

        // Displays the attacker's move and prompts for move selection.
        Move selectedMove = selectMove(attacker, defender);
        
        // If move selection is null (player chose to skip), then break out of the attack and return false (no knockout).
        if (selectedMove == null)
        {
            System.out.println("No move selected. Skipping attack.");
            return false;
        }

        // Calculate the move's damage based on the selected move, attacker's type, and defender's weakness/resistance.
        int moveDamage = calculateMoveDamage(selectedMove, attacker, defender);

        // Apply the move's damage to the defender's HP and return true if the defender is knocked out, false otherwise.
        return applyMoveDamage(moveDamage, attacker, defender);
    }

    /**
     * Displays the attacker's moves, prompts the player to select a move, validates the selection, and returns the selected move.
     * 
     * @param attacker  The attacking Pokémon.
     * @param defender  The defending Pokémon.
     * @return          The selected move if valid, null if the player chooses to skip the attack.
     */
    private Move selectMove(PokemonCard attacker, PokemonCard defender)
    {
        // Gets the attacker's moves and stores them in an array.
        Move[] moves = attacker.getMoves();

        // Display the attacker's moves and prompt for move selection.
        System.out.println("\nMoves (0 to skip):");
        for (int i = 0; i < moves.length; i++)
        {
            System.out.println("\t" + (i + 1) + ". " + moves[i].getName() + " - Damage: " + moves[i].getDamage() + " - " + moves[i].getEffect() + " - " + moves[i].getEnergyCost());
        }
        System.out.print("Selection: ");
        int selectedMoveIndex = in.nextInt() - 1;   // Subtract 1 to match the array's indexing (0-based) to the player's input (1-based).

        // If the player chooses 0, return null to indicate that the attack should be skipped.
        if (selectedMoveIndex == -1)
        {
            System.out.println("Move selection skipped.");
            return null;
        }

        // Validate the move is within range of the available moves.
        while(selectedMoveIndex < 0 || selectedMoveIndex >= moves.length)
        {
            System.out.println("Invalid selection. Please choose a valid move (1 - " + moves.length + "): ");
            selectedMoveIndex = in.nextInt() - 1;
        }

        // Validate the Pokémon has enough energy of the required type to perform the selected move.
        // First map the energy cost of the move to a map of energy types and their required amounts. Also track the used energy.
        Map<String, Integer> energyCost = moves[selectedMoveIndex].getEnergyCost();
        Map<String, Integer> usedEnergy = new HashMap<>();

        // Then check non-colorless energy requirements.
        for (Map.Entry<String, Integer> entry : energyCost.entrySet())
        {
            // Get the energy type and required amount for the move.
            String energyType = entry.getKey();
            int requiredAmount = entry.getValue();

            // Skip colorless energy for now. Colorless energy is handled separately at the end.
            if (energyType.equals("Colorless"))
            {
                continue;
            }

            // Check if the attacker has enough energy of the required type.
            // If not, prompt the player to select a different move or skip the attack.
            int availableAmount = attacker.getTypeEnergy(energyType);
            if (availableAmount < requiredAmount)
            {
                System.out.println("Not enough " + energyType + " energy. Required: " + requiredAmount + ", Available: " + availableAmount);
                System.out.println("Required energy is not available. Please select a different move or skip the attack.");
                return selectMove(attacker, defender);
            }

            // Track the used energy for each type.
            usedEnergy.put(energyType, requiredAmount);
        }

        // Now get the colorless energy requirement for the move.
        int colorlessRequired = energyCost.getOrDefault("Colorless", 0);

        // If there is a colorless energy requirement, check if the attacker has enough remaining energy (of any type) to meet the requirement.
        if (colorlessRequired > 0)
        {
            // Store the total remaining energy available for colorless energy requirement.
            int remainingTotalEnergy = 0;

            // Iterate through the attacker's energy map to calculate the total remaining energy after used energy.
            for (Map.Entry<String, Integer> entry : attacker.displayEnergyInMap().entrySet())
            {
                String type = entry.getKey();
                int availableAmount = entry.getValue();
                int used = usedEnergy.getOrDefault(type, 0);
                remainingTotalEnergy += Math.max(0, availableAmount - used);
            }

            // If the remaining total energy is less than the colorless requirement (i.e., the attacker does not have enough energy to meet the colorless requirement)
            // then prompt the player to select a different move.
            if (remainingTotalEnergy < colorlessRequired)
            {
                System.out.println("Not enough remaining energy to meet the colorless requirement.");
                System.out.println("Required: " + colorlessRequired + ", Available: " + remainingTotalEnergy);
                System.out.println("Required energy is not available. Please select a different move or skip the attack.");
                return selectMove(attacker, defender);
            }
        }
        
        // At this point, the move is valid and the energy cost is met, so return the selected move.
        return moves[selectedMoveIndex];
    }

    /**
     * Calculates the move's damage based on the move's base damage and the defender's weakness/resistance to the attacker's type.
     * 
     * @param move      The move being used by the attacker.
     * @param attacker  The attacking Pokémon.
     * @param defender  The defending Pokémon.
     * @return          The final damage value to be applied to the defender's HP (0 if negative after resistance calculation).
     */
    private int calculateMoveDamage(Move move, PokemonCard attacker, PokemonCard defender)
    {
        // Get the base damage of the move.
        int damage = move.getDamage();
        
        // Check for weakness and resistance based on the defender's type and the attacker's type.
        // If the defender is weak to the attacker's type, double the damage.
        if (defender.getWeakness() == attacker.getType())
        {
            damage *= 2;
        }
        // If the defender is resistant to the attacker's type, reduce the damage by 30.
        else if (defender.getResistance() == attacker.getType())
        {
            damage -= 30;
        }

        // If the damage is negative after resistance calculation, set it to 0 to avoid negative damage values, and return this value.
        return Math.max(damage, 0);
    }

    /**
     * Applies the move's damage to the defender's HP and checks if the defender is knocked out.
     * Displays the damage dealt and the defender's remaining HP.
     * If the defender's HP is less than or equal to 0 (i.e., knocked out), return true to indicate a knockout, otherwise return false.
     * 
     * @param damage    The damage to be applied to the defender's HP.
     * @param attacker  The attacking Pokémon.
     * @param defender  The defending Pokémon.
     * @return          True if the defender is knocked out, false otherwise.    
     */
    private boolean applyMoveDamage(int damage, PokemonCard attacker, PokemonCard defender)
    {
        // Apply the move's damage to the defender's HP.
        int defenderHP = defender.getHP();
        defender.setHP(defenderHP - damage);

        // Display the damage dealt and the defender's remaining HP.
        System.out.println(attacker.getName() + " dealt " + damage + " damage to " + defender.getName() + ".");
        System.out.println(defender.getName() + " has " + Math.max(defender.getHP(), 0) + " HP remaining.");

        // If the defender's HP is less than or equal to 0 (i.e., knocked out), then display a message and return true to indicate a knockout.
        if (defender.getHP() <= 0)
        {
            System.out.println(defender.getName() + " has been knocked out.");
            return true;
        }

        // If the defender's HP is still above 0, return false to indicate that the defender is not knocked out.
        return false;
    }
}