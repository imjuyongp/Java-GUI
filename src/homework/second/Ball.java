package homework.second;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ball extends JFrame {

    public Ball() {
        add(new MyPanel());
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class MyPanel extends JPanel implements ActionListener {
        private ArrayList<Point> balls;  // 여러 개의 원을 저장
        private JButton leftButton, rightButton, upButton, downButton, newButton;

        public MyPanel() {
            balls = new ArrayList<>();

            // 초기 원을 화면 중앙에 추가
            int centerX = 300;  // 600/2
            int centerY = 300;  // 600/2
            balls.add(new Point(centerX - 100, centerY - 100));  // 원의 크기가 200이므로 중앙 정렬

            setLayout(new BorderLayout());

            // 버튼 패널 생성
            JPanel buttonPanel = new JPanel();
            leftButton = new JButton("왼쪽");
            rightButton = new JButton("오른쪽");
            upButton = new JButton("위");
            downButton = new JButton("아래");
            newButton = new JButton("새로생성");

            leftButton.addActionListener(this);
            rightButton.addActionListener(this);
            upButton.addActionListener(this);
            downButton.addActionListener(this);
            newButton.addActionListener(this);

            buttonPanel.add(leftButton);
            buttonPanel.add(rightButton);
            buttonPanel.add(upButton);
            buttonPanel.add(downButton);
            buttonPanel.add(newButton);

            this.add(buttonPanel, BorderLayout.SOUTH);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 모든 원 그리기
           g.setColor(Color.BLACK);
            for (Point p : balls) {
                g.fillOval(p.x, p.y, 50, 50);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (balls.isEmpty()) {
                return;
            }

            // 가장 마지막 원 가져오기
            Point lastBall = balls.get(balls.size() - 1);

            if (e.getSource() == leftButton) {
                lastBall.x -= 20;
            } else if (e.getSource() == rightButton) {
                lastBall.x += 20;
            } else if (e.getSource() == upButton) {
                lastBall.y -= 20;
            } else if (e.getSource() == downButton) {
                lastBall.y += 20;
            } else if (e.getSource() == newButton) {
                balls.add(new Point(200, 200));
            }

            // 화면 다시 그리기
            repaint();
        }
    }
}
