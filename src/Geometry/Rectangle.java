//209852706 Maya Diamant
package Geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * The Geometry.Rectangle class represents a rectangle shape in a 2D plane.
 * It is defined by its lower-left corner, width, height, and color.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructs a Geometry.Rectangle with the specified lower-left corner, width, height, and color.
     *
     * @param upperLeft the lower-left corner of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the lower-left corner of the rectangle.
     *
     * @return the lower-left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * Returns the upper-left corner of the rectangle.
     *
     * @return the upper-left corner of the rectangle.
     */
    public Point getLeftDown() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

    /**
     * Returns the lower-right corner of the rectangle.
     *
     * @return the lower-right corner of the rectangle.
     */
    public Point getRightDown() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * Returns the upper-right corner of the rectangle.
     *
     * @return the upper-right corner of the rectangle.
     */
    public Point getRightUp() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * Returns the top side of the rectangle as a gui.Sprite.Geometry.Line.
     *
     * @return the top side of the rectangle.
     */
    public Line getTop() {
        return new Line(getUpperLeft(), getRightUp());
    }

    /**
     * Returns the bottom side of the rectangle as a gui.Sprite.Geometry.Line.
     *
     * @return the bottom side of the rectangle.
     */
    public Line getDown() {
        return new Line(getLeftDown(), getRightDown());
    }

    /**
     * Returns the left side of the rectangle as a gui.Sprite.Geometry.Line.
     *
     * @return the left side of the rectangle.
     */
    public Line getLeft() {
        return new Line(getLeftDown(), getUpperLeft());
    }

    /**
     * Returns the right side of the rectangle as a gui.Sprite.Geometry.Line.
     *
     * @return the right side of the rectangle.
     */
    public Line getRight() {
        return new Line(getRightUp(), getRightDown());
    }

    /**
     * Returns a list of intersection points between the given line and the edges of this object.
     * The list will contain up to four points, corresponding to intersections with the right, left,
     * top, and bottom edges of the object's rectangle.
     *
     * @param line The line to check for intersections with the edges of the object's rectangle.
     * @return A list of intersection points. If there is no intersection with an edge, the corresponding
     * entry in the list will be null.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        intersectionPoints.add(0, line.intersectionWith(getRight()));
        intersectionPoints.add(1, line.intersectionWith(getLeft()));
        intersectionPoints.add(2, line.intersectionWith(getTop()));
        intersectionPoints.add(3, line.intersectionWith(getDown()));
        return intersectionPoints;
    }
}
