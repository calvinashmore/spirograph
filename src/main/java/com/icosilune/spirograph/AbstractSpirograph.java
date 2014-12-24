/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.spirograph;

/**
 *
 * @author ashmore
 */
public abstract class AbstractSpirograph {

  public abstract double getArmA(double t);
  public abstract double getArmB(double t);

  /**
   * Return (in world units) the amount the pen extends from arm A.
   */
  public double getArmAExtension(double t) {return 0;}

  public abstract Point2d getPositionA(double t);
  public abstract Point2d getPositionB(double t);

  public Point2d getPenPosition(double t) {
    Point2d A = getPositionA(t);
    Point2d B = getPositionB(t);
    double ax = getArmA(t);
    double bx = getArmB(t);
    double ab = A.subtract(B).norm();

    double cosThetaA = (ab*ab + ax*ax - bx*bx) / (2*ab*ax);
    double sinThetaA = Math.sqrt(1- cosThetaA*cosThetaA);

    Point2d arm = new Point2d(cosThetaA, sinThetaA);
    return A.add(arm.multiply(ax + getArmAExtension(t)));
  }
}
