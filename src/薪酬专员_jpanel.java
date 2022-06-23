import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class 薪酬专员_jpanel extends JPanel {

	public 薪酬专员_jpanel() {

		setSize(1080, 720);
		JLabel l1 = new JLabel("薪酬专员");

		JButton 录入薪酬标准按钮 = new JButton("录入薪酬标准");
		JButton 修改薪酬标准按钮 = new JButton("查询/修改标准");
		JButton backButton = new JButton("返回");
		this.setLayout(null);// jPanel布局管理器取消默认设置

		this.add(l1);
		this.add(录入薪酬标准按钮);
		this.add(修改薪酬标准按钮);
		this.add(backButton);
		l1.setFont(new Font("黑体", Font.PLAIN, 32));
		l1.setBounds(335, 50, 300, 100);
		录入薪酬标准按钮.setBounds(300, 150, 200, 50);
		修改薪酬标准按钮.setBounds(300, 220, 200, 50);
		backButton.setBounds(300, 290, 200, 50);

		录入薪酬标准按钮.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				录入薪酬标准_jpanel 录入薪酬标准= new 录入薪酬标准_jpanel();
				add(录入薪酬标准);
				repaint();

			}
		});

		修改薪酬标准按钮.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				修改薪酬标准_jpanel 修改薪酬标准 = new 修改薪酬标准_jpanel();
				add(修改薪酬标准);
				repaint();

			}
		});

		backButton.addActionListener(new ActionListener() //
		{
			public void actionPerformed(ActionEvent e) {

				removeAll();
				身份选择_jpanel 身份选择 = new 身份选择_jpanel();
				add(身份选择);
				repaint();

			}
		});

	}
}
