package bentleyottmann;

import java.util.PriorityQueue;
import com.sun.istack.internal.NotNull;

class EventQueue extends PriorityQueue<Event> {
    @Override
    public boolean contains(@NotNull Object o) {
        boolean result = false;
        for (Event e : this) {
            if (e.nearlyEqual((Event) o)) {
                result = true;
                break;
            }
        }

        return result;
    }
}
