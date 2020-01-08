package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Event extends Point {
    public final static int POINT_LEFT = 1;
    public final static int POINT_RIGHT = 2;
    public final static int POINT_INTERSECT = 3;

    final private int mPointType;
    final private double mPriority;

    @NotNull
    final private List<SweepSegment> mSegments = new ArrayList<>();

    public Event(@NotNull Point p, @NotNull SweepSegment s, int pointType) {
        super(p.x, p.y);
        mPointType = pointType;
        mPriority = x;
        mSegments.add(s);
    }

    public Event(@NotNull Point p, @NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        this(p, s1, POINT_INTERSECT);
        mSegments.add(s2);
    }

    public int pointType() {
        return mPointType;
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

    public double priority() {
        return mPriority;
    }


/*
    @Override
    public boolean equals(@Nullable Object o) {
        if (!(o instanceof Event)) {
            return false;
        }

        final Event e = (Event) o;

        boolean equalSegments;
        if (e.segments().size() == segments().size()) {
            if (e.segments().size() == 2) {
                equalSegments = (e.firstSegment().equals(firstSegment()) && e.secondSegment().equals(secondSegment()));
            } else {
                equalSegments = e.firstSegment().equals(firstSegment());
            }
        } else {
            equalSegments = false;
        }

        // return super.equals(e) && equalSegments && e.pointType() == pointType();
        return equalSegments;
    }
*/
}