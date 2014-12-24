/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icosilune.spirograph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.function.Function;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ashmore
 */
public class Main {


  public static void main(String args[]) {

    FunctionalSpirograph spirograph = new FunctionalSpirograph();
    spirograph.phaseA = Math.PI/2;
//    spirograph.rotationRateA = t -> 1.1 + .5*Math.sin(t);
//    spirograph.rotationRateB = t -> 4.3 + .1*((int)5*t)%2;
//    spirograph.rotationArmB = t -> 1.0 - .5*Math.sin(t*.1);
//    spirograph.rotationArmA = t -> 1.0;

    spirograph.rotationRateA = t -> 0.4 + .5*Math.sin(t);
    spirograph.rotationRateB = t -> 1.2;
    spirograph.rotationArmB = t -> 2.0;
    spirograph.rotationArmA = t -> 1.0;
    spirograph.armA = t -> 5.5 + .5*Math.sin(t*.01);
    spirograph.armB = t -> 3.9;

    Function<Double, Double> mainRotator = t -> 2*Math.PI*t*.4;// + 1*Math.sin(.001*t) ;

    JFrame frame = new JFrame("meh");
    frame.add(new MainPanel(spirograph, mainRotator));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  private static final class MainPanel extends JPanel {
    private static final int INITIAL_RES = 800;

    private final AbstractSpirograph spirograph;
    private final Function<Double, Double> mainRotator;
    private double t = 0;
    private BufferedImage bg = new BufferedImage(INITIAL_RES, INITIAL_RES, BufferedImage.TYPE_INT_RGB);

    public MainPanel(AbstractSpirograph spirograph, Function<Double, Double> mainRotator) {
      this.spirograph = spirograph;
      this.mainRotator = mainRotator;

      Graphics2D g = bg.createGraphics();
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, INITIAL_RES, INITIAL_RES);
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(INITIAL_RES, INITIAL_RES);
    }

    @Override
    public void paint(Graphics g) {

      updateImage();

      g.drawImage(bg, 0, 0, this);

      repaint();
    }

    private void updateImage() {

      Graphics2D g = bg.createGraphics();
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      int lastX = 0;
      int lastY = 0;

      double scale = .3;

      //g.setColor(Color.BLACK);
      g.setColor(new Color(0,0,0, .05f));

      for(int i=0;i<300;i++) {
        t += .00001 * i;

        Point2d point = spirograph.getPenPosition(t);
        point = point.multiply(scale);
        double theta = mainRotator.apply(t);

        // apply global rotation
        point = new Point2d(
            point.getX() * Math.cos(theta) + point.getY() * Math.sin(theta),
            point.getY() * Math.cos(theta) - point.getX() * Math.sin(theta));

        int ix = (int) (getWidth()/2 * (1 + point.getX()));
        int iy = (int) (getHeight()/2 * (1 + point.getY()));

        if(i > 0) {
          g.drawLine(lastX, lastY, ix, iy);
        }
        lastX = ix;
        lastY = iy;
      }
    }
  }
}
