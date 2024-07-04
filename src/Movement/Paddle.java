//209852706 Maya Diamant
package Movement;

import Geometry.Point;
import Geometry.Rectangle;
import Sprites.Sprite;
import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import Game.Game;

import java.awt.Color;

import biuoop.GUI;
import Collidable.Collidable;

/**
 * The Movement.Paddle class represents the paddle in the game.
 * It is a sprite that can move left and right and can be collided with.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRectangle;
    private final Color color;
    private final double screenWidth;
    private final double paddleSpeed;

    /**
     * Constructs a Movement.Paddle object.
     *
     * @param gui             The GUI object to get the keyboard sensor.
     * @param paddleRectangle The rectangle representing the paddle's shape and position.
     * @param color           The color of the paddle.
     * @param screenWidth     The width of the screen to determine boundaries.
     * @param paddleSpeed     The speed at which the paddle moves.
     */
    public Paddle(GUI gui, Rectangle paddleRectangle, Color color, double screenWidth, double paddleSpeed) {
        this.keyboard = gui.getKeyboardSensor();
        this.paddleRectangle = paddleRectangle;
        this.color = color;
        this.screenWidth = screenWidth;
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Moves the paddle left by the paddleSpeed amount.
     * If the new position is outside the screen, it wraps around to the right side.
     */
    public void moveLeft() {
        double newX = this.getCollisionRectangle().getUpperLeft().getX() - this.paddleSpeed;
        if (newX < 0) {
            newX = screenWidth - paddleRectangle.getWidth();
        }
        paddleRectangle = new Rectangle(new Point(newX, paddleRectangle.getUpperLeft().getY()),
                paddleRectangle.getWidth(), paddleRectangle.getHeight());
    }

    /**
     * Moves the paddle right by the paddleSpeed amount.
     * If the new position is outside the screen, it wraps around to the left side.
     */
    public void moveRight() {
        double newX = paddleRectangle.getUpperLeft().getX() + paddleSpeed;
        if (newX > screenWidth - paddleRectangle.getWidth()) {
            newX = 0;
        }
        paddleRectangle = new Rectangle(new Point(newX, paddleRectangle.getUpperLeft().getY()),
                paddleRectangle.getWidth(), paddleRectangle.getHeight());
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) paddleRectangle.getUpperLeft().getX(), (int) paddleRectangle.getUpperLeft().getY(),
                (int) paddleRectangle.getWidth(), (int) paddleRectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) paddleRectangle.getUpperLeft().getX(), (int) paddleRectangle.getUpperLeft().getY(),
                (int) paddleRectangle.getWidth(), (int) paddleRectangle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleRectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double regionWidth = paddleRectangle.getWidth() / 5;
        double hitX = collisionPoint.getX() - paddleRectangle.getUpperLeft().getX();
        if (hitX < regionWidth) {
            return Velocity.fromAngleAndSpeed(210, currentVelocity.getSpeed());
        } else if (hitX < 2 * regionWidth) {
            return Velocity.fromAngleAndSpeed(240, currentVelocity.getSpeed());
        } else if (hitX < 3 * regionWidth) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (hitX < 4 * regionWidth) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        } else {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
    }

    @Override
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public String myName() {
        return "Movement.Paddle";
    }
}
