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

package com.jh.rangeslider.swing.plaf;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

/**
 * @author <font size=-1 color="#a3a3a3">Johnny Hujol</font>
 * @since 7/29/12
 */
public final class BasicRangeSliderUI extends RangeSliderUI {

  private final static BasicRangeSliderUI m_buttonUI = new BasicRangeSliderUI();

  public BasicRangeSliderUI() {
  }

  @Override
  public void paint(Graphics graphics, JComponent c) {
    super.paint(graphics, c);

    graphics.setColor(Color.blue);
    graphics.drawRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);

    final int x = 5;
    final int y = (c.getHeight() - 5) / 2;
    final int w = c.getWidth() - 2 * x;
    final int h = 5;
    graphics.setColor(Color.red);
    graphics.fillRect(x, y, w, h);
  }

  @Override
  public void installUI(JComponent c) {
    super.installUI(c);

    installDefaults();
    installComponents();
    installListeners();

//    c.setLayout(createLayoutManager());
    c.setBorder(new EmptyBorder(1, 1, 1, 1));

/*
    m_borderRaised = UIManager.getBorder("Button.border");
    m_borderLowered = UIManager.getBorder("Button.borderPressed");
    m_backgroundNormal = UIManager.getColor("Button.background");
    m_backgroundPressed = UIManager.getColor("Button.pressedBackground");
    m_foregroundNormal = UIManager.getColor("Button.foreground");
    m_foregroundActive = UIManager.getColor("Button.activeForeground");
    m_focusBorder = UIManager.getColor("Button.focusBorder");
    c.addMouseListener(this);
    c.addKeyListener(this);
*/
  }

  @Override
  public void uninstallUI(JComponent c) {
    super.uninstallUI(c);

    c.setLayout(null);
    uninstallListeners();
    uninstallComponents();
    uninstallDefaults();

//  		this.flexiSlider = null;

/*
    c.removeMouseListener(this);
    c.removeKeyListener(this);
*/
  }

  public void installDefaults() {

  }

  public void installComponents() {
/*
 		int controlPointCount = this.flexiSlider.getControlPointCount();
 		this.controlPointLabels = new JLabel[controlPointCount];
 		for (int i = 0; i < controlPointCount; i++) {
 			this.controlPointLabels[i] = new JLabel(this.flexiSlider
 					.getControlPointText(i));
 			this.controlPointLabels[i].setIcon(this.flexiSlider
 					.getControlPointIcon(i));
 			//this.controlPointLabels[i].setBorder(new LineBorder(Color.blue));
 			this.flexiSlider.add(this.controlPointLabels[i]);
 		}
 		this.slider = new JSlider(JSlider.VERTICAL);
 		this.slider.setFocusable(false);
 		// this.flexiSlider.add(this.slider);

 		this.sliderRendererPane = new CellRendererPane();
 		this.flexiSlider.add(sliderRendererPane);
*/

  }

  public void installListeners() {
/*
 		this.mouseListener = new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) {
 			}

 			@Override
 			public void mouseReleased(MouseEvent e) {
 				flexiSlider.getModel().setValueIsAdjusting(false);
 			}

 			@Override
 			public void mousePressed(MouseEvent e) {
 				flexiSlider.getModel().setValueIsAdjusting(true);
 				int y = e.getY();
 				FlexiRangeModel.Value modelValue = sliderValueToModelValue(y);
 				flexiSlider.setValue(modelValue);
 				// the following lines does the "magic" of snapping
 				// the slider thumb to control points of discrete ranges.
 				slider.setValue(modelValueToSliderValue(modelValue));
 			}
 		};
 		this.flexiSlider.addMouseListener(this.mouseListener);

 		this.mouseMotionListener = new MouseMotionAdapter() {
 			@Override
 			public void mouseDragged(MouseEvent e) {
 				flexiSlider.getModel().setValueIsAdjusting(true);
 				int y = e.getY();
 				FlexiRangeModel.Value modelValue = sliderValueToModelValue(y);
 				if (modelValue == null)
 					return;
 				flexiSlider.setValue(modelValue);
 				// the following lines does the "magic" of snapping
 				// the slider thumb to control points of discrete ranges.
 				slider.setValue(modelValueToSliderValue(modelValue));
 			}
 		};
 		this.flexiSlider.addMouseMotionListener(this.mouseMotionListener);

 		this.flexiSliderChangeListener = new ChangeListener() {
 			public void stateChanged(ChangeEvent e) {
 				flexiSlider.repaint();
 			}
 		};
 		this.flexiSlider.getModel().addChangeListener(
 				this.flexiSliderChangeListener);
*/
  }

  public void uninstallDefaults() {
/*
 		for (JLabel label : this.controlPointLabels)
 			this.flexiSlider.remove(label);
 		this.controlPointLabels = null;
 		this.flexiSlider.remove(this.sliderRendererPane);
 		this.sliderRendererPane = null;
*/
  }

  public void uninstallComponents() {
  }

  public void uninstallListeners() {
/*
 		this.flexiSlider.removeMouseListener(this.mouseListener);
 		this.mouseListener = null;

 		this.flexiSlider.removeMouseMotionListener(this.mouseMotionListener);
 		this.mouseMotionListener = null;

 		this.flexiSlider.getModel().removeChangeListener(
 				this.flexiSliderChangeListener);
 		this.flexiSliderChangeListener = null;
*/
  }
}
