import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

        private final int x; // x-coordinate of this point
        private final int y; // y-coordinate of this point

        public Point(int x, int y) {
                this.x = x;
                this.y = y;
        }

        // Draws this point to standard draw.
        public void draw() {
                StdDraw.point(x, y);
        }

        // Draws the line segment between this point and the specified point to standard
        public void drawTo(Point that) {
                /* DO NOT MODIFY */
                StdDraw.line(this.x, this.y, that.x, that.y);
        }

        /**
         * Returns the slope between this point and the specified point. Formally, if
         * the two points are (x0, y0) and (x1, y1), then the slope is (y1 - y0) / (x1 -
         * x0). For completeness, the slope is defined to be +0.0 if the line segment
         * connecting the two points is horizontal; Double.POSITIVE_INFINITY if the line
         * segment is vertical; and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1)
         * are equal.
         *
         * @param that the other point
         * @return the slope between this point and the specified point
         */
        public double slopeTo(Point that) {
                if (this.y == that.y && this.x == that.x)
                        return Double.NEGATIVE_INFINITY;
                else if (this.x == that.x)
                        return Double.POSITIVE_INFINITY;
                else if (this.y == that.y)
                        return +0.0;
                else {
                        double x0 = this.x, x1 = that.x, y0 = this.y, y1 = that.y;
                        return (y1 - y0) / (x1 - x0);
                }

        }

        /**
         * Compares two points by y-coordinate, breaking ties by x-coordinate. Formally,
         * the invoking point (x0, y0) is less than the argument point (x1, y1) if and
         * only if either y0 < y1 or if y0 = y1 and x0 < x1.
         *
         * @param that the other point
         * @return the value <tt>0</tt> if this point is equal to the argument point (x0
         *         = x1 and y0 = y1); a negative integer if this point is less than the
         *         argument point; and a positive integer if this point is greater than
         *         the argument point
         */
        public int compareTo(Point that) {
                if (this.y < that.y)
                        return -1;
                else if (this.y > that.y)
                        return +1;
                else {
                        if (this.x < that.x)
                                return -1;
                        else if (this.x > that.x)
                                return +1;
                        else
                                return 0;
                }
        }

        /**
         * Compares two points by the slope they make with this point. The slope is
         * defined as in the slopeTo() method.
         *
         * @return the Comparator that defines this ordering on points
         */
        private final Comparator<Point> slope = new SlopeOrder(this);

        public Comparator<Point> slopeOrder() {
                return slope;
        }

        private class SlopeOrder implements Comparator<Point> {

                private final Point p;

                SlopeOrder(Point point) {
                        this.p = point;
                }

                public int compare(Point p1, Point p2) {
                        double s1 = p.slopeTo(p1), s2 = p.slopeTo(p2);
                        if (s1 > s2)
                                return +1;
                        else if (s2 > s1)
                                return -1;
                        else
                                return 0;
                }

        }

        /**
         * Returns a string representation of this point. This method is provide for
         * debugging; your program should not rely on the format of the string
         * representation.
         *
         * @return a string representation of this point
         */
        public String toString() {
                /* DO NOT MODIFY */
                return "(" + x + ", " + y + ")";
        }

        /**
         * Unit tests the Point data type.
         */
        public static void main(String[] args) {
                /*
                 * Point p1 = new Point(0, 0); Point p2 = new Point(1, 1); Point p3 = new
                 * Point(5, 5); Point p4 = new Point(4, 8); Point p5 = new Point(2, 4);
                 * 
                 * p1.drawTo(p3); // p4.draw(); p3.drawTo(p5);
                 * 
                 * System.out.println(p2.slopeTo(p1)); Comparator<Point> p = p1.slopeOrder();
                 * System.out.println(p.compare(p3, p2));
                 */

                // read the n points from a file
                In in = new In(args[0]);
                int n = in.readInt();
                Point[] points = new Point[n];
                for (int i = 0; i < n; i++) {
                        int x = in.readInt();
                        int y = in.readInt();
                        points[i] = new Point(x, y);
                }

                // draw the points
                StdDraw.enableDoubleBuffering();
                StdDraw.setXscale(0, 32768);
                StdDraw.setYscale(0, 32768);
                for (Point p : points) {
                        p.draw();
                }
                StdDraw.show();

                // print and draw the line segments
                FastCollinearPoints collinear = new FastCollinearPoints(points);
                for (LineSegment segment : collinear.segments()) {
                        StdOut.println(segment);
                        segment.draw();
                }
                StdDraw.show();

        }
}