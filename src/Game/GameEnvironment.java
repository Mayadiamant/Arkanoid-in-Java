//209852706 Maya Diamant
package Game;

import java.util.List;

import Collidable.Collidable;
import Collidable.CollisionInfo;
import Geometry.Line;
import Geometry.Rectangle;
import Geometry.Point;

/**
 * The Game.GameEnvironment class represents the environment in which the game objects interact.
 * It manages a collection of collidable objects.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Constructs a Game.GameEnvironment with the specified list of collidable objects.
     *
     * @param collidableList The list of collidable objects.
     */
    public GameEnvironment(List<Collidable> collidableList) {
        this.collidables = collidableList;
    }

    /**
     * Adds the given collidable to the environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Removes a collidable object from the environment.
     *
     * @param c the collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Returns the list of collidable objects in the environment.
     *
     * @return The list of collidable objects.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Determines the closest collision that is going to occur for an object moving
     * along the specified trajectory. If no collision is detected, returns null.
     * Otherwise, returns the information about the closest collision.
     *
     * @param trajectory The trajectory of the moving object.
     * @return The information about the closest collision, or null if no collision occurs.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        // Iterate through all collidables to find the closest collision point
        for (Collidable collidable : collidables) {
            Rectangle rectangle = collidable.getCollisionRectangle();
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(rectangle);
            if (collisionPoint != null) {
                double distance = collisionPoint.distance(trajectory.start());
                // Update the closest collision if this one is closer
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPoint = collisionPoint;
                    closestCollidable = collidable;
                }
            }
        }
        // If no collidable was found, return null
        if (closestCollidable == null) {
            return null;
        }
        // Return the information about the closest collision
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
