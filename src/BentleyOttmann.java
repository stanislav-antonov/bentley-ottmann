import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

// See:
// http://geomalgorithms.com/a09-_intersect-3.html
// https://en.wikipedia.org/wiki/Bentley%E2%80%93Ottmann_algorithm
// https://www.hackerearth.com/practice/math/geometry/line-intersection-using-bentley-ottmann-algorithm/tutorial/
final public class BentleyOttmann {
    @NotNull
    private final Queue<Event> mEventQueue =
            new PriorityQueue<>(11, (o1, o2) -> Double.compare(o1.priority(), o2.priority()));

    @NotNull
    private final NavigableSet<SweepSegment> mSweepLineStatus =
            new TreeSet<>((o1, o2) -> Double.compare(o1.position(), o2.position()));

    @NotNull
    private final List<Point> mIntersections = new ArrayList<>();

    public void addSegments(@NotNull List<Segment> segments) {
        for (Segment segment : segments) {
            final SweepSegment s1 = new SweepSegment(segment);
            mEventQueue.add(new Event(s1.p1, s1, Event.POINT_LEFT));

            final SweepSegment s2 = new SweepSegment(segment);
            mEventQueue.add(new Event(s2.p2, s2, Event.POINT_RIGHT));
        }
    }

    @NotNull
    public List<Point> findIntersections() {
        while (!mEventQueue.isEmpty()) {
            final Event e = mEventQueue.poll();
            if (e == null) {
                continue;
            }

            SweepSegment s = e.firstSegment();
            updateSweepLineStatus(s, e.y);

            if (e.pointType() == Event.POINT_LEFT) {
                final SweepSegment r = above(s);
                final SweepSegment t = below(s);

                addEventIfIntersection(s, r, false);
                addEventIfIntersection(s, t, false);
            } else if (e.pointType() == Event.POINT_RIGHT) {
                final SweepSegment r = above(s);
                final SweepSegment t = below(s);

                removeSweepLineStatus(s);
                addEventIfIntersection(r, t, true);
            } else {
                mIntersections.add(e);
                SweepSegment t = e.secondSegment();

                // ensure s is above t
                if (s.position() < t.position()) {
                    SweepSegment swap = s;
                    s = t;
                    t = swap;
                }

                swap(s, t);

                final SweepSegment r = above(t);
                final SweepSegment u = below(s);
                addEventIfIntersection(t, r, true);
                addEventIfIntersection(s, u, true);
            }
        }

        return mIntersections;
    }

    @NotNull
    public List<Point> intersections() {
        return Collections.unmodifiableList(mIntersections);
    }

    public void reset() {
        mIntersections.clear();
        mEventQueue.clear();
        mSweepLineStatus.clear();
    }

    private void addEventIfIntersection(@Nullable SweepSegment s1, @Nullable SweepSegment s2, boolean checkIfExists) {
        if (s1 != null && s2 != null) {
            final Point i = Segment.intersection(s1, s2);
            if (i != null) {
                final Event e = new Event(i, s1, s2);
                if (checkIfExists) {
                    if (mEventQueue.contains(e)) {
                        return;
                    }
                }

                mEventQueue.add(e);
            }
        }
    }

    private void swap(@NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        mSweepLineStatus.remove(s1);
        mSweepLineStatus.remove(s2);

        final double swap = s1.position();
        s1.setPosition(s2.position());
        s2.setPosition(swap);

        mSweepLineStatus.add(s1);
        mSweepLineStatus.add(s2);
    }

    @Nullable
    private SweepSegment above(@NotNull SweepSegment s) {
        return mSweepLineStatus.higher(s);
    }

    @Nullable
    private SweepSegment below(@NotNull SweepSegment s) {
        return mSweepLineStatus.lower(s);
    }

    private void updateSweepLineStatus(@NotNull SweepSegment s, double position) {
        mSweepLineStatus.remove(s);
        s.setPosition(position);
        mSweepLineStatus.add(s);
    }

    private void removeSweepLineStatus(@NotNull SweepSegment s) {
        mSweepLineStatus.remove(s);
    }
}
