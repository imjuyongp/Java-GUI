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
        // 장애물 생성 난이도 증가 타이머
        Timer moreCreateObstacleTimer;
        // 장애물 이동 난이도 증가 타이머
        Timer moreMoveObstacleTimer;
        // 난이도 증가 트리거 타이머
        Timer levelUpTimer;

        // 게임 오버 패널
        GameOver gameOverPanel;

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

            // 장애물 생성 난이도 증가 타이머 객체 (더 빠른 생성 속도)
            moreCreateObstacleTimer = new Timer(500, this);

            // 장애물 이동 난이도 증가 타이머 객체 (더 빠른 이동 속도)
            moreMoveObstacleTimer = new Timer(30, this);

            // 난이도 증가 (일회성 타이머)
            levelUpTimer = new Timer(10000, e -> {
                createObstacleTimer.stop(); // 기존 장애물 생성 타이머 정지
                moreCreateObstacleTimer.start(); // 빠른 장애물 생성 타이머 시작
                moveObstacleTimer.stop(); // 기존 장애물 이동 타이머 정지
                moreMoveObstacleTimer.start(); // 빠른 장애물 이동 타이머 시작
                System.out.println("난이도 증가");
            });
            levelUpTimer.setRepeats(false); // 한 번만 실행

            levelUpTimer.start();
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
            if (e.getSource() == createObstacleTimer || e.getSource() == moreCreateObstacleTimer) {
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
            } else if (e.getSource() == moveObstacleTimer ||  e.getSource() == moreMoveObstacleTimer) {
                Iterator<Obstacle> iterator = obstacles.iterator(); // 장애물이 담겨있는 배열리스트를 순회
                while (iterator.hasNext()) {
                    Obstacle obstacle = iterator.next();
                    obstacle.move();

                    // 화면 밖으로 나간 장애물 제거
                    if (obstacle.isOutOfScreen()) {
                        iterator.remove();
                    }

                    collision(); // 충돌 감지
                }
            }
            repaint();
        }

        private void collision() { // 충돌 감지
            Rectangle playerBounds = player.getBounds();

            for  (Obstacle obstacle : obstacles) {
                if (playerBounds.intersects(obstacle.getBounds())) { // 충돌 감지로 intersects() 사용 : 두 사각형이 겹치면 true반환
                    gameOver(); // 게임 종료 호출
                    System.out.println("충돌");
                    return;
                }
            }
        }

        public void gameOver() {
            // 타이머 객체 종료
            moveObstacleTimer.stop();
            createObstacleTimer.stop();
            moreCreateObstacleTimer.stop();
            moreMoveObstacleTimer.stop();
            levelUpTimer.stop();

            // 게임 오버 패널 생성 및 표시
            gameOverPanel = new GameOver();
            gameOverPanel.setBounds(0, 0, getWidth(), getHeight());

            // 재시작 버튼 이벤트
            gameOverPanel.getRestartButton().addActionListener(e -> restartGame());

            // 게임 종료 버튼 이벤트
            gameOverPanel.getExitButton().addActionListener(e -> System.exit(0));

            add(gameOverPanel);
            gameOverPanel.setVisible(true);
            revalidate();
            repaint();
        }

        public void restartGame() {
            // 게임 오버 패널 제거
            if (gameOverPanel != null) {
                remove(gameOverPanel);
                gameOverPanel = null;
            }

            // 플레이어 위치 초기화
            player = new Player();

            // 장애물 초기화
            obstacles.clear();

            // 난이도 초기화 (일반 타이머로 복귀)
            moreCreateObstacleTimer.stop();
            moreMoveObstacleTimer.stop();

            // 타이머 재시작
            createObstacleTimer.start();
            moveObstacleTimer.start();

            // 10초 후 난이도 증가 타이머 재시작
            levelUpTimer.start();

            // 포커스 재설정
            requestFocusInWindow();
            repaint();
        }
    }
}
