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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import com.jh.rangeslider.swing.JRangeSlider;
import com.jh.rangeslider.swing.RangeSliderModel;

/**
 * A Basic L&F implementation of {@link RangeSliderUI}.
 *
 * @author <font size=-1 color="#a3a3a3">Johnny Hujol</font>
 * @since 7/29/12
 */
public final class BasicRangeSliderUI extends RangeSliderUI {

  private final static BasicRangeSliderUI m_buttonUI = new BasicRangeSliderUI();
  private MouseListener mouseListener = null;
  private JRangeSlider rangeSlider = null;
  private MouseMotionListener mouseMotionListener = null;

  private Insets insetCache = null;
  private Rectangle contentRect = null;
  private Rectangle trackRect = null;
  private Rectangle thumbValueRect = null;
  private Rectangle thumbSecondValueRect = null;

  private int valueCache = -1;
  private int secondValueCache = -1;
  private Color foregroundTrackColor = null;
  private Color backgroundColor = null;
  private Handler handler = null;
  private Polygon thumbValuePolygon = null;
  private Polygon thumbSecondValuePolygon = null;

  public BasicRangeSliderUI() {
  }

  @Override
  public void installUI(JComponent c) {
    rangeSlider = (JRangeSlider)c;

    installDefaults();
    installComponents();
    installListeners();

    insetCache = rangeSlider.getInsets();
    contentRect = new Rectangle();

    trackRect = new Rectangle();
    thumbValueRect = new Rectangle();
    thumbSecondValueRect = new Rectangle();
    valueCache = rangeSlider.getModel().getValue();
    secondValueCache = rangeSlider.getModel().getSecondValue();

    calculateGeometry(); // This figures out where the labels, ticks, track, and thumb are.

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

  private void calculateGeometry() {
    calculateContentRect();
    calculateTrackRect();
    calculateThumbRect();
//    BasicGraphicsUtils.drawDashedRect();
  }

  private void calculateContentRect() {
    final int paddingHorizontal = 8;

    contentRect.x = insetCache.left + paddingHorizontal;
    contentRect.y = insetCache.top;
    contentRect.width = rangeSlider.getWidth() - (insetCache.left + insetCache.right + 2 * paddingHorizontal);
    contentRect.height = rangeSlider.getHeight() - (insetCache.top + insetCache.bottom);

//    contentRect.x = 5;
//    contentRect.y = (rangeSlider.getHeight() - 5) / 2;
//    contentRect.width = rangeSlider.getWidth() - 2 * contentRect.x;
//    contentRect.height = 5;
  }

  private void calculateTrackRect() {
    int centerSpacing = 0;

    trackRect.height = 5;
    trackRect.x = contentRect.x;
    trackRect.y = contentRect.y + (contentRect.height - centerSpacing - trackRect.height) / 2;
    trackRect.width = contentRect.width;
  }

  private void calculateThumbRect() {
    setThumbRectangle(rangeSlider.getModel().getValue(), thumbValueRect);
    setThumbRectangle(rangeSlider.getModel().getSecondValue(), thumbSecondValueRect);

    thumbValuePolygon = new Polygon();
    setThumbPolygon(thumbValuePolygon, thumbValueRect);
    thumbSecondValuePolygon = new Polygon();
    setThumbPolygon(thumbSecondValuePolygon, thumbSecondValueRect);
  }

  private static void setThumbPolygon(Polygon thumbValuePolygon, Rectangle rectangle) {
    thumbValuePolygon.addPoint(rectangle.x, rectangle.y);
    thumbValuePolygon.addPoint(rectangle.x + rectangle.width, rectangle.y);
    thumbValuePolygon.addPoint(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height);
  }

  private void setThumbRectangle(final int value, final Rectangle thumbRect) {
    final int thumbWidth = 14;
    final int thumbHeight = 20 + trackRect.height;

    final int sliderValue = modelValueToSliderValue(value);
//    final int thumbHeight = (int)(contentRect.height * .8);
    thumbRect.x = sliderValue - thumbWidth / 2;
    thumbRect.y = trackRect.y - (thumbHeight - trackRect.height) / 2;
    thumbRect.width = thumbWidth;
    thumbRect.height = thumbHeight;
  }

  @Override
  public void paint(Graphics g, JComponent c) {
    recalculateIfInsetsChanged();
    recalculateIfValueChanged();
    recalculateIfSecondValueChanged();

    paintContentRectangle(g);
    paintTrackRectangle(g);
    paintThumbs(g);

//    Rectangle clip = g.getClipBounds();

//    if(!clip.intersects(trackRect) && rangeSlider.getPaintTrack()) {
//      calculateGeometry();
//    }

//    if(rangeSlider.getPaintTrack() && clip.intersects(trackRect)) {
//      paintTrack(g);
//    }
//    if(rangeSlider.getPaintTicks() && clip.intersects(tickRect)) {
//      paintTicks(g);
//    }
//    if(rangeSlider.getPaintLabels() && clip.intersects(labelRect)) {
//      paintLabels(g);
//    }
//    if(rangeSlider.hasFocus() && clip.intersects(focusRect)) {
//      paintFocus(g);
//    }
//    if(clip.intersects(thumbValueRect)) {
//      paintThumb(g);
//    }
  }

  private void paintContentRectangle(Graphics g) {
    g.setColor(backgroundColor);
    g.fillRect(contentRect.x, contentRect.y, contentRect.width, contentRect.height);
  }

  private void paintTrackRectangle(Graphics g) {
    final Object o = UIManager.get("Slider.gradient");
    if(o instanceof List && g instanceof Graphics2D) {
      List l = (List)o;
      ((Graphics2D)g).setPaint(new GradientPaint(trackRect.x, trackRect.y, (Color)l.get(3), trackRect.x, trackRect.y + trackRect.height, foregroundTrackColor));
    }
    else {
      g.setColor(foregroundTrackColor);
    }
    g.fillRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height);

    g.setColor(foregroundTrackColor);
    g.drawRect(trackRect.x, trackRect.y, trackRect.width, trackRect.height);
  }

  private void paintThumbs(Graphics g) {
//    g.fillRect(thumbValueRect.x, thumbValueRect.y, thumbValueRect.width, thumbValueRect.height);
    final Object o = UIManager.get("Slider.gradient");
    if(o instanceof List && g instanceof Graphics2D) {
      List l = (List)o;
      ((Graphics2D)g).setPaint(new GradientPaint(trackRect.x + trackRect.width / 2, trackRect.y, (Color)l.get(2), trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height, (Color)l.get(3)));
    }
    else {
      g.setColor(foregroundTrackColor);
    }
    g.fillPolygon(thumbValuePolygon);
    g.fillPolygon(thumbSecondValuePolygon);

    g.setColor(foregroundTrackColor);
    g.drawPolygon(thumbValuePolygon);
    g.drawPolygon(thumbSecondValuePolygon);
  }

  @Override
  public void uninstallUI(JComponent c) {
    super.uninstallUI(c);

    c.setLayout(null);
    uninstallListeners();
    uninstallComponents();
    uninstallDefaults();

    this.rangeSlider = null;
  }

  private void recalculateIfInsetsChanged() {
    Insets newInsets = rangeSlider.getInsets();
    if(!newInsets.equals(insetCache)) {
      insetCache = newInsets;
      calculateGeometry();
    }
  }

  private void recalculateIfValueChanged() {
    int newValue = rangeSlider.getModel().getValue();
    if(newValue != valueCache) {
      valueCache = newValue;
      calculateGeometry();
    }
  }

  private void recalculateIfSecondValueChanged() {
    int newSecondValue = rangeSlider.getModel().getSecondValue();
    if(newSecondValue != secondValueCache) {
      secondValueCache = newSecondValue;
      calculateGeometry();
    }
  }

  private void installDefaults() {
    LookAndFeel.installBorder(rangeSlider, "Slider.border");
    LookAndFeel.installColorsAndFont(rangeSlider, "Slider.background",
        "Slider.foreground", "Slider.font");
//    backgroundColor = UIManager.getColor("Slider.highlight");
    backgroundColor = UIManager.getColor("Panel.background");

    foregroundTrackColor = UIManager.getColor("Slider.foreground");
//    focusColor = UIManager.getColor("Slider.focus");

//    focusInsets = (Insets)UIManager.get("Slider.focusInsets");
  }

  private void installComponents() {
/*
 		int controlPointCount = this.rangeSlider.getControlPointCount();
 		this.controlPointLabels = new JLabel[controlPointCount];
 		for (int i = 0; i < controlPointCount; i++) {
 			this.controlPointLabels[i] = new JLabel(this.rangeSlider
 					.getControlPointText(i));
 			this.controlPointLabels[i].setIcon(this.rangeSlider
 					.getControlPointIcon(i));
 			//this.controlPointLabels[i].setBorder(new LineBorder(Color.blue));
 			this.rangeSlider.add(this.controlPointLabels[i]);
 		}
 		this.slider = new JSlider(JSlider.VERTICAL);
 		this.slider.setFocusable(false);
 		// this.rangeSlider.add(this.slider);

 		this.sliderRendererPane = new CellRendererPane();
 		this.rangeSlider.add(sliderRendererPane);
*/

  }

  private void installListeners() {
    this.mouseListener = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        rangeSlider.getModel().setValueIsAdjusting(false);
      }

      @Override
      public void mousePressed(MouseEvent e) {
        processMouseEvent(e);
      }

    };
    this.rangeSlider.addMouseListener(this.mouseListener);

    this.mouseMotionListener = new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        processMouseEvent(e);
      }
    };
    this.rangeSlider.addMouseMotionListener(this.mouseMotionListener);

    handler = new Handler();

    rangeSlider.addPropertyChangeListener(handler);

//    this.flexiSliderChangeListener = new ChangeListener() {
//      public void stateChanged(ChangeEvent e) {
//        rangeSlider.repaint();
//      }
//    };
//    this.rangeSlider.getModel().addChangeListener(
//        this.flexiSliderChangeListener);
  }

//   TODO
//   TODO
//  http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/javax/swing/plaf/basic/BasicSliderUI.java#BasicSliderUI.paintThumb%28java.awt.Graphics%29
//   TODO
//   TODO


  private void processMouseEvent(MouseEvent e) {
    final RangeSliderModel model = rangeSlider.getModel();
    model.setValueIsAdjusting(true);

    Rectangle thumbRect = getThumbRectMouseIsOver(e);

    final int modelValue = sliderValueToModelValue(e.getX());

    if(null == thumbRect) {
      // TODO: get the extent to move it up or down the range if modelValue is not in [value; secondValue]
      // Otherwise ??
    }
    else {
      {
        if(BasicRangeSliderUI.this.thumbValueRect == thumbRect) {
          model.setValue(modelValue);
        }
        else if(BasicRangeSliderUI.this.thumbSecondValueRect == thumbRect) {
          model.setSecondValue(modelValue);
        }
      }
    }
  }

  private Rectangle getThumbRectMouseIsOver(MouseEvent e) {
    if(thumbValueRect.contains(e.getPoint())) return thumbValueRect;

    if(thumbSecondValueRect.contains(e.getPoint())) return thumbSecondValueRect;

    return null;
  }

  private int sliderValueToModelValue(int x) {
    int newX = x;
    if(newX > trackRect.x + trackRect.width) {
      newX = trackRect.x + trackRect.width;
    }

    if(newX < trackRect.x) {
      newX = trackRect.x;
    }

    final double valuePer = (double)(newX - trackRect.x) / trackRect.width;

    final RangeSliderModel model = rangeSlider.getModel();
    final int valueRange = model.getMaximum() - model.getMinimum();

    return (int)(valueRange * valuePer);
  }

  private int modelValueToSliderValue(final int value) {
    final int maximum = rangeSlider.getModel().getMaximum();
    final int minimum = rangeSlider.getModel().getMinimum();
    double valuePer = (double)value / (maximum - minimum);

    return trackRect.x + (int)(trackRect.width * valuePer);
  }

  private void uninstallDefaults() {
/*
 		for (JLabel label : this.controlPointLabels)
 			this.rangeSlider.remove(label);
 		this.controlPointLabels = null;
 		this.rangeSlider.remove(this.sliderRendererPane);
 		this.sliderRendererPane = null;
*/
  }

  private void uninstallComponents() {
  }

  private void uninstallListeners() {
    this.rangeSlider.removeMouseListener(this.mouseListener);
    this.mouseListener = null;

    this.rangeSlider.removeMouseMotionListener(this.mouseMotionListener);
    this.mouseMotionListener = null;

    rangeSlider.removePropertyChangeListener(handler);

// 		this.rangeSlider.getModel().removeChangeListener(
// 				this.flexiSliderChangeListener);
// 		this.flexiSliderChangeListener = null;
  }


  private final class Handler implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
      calculateGeometry();
      rangeSlider.repaint();
    }
  }
}
