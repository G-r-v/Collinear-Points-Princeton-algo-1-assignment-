import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

        private int size;
        private Point[] p;
        private LineSegment[] segments;

        public BruteCollinearPoints(Point[] points) // finds all line segments containing 4 points{
        {
                if (points == null)
                        throw new IllegalArgumentException("argument to constructor is null");
                size = points.length;
                p = points.clone();
                checknull();

                Arrays.sort(p);
                checkrepeated();

                ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();

                for (int a = 0; a < size - 3; a++) {
                        Point pa = p[a];
                        for (int b = a + 1; b < size - 2; b++) {
                                double slopeAB = pa.slopeTo(p[b]);
                                for (int c = b + 1; c < size - 1; c++) {
                                        double slopeAC = pa.slopeTo(p[c]);

                                        if (slopeAB == slopeAC) {
                                                for (int d = c + 1; d < size; d++) {
                                                        double slopeAD = pa.slopeTo(p[d]);

                                                        if (slopeAC == slopeAD) {
                                                                LineSegment temp = new LineSegment(pa, p[d]);
                                                                if (!segmentList.contains(temp))
                                                                        segmentList.add(temp);
                                                        }
                                                }
                                        }
                                }
                        }
                }

                segments = segmentList.toArray(new LineSegment[segmentList.size()]);
        }

        private void checknull() {
                for (int i = 0; i < size; i++)
                        if (p[i] == null)
                                throw new IllegalArgumentException("one or more points are null");
        }

        private void checkrepeated() {
                for (int i = 0; i < size - 1; i++)
                        if (p[i] == p[i + 1])
                                throw new IllegalArgumentException("one or more points are repeated");
        }

        public int numberOfSegments() // the number of line segments
        {
                return segments.length;
        }

        public LineSegment[] segments() // the line segments{

        {
                return segments.clone();
        }
}