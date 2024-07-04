//209852706 Maya Diamant
package Movement;

import Collidable.Collidable;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import biuoop.DrawSurface;
import Game.GameEnvironment;
import Sprites.Sprite;
import Game.Game;
import Collidable.CollisionInfo;

import java.awt.Color;

import Game.Methods;

import java.util.Objects;

/**
 * The gui.Sprite.Movement.Ball class represents a ball with a center point, radius, velocity, color, and bounding area.
 * It provides methods to draw the ball, move it, and handle collisions with a rectangular area.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Ball implements Sprite {
    private final int radius;
    private Velocity velocity;
    private Point center;
    private Color color;
    private final GameEnvironment environment;

    /**
     * Constructs a ball with the specified center point, radius, color, and bounding area.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     *               //@param area the rectangular area in which the ball can move.
     * @param env    the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment env) {
        this.center = new Point(center.getX(), center.getY());
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = env;
    }

    /**
     * Constructs a ball with the specified coordinates for the center point, radius, color, and bounding area.
     *
     * @param x     the x-coordinate of the center point.
     * @param y     the y-coordinate of the center point.
     * @param r     the radius of the ball.
     * @param color the color of the ball.
     *              //@param area the rectangular area in which the ball can move.
     * @param env   the game environment
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment env) {
        int x1 = (int) x;
        int y1 = (int) y;
        this.center = new Point(x1, y1);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = env;
    }

    // Accessors

    /**
     * Gets the x-coordinate of the center point of the ball.
     *
     * @return the x-coordinate of the center point.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets the y-coordinate of the center point of the ball.
     *
     * @return the y-coordinate of the center point.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return radius;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Returns a new gui.Sprite.Geometry.Point object that represents the center of this object.
     * The returned gui.Sprite.Geometry.Point is a copy of the current center point to preserve encapsulation.
     *
     * @return A new gui.Sprite.Geometry.Point object representing the center of this object.
     */
    public Point getCenter() {
        return new Point(this.center.getX(), this.center.getY());
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillCircle(getX(), getY(), radius);
        d.setColor(Color.black);
        d.drawCircle(getX(), getY(), radius);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the Movement.Velocity object representing the new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in the x-direction per movement step.
     * @param dy the change in the y-direction per movement step.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets the current velocity of the ball.
     *
     * @return the current velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the center point of the object to the specified point.
     * The x and y coordinates of the specified point are copied to the center point.
     *
     * @param point The new center point of the object.
     */
    public void setCenter(Point point) {
        this.center.setX(point.getX());
        this.center.setY(point.getY());
    }

    /**
     * Moves the ball one step, checking for collisions with the bounding area.
     */
    public void moveOneStep() {
        Point nextCenter = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextCenter);
        CollisionInfo collisionInfo = this.environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = nextCenter;
        } else {
            Point collisionPoint = collisionInfo.collisionPoint();
            Collidable hitObject = collisionInfo.collisionObject();
            setCenter(new Line(this.center, collisionPoint).middle());
            this.velocity = hitObject.hit(this, collisionPoint, this.velocity);
            if (Objects.equals(collisionInfo.collisionObject().myName(), "Movement.Paddle")
                    && center.pointInRectangle(collisionInfo.collisionObject().getCollisionRectangle())) {
                setCenter(new Point(getCenter().getX(), getCenter().getY() - 10));
            }
        }
    }


    /**
     * Checks if the ball collides with any of the corners of the given rectangle.
     * If a collision is detected, the velocity of the ball is reversed.
     *
     * @param rec the rectangle to check for collisions with.
     * @return true if a collision is detected, false otherwise.
     */
    public boolean ballAndPoint(Rectangle rec) {
        Point[] corners = {rec.getUpperLeft(), rec.getRightUp(), rec.getLeftDown(), rec.getRightDown()};
        for (Point corner : corners) {
            if (Methods.areDoublesSmallEqual(this.center.distance(corner), radius)) {
                this.velocity = new Velocity(-this.velocity.getDx(), -this.velocity.getDy());
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the ball collides with any of the edges of the given rectangle.
     * If a collision is detected, the velocity of the ball is adjusted accordingly.
     *
     * @param rectangle the rectangle to check for collisions with.
     * @return true if a collision is detected, false otherwise.
     */
    public boolean findLine(Rectangle rectangle) {
        if (!ballAndPoint(rectangle)) {
            boolean flag = false;
            Point leftBall = new Point(this.center.getX() - radius, this.center.getY());
            Line left = new Line(this.center, leftBall);
            Point rightBall = new Point(this.center.getX() + radius, this.center.getY());
            Line right = new Line(this.center, rightBall);
            Point topBall = new Point(this.center.getX(), this.center.getY() + radius);
            Line up = new Line(this.center, topBall);
            Point bottomBall = new Point(this.center.getX(), this.center.getY() - radius);
            Line down = new Line(this.center, bottomBall);

            // Check for collisions with the left edge
            if (right.isIntersecting(rectangle.getLeft())) {
                this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
                flag = true;
            }

            // Check for collisions with the right edge
            if (left.isIntersecting(rectangle.getRight())) {
                this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
                flag = true;
            }

            // Check for collisions with the bottom edge
            if (up.isIntersecting(rectangle.getDown())) {
                this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
                flag = true;
            }

            // Check for collisions with the top edge
            if (down.isIntersecting(rectangle.getTop())) {
                this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
                flag = true;
            }

            return flag;
        }
        return false;
    }

    /**
     * Adjusts the velocity of the ball based on its position relative to the given rectangle.
     * If the ball is found to be colliding with the rectangle, its velocity is adjusted accordingly.
     *
     * @param rectangle the rectangle to check for collisions with.
     */
    public void centerPoint(Rectangle rectangle) {
        if (!findLine(rectangle)) {
            Point newDX = new Point(this.center.getX() + this.velocity.getDx(), this.center.getY());
            Point newDY = new Point(this.center.getX(), this.center.getY() + this.velocity.getDy());
            if (newDX.pointInRectangle(rectangle)) {
                this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
            }
            if (newDY.pointInRectangle(rectangle)) {
                this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
            }
        }
    }

    /**
     * Ensures the ball's center point stays inside the given rectangle.
     * If the ball's center point is found to be outside the rectangle, its velocity is adjusted accordingly.
     *
     * @param rectangle the rectangle to check for boundary violations.
     */

    public void centralPointInside(Rectangle rectangle) {
        Point newDX = new Point(this.center.getX() + this.velocity.getDx(), this.center.getY());
        Point newDY = new Point(this.center.getX(), this.center.getY() + this.velocity.getDy());
        if (!newDX.pointInRectangle(rectangle)) {
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (!newDY.pointInRectangle(rectangle)) {
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }

    /**
     * Sets the color of this ball.
     *
     * @param color the new color to be set.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Removes this ball from the specified game.
     *
     * @param game the game from which this object is to be removed.
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}