package JFrame.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasicPaint extends JFrame {

  public BasicPaint() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(new JPanel());
    setSize(800, 600);
    MyPanel panel = new MyPanel();
    this.add(panel);
    setVisible(true);

  }

  class MyPanel extends JPanel { // 내부 클래스 정의

    private int squareX = 100;
    private int squareY = 100;
    private int squareW = 20;
    private int squareH = 20;

    public MyPanel() {
      setBorder(BorderFactory.createLineBorder(Color.BLACK));

      addMouseListener(new MouseAdapter() { // JPanel에 마우스 이벤트 리스너 등록
        public void mousePressed(MouseEvent e) {
          moveSquare(e.getX(), e.getY());
        }
      });

      addMouseListener(new MouseAdapter() {
        public void mouseDragged(MouseEvent e) {
          moveSquare(e.getX(), e.getY());
        }
      });
    }

    private void moveSquare(int x, int y) {
      int OFFSET = 10;
      if((squareX != x) || (squareY != y)) {
        repaint(squareX, squareY, squareW +  OFFSET, squareH + OFFSET);

        squareX = x;
        squareY = y;

        repaint(squareX, squareY, squareW + OFFSET, squareH + OFFSET);
      }
    }

    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawString("마우스를 클릭하면 사각형이 그려집니다!", 10, 20);
      g.setColor(Color.RED);
      g.fillRect(squareX, squareY, squareW, squareH);
      g.setColor(Color.BLACK);
      g.drawRect(squareX, squareY, squareW, squareH);
    }

  }
}
