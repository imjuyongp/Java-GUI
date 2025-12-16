# Dodge Zone 게임 프로젝트

## 📋 프로젝트 개요

**Dodge Zone**은 Java Swing을 사용하여 개발된 2D 회피 게임입니다. 플레이어는 4방향에서 날아오는 장애물을 피하며 최대한 오래 생존하는 것이 목표입니다.

---

## 🎮 게임 특징

### 핵심 기능
- **4방향 장애물**: 위, 아래, 왼쪽, 오른쪽에서 랜덤하게 생성되는 장애물
- **키보드 조작**: 방향키(↑↓←→)를 사용한 플레이어 이동
- **충돌 감지**: 플레이어와 장애물 간 실시간 충돌 감지
- **난이도 증가**: 게임 시작 10초 후 자동으로 난이도 상승
- **게임 오버 시스템**: 충돌 시 게임 오버 패널 표시
- **재시작 기능**: 게임 오버 후 재시작 또는 종료 선택 가능

### 난이도 시스템
- **초급 (0~10초)**
  - 장애물 생성 주기: 1초
  - 장애물 이동 속도: 50ms

- **고급 (10초 이후)**
  - 장애물 생성 주기: 0.5초 (2배 빠름)
  - 장애물 이동 속도: 30ms (약 1.7배 빠름)

---

## 📁 프로젝트 구조

```
src/project/dodgezone/
├── PlayGame.java                    # 메인 클래스 (게임 실행)
├── game/                            # 게임 로직 패키지
│   ├── Obstacle.java                # 장애물 추상 클래스
│   ├── ObstacleUp.java              # 위에서 아래로 이동하는 장애물
│   ├── ObstacleDown.java            # 아래에서 위로 이동하는 장애물
│   ├── ObstacleLeft.java            # 왼쪽에서 오른쪽으로 이동하는 장애물
│   ├── ObstacleRight.java           # 오른쪽에서 왼쪽으로 이동하는 장애물
│   └── Player.java                  # 플레이어 클래스
└── screen/                          # 화면 UI 패키지
    ├── GameFrame.java               # 메인 게임 프레임
    └── GameOver.java                # 게임 오버 패널
```

---

## 🔧 주요 클래스 설명

### 1. `PlayGame.java`
- **역할**: 게임의 진입점 (Entry Point)
- **기능**: GameFrame 인스턴스를 생성하여 게임 시작

```java
public static void main(String[] args) {
    GameFrame gameFrame = new GameFrame();
}
```

---

### 2. `Player.java` (게임 캐릭터)

#### 주요 속성
- `BufferedImage img`: 플레이어 이미지
- `int x, y`: 플레이어 좌표 (초기값: 250, 250)
- 크기: 30x30 픽셀

#### 주요 메서드
| 메서드 | 설명 |
|--------|------|
| `paintPlayer(Graphics g)` | 플레이어를 화면에 그림 |
| `moveUp/Down/Left/Right()` | 방향키 입력에 따라 10픽셀씩 이동 |
| `getBounds()` | 충돌 감지를 위한 사각형 영역 반환 |

#### 이동 제한
- 왼쪽: x > 0
- 오른쪽: x < 470
- 위: y > 0
- 아래: y < 470

---

### 3. `Obstacle.java` (추상 클래스)

#### 설계 패턴
**다형성(Polymorphism)**을 활용한 추상 클래스 설계

#### 추상 메서드
```java
public abstract void paintObstacle(Graphics g);  // 장애물 그리기
public abstract void move();                      // 장애물 이동
public abstract boolean isOutOfScreen();          // 화면 밖 체크
```

#### 구체 메서드
```java
public Rectangle getBounds() {
    return new Rectangle(x, y, 10, 10);  // 충돌 감지용
}
```

---

### 4. 장애물 구현 클래스

#### `ObstacleUp` (위 → 아래)
- 시작 위치: `(랜덤 x, 0)`
- 이동 방향: `y += 5`
- 색상: 노란색 (YELLOW)

#### `ObstacleDown` (아래 → 위)
- 시작 위치: `(랜덤 x, 500)`
- 이동 방향: `y -= 5`
- 색상: 노란색

#### `ObstacleLeft` (왼쪽 → 오른쪽)
- 시작 위치: `(0, 랜덤 y)`
- 이동 방향: `x += 5`
- 색상: 노란색

#### `ObstacleRight` (오른쪽 → 왼쪽)
- 시작 위치: `(500, 랜덤 y)`
- 이동 방향: `x -= 5`
- 색상: 노란색

---

### 5. `GameFrame.java` (메인 게임 로직)

#### 타이머 구조
| 타이머 | 주기 | 역할 |
|--------|------|------|
| `createObstacleTimer` | 1000ms | 초급 장애물 생성 |
| `moveObstacleTimer` | 50ms | 초급 장애물 이동 |
| `moreCreateObstacleTimer` | 500ms | 고급 장애물 생성 |
| `moreMoveObstacleTimer` | 30ms | 고급 장애물 이동 |
| `levelUpTimer` | 10000ms | 난이도 증가 트리거 (일회성) |

#### 주요 메서드

**`actionPerformed(ActionEvent e)`**
- 장애물 생성 로직 (50% 확률로 1~5개 생성)
- 장애물 이동 및 화면 밖 제거
- 충돌 감지 호출

**`collision()`**
- `Rectangle.intersects()` 메서드로 충돌 감지
- 충돌 시 `gameOver()` 호출

**`gameOver()`**
- 모든 타이머 정지
- GameOver 패널 표시

**`restartGame()`**
- 게임 오버 패널 제거
- 플레이어 및 장애물 초기화
- 타이머 재시작
- 난이도 초기화 (일반 모드로 복귀)

---

### 6. `GameOver.java` (게임 오버 UI)

#### UI 구성
- **메시지**: "게임 종료!" (흰색, 40pt 굵은 글씨)
- **배경**: 반투명 검은색 `(0, 0, 0, 200)`
- **버튼**:
  - 재시작 버튼: 파란색 배경 `(70, 130, 180)`
  - 게임 종료 버튼: 빨간색 배경 `(220, 20, 60)`

#### 인터랙션
- **마우스 호버 효과**: 버튼 색상이 밝게 변경
- **재시작 버튼**: `restartGame()` 호출
- **게임 종료 버튼**: `System.exit(0)` 호출

---

## 🎯 게임 플레이 흐름

```
[게임 시작]
    ↓
[플레이어 생성 (250, 250)]
    ↓
[타이머 시작]
    ↓
[10초 경과?] ──No──→ [일반 난이도]
    ↓                      ↓
   Yes                     ↓
    ↓                      ↓
[난이도 증가] ←───────────┘
    ↓
[장애물 생성 & 이동]
    ↓
[충돌 감지]
    ↓
  충돌? ──No──→ [계속 플레이]
    ↓                 ↓
   Yes                ↓
    ↓                 ↓
[게임 오버] ←─────────┘
    ↓
[사용자 선택]
    ↓
재시작? ──Yes──→ [게임 시작]
    ↓
   No
    ↓
[게임 종료]
```

---

## 💡 핵심 기술 및 패턴

### 1. **객체지향 프로그래밍 (OOP)**
- 추상 클래스 `Obstacle`을 통한 다형성 구현
- 상속을 통한 코드 재사용

### 2. **이벤트 기반 프로그래밍**
- `ActionListener` 인터페이스 구현
- `KeyListener`를 통한 키보드 입력 처리
- `MouseListener`를 통한 버튼 호버 효과

### 3. **타이머 기반 게임 루프**
- `javax.swing.Timer`를 활용한 게임 루프 구현
- 장애물 생성/이동을 별도 타이머로 관리

### 4. **충돌 감지 알고리즘**
- `Rectangle.intersects()` 메서드 활용
- 각 객체의 `getBounds()` 메서드로 충돌 영역 정의

### 5. **컬렉션 관리**
- `ArrayList<Obstacle>`로 모든 장애물 통합 관리
- `Iterator` 패턴으로 안전한 요소 제거

---

## 🎨 화면 구성

- **게임 창 크기**: 500 x 500 픽셀
- **배경색**: 검은색 (BLACK)
- **플레이어**: 30 x 30 픽셀 이미지
- **장애물**: 10 x 10 픽셀 노란색 원

---

## 📝 코드 하이라이트

### 다형성 활용 예시
```java
// 장애물 생성 시 랜덤하게 4방향 중 선택
int direction = (int) (Math.random() * 4);
switch (direction) {
    case 0 -> obstacles.add(new ObstacleUp());
    case 1 -> obstacles.add(new ObstacleDown());
    case 2 -> obstacles.add(new ObstacleLeft());
    case 3 -> obstacles.add(new ObstacleRight());
}

// 모든 장애물을 동일한 방식으로 처리 (다형성)
for (Obstacle obstacle : obstacles) {
    obstacle.paintObstacle(g);  // 각 장애물의 구현된 메서드 호출
}
```

### 난이도 증가 로직
```java
// 10초 후 자동 난이도 증가
levelUpTimer = new Timer(10000, e -> {
    createObstacleTimer.stop();
    moreCreateObstacleTimer.start();
    moveObstacleTimer.stop();
    moreMoveObstacleTimer.start();
});
levelUpTimer.setRepeats(false);
levelUpTimer.start();
```

---

이 프로젝트는 Java Swing의 기본 개념과 게임 개발의 핵심 요소(이벤트 처리, 충돌 감지, 타이머 관리)를 학습하기에 적합한 예제입니다.