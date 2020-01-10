package bentleyottmann;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

// See:
// http://geomalgorithms.com/a09-_intersect-3.html
// https://en.wikipedia.org/wiki/Bentley%E2%80%93Ottmann_algorithm
final public class BentleyOttmann {
    @NotNull
    final private Queue<Event> mEventQueue = new PriorityQueue<>();

    @NotNull
    final private NavigableSet<SweepSegment> mSweepLine = new TreeSet<>(Comparator.comparingDouble(SweepSegment::position));

    @NotNull
    final private List<Point> mIntersections = new ArrayList<>();

    @Nullable
    private OnIntersectionListener mListener;

    public void addSegments(@NotNull List<Segment> segments) {
        for (Segment s : segments) {
            mEventQueue.add(new Event(s.leftPoint(), new SweepSegment(s), Event.Type.POINT_LEFT));
            mEventQueue.add(new Event(s.rightPoint(), new SweepSegment(s), Event.Type.POINT_RIGHT));
        }
    }

    public void findIntersections() {
        while (!mEventQueue.isEmpty()) {
            final Event E = mEventQueue.poll();
            if (E.type() == Event.Type.POINT_LEFT) {
                final SweepSegment segE = E.upperSegment();

                addSweepLineStatus(segE);

                final SweepSegment segA = above(segE);
                final SweepSegment segB = below(segE);

                addEventIfIntersection(segE, segA, E, false);
                addEventIfIntersection(segE, segB, E, false);
            } else if (E.type() == Event.Type.POINT_RIGHT) {
                final SweepSegment segE = E.upperSegment();
                final SweepSegment segA = above(segE);
                final SweepSegment segB = below(segE);

                removeSweepLineStatus(segE);
                addEventIfIntersection(segA, segB, E, true);
            } else {
                mIntersections.add(E);
                SweepSegment segE1 = E.upperSegment();
                SweepSegment segE2 = E.lowerSegment();

                if (mListener != null) {
                    mListener.onIntersection(segE1, segE2, E);
                }

                swap(segE1, segE2);

                final SweepSegment segA = above(segE2);
                final SweepSegment segB = below(segE1);
                addEventIfIntersection(segE2, segA, E, true);
                addEventIfIntersection(segE1, segB, E, true);
            }
        }
    }

    @NotNull
    public List<Point> intersections() {
        return Collections.unmodifiableList(mIntersections);
    }

    public void reset() {
        mIntersections.clear();
        mEventQueue.clear();
        mSweepLine.clear();
    }

    public void setListener(@NotNull OnIntersectionListener listener) {
        mListener = listener;
    }

    private void addEventIfIntersection(@Nullable SweepSegment s1, @Nullable SweepSegment s2,
                                        @NotNull Event E, boolean check) {
        if (s1 != null && s2 != null) {
            final Point i = Segment.intersection(s1, s2);
            // Check if the further event point intersection is ahead
            // relative the current sweep line position according to x.
            if (i != null && i.x > E.x) {
                final Event e = new Event(i, s1, s2);
                if (check) {
                    if (mEventQueue.contains(e)) {
                        return;
                    }
                }

                mEventQueue.add(e);
            }
        }
    }

    private void swap(@NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        mSweepLine.remove(s1);
        mSweepLine.remove(s2);

        final double swap = s1.position();
        s1.setPosition(s2.position());
        s2.setPosition(swap);

        mSweepLine.add(s1);
        mSweepLine.add(s2);
    }

    @Nullable
    private SweepSegment above(@NotNull SweepSegment s) {
        return mSweepLine.higher(s);
    }

    @Nullable
    private SweepSegment below(@NotNull SweepSegment s) {
        return mSweepLine.lower(s);
    }

    private void addSweepLineStatus(@NotNull SweepSegment s) {
        mSweepLine.add(s);
    }

    private void removeSweepLineStatus(@NotNull final SweepSegment s) {
        // A regular remove() can not be leveraged here since TreeSet tries to find
        // the required element by traversing the underlying tree structure
        // relying to the given Comparator (or to Comparable implementation of elements)
        // and NOT to elements equals() implementation.
        // For our case all the segments when being stored to TreeSet according
        // to the Comparator are ordered by their position, when any removals should be
        // performed based on segments equals() implementation.
        mSweepLine.removeIf(sweepSegment -> sweepSegment.equals(s));
    }
}
