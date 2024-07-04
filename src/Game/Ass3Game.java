//209852706 Maya Diamant
package Game;


/**
 * The Game.Ass3Game class serves as the entry point for the game application.
 * It initializes and runs the game.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Ass3Game {

    /**
     * The main method where the application starts.
     * It creates an instance of the Game.GameEnvironment.Game.Game class, initializes it, and runs the game.
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create an instance of the Game.GameEnvironment.Game.Game class
        Game game = new Game();

        // Initialize the game
        game.initialize();

        // Run the game
        game.run();
    }
}
