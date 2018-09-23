package me.winterfall.algorithms.assignment3;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private List<Point[]> contains;
    private List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        checkNullAndDuplicated(points);
        if (points.length >= 4) {
            contains = new ArrayList<>();
            segments = new ArrayList<>();
            for (int j = 0; j < points.length; j++) {
                Point point = points[j];
                Point[] pointCopy = Arrays.copyOf(points, points.length);
                Arrays.sort(pointCopy, point.slopeOrder());
                for (int i = 1; i < pointCopy.length-2; i++) {
                    if (point.slopeTo(pointCopy[i]) == point.slopeTo(pointCopy[i + 1]) && point.slopeTo(pointCopy[i + 1]) == point.slopeTo(pointCopy[i + 2])) {
                        Point[] ps = new Point[4];
                        ps[0] = point;
                        ps[1] = pointCopy[i];
                        ps[2] = pointCopy[i + 1];
                        ps[3] = pointCopy[i + 2];
                        Arrays.sort(ps);

                        boolean alreadyAdded = false;
                        for (Point[] containPoints : contains) {
                            if (onTheSameLine(containPoints, ps[0], ps[3])) {


                                alreadyAdded = true;
                                break;
                            }
                        }
                        if (!alreadyAdded) {
                            Point[] containPoints = new Point[2];
                            containPoints[0] = ps[0];
                            containPoints[1] = ps[3];
                            contains.add(containPoints);
                            segments.add(new LineSegment(ps[0], ps[3]));
                        }
                    }
                }
            }
        } else {
            segments = new ArrayList<>();
        }

    }

    private boolean onTheSameLine(Point[] containPoints, Point p, Point p1) {
        Point contain0 = containPoints[0];
        Point contain1 = containPoints[1];
        if (contain0.slopeTo(contain1) == p.slopeTo(p1)) {
            if (contain0 != p) {
                return contain0.slopeTo(contain1) == contain0.slopeTo(p);
            } else {
                return contain0.slopeTo(contain1) == contain0.slopeTo(p1);
            }
        }
        return false;
    }

    private void checkNullAndDuplicated(Point[] points) {
        List<Point> alreadAdded = new ArrayList<>();
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
            if (alreadAdded.contains(p)) {
                throw new IllegalArgumentException();
            }
            alreadAdded.add(p);
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[]{});
    }

    public static void main(String[] args) {
        In in = new In("input8.txt");
        int num = in.readInt();
        int[] ints = in.readAllInts();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < ints.length; i += 2) {
            Point p = new Point(ints[i], ints[i+1]);
            points.add(p);
        }
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points.toArray(new Point[]{}));
        System.out.println(fastCollinearPoints.numberOfSegments());
        System.out.println(Arrays.toString(fastCollinearPoints.segments()));
    }

}
