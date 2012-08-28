/*
 * RangeSlider allows a range selection from X axis.
 * Copyright (C) <2012>  <itudoben at gmail dot com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jh.rangeslider.swing;

import javax.swing.DefaultBoundedRangeModel;

/**
 * @author <font size=-1 color="#a3a3a3">Johnny Hujol</font>
 * @since 7/29/12
 */
public final class SimpleRangeSliderModel extends DefaultBoundedRangeModel implements RangeSliderModel {

  private int value = 33;
  private int secondValue = 66;
  private int extent = 10;
  private int min = 0;
  private int max = 100;
  private boolean isAdjusting = false;

  @Override
  public int getSecondValue() {
    return secondValue;
  }

  @Override
  public void setSecondValue(int n) {
    n = Math.min(n, Integer.MAX_VALUE - extent);

    int newValue = Math.max(n, min);
    if(newValue + extent > max) {
      newValue = max - extent;
    }
    setRangeProperties(value, newValue, extent, min, max, isAdjusting);
  }

  @Override
  public void setExtent(int n) {
    int newExtent = Math.max(0, n);
    if(value + newExtent > max) {
      newExtent = max - value;
    }
    if(secondValue + newExtent > max) {
      newExtent = max - secondValue;
    }
    setRangeProperties(value, secondValue, newExtent, min, max, isAdjusting);
  }

  @Override
  public void setMaximum(int n) {
    int newMin = Math.min(n, min);
    int newExtent = Math.min(n - newMin, extent);
    int newValue = Math.min(n - newExtent, value);
    int newSecondValue = Math.min(n - newExtent, secondValue);
    setRangeProperties(newValue, newSecondValue, newExtent, newMin, n, isAdjusting);
  }

  @Override
  public void setMinimum(int n) {
    int newMax = Math.max(n, max);
    int newValue = Math.max(n, value);
    int newExtent = Math.min(newMax - newValue, extent);
    int newSecondValue = Math.max(n, secondValue);
    newExtent = Math.min(newMax - newSecondValue, newExtent);
    setRangeProperties(newValue, newSecondValue, newExtent, n, newMax, isAdjusting);
  }

  @Override
  public void setRangeProperties(int newValue, int newExtent, int newMin, int newMax, boolean adjusting) {
    setRangeProperties(newValue, newValue, newExtent, newMin, newMax, adjusting);
  }

  @Override
  public void setValue(int n) {
    n = Math.min(n, Integer.MAX_VALUE - extent);

    int newValue = Math.max(n, min);
    if(newValue + extent > max) {
      newValue = max - extent;
    }

    setRangeProperties(newValue, secondValue, extent, min, max, isAdjusting);
  }

  @Override
  public void setValueIsAdjusting(boolean b) {
    setRangeProperties(value, secondValue, extent, min, max, b);
  }

  @Override
  public void setRangeProperties(int newValue, int newSecondValue, int newExtent, int newMin, int newMax, boolean adjusting) {
    /*
    Need to respect
    minimum <= value <= value+extent <= maximum
    minimum <= secondValue <= secondValue+extent <= maximum
     */

    if(newMin > newMax) {
      newMin = newMax;
    }
    if(newValue > newMax) {
      newMax = newValue;
    }
    if(newValue < newMin) {
      newMin = newValue;
    }

    if(newSecondValue > newMax) {
      newMax = newSecondValue;
    }
    if(newSecondValue < newMin) {
      newMin = newSecondValue;
    }

    /* Convert the addends to long so that extent can be
    * Integer.MAX_VALUE without rolling over the sum.
    * A JCK test covers this, see bug 4097718.
    */
    if((long)newExtent + (long)newValue > newMax) {
      newExtent = newMax - newValue;
    }

    if((long)newExtent + (long)newSecondValue > newMax) {
      newExtent = newMax - newSecondValue;
    }

    if(newExtent < 0) {
      newExtent = 0;
    }

    boolean isChange =
        (newValue != value) ||
            (newSecondValue != secondValue) ||
            (newExtent != extent) ||
            (newMin != min) ||
            (newMax != max) ||
            (adjusting != isAdjusting);

    if(isChange) {
      value = newValue;
      secondValue = newSecondValue;
      extent = newExtent;
      min = newMin;
      max = newMax;
      isAdjusting = adjusting;

      fireStateChanged();
    }
  }

  @Override
  public int getExtent() {
    return extent;
  }

  @Override
  public boolean getValueIsAdjusting() {
    return isAdjusting;
  }

  @Override
  public int getMaximum() {
    return max;
  }

  @Override
  public int getMinimum() {
    return min;
  }

  @Override
  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("SimpleRangeSliderModel");
    sb.append("{extent=").append(extent);
    sb.append(", value=").append(value);
    sb.append(", secondValue=").append(secondValue);
    sb.append(", min=").append(min);
    sb.append(", max=").append(max);
    sb.append(", isAdjusting=").append(isAdjusting);
    sb.append('}');
    return sb.toString();
  }
}
