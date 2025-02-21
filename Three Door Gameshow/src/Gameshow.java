public class Gameshow
{
    public int playGameStickWithChoice(int numberOfRounds)
    {
        return playGame(numberOfRounds, false);
    }

    public int playGameSwitchChoice(int numberOfRounds)
    {
        return playGame(numberOfRounds, true);
    }

    private int playGame(int numberOfRounds, boolean switchChoice)
    {
        int winCounter = 0;

        for (int roundNumber = 0; roundNumber < numberOfRounds; roundNumber++)
        {
            // set up the doors -- put goats behind them all
            Door[] doors = new Door[3];
            for (int i = 0; i < doors.length; i++)
            {
                doors[i] = new Door();
                doors[i].contents = "goat";
            }

            // then pick the winning door and put a goat behind it
            int winningDoorNumber = (int) (Math.random() * 3);
            doors[winningDoorNumber].contents = "car";

            // have the "player" select a random door
            int selectedDoorNumber = (int) (Math.random() * 3);

            // eliminate a door
            int eliminatedDoorNumber = eliminateGoatDoor(doors, selectedDoorNumber, winningDoorNumber);

            // after eliminating a door, if they want to switch, then switch
            if (switchChoice)
            {
                selectedDoorNumber = switchDoor(doors, selectedDoorNumber, eliminatedDoorNumber);
            }

            // now check if they selected the winning door
            if (doors[selectedDoorNumber].contents.equals("car"))
            {
                winCounter++;
            }
        }

        return winCounter;
    }

    private int eliminateGoatDoor(Door[] doors, int selectedDoorNumber, int winningDoorNumber)
    {
        for (int i = 0; i < doors.length; i++)
        {
            if (i != selectedDoorNumber && doors[i].contents.equals("goat"))
            {
                doors[i].contents = "removed";
                return i;
            }
        }
        return -1;
    }

    private int switchDoor(Door[] doors, int selectedDoorNumber, int eliminatedDoorNumber)
    {
        for (int i = 0; i < doors.length; i++)
        {
            if (i != selectedDoorNumber && i != eliminatedDoorNumber)
            {
                return i;
            }
        }
        return -1;
    }
}