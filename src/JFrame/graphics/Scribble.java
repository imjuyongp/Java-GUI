package JFrame.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Scribble extends JFrame {
  public Scribble() {
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new MyPanel());
    setVisible(true);
  }

  class MyPanel extends JPanel {

    private int index = 0;
    Point[] array = new Point[1000];

    public MyPanel() {
      this.addMouseMotionListener(new MouseAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
          int x = e.getX();
          int y = e.getY();
          if (index > 1000) {
            return;
          }
          array[index] = new Point();
          array[index].x = x;
          array[index].y = y;
          index++;
          repaint();
        }
      });
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for(Point p : array) {
        if(p != null) {
          g.drawRect(p.x, p.y, 1,1);
        }
      }
    }
  }
}
