package homework.second;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ternary extends JFrame {

    public Ternary() {
        add(new MyPanel());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class MyPanel extends JPanel implements ActionListener {
        JButton zero, one, two, plus, equals;
        JLabel label;

        // 계산에 필요한 변수들
        private String currentInput = "";  // 현재 입력 중인 숫자
        private String firstOperand = "";   // 첫 번째 피연산자
        private boolean operatorPressed = false;  // + 버튼이 눌렸는지 확인

        public MyPanel() {
            setLayout(new BorderLayout());

            // 라벨 생성 및 배치
            label = new JLabel("0", SwingConstants.RIGHT);

            // 버튼 패널 생성
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(2, 3, 10, 10));

            zero = new JButton("0");
            one = new JButton("1");
            two = new JButton("2");
            plus = new JButton("+");
            equals = new JButton("=");

            /*// 버튼 폰트 설정
            Font buttonFont = new Font("Arial", Font.BOLD, 20);
            zero.setFont(buttonFont);
            one.setFont(buttonFont);
            two.setFont(buttonFont);
            plus.setFont(buttonFont);
            equals.setFont(buttonFont);*/

            zero.addActionListener(this);
            one.addActionListener(this);
            two.addActionListener(this);
            plus.addActionListener(this);
            equals.addActionListener(this);

            // 버튼 패널에 추가
            buttonPanel.add(zero);
            buttonPanel.add(one);
            buttonPanel.add(two);
            buttonPanel.add(plus);
            buttonPanel.add(equals);

            // 메인 패널에 추가
            this.add(label, BorderLayout.NORTH);
            this.add(buttonPanel, BorderLayout.CENTER);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == zero) {
                // 0 버튼 클릭
                currentInput += "0";
                label.setText(currentInput);

            } else if (e.getSource() == one) {
                // 1 버튼 클릭
                currentInput += "1";
                label.setText(currentInput);

            } else if (e.getSource() == two) {
                // 2 버튼 클릭
                currentInput += "2";
                label.setText(currentInput);

            } else if (e.getSource() == plus) {
                // + 버튼 클릭
                if (!currentInput.isEmpty()) {
                    firstOperand = currentInput;
                    currentInput = "";
                    operatorPressed = true;
                    label.setText(firstOperand + " +");
                }

            } else if (e.getSource() == equals) {
                // = 버튼 클릭
                if (operatorPressed && !currentInput.isEmpty() && !firstOperand.isEmpty()) {
                    // 3진수를 10진수로 변환
                    int num1 = ternaryToDecimal(firstOperand);
                    int num2 = ternaryToDecimal(currentInput);

                    // 덧셈 수행
                    int result = num1 + num2;

                    // 10진수를 3진수로 변환
                    String ternaryResult = decimalToTernary(result);

                    // 결과 표시
                    label.setText(firstOperand + " + " + currentInput + " = " + ternaryResult);

                    // 초기화
                    currentInput = ternaryResult;
                    firstOperand = "";
                    operatorPressed = false;
                }
            }
        }

        // 3진수를 10진수로 변환
        private int ternaryToDecimal(String ternary) {
            int decimal = 0;
            int power = 0;

            // 오른쪽부터 왼쪽으로 계산
            for (int i = ternary.length() - 1; i >= 0; i--) {
                int digit = ternary.charAt(i) - '0';
                decimal += digit * Math.pow(3, power);
                power++;
            }

            return decimal;
        }

        // 10진수를 3진수로 변환
        private String decimalToTernary(int decimal) {
            if (decimal == 0) {
                return "0";
            }

            StringBuilder ternary = new StringBuilder();

            while (decimal > 0) {
                int remainder = decimal % 3;
                ternary.insert(0, remainder);
                decimal = decimal / 3;
            }

            return ternary.toString();
        }
    }
}
