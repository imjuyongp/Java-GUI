package JFrame.eventprogramming;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame extends JFrame implements ActionListener {

  private JButton button;
  private JLabel label;

  public MyFrame() {
    this.setSize(300, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("이벤트 예제");
    JPanel panel = new JPanel();
    button = new JButton("버튼을 누르시오");
    label = new JLabel("아직 버튼이 눌려지지 않았습니다.");
    button.addActionListener(this); // 버튼에 이벤트 리스너 등록
    panel.add(button);
    panel.add(label);
    this.add(panel); // JFrame에 panel을 띄움
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == button) {
      label.setText("마침내 버튼이 눌러졌습니다.");
    }
  }

  /*// 내부 클래스로 작성
  private class MyListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == button) {
        label.setText("마침내 버튼이 눌러졌습니다.");
      }
    }
  }*/

}

