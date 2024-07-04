//209852706 Maya Diamant
package Observers;

/**
 * Counter is a simple class that maintains a count and provides methods to increase,
 * decrease, and retrieve the current count.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Counter {
    private int value;

    /**
     * Constructs a Counter with the specified initial value.
     *
     * @param value the initial value of the counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Adds the specified number to the current count.
     *
     * @param number the number to add to the current count.
     */
    public void increase(int number) {
        value += number;
    }

    /**
     * Subtracts the specified number from the current count.
     *
     * @param number the number to subtract from the current count.
     */
    public void decrease(int number) {
        value -= number;
    }

    /**
     * Returns the current count.
     *
     * @return the current count.
     */
    public int getValue() {
        return this.value;
    }
}
