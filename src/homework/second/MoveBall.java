package homework.second;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MoveBall extends JFrame {
    public MoveBall() {
        add(new MyPanel());
        setSize(300,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    class MyPanel extends JPanel {
        private int x,y;
        public MyPanel() {
            x = 150;
            y = 30;
            setLayout(null);

            // 패널이 키 이벤트를 받을 수 있도록 설정
            setFocusable(true);
            requestFocusInWindow();

            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        // 왼쪽 키: 왼쪽으로 20 이동
                        x -= 20;
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        // 오른쪽 키: 오른쪽으로 20 이동
                        x += 20;
                    } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        y += 20;
                    }
                    repaint();
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillOval(x,y,50,50);
        }
    }
}
