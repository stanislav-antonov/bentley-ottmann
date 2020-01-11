package bentleyottmann;

import com.sun.istack.internal.NotNull;

public interface OnIntersectionListener {
    void onIntersection(@NotNull ISegment s1, @NotNull ISegment s2, @NotNull IPoint p);
}
