package tests;

import bentleyottmann.BentleyOttmann;
import bentleyottmann.Point;
import bentleyottmann.Segment;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BentleyOttmannTest extends TestCase {
    public void testFindIntersections1() {
        final List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(0.8, 6.1), new Point(11.72, 9.32)));
        segments.add(new Segment(new Point(6.84, 3.56), new Point(15.06, 8.38)));
        segments.add(new Segment(new Point(3.5, 8.17), new Point(10.44, 4.08)));
        segments.add(new Segment(new Point(13.32, 4.22), new Point(2.42, 12.67)));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        assertEquals(4, bentleyOttmann.intersections().size());
    }

    public void testFindIntersections2() {
        final List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(0.8, 6.1), new Point(11.72, 9.32)));
        segments.add(new Segment(new Point(6.84, 3.56), new Point(15.06, 8.38)));
        segments.add(new Segment(new Point(3.5, 8.17), new Point(10.44, 4.08)));
        segments.add(new Segment(new Point(13.32, 4.22), new Point(2.42, 12.67)));
        segments.add(new Segment(new Point(5.39, 5.68), new Point(8.39, 11.23)));
        segments.add(new Segment(new Point(9.5, 3.91), new Point(11, 10.06)));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        assertEquals(11, bentleyOttmann.intersections().size());
    }

    public void testFindIntersections3() {
        final List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(1.76, 6.86), new Point(3.84, 4.76), "a"));
        segments.add(new Segment(new Point(3.96, 5.36), new Point(1.22, 2.4), "b"));
        segments.add(new Segment(new Point(0.8, 3.1), new Point(8, 1.7), "c"));
        segments.add(new Segment(new Point(7.4, 1.5), new Point(9.4, 4.8), "d"));
        segments.add(new Segment(new Point(2.2, 6.04), new Point(4.9, 8.46), "e"));
        segments.add(new Segment(new Point(3.72, 8.5), new Point(9.64, 4.02), "f"));
        segments.add(new Segment(new Point(6.2, 6.98), new Point(5.9, 1.82), "g"));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        assertEquals(8, bentleyOttmann.intersections().size());
    }

    public void testFindIntersections4() {
        final List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(0.8, 6.1), new Point(11.72, 9.32)));
        segments.add(new Segment(new Point(6.84, 3.56), new Point(15.06, 8.38)));
        segments.add(new Segment(new Point(3.5, 8.17), new Point(10.44, 4.08)));
        segments.add(new Segment(new Point(13.32, 4.22), new Point(2.42, 12.67)));

        final Map<Segment, List<Point>> intersectionsOnSegment = new HashMap<>();
        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.setListener((s1, s2, p) -> {
            intersectionsOnSegment.computeIfAbsent(s1, k -> new ArrayList<>()).add(p);
            intersectionsOnSegment.computeIfAbsent(s2, k -> new ArrayList<>()).add(p);
        });

        bentleyOttmann.findIntersections();
        assertEquals(4, intersectionsOnSegment.size());
    }
}