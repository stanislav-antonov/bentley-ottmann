package bentleyottmann;

import com.sun.istack.internal.NotNull;

public class Segment {
    @NotNull
    public Point p1;

    @NotNull
    public Point p2;

    public Segment(@NotNull Point p1, @NotNull Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
}