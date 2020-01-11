package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

final class Event implements Comparable<Event> {
    enum Type {
        POINT_LEFT, POINT_RIGHT, INTERSECTION
    }

    final private static double EPSILON = 1E-12;

    @NotNull
    private Type mType;

    @NotNull
    final private IPoint mPoint;

    @NotNull
    final private List<SweepSegment> mSegments = new ArrayList<>();

    Event(@NotNull IPoint p, @NotNull SweepSegment s1, @NotNull Type type) {
        mPoint = p;
        mType = type;
        mSegments.add(s1);
    }

    Event(@NotNull IPoint p, @NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        this(p, s1, Type.INTERSECTION);
        mSegments.add(s2);

        // Ensure s1 is always above s2
        if (!(mSegments.get(0).position() > mSegments.get(1).position())) {
            Collections.swap(mSegments, 0, 1);
        }
    }

    void setType(@NotNull Type type) {
        mType = type;
    }

    @NotNull
    Type type() {
        return mType;
    }

    @NotNull
    IPoint point() {
        return mPoint;
    }

    @NotNull
    SweepSegment firstSegment() {
        return mSegments.get(0);
    }

    @NotNull
    SweepSegment secondSegment() {
        return mSegments.get(1);
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "[%s, %s]", point().x(), point().y());
    }

    @Override
    public int compareTo(@NotNull Event e) {
        if (e.point().x() < point().x() ||
                (Math.abs(e.point().x() - point().x()) < EPSILON && e.point().y() < point().y())) {
            return 1;
        }

        if (e.point().x() > point().x() ||
                (Math.abs(e.point().x() - point().x()) < EPSILON && e.point().y() > point().y())) {
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
        return Math.abs(point().x() - e.point().x()) < EPSILON && Math.abs(point().y() - e.point().y()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return 31 * Double.valueOf(point().x()).hashCode() + Double.valueOf(point().y()).hashCode();
    }
}
