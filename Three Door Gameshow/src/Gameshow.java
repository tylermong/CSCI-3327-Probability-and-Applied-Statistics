/**
 * This class simulates the game show for N rounds and returns the number of wins based on the player's choice.
 *
 * @author  Tyler Mong
 * @version 1.0
 */
public class Gameshow
{
    /**
     * Simulates the player sticking with their initial choice for N rounds.
     * 
     * @param numberOfRounds    The number of rounds to simulate.
     * @return                  The number of wins.
     */
    public int playGameStickWithChoice(int numberOfRounds)
    {
        return playGame(numberOfRounds, false);
    }

    /**
     * Simulates the player switching from their initial choice for N rounds.
     * 
     * @param numberOfRounds    The number of rounds to simulate.
     * @return                  The number of wins.
     */
    public int playGameSwitchChoice(int numberOfRounds)
    {
        return playGame(numberOfRounds, true);
    }

    /**
     * Simulates playing the game for N rounds.
     * 
     * @param numberOfRounds    The number of rounds to simulate.
     * @param switchChoice      Player's decision to switch doors.
     * @return                  The number of wins.
     */
    private int playGame(int numberOfRounds, boolean switchChoice)
    {
        int winCounter = 0;

        // Play the game for N rounds.
        for (int roundNumber = 0; roundNumber < numberOfRounds; roundNumber++)
        {
            // Create the three doors and put a goat behind each.
            Door[] doors = new Door[3];
            for (int i = 0; i < doors.length; i++)
            {
                doors[i] = new Door();
                doors[i].contents = "goat";
            }

            // Pick the winning door and put a goat behind it.
            int winningDoorNumber = (int) (Math.random() * 3);
            doors[winningDoorNumber].contents = "car";

            // Have the "player" select a random door.
            int selectedDoorNumber = (int) (Math.random() * 3);

            // Eliminate a goat door.
            int eliminatedDoorNumber = eliminateGoatDoor(doors, selectedDoorNumber, winningDoorNumber);

            // After eliminating a door, if they want to switch, then switch doors.
            if (switchChoice)
            {
                selectedDoorNumber = switchDoor(doors, selectedDoorNumber, eliminatedDoorNumber);
            }

            // Finally check if they selected the winning door. If so, increment the win counter.
            if (doors[selectedDoorNumber].contents.equals("car"))
            {
                winCounter++;
            }
        }
        return winCounter;
    }

    /**
     * Eliminates a goat door that the player did not select.
     * 
     * @param doors                 The three doors.
     * @param selectedDoorNumber    The door the player selected.
     * @param winningDoorNumber     The door with the car behind it.
     * @return                      The eliminated door number.
     */
    private int eliminateGoatDoor(Door[] doors, int selectedDoorNumber, int winningDoorNumber)
    {
        for (int i = 0; i < doors.length; i++)
        {
            // If the current door is not the selected door and is not the winning door, then eliminate it, by changing
            // its contents from "goat" to "removed".
            if (i != selectedDoorNumber && doors[i].contents.equals("goat"))
            {
                doors[i].contents = "removed";
                return i;
            }
        }
        // If no goat doors were found, return -1. (This should never happen.)
        return -1;
    }

    /**
     * Switches the player's selected door (if they want to switch).
     * 
     * @param doors                 The three doors (two remaining + one eliminated).
     * @param selectedDoorNumber    The door the player selected.
     * @param eliminatedDoorNumber  The door that was eliminated.
     * @return                      The switched door number.
     */
    private int switchDoor(Door[] doors, int selectedDoorNumber, int eliminatedDoorNumber)
    {
        for (int i = 0; i < doors.length; i++)
        {
            // If the current door is not the selected door and is not the eliminated door, then switch to it.
            if (i != selectedDoorNumber && i != eliminatedDoorNumber)
            {
                return i;
            }
        }
        return -1;
    }
}