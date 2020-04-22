import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {

        private final int N;
        private Point[] p;
        private LineSegment[] segments;

        public FastCollinearPoints(Point[] points) // finds all line segments containing 4 or more points
        {
                checknull();
                N = points.length;
                p = points.clone();

                LinkedList<LineSegment> s = new LinkedList<LineSegment>();
                Arrays.sort(p);
                checkrepeated();

                for (int i = 0; i < N; i++) {
                        Point origin = p[i];
                        Point[] bySlope = p.clone();
                        Arrays.sort(bySlope, origin.slopeOrder());

                        int x = 1;
                        while (x < N) {

                                LinkedList<Point> candidates = new LinkedList<>();
                                final double SLOPE_REF = origin.slopeTo(bySlope[x]);
                                do {
                                        candidates.add(bySlope[x++]);
                                } while (x < N && origin.slopeTo(bySlope[x]) == SLOPE_REF);

                                // Candidates have a max line segment if ...
                                // 1. Candidates are collinear: At least 4 points are located
                                // at the same line, so at least 3 without "origin".
                                // 2. The max line segment is created by the point "origin" and the
                                // last point in candidates: so "origin" must be the smallest
                                // point having this slope comparing to all candidates.
                                if (candidates.size() >= 3 && origin.compareTo(candidates.peek()) < 0) {
                                        Point min = origin;
                                        Point max = candidates.removeLast();
                                        s.add(new LineSegment(min, max));
                                }
                        }
                }

                segments = s.toArray(new LineSegment[s.size()]);
        }

        private void checknull() {
                for (int i = 0; i < N; i++)
                        if (p[i] == null)
                                throw new IllegalArgumentException("one or more points are null");
        }

        private void checkrepeated() {
                for (int i = 0; i < N - 1; i++)
                        if (p[i] == p[i + 1])
                                throw new IllegalArgumentException("one or more points are repeated");
        }

        public int numberOfSegments() // the number of line segments
        {
                return segments.length;
        }

        public LineSegment[] segments() // the line segments
        {
                return segments.clone();
        }

}