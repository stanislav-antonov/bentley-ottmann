package bentleyottmann;

import java.util.Comparator;
import java.util.TreeSet;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class SweepLine extends TreeSet<SweepSegment> {
    SweepLine() {
        super(Comparator.comparingDouble(SweepSegment::position));
    }

    @Override
    public boolean remove(@NotNull Object o) {
        return removeIf(sweepSegment -> sweepSegment.nearlyEqual((SweepSegment) o));
    }

    void swap(@NotNull SweepSegment s1, @NotNull SweepSegment s2) {
        remove(s1);
        remove(s2);

        final double swap = s1.position();
        s1.setPosition(s2.position());
        s2.setPosition(swap);

        add(s1);
        add(s2);
    }

    @Nullable
    SweepSegment above(@NotNull SweepSegment s) {
        return higher(s);
    }

    @Nullable
    SweepSegment below(@NotNull SweepSegment s) {
        return lower(s);
    }
}
