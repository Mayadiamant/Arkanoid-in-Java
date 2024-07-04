//209852706 Maya Diamant
package Game;

import Collidable.Collidable;
import Geometry.Point;
import Movement.Ball;
import Movement.Paddle;
import Movement.Velocity;
import Observers.BallRemover;
import Observers.BlockRemover;
import Observers.Counter;
import Observers.ScoreTrackingListener;
import Sprites.ScoreIndicator;
import Sprites.Sprite;
import Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import Collidable.Block;

/**
 * The Game.GameEnvironment.Game.Game class represents the main game environment.
 * It manages the sprites and collidables, initializes the game objects, and runs the game loop.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Sleeper sleeper;
    private final Counter blocksCounter;
    private final Counter ballsCounter;
    private final Counter score;

    /**
     * Constructs a new Game.GameEnvironment.Game.Game object and initializes the sprite collection,
     * game environment, GUI, and sleeper.
     */
    public Game() {
        this.sprites = new SpriteCollection(new ArrayList<>());
        this.environment = new GameEnvironment(new ArrayList<>());
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sleeper = new Sleeper();
        this.blocksCounter = new Counter(0);
        this.ballsCounter = new Counter(0);
        this.score = new Counter(0);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s The sprite object to be added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes a new game: creates the blocks, ball, paddle, and edges,
     * and adds them to the game.
     */
    public void initialize() {
        Random rnd = new Random();
        int rectsAmount = 12;
        int linesAmount = 6;
        Color[] colors = new Color[8];
        colors[0] = new Color(255, 179, 186); // Light pink
        colors[1] = new Color(255, 223, 186); // Peach
        colors[2] = new Color(255, 255, 186); // Light yellow
        colors[3] = new Color(186, 255, 201); // Mint green
        colors[4] = new Color(186, 225, 255); // Light blue
        colors[5] = new Color(204, 204, 255); // Lavender
        colors[6] = new Color(255, 204, 229); // Light magenta
        // Create and add the screen background block
        Block screen = new Block(new Point(0, 0), 800, 600, colors[6]);
        addSprite(screen);
        BlockRemover remove = new BlockRemover(this, blocksCounter);
        BallRemover ballsRemove = new BallRemover(ballsCounter, this);
        ScoreTrackingListener scores = new ScoreTrackingListener(score);
        ScoreIndicator display = new ScoreIndicator(score);
        display.addToGame(this);
        // Define colors for the blocks

        // Create and add blocks to the game
        for (int i = 0; i < linesAmount; i++) {
            for (int j = rectsAmount - i; j > 0; j--) {
                Block block = new Block(new Point(790 - (40 * j), 100 + i * 20), 40, 20, colors[i]);
                block.addToGame(this);
                block.addHitListener(remove);
                block.addHitListener(scores);
                blocksCounter.increase(1);
            }
        }

        // Create and add the paddle to the game
        Rectangle rectangle = new Rectangle(new Point(0, 570), 100, 20);
        Paddle paddle = new Paddle(gui, rectangle, Color.white, 800, 10);
        paddle.addToGame(this);

        // Create and add the edges to the game
        Block topEdge = new Block(new Point(0, 0), 800, 10, Color.lightGray);
        Block leftEdge = new Block(new Point(0, 0), 10, 600, Color.lightGray);
        Block rightEdge = new Block(new Point(790, 0), 10, 600, Color.lightGray);
        Block bottomEdge = new Block(new Point(0, 600), 800, 10, Color.lightGray);
        bottomEdge.addToGame(this);
        bottomEdge.addHitListener(ballsRemove);
        topEdge.addToGame(this);
        leftEdge.addToGame(this);
        rightEdge.addToGame(this);

        // Create and add balls to the game
        Ball[] balls = new Ball[3];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Ball(new Point(500, 500), 5, colors[6], environment);
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt(360), 5));
            balls[i].addToGame(this);
            ballsCounter.increase(1);
        }
    }

    /**
     * Runs the game -- starts the animation loop.
     */
    public void run() {
        // Set frames per second and milliseconds per frame
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        // Main game loop
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            // Draw all sprites on the drawing surface
            this.sprites.drawAllOn(d);
            gui.show(d);

            // Notify all sprites that time has passed
            this.sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
            if (blocksCounter.getValue() == 0) {
                score.increase(100);
                gui.close();
                return;
            }
            if (ballsCounter.getValue() == 0) {
                gui.close();
                return;
            }
        }
    }

    /**
     * Removes a collidable object from the environment.
     *
     * @param c the collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the list of sprites.
     *
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
}
