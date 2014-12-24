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
public class SimpleSpirograph extends AbstractSpirograph{

  double armExtensionA = 0;
  Point2d centerA = new Point2d(-4,-3);
  Point2d centerB = new Point2d(-4,3);
  double rotationArmA = .2;
  double rotationArmB = .3;
  double rotationRateA = 1;
  double rotationRateB = 1;
  double phaseA = 0;
  double phaseB = 0;
  double armA = 5;
  double armB = 5;

  @Override
  public double getArmA(double t) {
    return centerA.norm();
  }

  @Override
  public double getArmB(double t) {
    return centerB.norm();
  }

  @Override
  public Point2d getPositionA(double t) {
    double angle = phaseA + rotationRateA*t;
    return centerA.add( new Point2d(Math.cos(angle), Math.sin(angle)).multiply(rotationArmA));
  }

  @Override
  public Point2d getPositionB(double t) {
    double angle = phaseB + rotationRateB*t;
    return centerB.add( new Point2d(Math.cos(angle), Math.sin(angle)).multiply(rotationArmB));
  }

  @Override
  public double getArmAExtension(double t) {
    return armExtensionA;
  }
}
