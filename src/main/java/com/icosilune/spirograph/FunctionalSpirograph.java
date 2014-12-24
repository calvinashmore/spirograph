/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.spirograph;

import java.util.function.Function;

/**
 *
 * @author ashmore
 */
public class FunctionalSpirograph extends AbstractSpirograph{

  Point2d centerA = new Point2d(-4,-3);
  Point2d centerB = new Point2d(-4,3);

  Function<Double, Double> armExtensionA = t -> 0.0;
  Function<Double, Double> rotationArmA = t -> 0.2;
  Function<Double, Double> rotationArmB = t -> 0.3;
  Function<Double, Double> rotationRateA = t -> 1.0;
  Function<Double, Double> rotationRateB = t -> 1.0;
  double phaseA = 0;
  double phaseB = 0;
  Function<Double, Double> armA = t -> 5.0;
  Function<Double, Double> armB = t -> 5.0;

  @Override
  public double getArmA(double t) {
    return armA.apply(t);
  }

  @Override
  public double getArmB(double t) {
    return armB.apply(t);
  }

  @Override
  public Point2d getPositionA(double t) {
    phaseA += rotationRateA.apply(t)*dt;
    return centerA.add(new Point2d(Math.cos(phaseA), Math.sin(phaseA)).multiply(rotationArmA.apply(t)));
  }

  @Override
  public Point2d getPositionB(double t) {
    phaseB += rotationRateB.apply(t)*dt;
    return centerB.add( new Point2d(Math.cos(phaseB), Math.sin(phaseB)).multiply(rotationArmB.apply(t)));
  }

  @Override
  public double getArmAExtension(double t) {
    return armExtensionA.apply(t);
  }

  double dt = 0;
  double lastT = 0;

  @Override
  public Point2d getPenPosition(double t) {
    dt = t - lastT;
    lastT = t;
    return super.getPenPosition(t);
  }
}
