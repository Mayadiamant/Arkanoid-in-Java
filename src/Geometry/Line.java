//209852706 Maya Diamant
package Geometry;

import Game.Methods;

import java.util.List;

/**
 * The gui.Sprite.Geometry.Line class represents a line segment defined by two points.
 * It provides methods to calculate properties such as length, middle point,
 * slope, and intersection with other lines.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructs a line segment with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * Constructs a line segment with the specified coordinates.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line segment.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line segment.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        if (this.end.equals(this.start)) {
            return new Point(this.start.getX(), this.start.getY());
        }
        double x = (this.end.getX() + this.start.getX()) / 2.0;
        double y = (this.end.getY() + this.start.getY()) / 2.0;
        return new Point(x, y);
    }

    /**
     * Returns the start point of the line segment.
     *
     * @return the start point of the line
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * Returns the end point of the line segment.
     *
     * @return the end point of the line
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Returns the slope of the line segment.
     *
     * @return the slope of the line
     */
    public double getSlope() {
        if (Methods.areDoublesEqual(this.end.getX(), this.start.getX())) {
            return Double.POSITIVE_INFINITY;
        }
        double slopeY = this.end.getY() - this.start.getY();
        double slopeX = this.end.getX() - this.start.getX();
        return slopeY / slopeX;
    }

    /**
     * Checks if a point is inside the bounding box defined by the line and another line.
     *
     * @param p     the point to check
     * @param other the other line
     * @return true if the point is inside the bounding box, false otherwise
     */
    private boolean isPointInside(Point p, Line other) {
        if ((p.equals(this.start) || p.equals(this.end)) && (p.equals(other.start) || p.equals(other.end))) {
            return true;
        }
        double maxX1 = Math.max(this.start.getX(), this.end.getX());
        double minX1 = Math.min(this.end.getX(), this.start.getX());
        double maxX2 = Math.max(other.start().getX(), other.end().getX());
        double minX2 = Math.min(other.start().getX(), other.end().getX());
        double xPoint = p.getX();
        return Methods.areDoublesBigEqual(xPoint, minX1) && Methods.areDoublesSmallEqual(xPoint, maxX1)
                && Methods.areDoublesBigEqual(xPoint, minX2) && Methods.areDoublesSmallEqual(xPoint, maxX2);
    }

    /**
     * Checks if a point is inside the bounding box defined by the line and another line, considering vertical lines.
     *
     * @param p     the point to check
     * @param other the other line
     * @return true if the point is inside the bounding box, false otherwise
     */
    private boolean isPointInfinity(Point p, Line other) {
        double maxX1 = Math.max(this.start.getX(), this.end.getX());
        double maxY1 = Math.max(this.start.getY(), this.end.getY());
        double minX1 = Math.min(this.end.getX(), this.start.getX());
        double minY1 = Math.min(this.end.getY(), this.start.getY());
        double maxX2 = Math.max(other.start.getX(), other.end.getX());
        double maxY2 = Math.max(other.start.getY(), other.end.getY());
        double minX2 = Math.min(other.end.getX(), other.start.getX());
        double minY2 = Math.min(other.end.getY(), other.start.getY());
        double xPoint = p.getX();
        double yPoint = p.getY();
        return Methods.areDoublesBigEqual(xPoint, minX1) && Methods.areDoublesSmallEqual(xPoint, maxX1)
                && Methods.areDoublesBigEqual(xPoint, minX2) && Methods.areDoublesSmallEqual(xPoint, maxX2)
                && Methods.areDoublesBigEqual(yPoint, minY1) && Methods.areDoublesSmallEqual(yPoint, maxY1)
                && Methods.areDoublesBigEqual(yPoint, minY2) && Methods.areDoublesSmallEqual(yPoint, maxY2);
    }

    /**
     * Checks if the bounding boxes of two lines intersect, considering vertical lines.
     *
     * @param other the other line
     * @return true if the bounding boxes intersect, false otherwise
     */
    private boolean isINFINITY(Line other) {
        if (this.equals(other)) {
            return true;
        }
        double maxX1 = Math.max(this.start.getX(), this.end.getX());
        double maxY1 = Math.max(this.start.getY(), this.end.getY());
        double minX1 = Math.min(this.end.getX(), this.start.getX());
        double minY1 = Math.min(this.end.getY(), this.start.getY());
        double maxX2 = Math.max(other.start.getX(), other.end.getX());
        double maxY2 = Math.max(other.start.getY(), other.end.getY());
        double minX2 = Math.min(other.end.getX(), other.start.getX());
        double minY2 = Math.min(other.end.getY(), other.start.getY());
        if (Methods.areDoublesSmallEqual(maxX1, maxX2) && Methods.areDoublesBigEqual(maxX1, minX2)
                && Methods.areDoublesBigEqual(maxY1, minY2) && Methods.areDoublesSmallEqual(maxY1, maxY2)) {
            return true;
        }
        if (Methods.areDoublesSmallEqual(minX1, maxX2) && Methods.areDoublesBigEqual(minX1, minX2)
                && Methods.areDoublesBigEqual(minY1, minY2) && Methods.areDoublesSmallEqual(minY1, maxY2)) {
            return true;
        }
        if (Methods.areDoublesSmallEqual(maxX2, maxX1) && Methods.areDoublesBigEqual(maxX2, minX1)
                && Methods.areDoublesBigEqual(maxY2, minY1) && Methods.areDoublesSmallEqual(maxY2, maxY1)) {
            return true;
        }
        if (Methods.areDoublesSmallEqual(minX2, maxX1) && Methods.areDoublesBigEqual(minX2, minX1)
                && Methods.areDoublesBigEqual(minY2, minY1) && Methods.areDoublesSmallEqual(minY2, maxY1)) {
            return true;
        }
        if (Methods.areDoublesSmallEqual(minX2, maxX1) && Methods.areDoublesBigEqual(minX2, minX1)
                && Methods.areDoublesBigEqual(minY1, minY2) && Methods.areDoublesSmallEqual(minY1, maxY2)) {
            return true;
        }
        if (Methods.areDoublesSmallEqual(maxX2, maxX1) && Methods.areDoublesBigEqual(maxX2, minX1)
                && Methods.areDoublesBigEqual(maxY1, minY2) && Methods.areDoublesSmallEqual(maxY1, maxY2)) {
            return true;
        }
        if (Methods.areDoublesSmallEqual(maxX1, maxX2) && Methods.areDoublesBigEqual(maxX1, minX2)
                && Methods.areDoublesBigEqual(maxY2, minY1) && Methods.areDoublesSmallEqual(maxY2, maxY1)) {
            return true;
        }
        return Methods.areDoublesSmallEqual(minX1, maxX2) && Methods.areDoublesBigEqual(minX1, minX2)
                && Methods.areDoublesBigEqual(minY2, minY1) && Methods.areDoublesSmallEqual(minY2, maxY1);
    }

    /**
     * Checks if the line segment is a single point.
     *
     * @return true if the line segment is a single point, false otherwise
     */
    private boolean lineIsPoint() {
        return this.start.equals(this.end);
    }

    /**
     * Checks if a point is on the line segment.
     *
     * @param p the point to check
     * @return true if the point is on the line, false otherwise
     */
    public boolean pointOnLine(Point p) {
        double maxX1 = Math.max(this.start.getX(), this.end.getX());
        double minX1 = Math.min(this.end.getX(), this.start.getX());
        double maxY1 = Math.max(this.end.getY(), this.start.getY());
        double minY1 = Math.min(this.end.getY(), this.start.getY());
        double xPoint = p.getX();
        double yPoint = p.getY();
        double slope = this.getSlope();
        if (slope == Double.POSITIVE_INFINITY) {
            if (Methods.areDoublesEqual(xPoint, this.end().getX())) {
                return Methods.areDoublesSmallEqual(yPoint, maxY1)
                        && Methods.areDoublesBigEqual(yPoint, minY1);
            }
            return false;
        }
        double b1 = this.start.getY() - slope * this.start.getX();
        double checkY = slope * xPoint + b1;
        if (Methods.areDoublesBigEqual(xPoint, minX1) && Methods.areDoublesSmallEqual(xPoint, maxX1)
                && Methods.areDoublesSmallEqual(yPoint, maxY1) && Methods.areDoublesBigEqual(yPoint, minY1)) {
            return Methods.areDoublesEqual(checkY, yPoint);
        }
        return false;
    }

    /**
     * Returns the common point if the lines have the same slope and overlap, null otherwise.
     *
     * @param other the other line
     * @return the common point if the lines overlap, null otherwise
     */
    private Point sameSlope(Line other) {
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return new Point(this.start.getX(), this.start.getY());
        }
        if (this.end.equals(other.end) || this.end.equals(other.start)) {
            return new Point(this.end.getX(), this.end.getY());
        }
        return null;
    }

    /**
     * Returns the intersection point if the lines intersect, null otherwise.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point interPoint(Line other) {
        if (this.equals(other)) {
            return null;
        }
        double slope1 = this.getSlope();
        double slope2 = other.getSlope();
        double b1 = this.start.getY() - slope1 * this.start.getX();
        double b2 = other.start.getY() - slope2 * other.start.getX();
        double xIntersection;
        double yIntersection;
        xIntersection = (b2 - b1) / (slope1 - slope2);
        yIntersection = slope1 * xIntersection + b1;
        Point inter = new Point(xIntersection, yIntersection);
        if (this.isPointInside(inter, other)) {
            return inter;
        }
        return null;
    }

    /**
     * Returns the intersection point if the lines intersect and one is vertical, null otherwise.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, null otherwise
     */
    private Point interInfinity(Line other) {
        double slope2 = other.getSlope();
        double b2 = other.start.getY() - slope2 * other.start.getX();
        double yInter = slope2 * this.start.getX() + b2;
        Point inter = new Point(this.start.getX(), yInter);
        if (this.isPointInfinity(inter, other)) {
            return inter;
        }
        return null;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        if (other.lineIsPoint() && (!this.lineIsPoint())) {
            return this.pointOnLine(other.start);
        }
        if (this.lineIsPoint() && (!other.lineIsPoint())) {
            return other.pointOnLine(this.start);
        }
        if (this.lineIsPoint() && other.lineIsPoint()) {
            return this.start.equals(other.start);
        }
        double slope1 = this.getSlope();
        double slope2 = other.getSlope();
        double b1;
        double b2;
        if (slope1 == Double.POSITIVE_INFINITY && slope2 == Double.POSITIVE_INFINITY) {
            return this.isINFINITY(other);
        }
        b1 = this.start.getY() - slope1 * this.start.getX();
        b2 = other.start.getY() - slope2 * other.start.getX();
        double xIntersection;
        double yIntersection;
        if (Methods.areDoublesEqual(slope1, slope2) && !Methods.areDoublesEqual(b1, b2)) {
            return false;
        }
        if (Methods.areDoublesEqual(slope1, slope2)) {
            return this.isINFINITY(other);
        }
        if (slope1 == Double.POSITIVE_INFINITY) {
            if (this.interInfinity(other) != null) {
                return true;
            }
        }
        if (slope2 == Double.POSITIVE_INFINITY) {
            if (other.interInfinity(this) != null) {
                return true;
            }
        }
        if (slope1 != slope2) {
            xIntersection = (b2 - b1) / (slope1 - slope2);
            yIntersection = slope1 * xIntersection + b1;
            Point inter = new Point(xIntersection, yIntersection);
            return this.isPointInside(inter, other);
        }
        return false;
    }

    /**
     * Returns true if the line intersects with both other lines, false otherwise.
     *
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if the line intersects with both other lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        boolean isIntersecting1 = this.isIntersecting(other1);
        boolean isIntersecting2 = this.isIntersecting(other2);
        return isIntersecting1 && isIntersecting2;
    }

    /**
     * Returns true if the three lines form a triangle, false otherwise.
     *
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if the three lines form a triangle, false otherwise
     */
    public boolean isTriangle(Line other1, Line other2) {
        boolean triangle = isIntersecting(other1, other2) && other1.isIntersecting(other2);
        Point intersection1 = this.intersectionWith(other1);
        Point intersection2 = this.intersectionWith(other2);
        Point intersection3 = other1.intersectionWith(other2);
        if (intersection1 != null && intersection2 != null && intersection3 != null) {
            if (intersection1.equals(intersection2) || intersection1.equals(intersection3)
                    || intersection2.equals(intersection3)) {
                return false;
            }
        }
        return triangle;
    }

    /**
     * Returns the intersection point if the lines intersect, null otherwise.
     *
     * @param other the other line
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        double slope1 = this.getSlope();
        double slope2 = other.getSlope();
        double b1;
        double b2;
        if ((slope1 == Double.POSITIVE_INFINITY) && (slope2 == Double.POSITIVE_INFINITY)) {
            if (this.isINFINITY(other)) {
                return this.sameSlope(other);
            }
        }
        b1 = this.start.getY() - slope1 * this.start.getX();
        b2 = other.start.getY() - slope2 * other.start.getX();
        if (Methods.areDoublesEqual(slope1, slope2) && Methods.areDoublesEqual(b1, b2)) {
            if (this.isINFINITY(other)) {
                return this.sameSlope(other);
            }
        }
        if (Methods.areDoublesEqual(slope1, slope2) && !Methods.areDoublesEqual(b1, b2)) {
            return null;
        }
        if (slope1 == Double.POSITIVE_INFINITY) {
            return this.interInfinity(other);
        }
        if (slope2 == Double.POSITIVE_INFINITY) {
            return other.interInfinity(this);
        }
        if (slope1 != slope2) {
            return this.interPoint(other);
        }
        if (this.isINFINITY(other)) {
            return this.sameSlope(other);
        }
        return null;
    }

    /**
     * Checks if the two lines are equal.
     *
     * @param other the other line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                || this.end.equals(other.start) && this.start.equals(other.end);
    }

    /**
     * Finds the closest intersection point between the start of this line and a given rectangle.
     * If no intersection is found, returns null.
     *
     * @param rect The rectangle to check for intersections with this line.
     * @return The closest intersection point to the start of the line, or null if no intersection is found.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // If this line does not intersect with the rectangle, return null.
        // Otherwise, return the closest intersection point to the
        // start of the line.
        // Get the list of intersection points between this line and the rectangle
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        double distance = Double.POSITIVE_INFINITY;
        Point closestPoint = null;
        // Iterate through the intersection points to find the closest one to the start of the line
        for (Point point : intersectionPoints) {
            if (point != null) {
                // If the current intersection point is closer than the previously found,
                // closest point, update the closest point
                if (point.distance(this.start) < distance) {
                    distance = point.distance(this.start);
                    closestPoint = point;
                }
            }
        }

        // Return the closest intersection point, or null if no intersections were found
        return closestPoint;
    }

}
