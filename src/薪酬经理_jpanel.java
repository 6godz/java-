import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class 薪酬经理_jpanel extends JPanel{
	
	public 薪酬经理_jpanel() {

		setSize(1080, 720);
		JLabel l1 = new JLabel("薪酬经理");

		JButton 审核与修改薪酬标准按钮 = new JButton("审核/修改薪酬标准");
		JButton 查看个人薪酬发放情况 = new JButton("个人薪酬发放情况");
		JButton backButton = new JButton("返回");
		this.setLayout(null);// jPanel布局管理器取消默认设置

		this.add(l1);
		this.add(审核与修改薪酬标准按钮);
		this.add(查看个人薪酬发放情况);
		this.add(backButton);
		l1.setFont(new Font("黑体", Font.PLAIN, 32));
		l1.setBounds(335, 50, 300, 100);
		审核与修改薪酬标准按钮.setBounds(280, 150, 250, 70);
		查看个人薪酬发放情况.setBounds(280, 240, 250, 70);
		backButton.setBounds(280, 330, 250, 70);

		审核与修改薪酬标准按钮.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				审核与修改薪酬标准_jpanel 审核与修改薪酬标准 = new 审核与修改薪酬标准_jpanel();
				add(审核与修改薪酬标准);
				repaint();

			}
		});

		查看个人薪酬发放情况.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAll();
				个人薪酬发放情况_jpanel 个人薪酬发放情况 = new 个人薪酬发放情况_jpanel();
				add(个人薪酬发放情况);
				repaint();

			}
		});

		backButton.addActionListener(new ActionListener() // 监听人事经理按钮
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
