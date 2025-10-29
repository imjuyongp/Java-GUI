package JFrame.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventTest extends JFrame {
  public MouseEventTest() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new JPanel());
    setSize(800, 600);
    MyPanel panel = new MyPanel();
    this.add(panel);
    setVisible(true);
  }

  class Rectangle {
    int x,y,w,h;
  }

  class MyPanel extends JPanel {
    Rectangle[] array =  new Rectangle[5]; // 화면에 그리는 사각형을 저장하는 배열 객체
    int index = 0;

    public MyPanel() {
      this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          if(index > 5) {
            return; // 배열의 크기를 넘어서면 종료
          }
          array[index] = new Rectangle();
          array[index].x = e.getX();
          array[index].y = e.getY();
          array[index].w = 50;
          array[index].h = 50;
          index++;
          repaint();
        }
      });
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for(Rectangle r : array) {
        if (r != null) {
          g.drawRect(r.x, r.y, r.w, r.h);
        }
      }
    }

  }


}
