# Collinear-Points-Princeton-algo-1-assignment-
Program to recognize line patterns in a given set of points.

Computer vision involves analyzing patterns in visual images and reconstructing the real-world objects that produced them. The process is often broken up into two phases: feature detection and pattern recognition. Feature detection involves selecting important features of the image; pattern recognition involves discovering patterns in the features. We will investigate a particularly clean pattern recognition problem involving points and line segments. This kind of pattern recognition arises in many other applications such as statistical data analysis.

The problem: Given a set of n distinct points in the plane, find every (maximal) line segment that connects a subset of 4 or more of the points.


            API:
            Point data type: Create an immutable data type Point that represents a point in the plane

            public class Point implements Comparable<Point> {
               public Point(int x, int y)                         // constructs the point (x, y)

               public   void draw()                               // draws this point
               public   void drawTo(Point that)                   // draws the line segment from this point to that point
               public String toString()                           // string representation

               public               int compareTo(Point that)     // compare two points by y-coordinates, breaking ties by x-coordinates
               public            double slopeTo(Point that)       // the slope between this point and that point
               public Comparator<Point> slopeOrder()              // compare two points by slopes they make with this point
            }
            
            
 Brute force: examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments. To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between p and s are all equal.

            public class BruteCollinearPoints {
               public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
               public           int numberOfSegments()        // the number of line segments
               public LineSegment[] segments()                // the line segments
            }
            
            
            
 A faster, sorting-based solution. It is possible to solve the problem much faster than the brute-force solution described above. Given a point p, the following method determines whether p participates in a set of 4 or more collinear points.

        -Think of p as the origin.
        -For each other point q, determine the slope it makes with p.
        -Sort the points according to the slopes they makes with p.
        -Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.
        
 implementing this algorithm.

                public class FastCollinearPoints {
                   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
                   public           int numberOfSegments()        // the number of line segments
                   public LineSegment[] segments()                // the line segments
                }
