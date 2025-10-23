package JFrame.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DropBall extends JFrame {
  public DropBall() {
    add(new MyPanel());
    setSize(300,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  class MyPanel extends JPanel implements ActionListener {

    int x, y;

    public MyPanel() {
      x = 150;
      y = 30;
      setLayout(new BorderLayout());
      JButton button = new JButton("Drop Ball Fast");
      button.addActionListener(this); // 패널에 버튼 이벤트 리스너 추가
      add(button, BorderLayout.SOUTH);

      Timer timer = new Timer(1000,this); // 시간이 지날때마다 이벤트를 발생시키는 객체
      timer.start(); // 타이머 시작
    }

    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawString("버튼을 누르면 공이 더 빨리 떨어집니다!", 10, 20);
      g.drawOval(x, y, 10, 10);

    }

    @Override // 인터페이스 구현체
    public void actionPerformed(ActionEvent e) {
      y += 30;
      repaint();
      if (y >= 500) {
        y = 10;
        repaint();
      }
    }
  }
}
