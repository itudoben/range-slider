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

package com.jh.rangeslider;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import com.google.common.collect.Lists;
import com.jh.rangeslider.swing.JRangeSlider;

/**
 * @author <font size=-1 color="#a3a3a3">Johnny Hujol</font>
 * @since 7/21/12
 */
public final class Application extends JFrame {

  public static void main(String[] args) {
    final Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
//          UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//          UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
//          UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
          UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }
        catch(Exception e) {
          System.out.println("Couldn't load Motif L&F " + e);
        }

//        printUIDefaults();
//        if (true) return;

        Application application = new Application();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setTitle("Range Slider Example - #" + System.getProperty("build.number", "demo"));

        final JLabel label = new JLabel("Start");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        final JPanel pp = new JPanel();
        pp.setLayout(new BoxLayout(pp, BoxLayout.PAGE_AXIS));

        final JRangeSlider jRangeSlider = new JRangeSlider();
        jRangeSlider.getModel().setExtent(0);

        final Dimension d = new Dimension(450, 61);
        jRangeSlider.setSize(d);
        jRangeSlider.setPreferredSize(d);
        jRangeSlider.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.GREEN));

        JPanel pan = new JPanel();
        pan.add(jRangeSlider);

        pp.add(pan);
        pp.add(Box.createVerticalStrut(10));

        final JSlider jSlider = new JSlider();
        jSlider.setSize(d);
        jSlider.setPreferredSize(d);
        jSlider.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.YELLOW));

        JPanel pan2 = new JPanel();
        pan2.add(jSlider);

        pp.add(pan2);
        pp.add(Box.createVerticalGlue());

        panel.add(pp, BorderLayout.CENTER);
        application.getContentPane().add(panel);

        application.setLocationRelativeTo(null);
        application.setSize(800, 600);
        application.setVisible(true);

        jRangeSlider.getModel().addChangeListener(new ChangeListener() {
          @Override
          public void stateChanged(ChangeEvent changeEvent) {
            label.setText("Slider info: " + jRangeSlider.getModel().toString());
          }
        });
      }
    };
    EventQueue.invokeLater(runnable);
  }

  private static void printUIDefaults() {
    UIDefaults uidefs = UIManager.getLookAndFeelDefaults();
    List<Object> keys = Lists.newArrayList(uidefs.keySet());

    Collections.sort(keys, new Comparator<Object>() {
      @Override
      public int compare(Object o, Object o1) {
        return o.toString().toLowerCase().compareTo(o1.toString().toLowerCase());
      }
    });

    for(Object key : keys) {
      Object v = uidefs.get(key);
      System.out.println(String.format("%s: %S", key, v));
      if(v instanceof Integer) {
        int intVal = uidefs.getInt(key);
      }
      else if(v instanceof Boolean) {
        boolean boolVal = uidefs.getBoolean(key);
      }
      else if(v instanceof String) {
        String strVal = uidefs.getString(key);
      }
      else if(v instanceof Dimension) {
        Dimension dimVal = uidefs.getDimension(key);
      }
      else if(v instanceof Insets) {
        Insets insetsVal = uidefs.getInsets(key);
      }
      else if(v instanceof Color) {
        Color colorVal = uidefs.getColor(key);
      }
      else if(v instanceof Font) {
        Font fontVal = uidefs.getFont(key);
      }
      else if(v instanceof Border) {
        Border borderVal = uidefs.getBorder(key);
      }
      else if(v instanceof Icon) {
        Icon iconVal = uidefs.getIcon(key);
      }
      else if(v instanceof JTextComponent.KeyBinding[]) {
        JTextComponent.KeyBinding[] keyBindsVal = (JTextComponent.KeyBinding[])uidefs
            .get(key);
      }
      else if(v instanceof InputMap) {
        InputMap imapVal = (InputMap)uidefs.get(key);
      }
      else {
        System.out.println("Unknown type");
      }
    }
  }
}
