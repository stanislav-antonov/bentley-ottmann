package bentleyottmann;

import com.sun.istack.internal.NotNull;

public interface OnIntersectionListener {
    void onIntersection(@NotNull Segment s1, @NotNull Segment s2, @NotNull Point p);
}
