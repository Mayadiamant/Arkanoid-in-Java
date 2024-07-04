//209852706 Maya Diamant
package Geometry;
import Game.Methods;

/**
 * Represents a point in 2D space.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a gui.Sprite.Geometry.Point with specified x and y coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point to which the distance is calculated.
     * @return the distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point to compare with.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return Methods.areDoublesEqual(this.x, other.x) && Methods.areDoublesEqual(this.y, other.y);
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return the x-coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return the y-coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Checks if this point is inside a given rectangle.
     *
     * @param rec the rectangle to check against.
     * @return true if this point is inside the rectangle, false otherwise.
     */
    public boolean pointInRectangle(Rectangle rec) {
        return Methods.areDoublesBigEqual(this.x, rec.getLeftDown().getX())
                && Methods.areDoublesSmallEqual(this.x, rec.getRightUp().getX())
                && Methods.areDoublesBigEqual(this.y, rec.getUpperLeft().getX())
                && Methods.areDoublesSmallEqual(this.y, rec.getLeftDown().getY());
    }
}
