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

    public void testFindIntersections5() {
        final List<ISegment> segments = new ArrayList<>();
        segments.add(new Segment(new Point (2.0681655529586,6.7721029389621), new Point (3.8750905945826,4.7795349136096), "f"));
        segments.add(new Segment(new Point(3.7637048043455,5.0765636875751) , new Point(2.5013325149917,2.9107288774094), "g"));
        segments.add(new Segment(new Point (2.4146991225851,3.1706290546293), new Point (4.4196433468528,1.6731089858861), "h"  ));
        segments.add(new Segment(new Point (4.1844955674634,1.7597423782927), new Point (7.1547833071193,2.8612240817484), "i"  ));
        segments.add(new Segment(new Point (6.870130732069,2.9354812752398), new Point (8.887451155252,1.3637040130053) , "j" ));
        segments.add(new Segment(new Point (8.5904223812864,1.4008326097509), new Point(12.7364490178894,5.1384446821513) , "k"));
        segments.add(new Segment(new Point (12.7735776146351,4.8785445049314), new Point (11.1399193578244,8.7399185664841) , "l" ));
        segments.add(new Segment(new Point(11.4369481317899,8.5666517816708) , new Point (7.0681499147127,9.6557572862113) , "m" ));
        segments.add(new Segment(new Point (7.3032976941021,9.7300144797027), new Point (5.2736010720039,8.3067516044509) , "n" ));
        segments.add(new Segment(new Point (5.5582536470543,8.3686325990271), new Point (3.1696472564143,9.0369473404497) , "p" ));
        segments.add(new Segment(new Point (3.5161808260408,9.160709329602), new Point (2.092917950789,6.5493313584879), "q"  ));

        segments.add(new Segment(new Point (5.0434037721806,9.2448674822256), new Point (2.0347498158874,5.3649291223001) , "r" ));
        segments.add(new Segment(new Point (3.2599935084955,2.1656817027123), new Point (6.47285474689,9.7758064156891) , "s"));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann(Point::new);
        bentleyOttmann.addSegments(segments);
        bentleyOttmann.findIntersections();
        assertEquals(15, bentleyOttmann.intersections().size());
    }
}