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

import javax.swing.BoundedRangeModel;

/**
 * @author <font size=-1 color="#a3a3a3">Johnny Hujol</font>
 * @since 7/29/12
 */
public interface RangeSliderModel extends BoundedRangeModel {

  int getSecondValue();

  /**
   * To keep this consistent to the original {@link BoundedRangeModel#setValue(int)}, this method behaves the same
   * way when it sets the {@code newValue} and obey to the same constraints.
   *
   * @param newValue the model's new second value.
   *
   * @see BoundedRangeModel#setValue(int)
   */
  void setSecondValue(int newValue);

  /**
   * This method sets all of the model's data with a single method call. The method results in a single event
   * being generated. This is consistent to the method {@link BoundedRangeModel#setRangeProperties(int, int, int, int, boolean)}.
   *
   * @param value       the current value.
   * @param secondValue the second value.
   * @param extent      the amount to which the value can jump.
   * @param min         the min to which the value or second value can be set to.
   * @param max         the max to which the value or second value can be set to.
   * @param adjusting   true if a series of changes are in progress.
   */
  void setRangeProperties(int value, int secondValue, int extent, int min, int max, boolean adjusting);
}
