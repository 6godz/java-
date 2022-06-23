import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class 身份选择_jpanel extends JPanel {
	public 身份选择_jpanel() {
		setSize(1080, 720);
		JLabel l1 = new JLabel("身份选择");
		JButton b1 = new JButton("人事专员");
		JButton b2 = new JButton("人事经理");
		JButton b3 = new JButton("薪酬专员");
		JButton b4 = new JButton("薪酬经理");
		JButton exitButton = new JButton("退出");
		setLayout(null);// jPanel布局管理器取消默认设置

		add(l1);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(exitButton);
		l1.setFont(new Font("黑体", Font.PLAIN, 32));
		l1.setBounds(335, 50, 300, 100);
		b1.setBounds(300, 150, 200, 50);
		b2.setBounds(300, 220, 200, 50);
		b3.setBounds(300, 290, 200, 50);
		b4.setBounds(300, 360, 200, 50);
		exitButton.setBounds(300, 430, 200, 50);
		b1.addActionListener(new ActionListener() // 监听人事专员按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				人事专员_jpanel 人事专员 = new 人事专员_jpanel();
				add(人事专员);
				repaint();
			}
		});

		b2.addActionListener(new ActionListener() // 监听人事经理按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				人事经理_jpanel 人事经理 = new 人事经理_jpanel();
				add(人事经理);
				repaint();
			}
		});

		b3.addActionListener(new ActionListener() // 监听薪酬专员按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				薪酬专员_jpanel 薪酬专员 = new 薪酬专员_jpanel();
				add(薪酬专员);
				repaint();
			}
		});

		b4.addActionListener(new ActionListener() // 监听薪酬经理按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				薪酬经理_jpanel 薪酬经理 = new 薪酬经理_jpanel();
				add(薪酬经理);
				repaint();
			}
		});

		exitButton.addActionListener(new ActionListener() // 退出按钮
		{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

	}
}