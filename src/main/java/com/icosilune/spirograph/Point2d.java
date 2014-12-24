/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.spirograph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ashmore
 */
public class Point2d extends Linear<Point2d> {

  // tolerance for equals and hashCode
  private static final double TOLERANCE = .00001;

  public static final Point2d ZERO = new Point2d(0, 0);
  public static final Point2d UNIT_X = new Point2d(1, 0);
  public static final Point2d UNIT_Y = new Point2d(0, 1);

  private final double x, y;

  public Point2d(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public Point2d add(Point2d other) {
    return new Point2d(this.x+other.x, this.y+other.y);
  }

  @Override
  public Point2d multiply(double other) {
    return new Point2d(this.x*other, this.y*other);
  }

  @Override
  public double norm() {
    // L2 norm
    return Math.sqrt(x*x + y*y);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point2d) {
      Point2d that = (Point2d) obj;
      return equalsWithinTolerance(this.x, that.x)
          && equalsWithinTolerance(this.y, that.y);
    } else {
      return false;
    }
  }

  private static boolean equalsWithinTolerance(double a, double b) {
    return Math.abs(a-b) < TOLERANCE;
  }

  @Override
  public int hashCode() {
    return Objects.hash(toleranceHash(x), toleranceHash(y));
  }

  private static int toleranceHash(double a) {
    return (int) (a/TOLERANCE);
  }

  public double dot(Point2d point) {
    return x*point.x + y*point.y;
  }

  @Override
  public String toString() {
    return String.format("<%f, %f>", x, y);
  }

  /** Normalizes to -1 +1 */
  public static List<Point2d> normalize(List<Point2d> points) {
    double minx = Double.POSITIVE_INFINITY;
    double miny = Double.POSITIVE_INFINITY;
    double maxx = Double.NEGATIVE_INFINITY;
    double maxy = Double.NEGATIVE_INFINITY;

    for(Point2d point : points) {
      if(minx > point.x) minx = point.x;
      if(miny > point.y) miny = point.y;
      if(maxx < point.x) maxx = point.x;
      if(maxy < point.y) maxy = point.y;
    }

    System.out.println(String.format("x: [%f %f]", minx, maxx));
    System.out.println(String.format("y: [%f %f]", miny, maxy));

    List<Point2d> normalized = new ArrayList<>();
    for(Point2d point : points) {
      normalized.add(new Point2d(
              2*(point.x-minx)/(maxx-minx) - 1,
              2*(point.y-miny)/(maxy-miny) - 1));
    }
    return normalized;
  }
}