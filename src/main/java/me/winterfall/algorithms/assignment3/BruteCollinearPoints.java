package me.winterfall.algorithms.assignment3;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private List<Point[]> contains;
    private List<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        checkNullAndDuplicated(points);
        if (points.length >= 4) {
            this.contains = new ArrayList<>();
            this.segments = new ArrayList<>();
            for (int i = 0; i < points.length - 3; i++) {
                for (int j = i + 1; j < points.length - 2; j++) {
                    for (int k = j + 1; k < points.length - 1; k++) {
                        for (int z = k + 1; z < points.length; z++) {
                            double ij = points[i].slopeTo(points[j]);
                            double ik = points[i].slopeTo(points[k]);
                            double iz = points[i].slopeTo(points[z]);
                            if (ij == ik && ik == iz) {
                                Point[] ps = new Point[4];
                                ps[0] = points[i];
                                ps[1] = points[j];
                                ps[2] = points[k];
                                ps[3] = points[z];
                                Arrays.sort(ps);
                                boolean alreadyAdded = false;
                                for (Point[] containPoints : contains) {
                                    if ((ps[0].equals(containPoints[0]) && ps[3].equals(containPoints[1])) ||
                                            (ps[0].equals(containPoints[1]) && ps[3].equals(containPoints[0]))) {
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
                }
            }
        } else {
            segments = new ArrayList<>();
        }
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
        int nums = in.readInt();
        int[] ints = in.readAllInts();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < ints.length; i += 2) {
            Point p = new Point(ints[i], ints[i+1]);
            points.add(p);
        }
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points.toArray(new Point[]{}));
        System.out.println(bruteCollinearPoints.numberOfSegments());
        System.out.println(Arrays.toString(bruteCollinearPoints.segments()));
    }
}
