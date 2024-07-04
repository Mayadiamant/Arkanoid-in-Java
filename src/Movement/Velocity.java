//209852706 Maya Diamant
package Movement;


import Geometry.Point;

/**
 * The Movement.Velocity class represents a velocity in a 2D plane.
 * It specifies the change in position on the `x` and the `y` axes.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructs a Movement.Velocity with the specified change in position on the x and y axes.
     *
     * @param dx the change in position on the x-axis.
     * @param dy the change in position on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a new Movement.Velocity that is a copy of the specified Movement.Velocity.
     *
     * @param v the Movement.Velocity to copy.
     */
    public Velocity(Velocity v) {
        this.dx = v.dx;
        this.dy = v.dy;
    }

    /**
     * Applies this velocity to a given point and returns a new point
     * with the updated position.
     *
     * @param p the point to which the velocity is applied.
     * @return a new gui.Sprite.Geometry.Point with the position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns the change in position on the x-axis.
     *
     * @return the change in position on the x-axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position on the y-axis.
     *
     * @return the change in position on the y-axis.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Creates a Movement.Velocity from an angle and speed.
     *
     * @param angle the angle in degrees.
     * @param speed the speed.
     * @return a new Movement.Velocity with the specified angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * Calculates and returns the speed of the object based on its velocity components.
     * The speed is computed as the square root of the sum of the squares of the velocity components (dx and dy).
     *
     * @return The speed of the object.
     */
    public double getSpeed() {
        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
