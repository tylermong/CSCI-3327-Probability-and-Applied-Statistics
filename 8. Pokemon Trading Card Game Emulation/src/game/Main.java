package game;

/**
 * This class runs the Pokémon Trading Card Game emulation. It initializes the game and starts the game loop/
 * 
 * @author  Tyler Mong
 * @version 1.0
 */
public class Main
{
    /**
     * Sets up the game and starts the game loop.
     * 
     * @param args  Command line arguments.
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.setupGame();
        game.startGame();
    }
}