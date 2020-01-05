import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(new Point(1.01, 3.22), new Point(5.22, 7.93)));
        segments.add(new Segment(new Point(3.13, 7.12), new Point(9.54, 4.11)));
        segments.add(new Segment(new Point(9.83, 5.42), new Point(7.24, 1.11)));
        segments.add(new Segment(new Point(8.53, 2.12), new Point(2.44, 1.62)));

        final BentleyOttmann bentleyOttmann = new BentleyOttmann();
        bentleyOttmann.addSegments(segments);

        final List<Point> intersections = bentleyOttmann.findIntersections();
        intersections.forEach(System.out::println);
    }
}
