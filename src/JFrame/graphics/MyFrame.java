package JFrame.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
  public MyFrame() {
    add(new MyPanel());
    setSize(300,500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  class MyPanel extends JPanel implements ActionListener {
    boolean flag = false;
    private int light_number = 0;

    public MyPanel() {
      setLayout(new BorderLayout());
      JButton button = new JButton("traffic light turn on");
      button.addActionListener(this); // 패널에 버튼 이벤트 리스너 추가
      add(button, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.setColor(Color.BLACK);
      g.drawOval(100,100,100,100);
      g.drawOval(100,200,100,100);
      g.drawOval(100,300,100,100);

      if (light_number == 0) {
        g.setColor(Color.RED);
        g.fillOval(100,100,100,100);
      } else if (light_number == 1) {
        g.setColor(Color.YELLOW);
        g.fillOval(100,200,100,100);
      } else {
        g.setColor(Color.GREEN);
        g.fillOval(100,300,100,100);
      }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
      light_number += 1;
      if (light_number >= 3) {
        light_number = 0;
      }
      repaint();
    }
  }

}
