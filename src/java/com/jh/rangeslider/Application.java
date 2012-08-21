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
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        }
        catch(Exception e) {
          System.out.println("Couldn't load Motif L&F " + e);
        }

        Application application = new Application();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setTitle("Range Slider Example - #" + System.getProperty("build.number", "demo"));

        final JRangeSlider jRangeSlider = new JRangeSlider();
        jRangeSlider.setPreferredSize(new Dimension(400, 30));

        JLabel label = new JLabel("Slider info: " + jRangeSlider.getmodel().toString());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        final JPanel pp = new JPanel();
        pp.add(jRangeSlider);

        panel.add(pp, BorderLayout.CENTER);
        application.getContentPane().add(panel);

        application.setLocationRelativeTo(null);
        application.setSize(800, 600);
        application.setVisible(true);
      }
    };
    EventQueue.invokeLater(runnable);
  }
}
