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

package com.jh.rangeslider.test;

import com.jh.rangeslider.swing.RangeSliderModel;
import com.jh.rangeslider.swing.SimpleRangeSliderModel;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author <font size=-1 color="#a3a3a3">Johnny Hujol</font>
 * @since 8/20/12
 */
public final class RangeSliderModelTest {

  @Test
  public void setRange() {
    RangeSliderModel model = new SimpleRangeSliderModel();
    model.setRangeProperties(200, 400, 20, 10, 300, true);
    Assert.assertEquals("Value was not 200.", model.getValue(), 200);
    Assert.assertEquals("SecondValue was not 400.", model.getSecondValue(), 400);
    Assert.assertEquals("Minimum was not 10.", model.getMinimum(), 10);
    Assert.assertEquals("Maximum was not 400.", model.getMaximum(), 400);
    Assert.assertEquals("Extent was not 0.", model.getExtent(), 0);

    model.setSecondValue(300);
    Assert.assertEquals("SecondValue was not 300.", model.getSecondValue(), 300);

    model.setValue(600);
    Assert.assertEquals("Value was not 400.", model.getValue(), 400);
    Assert.assertEquals("SecondValue was not 300.", model.getSecondValue(), 300);
    Assert.assertEquals("Minimum was not 10.", model.getMinimum(), 10);
    Assert.assertEquals("Maximum was not 400.", model.getMaximum(), 400);
    Assert.assertEquals("Extent was not 0.", model.getExtent(), 0);

    model.setMinimum(450);
    Assert.assertEquals("Value was not 450.", model.getValue(), 450);
    Assert.assertEquals("SecondValue was not 450.", model.getSecondValue(), 450);
    Assert.assertEquals("Minimum was not 450.", model.getMinimum(), 450);
    Assert.assertEquals("Maximum was not 450.", model.getMaximum(), 450);
    Assert.assertEquals("Extent was not 0.", model.getExtent(), 0);

    model.setRangeProperties(100, 300, 50, 00, 1000, true);
    Assert.assertEquals("Value was not 100.", model.getValue(), 100);
    Assert.assertEquals("SecondValue was not 300.", model.getSecondValue(), 300);
    Assert.assertEquals("Minimum was not 0.", model.getMinimum(), 0);
    Assert.assertEquals("Maximum was not 1000.", model.getMaximum(), 1000);
    Assert.assertEquals("Extent was not 50.", model.getExtent(), 50);

    model.setSecondValue(200);
    model.setValue(511);
    model.setExtent(100);
    Assert.assertEquals("Value was not 511.", model.getValue(), 511);
    Assert.assertEquals("SecondValue was not 200.", model.getSecondValue(), 200);
    Assert.assertEquals("Minimum was not 0.", model.getMinimum(), 0);
    Assert.assertEquals("Maximum was not 1000.", model.getMaximum(), 1000);
    Assert.assertEquals("Extent was not 100.", model.getExtent(), 100);
  }
}
