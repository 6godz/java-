import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class 修改薪酬标准_jpanel extends JPanel {
	public 修改薪酬标准_jpanel() {
		setSize(1080, 720);
		String[][] datas = {};
		String[] titles = { "薪酬标准编号", "薪酬标准名称", "职位名称", "薪酬总额", "制定人", "登记人", "登记时间", "基本工资", "交通补助", "午餐补助", "通信补助",
				"养老保险", "失业保险", "医疗保险", "住房公积金", "标准状态" };
		DefaultTableModel myModel = new DefaultTableModel(datas, titles);// myModel存放表格的数据
		JTable table = new JTable(myModel);// 表格对象table的数据来源是myModel对象
		JScrollPane scrollPane = new JScrollPane(table);// 带滚动条的面板
		table.setRowHeight(20);// 行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 将表格自动调整的状态给关闭掉
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 设置水平滚动条需要时可见
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		TableColumn tableColumn = table.getColumn("标准状态");  
		//DefaultTableCellRenderer类可以绘制单元格的背景、字体颜色等功能   
		DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();   
		 //绘制类别列的背景为蓝色   
		backGroundColor.setForeground(Color.BLUE);   
		tableColumn.setCellRenderer(backGroundColor); 
		table.setSelectionForeground(Color.red);

		JLabel l11 = new JLabel("当前正在做的业务是:");
		JLabel l12 = new JLabel("<html><u>首页--></u><html>");
		l12.setForeground(Color.blue);
		JLabel l13 = new JLabel("<html><u>薪酬专员--></u><html>");
		l13.setForeground(Color.blue);
		JLabel l14 = new JLabel("修改薪酬标准");

		JButton b2 = new JButton("刷新");// 刷新按钮
		JButton b3 = new JButton("修改");// 修改按钮
		JButton backButton = new JButton("返回");// 返回按钮

		setLayout(null);// jPanel布局管理器取消默认设置

		
		add(scrollPane);

		add(l11);
		add(l12);
		add(l13);
		add(l14);

		add(b2);
		add(b3);
		add(backButton);
		scrollPane.setBounds(20, 100, 1000, 500);// 通信补助输入框位置

		l11.setBounds(50, 10, 150, 25);
		l12.setBounds(200, 10, 50, 25);
		l13.setBounds(270, 10, 75, 25);
		l14.setBounds(350, 10, 75, 25);

		b2.setBounds(200, 50, 150, 30);// 清空按钮位置
		b3.setBounds(400, 50, 150, 30);// 修改按钮位置
		backButton.setBounds(600,50, 150, 30);// 返回按钮位置

		// b1.setEnabled(false);
		l12.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l13.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() <= l12.getX() + l12.getWidth() && e.getX() >= l12.getX()
						&& e.getY() <= l12.getY() + l12.getHeight() && e.getY() >= l12.getY()) {
					removeAll();
					身份选择_jpanel 身份选择 = new 身份选择_jpanel();
					add(身份选择);
					repaint();
				}
				if (e.getX() <= l13.getX() + l13.getWidth() && e.getX() >= l13.getX()
						&& e.getY() <= l13.getY() + l13.getHeight() && e.getY() >= l13.getY()) {
					removeAll();
					薪酬专员_jpanel 薪酬专员 = new 薪酬专员_jpanel();
					add(薪酬专员);
					repaint();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		
		


		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=人力资源管理系统2"; // 人力资源管理系统为数据库名
		String userName = "sa"; // 账号
		String userPwd = "sa"; // 填写密码
		
		try {
			Class.forName(driverName);
			Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			Statement st = dbConn.createStatement();
			System.out.println("连接数据库成功");
			String strSQL = "select * from dbo.薪酬标准表";
			ResultSet rs = st.executeQuery(strSQL);
			if (myModel.getRowCount() != 0) {
				int num = myModel.getRowCount();
				for (int i = num - 1; i >= 0; i--) {
					myModel.removeRow(i);
				}
			}
			while (rs.next()) {
				Vector<String> ve = new Vector<String>();
				ve.addElement(rs.getString(1));
				ve.addElement(rs.getString(2));
				ve.addElement(rs.getString(3));
				ve.addElement(rs.getString(4));
				ve.addElement(rs.getString(5));
				ve.addElement(rs.getString(6));
				ve.addElement(rs.getString(7));
				ve.addElement(rs.getString(8));
				ve.addElement(rs.getString(9));
				ve.addElement(rs.getString(10));
				ve.addElement(rs.getString(11));
				ve.addElement(rs.getString(12));
				ve.addElement(rs.getString(13));
				ve.addElement(rs.getString(14));
				ve.addElement(rs.getString(15));
				ve.addElement(rs.getString(16));
				myModel.addRow(ve); // 添加一行到模型结尾
			}
			dbConn.close();

		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.print("连接失败");
		}


		b2.addActionListener(new ActionListener() // 刷新按钮
				{
					public void actionPerformed(ActionEvent e) {
						removeAll();
						修改薪酬标准_jpanel 修改薪酬标准 = new 修改薪酬标准_jpanel();
						add(修改薪酬标准);
						repaint();
					}
				});
		
		b3.addActionListener(new ActionListener() // 修改按钮
		{
			public void actionPerformed(ActionEvent e) {
				int count = table.getSelectedRow();// 获取选中的行
				if (count != -1) {
					try {
						Class.forName(driverName);
						Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
						Statement st = dbConn.createStatement();
						System.out.println("连接数据库成功");
						String 薪酬标准编号 = table.getValueAt(count, 0).toString();
						String strSQL = "update 薪酬标准表 set 薪酬标准名称='" + table.getValueAt(count, 1) + "',职位名称='"
								+ table.getValueAt(count, 2) + "',制定人='" + table.getValueAt(count, 4) + "',登记人='"
								+ table.getValueAt(count, 5) + "',基本工资=" + table.getValueAt(count, 7) + ",交通补助="
								+ table.getValueAt(count, 8) + ",午餐补助=" + table.getValueAt(count, 9) + ",通信补助="
								+ table.getValueAt(count, 10) + ",标准状态='待复核'  where 薪酬标准编号=" + 薪酬标准编号;
						boolean rs = st.execute(strSQL);
						System.out.print(rs);
						JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.DEFAULT_OPTION);
						dbConn.close();
					} catch (Exception ee) {
						ee.printStackTrace();
						System.out.print("连接失败");
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先选择需要修改的一行！", "提示", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

	

		backButton.addActionListener(new ActionListener() // 返回按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				薪酬专员_jpanel 薪酬专员 = new 薪酬专员_jpanel();
				add(薪酬专员);
				repaint();
			}
		});

	}

}
