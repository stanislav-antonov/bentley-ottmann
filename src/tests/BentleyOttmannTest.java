package tests;

import bentleyottmann.BentleyOttmann;
import bentleyottmann.IPoint;
import bentleyottmann.ISegment;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BentleyOttmannTest extends TestCase {
    public void testFindIntersections1() {
        final List<ISegment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(0.8, 6.1), new Point(11.72, 9.32)));
        segments.add(new Segment(new Point(6.84, 3.56), new Point(15.06, 8.38)));
        segments.add(new Segment(new Point(3.5, 8.17), new Point(10.44, 4.08)));
        segments.add(new Segment(new Point(13.32, 4.22), new Point(2.42, 12.67)));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann(Point::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        assertEquals(4, bentleyOttmann.intersections().size());
    }

    public void testFindIntersections2() {
        final List<ISegment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(0.8, 6.1), new Point(11.72, 9.32)));
        segments.add(new Segment(new Point(6.84, 3.56), new Point(15.06, 8.38)));
        segments.add(new Segment(new Point(3.5, 8.17), new Point(10.44, 4.08)));
        segments.add(new Segment(new Point(13.32, 4.22), new Point(2.42, 12.67)));
        segments.add(new Segment(new Point(5.39, 5.68), new Point(8.39, 11.23)));
        segments.add(new Segment(new Point(9.5, 3.91), new Point(11, 10.06)));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann(Point::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        assertEquals(11, bentleyOttmann.intersections().size());
    }

    public void testFindIntersections3() {
        final List<ISegment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(1.76, 6.86), new Point(3.84, 4.76)));
        segments.add(new Segment(new Point(3.96, 5.36), new Point(1.22, 2.4)));
        segments.add(new Segment(new Point(0.8, 3.1), new Point(8, 1.7)));
        segments.add(new Segment(new Point(7.4, 1.5), new Point(9.4, 4.8)));
        segments.add(new Segment(new Point(2.2, 6.04), new Point(4.9, 8.46)));
        segments.add(new Segment(new Point(3.72, 8.5), new Point(9.64, 4.02)));
        segments.add(new Segment(new Point(6.2, 6.98), new Point(5.9, 1.82)));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann(Point::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();

        assertEquals(8, bentleyOttmann.intersections().size());
    }

    public void testFindIntersections4() {
        final List<ISegment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(0.8, 6.1), new Point(11.72, 9.32)));
        segments.add(new Segment(new Point(6.84, 3.56), new Point(15.06, 8.38)));
        segments.add(new Segment(new Point(3.5, 8.17), new Point(10.44, 4.08)));
        segments.add(new Segment(new Point(13.32, 4.22), new Point(2.42, 12.67)));

        final Map<ISegment, List<IPoint>> intersectionsOnSegment = new HashMap<>();
        final BentleyOttmann bentleyOttmann = new BentleyOttmann(Point::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.setListener((s1, s2, p) -> {
            intersectionsOnSegment.computeIfAbsent(s1, k -> new ArrayList<>()).add(p);
            intersectionsOnSegment.computeIfAbsent(s2, k -> new ArrayList<>()).add(p);
        });

        bentleyOttmann.findIntersections();
        assertEquals(4, intersectionsOnSegment.size());
    }

    /*
    public void testFindIntersections5() {
        final List<ISegment> segments = new ArrayList<>();
        segments.add(new Segment(new Point (4.76,8.77), new Point(7.84,4.49) ));
        segments.add(new Segment(new Point (7.34,4.61), new Point(10.5,6.05) ));
        segments.add(new Segment(new Point (9.84,6.05), new Point (16.14,4.19)));
        segments.add(new Segment(new Point (15.82,3.97), new Point(14.62,8.51)));
        segments.add(new Segment(new Point (14.46,8.03), new Point (16.04,10.81)));
        segments.add(new Segment(new Point (4.58,8.31), new Point (10.86,10.07)));
        segments.add(new Segment(new Point (10.66,9.65), new Point (9.44,12.19)));
        segments.add(new Segment(new Point (9.24,11.73), new Point(12.14,12.69) ));
        segments.add(new Segment(new Point (11.4,12.75), new Point (16.14,10.39)));
        segments.add(new Segment(new Point(5.9,6.35) , new Point (9.08,4.21)));
        segments.add(new Segment(new Point (5.24,7.63), new Point (10.24,4.23)));
        segments.add(new Segment(new Point (4.56,9.59), new Point(11.66,4.25) ));
        segments.add(new Segment(new Point (6.28,10.51), new Point(13.16,4.47) ));
        segments.add(new Segment(new Point(7.8,11.09) , new Point(15.48,3.57) ));
        segments.add(new Segment(new Point  (9.54,13.03), new Point (16.86,4.67)));
        segments.add(new Segment(new Point (10.58,13.23) , new Point (15.84,8.67)));

        final Map<ISegment, List<IPoint>> intersectionsOnSegment = new HashMap<>();
        final BentleyOttmann bentleyOttmann = new BentleyOttmann(Point::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();
        assertEquals(4, intersectionsOnSegment.size());
    }
    */
}