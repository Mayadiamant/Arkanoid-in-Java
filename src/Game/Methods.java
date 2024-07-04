//209852706 Maya Diamant
package Game;
import java.awt.Color;
import java.util.Random;

/**
 * The Game.Methods class provides utility functions for comparing double values
 * with a specified precision threshold and for generating random colors.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Methods {
    // The precision threshold for comparing double values
    private static final double THRESHOLD = 1e-5;

    /**
     * Compares two double values for equality within a specified threshold.
     *
     * @param d1 the first double value.
     * @param d2 the second double value.
     * @return true if the absolute difference between d1 and d2 is less than the threshold, false otherwise.
     */
    public static boolean areDoublesEqual(double d1, double d2) {
        return Math.abs(d1 - d2) < THRESHOLD;
    }

    /**
     * Checks if the first double value is greater than or equal to the second double value
     * within a specified threshold.
     *
     * @param d1 the first double value.
     * @param d2 the second double value.
     * @return true if d1 is greater than or approximately equal to d2, false otherwise.
     */
    public static boolean areDoublesBigEqual(double d1, double d2) {
        return d1 > d2 || areDoublesEqual(d1, d2);
    }

    /**
     * Checks if the first double value is less than or equal to the second double value
     * within a specified threshold.
     *
     * @param d1 the first double value.
     * @param d2 the second double value.
     * @return true if d1 is less than or approximately equal to d2, false otherwise.
     */
    public static boolean areDoublesSmallEqual(double d1, double d2) {
        return d1 < d2 || areDoublesEqual(d1, d2);
    }

    /**
     * Generates a random color.
     *
     * @return a Color object with random red, green, and blue values.
     */
    public static Color getRandomColor() {
        Random rand = new Random();
        int red = rand.nextInt(256);
        int green = rand.nextInt(256);
        int blue = rand.nextInt(256);
        return new Color(red, green, blue);
    }
}
