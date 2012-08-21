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

import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jh.rangeslider.swing.plaf.BasicRangeSliderUI;
import com.jh.rangeslider.swing.plaf.RangeSliderUI;

/**
 * @author <font size=-1 color="#a3a3a3">Johnny Hujol</font>
 * @since 7/29/12
 */
public final class JRangeSlider extends JComponent implements ChangeListener {

  private static final String uiClassID = "RangeSliderUI";

  private RangeSliderModel model;

  public JRangeSlider() {
    model = new SimpleRangeSliderModel();
    model.addChangeListener(this);
    this.updateUI();
  }

  @Override
  public String getUIClassID() {
    return uiClassID;
  }

  @Override
  public void updateUI() {
    if(null != UIManager.get(uiClassID)) {
      setUI((RangeSliderUI)UIManager.getUI(this));
    }
    else {
      setUI(new BasicRangeSliderUI());
    }
  }

  public void setUI(RangeSliderUI newUI) {
    super.setUI(newUI);
  }

  public RangeSliderUI getUI() {
    return (RangeSliderUI)ui;
  }

  public RangeSliderModel getmodel() {
    return model;
  }

  @Override
  public void stateChanged(ChangeEvent changeEvent) {
    repaint();
  }
}
