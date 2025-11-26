package project.dodgezone.screen;
import project.dodgezone.game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class GameFrame extends JFrame {
    public GameFrame() {
        add(new GamePanel());
        setTitle("Dodge Zone");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class GamePanel extends JPanel implements ActionListener {

        // 플레이어 객체 생성
        Player player = new Player();

        // 모든 방향의 장애물을 하나의 리스트로 관리
        ArrayList<Obstacle> obstacles = new ArrayList<>();

        // 장애물 생성 타이머
        Timer createObstacleTimer;
        // 장애물 이동 타이머
        Timer moveObstacleTimer;

        public GamePanel() {
            setBackground(Color.BLACK);
            setLayout(null);

            setFocusable(true);
            requestFocusInWindow(); // 패널 자체에 포커스를 주어 키보드를 입력 받음

            // 키 이벤트 정의 (플레이어 이동)
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> player.moveUp();
                        case KeyEvent.VK_DOWN -> player.moveDown();
                        case KeyEvent.VK_LEFT -> player.moveLeft();
                        case KeyEvent.VK_RIGHT -> player.moveRight();
                    }
                    repaint(); // 이벤트 발생 후 해당 메서드 실행 후 그림을 다시 그림
                }
            });

            // 장애물 생성 타이머 객체
            createObstacleTimer = new Timer(1000, this);
            createObstacleTimer.start();

            // 장애물 이동 타이머 객체
            moveObstacleTimer = new Timer(50, this);
            moveObstacleTimer.start();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            player.paintPlayer(g); // 플레이어를 그림

            // 모든 장애물을 그림
            for (Obstacle obstacle : obstacles) {
                obstacle.paintObstacle(g);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createObstacleTimer) {
                // 장애물 생성 (50% 확률)
                if (Math.random() < 0.5) {
                    int createNum = (int) (Math.random() * 5) + 1; // 1~5개 생성
                    for (int i = 0; i < createNum; i++) {
                        // 랜덤하게 4방향 중 하나 선택
                        int direction = (int) (Math.random() * 4);
                        switch (direction) {
                            case 0 -> obstacles.add(new ObstacleUp());
                            case 1 -> obstacles.add(new ObstacleDown());
                            case 2 -> obstacles.add(new ObstacleLeft());
                            case 3 -> obstacles.add(new ObstacleRight());
                        }
                    }
                }
            } else if (e.getSource() == moveObstacleTimer) {
                // 모든 장애물 이동 및 화면 밖으로 나간 장애물 제거 (다형성 활용)
                Iterator<Obstacle> iterator = obstacles.iterator(); // 장애물이 담겨있는 배열리스트를 iterator 타입의 리스트에 넣음
                while (iterator.hasNext()) {
                    Obstacle obstacle = iterator.next();
                    obstacle.move();

                    // 화면 밖으로 나간 장애물 제거
                    if (obstacle.isOutOfScreen()) {
                        iterator.remove();
                    }
                }
            }
            repaint();
        }
    }
}
