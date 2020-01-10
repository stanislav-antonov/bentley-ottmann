package bentleyottmann;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Event extends Point {
    enum Type {
        POINT_LEFT, POINT_RIGHT, INTERSECTION
    }

    final private Type mType;

    @NotNull
    final private List<SweepSegment> mSegments = new ArrayList<>();

    public Event(@NotNull Point p, @NotNull SweepSegment s, Type type) {
        super(p.x, p.y);
        mType = type;
        mSegments.add(s);
    }

    public Event(@NotNull Point p, @NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        this(p, s1, Type.INTERSECTION);
        mSegments.add(s2);
    }

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

    @NotNull
    public List<SweepSegment> segments() {
        return Collections.unmodifiableList(mSegments);
    }
}
