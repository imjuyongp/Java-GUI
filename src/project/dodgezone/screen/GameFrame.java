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

        // 위쪽 장애물 리스트
        ArrayList<ObstacleUp> obstacleUpList = new ArrayList<>();

        // 아래쪽 장애물 리스트
        ArrayList<ObstacleDown> obstacleDownList = new ArrayList<>();

        // 왼쪽 장애물 리스트
        ArrayList<ObstacleLeft> obstacleLeftList = new ArrayList<>();

        // 오른쪽 장애물 리스트
        ArrayList<ObstacleRight> obstacleRightList = new ArrayList<>();

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
            player.paintImg(g); // 플레이어를 그림

            // 위쪽에 모든 장애물을 그림
            for (ObstacleUp obstacle : obstacleUpList) {
                obstacle.paintObstacle(g);
            }
            // 아래쪽에 모든 장애물을 그림
            for (ObstacleDown obstacle : obstacleDownList) {
                obstacle.paintObstacle(g);
            }

            // 왼쪽에 모든 장애물을 그림
            for (ObstacleLeft obstacle : obstacleLeftList) {
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
                        obstacleUpList.add(new ObstacleUp());
                        obstacleDownList.add(new ObstacleDown());
                        obstacleLeftList.add(new ObstacleLeft());
                        obstacleRightList.add(new ObstacleRight());
                    }
                }
            } else if (e.getSource() == moveObstacleTimer) {
                // 위쪽 장애물 이동
                Iterator<ObstacleUp> iteratorUp = obstacleUpList.iterator();
                while (iteratorUp.hasNext()) {
                    ObstacleUp obstacle = iteratorUp.next();
                    obstacle.move();

                    // 화면 밖으로 나간 장애물 제거
                    if (obstacle.isOutOfScreen()) {
                        iteratorUp.remove();
                    }
                }

                // 아래쪽 장애물 이동
                Iterator<ObstacleDown> iteratorDown = obstacleDownList.iterator();
                while (iteratorDown.hasNext()) {
                    ObstacleDown obstacle = iteratorDown.next();
                    obstacle.move();
                    if (obstacle.isOutOfScreen()) {
                        iteratorDown.remove();
                    }
                }

                // 왼쪽 장애물 이동
                Iterator<ObstacleLeft> iteratorLeft = obstacleLeftList.iterator();
                while (iteratorLeft.hasNext()) {
                    ObstacleLeft obstacle = iteratorLeft.next();
                    obstacle.move();
                    if (obstacle.isOutOfScreen()) {
                        iteratorLeft.remove();
                    }
                }

                // 오른쪽 장애물 이동
                Iterator<ObstacleRight> iteratorRight = obstacleRightList.iterator();
                while (iteratorRight.hasNext()) {
                    ObstacleRight obstacle = iteratorRight.next();
                    obstacle.move();
                    if (obstacle.isOutOfScreen()) {
                        iteratorRight.remove();
                    }
                }

            }
            repaint();
        }
    }
}
