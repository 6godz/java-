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
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
public class 查询与修改_jpanel extends JPanel {
	public 查询与修改_jpanel() {
		setSize(1080, 720);
		setLayout(null);// jPanel布局管理器取消默认设置
		JLabel l1 = new JLabel("一级机构:");
		JLabel l2 = new JLabel("二级机构:");
		JLabel l3 = new JLabel("三级机构:");
		JLabel l4 = new JLabel("职位:");
		JLabel l5 = new JLabel("建档时间:");
		JLabel l6 = new JLabel("至");
		JComboBox<String> c1 = new JComboBox<String>();// 一级机构下拉框
		JComboBox<String> c2 = new JComboBox<String>();// 二级机构下拉框
		JComboBox<String> c3 = new JComboBox<String>();// 三级机构下拉框
		JComboBox<String> c4 = new JComboBox<String>();// 职位下拉框

		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=人力资源管理系统2"; // 人力资源管理系统为数据库名
		String userName = "sa"; // 账号
		String userPwd = "sa"; // 填写密码
		
		try {
			Class.forName(driverName);
			Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			Statement st = dbConn.createStatement();
			System.out.println("连接数据库成功1");
			String strSQL1 = "select 单位名称 from 机构与职位表 where 单位关系 like '_'";
			ResultSet rs1 = st.executeQuery(strSQL1);
			while (rs1.next()) {
				c1.addItem(rs1.getString(1));
			}
			
			String strSQL3 = "select 单位名称 from 机构与职位表 where 单位关系='0.0.0'";
			ResultSet rs3 = st.executeQuery(strSQL3);
			while (rs3.next()) {
				c3.addItem(rs3.getString(1));
			}
			
			String strSQL4 = "select 单位名称 from 机构与职位表 where 单位关系='0.0.0.0'";
			ResultSet rs4 = st.executeQuery(strSQL4);
			while (rs4.next()) {
				c4.addItem(rs4.getString(1));
			}
			dbConn.close();
		} catch (Exception ee) {
			ee.printStackTrace();
			System.out.print("连接失败");
		}
		
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c2.removeAllItems();
				try {
					Class.forName(driverName);
					Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					Statement st = dbConn.createStatement();
					System.out.println("连接数据库成功1");
					
					String strSQL1 = "declare @单位关系 varchar(10) set @单位关系=(select 单位关系 from 机构与职位表 where 单位名称='"
							+ c1.getSelectedItem() + "' ) select 单位名称 from 机构与职位表 where 单位关系 like @单位关系+'._'";
					ResultSet rs1 = st.executeQuery(strSQL1);
					while (rs1.next()) {
						c2.addItem(rs1.getString(1));
					}
					dbConn.close();
				} catch (Exception ee) {
					ee.printStackTrace();
					System.out.print("连接失败");
				}

			}
		});
		
		JTextField t1 = new JTextField();// 查找时间范围
		JTextField t2 = new JTextField();

		JButton b1 = new JButton("按条件查询");// 查找按钮
		JButton b2 = new JButton("清空");// 清空按钮
		JButton b3 = new JButton("显示所有");// 显示所有员工按钮
		JButton b4 = new JButton("修改");// 修改按钮
		JButton backButton = new JButton("返回");// 返回按钮

		JLabel l21 = new JLabel("当前正在做的业务是:");
		JLabel l22 = new JLabel("<html><u>首页--></u><html>");
		l22.setForeground(Color.blue);
		JLabel l23 = new JLabel("<html><u>人事专员--></u><html>");
		l23.setForeground(Color.blue);
		JLabel l24 = new JLabel("查询与修改员工资料");

		String[][] datas = {};
		String[] titles = { "档案编号", "员工编号", "一级机构", "二级机构", "三级机构", "职位", "姓名", "性别", "Email", "电话", "QQ", "住址", "国籍",
				"民族", "年龄", "学历", "薪酬标准", "个人履历", "家庭关系信息", "备注", "登记人", "登记时间", "状态" };
		DefaultTableModel myModel = new DefaultTableModel(datas, titles);// myModel存放表格的数据
		JTable table = new JTable(myModel);// 表格对象table的数据来源是myModel对象
		JScrollPane scrollPane = new JScrollPane(table);// 带滚动条的面板
		table.setRowHeight(20);// 行高
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 将表格自动调整的状态给关闭掉
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 设置水平滚动条需要时可见
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		table.setSelectionForeground(Color.red);

		TableColumn tableColumn = table.getColumn("状态");
		// DefaultTableCellRenderer类可以绘制单元格的背景、字体颜色等功能
		DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();
		// 绘制类别列的背景为蓝色
		backGroundColor.setForeground(Color.BLUE);
		tableColumn.setCellRenderer(backGroundColor);

		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(l5);
		add(l6);

		add(c1);
		add(c2);
		add(c3);
		add(c4);

		add(t1);
		add(t2);

		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(backButton);
		add(scrollPane);

		add(l21);
		add(l22);
		add(l23);
		add(l24);

		l1.setBounds(70, 50, 75, 25);// 一级机构位置
		c1.setBounds(125, 50, 100, 25);
		l2.setBounds(245, 50, 75, 25);// 二级机构位置
		c2.setBounds(300, 50, 100, 25);
		l3.setBounds(420, 50, 75, 25);// 三级机构位置
		c3.setBounds(475, 50, 100, 25);
		l4.setBounds(595, 50, 75, 25);// 职位位置
		c4.setBounds(650, 50, 100, 25);
		l5.setBounds(770, 50, 75, 25);// 建档时间
		t1.setBounds(825, 50, 75, 25);
		l6.setBounds(900, 50, 25, 25);
		t2.setBounds(925, 50, 75, 25);

		b1.setBounds(75, 100, 150, 30);// 查找按钮位置
		b2.setBounds(275, 100, 150, 30);// 清空按钮位置
		b3.setBounds(475, 100, 150, 30);// 显示所有员工按钮位置
		b4.setBounds(675, 100, 150, 30);// 修改按钮位置
		backButton.setBounds(875, 100, 150, 30);// 返回按钮位置
		scrollPane.setBounds(50, 150, 950, 500);

		l21.setBounds(50, 10, 150, 25);
		l22.setBounds(200, 10, 50, 25);
		l23.setBounds(270, 10, 75, 25);
		l24.setBounds(350, 10, 150, 25);

		l22.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l23.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() <= l22.getX() + l22.getWidth() && e.getX() >= l22.getX()
						&& e.getY() <= l22.getY() + l22.getHeight() && e.getY() >= l22.getY()) {
					removeAll();
					身份选择_jpanel 身份选择 = new 身份选择_jpanel();
					add(身份选择);
					repaint();
				}
				if (e.getX() <= l23.getX() + l23.getWidth() && e.getX() >= l23.getX()
						&& e.getY() <= l23.getY() + l23.getHeight() && e.getY() >= l23.getY()) {
					removeAll();
					人事专员_jpanel 人事专员 = new 人事专员_jpanel();
					add(人事专员);
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

		b1.addActionListener(new ActionListener() // 查询按钮
		{
			public void actionPerformed(ActionEvent e) {
				if (t1.getText().length() != 0 && t2.getText().length() != 0) {
					try {
						Class.forName(driverName);
						Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
						Statement st = dbConn.createStatement();
						System.out.println("连接数据库成功");
						String strSQL = "select * from dbo.员工资料表 where 一级机构='" + c1.getSelectedItem() + "' and 二级机构='"
								+ c2.getSelectedItem() + "' and 三级机构='" + c3.getSelectedItem() + "' and 职位='"
								+ c4.getSelectedItem() + "' and 登记时间 between convert(date,'" + t1.getText()
								+ "',120) and CONVERT(date,'" + t2.getText() + "',120)";
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
							ve.addElement(rs.getString(17));
							ve.addElement(rs.getString(18));
							ve.addElement(rs.getString(19));
							ve.addElement(rs.getString(20));
							ve.addElement(rs.getString(21));
							ve.addElement(rs.getString(22));
							ve.addElement(rs.getString(23));
							myModel.addRow(ve); // 添加一行到模型结尾

						}
						dbConn.close();

					} catch (Exception ee) {
						ee.printStackTrace();
						System.out.print("连接失败");
					}
				} else {
					JOptionPane.showMessageDialog(null, "建档时间不能留空！", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		b2.addActionListener(new ActionListener() // 清空按钮
		{
			public void actionPerformed(ActionEvent e) {
				removeAll();
				查询与修改_jpanel 查询与修改 = new 查询与修改_jpanel();// 重新打开此页面
				add(查询与修改);
				repaint();
			}
		});

		b3.addActionListener(new ActionListener() // 显示所有员工信息按钮
		{
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName(driverName);
					Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
					Statement st = dbConn.createStatement();

					System.out.println("连接数据库成功");

					String strSQL = "select * from dbo.员工资料表";
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
						ve.addElement(rs.getString(17));
						ve.addElement(rs.getString(18));
						ve.addElement(rs.getString(19));
						ve.addElement(rs.getString(20));
						ve.addElement(rs.getString(21));
						ve.addElement(rs.getString(22));
						ve.addElement(rs.getString(23));
						myModel.addRow(ve); // 添加一行到模型结尾

					}
					dbConn.close();

				} catch (Exception ee) {
					ee.printStackTrace();
					System.out.print("连接失败");
				}

			}
		});

		b4.addActionListener(new ActionListener() // 修改按钮
		{
			public void actionPerformed(ActionEvent e) {
				int count = table.getSelectedRow();// 获取选中的行
				if (count != -1) {
					String s1 = table.getValueAt(count, 22).toString();
					String s2 = "已删除";
					if (!s1.equals(s2)) {
						try {
							Class.forName(driverName);
							Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
							Statement st = dbConn.createStatement();

							String strSQL = "update 员工资料表 set 姓名='" + table.getValueAt(count, 6) + "',性别='"
									+ table.getValueAt(count, 7) + "',Email='" + table.getValueAt(count, 8) + "',电话='"
									+ table.getValueAt(count, 9) + "',QQ='" + table.getValueAt(count, 10) + "',住址='"
									+ table.getValueAt(count, 11) + "',国籍='" + table.getValueAt(count, 12) + "',民族='"
									+ table.getValueAt(count, 13) + "',年龄=" + table.getValueAt(count, 14) + ",学历='"
									+ table.getValueAt(count, 15) + "',薪酬标准='" + table.getValueAt(count, 16)
									+ "',个人履历='" + table.getValueAt(count, 17) + "',家庭关系信息='"
									+ table.getValueAt(count, 18) + "',备注='" + table.getValueAt(count, 19) + "',登记人='"
									+ table.getValueAt(count, 20) + "',状态='待复核'  where 档案编号='"
									+ table.getValueAt(count, 0) + "'";
							boolean rs = st.execute(strSQL);
							System.out.println(rs);
							JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.DEFAULT_OPTION);
							dbConn.close();

						} catch (Exception ee) {
							ee.printStackTrace();
							System.out.print("连接失败");
						}
					} else {
						JOptionPane.showMessageDialog(null, "不能修改已删除的数据！", "提示", JOptionPane.ERROR_MESSAGE);
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
				人事专员_jpanel 人事专员 = new 人事专员_jpanel();
				add(人事专员);
				repaint();
			}
		});
	}
}
