package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

final class Event extends Point implements Comparable<Event> {
    enum Type {
        POINT_LEFT, POINT_RIGHT, INTERSECTION
    }

    final private static double EPSILON = 1E-12;

    @NotNull
    private Type mType;

    @NotNull
    final private List<SweepSegment> mSegments = new ArrayList<>();

    public Event(@NotNull Point p, @NotNull SweepSegment s1, @NotNull Type type) {
        super(p.x, p.y);
        mType = type;
        mSegments.add(s1);
    }

    public Event(@NotNull Point p, @NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        this(p, s1, Type.INTERSECTION);
        mSegments.add(s2);

        // Ensure s1 is always above s2
        if (!(mSegments.get(0).position() > mSegments.get(1).position())) {
            Collections.swap(mSegments, 0, 1);
        }
    }

    public void setType(@NotNull Type type) {
        mType = type;
    }

    @NotNull
    public Type type() {
        return mType;
    }

    @NotNull
    public SweepSegment firstSegment() {
        return mSegments.get(0);
    }

    @NotNull
    public SweepSegment secondSegment() {
        return mSegments.get(1);
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "[%s, %s]", x, y);
    }

    @Override
    public int compareTo(@NotNull Event p) {
        if (p.x < x || (Math.abs(p.x - x) < EPSILON && p.y < y)) {
            return 1;
        }

        if (p.x > x || (Math.abs(p.x - x) < EPSILON && p.y > y)) {
            return -1;
        }

        return 0;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (!(o instanceof Event)) {
            return false;
        }

        final Event e = (Event) o;
        return Math.abs(x - e.x) < EPSILON && Math.abs(y - e.y) < EPSILON;
    }

    @Override
    public int hashCode() {
        return 31 * Double.valueOf(x).hashCode() + Double.valueOf(y).hashCode();
    }
}
