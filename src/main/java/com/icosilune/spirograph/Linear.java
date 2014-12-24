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
abstract public class Linear<T extends Linear<T>> {

  /** Returns the sum of this and other. */
  abstract public T add(T other);

  /** Returns the scalar product of this with other. */
  abstract public T multiply(double other);

  /** Returns the norm of this. */
  abstract public double norm();

  /** Returns the difference of this and other. */
  public T subtract(T other) {
    return add(other.multiply(-1));
  }

  /** Returns the normalized value of this. Does not protect against division by zero. */
  public T normalize() {
    return multiply(1.0 / norm());
  }

  @Override
  abstract public boolean equals(Object obj);

  @Override
  abstract public int hashCode();
}
