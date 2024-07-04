//209852706 Maya Diamant
package Observers;

/**
 * HitNotifier is an interface for objects that can notify listeners about hit events.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public interface HitNotifier {
    /**
     * Adds a listener to be notified of hit events.
     *
     * @param hl the listener to add.
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a listener from the list of listeners to hit events.
     *
     * @param hl the listener to remove.
     */
    void removeHitListener(HitListener hl);
}
