# Java 学生管理系统 StudentManagementSystem
Student Management System Written by Java with MySQL

# 欢迎大家关注我的公众号【kingdeguo】
![](公众号二维码.jpeg)
# 项目目标
这篇文章是Java语言得课程设计大作业记录。 
- 项目由**Java**和**Mysql**实现。 
- 本片文章包含完整的**源程序代码**和**结构图**。 
- 实现对学生信息的**增**、**删**、**改**、**查**功能

项目地址: https://github.com/KingdeGuo/StudentManagementSystem
<br>
## 项目截图展示

- 软件初始界面

  ![https://img-blog.csdnimg.cn/2020092222350661.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/2020092222350661.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)

- 查询界面（按照姓名查找）

  ![https://img-blog.csdnimg.cn/20200922223552184.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922223552184.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)

- 添加学生信息界面

修改删除界面

    ![https://img-blog.csdnimg.cn/20200922223637859.png#pic_center](https://img-blog.csdnimg.cn/20200922223637859.png#pic_center)
    
    ![https://img-blog.csdnimg.cn/20200922223727955.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922223727955.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)


## 项目Java源程序

**各个源文件的名字在第一行的注释信息中给出**，注意自己在保存的时候使用正确的命明，否则可能找不到包的位置或者类的位置。
    另外需要注意的是**我使用的是数据库Mysql8版本的**，一般学生可能使用的Mysql5版本的人比较多。**两个版本在连接数据库时使用的语法是不同的**。**具体应使用什么连接语句在像相应的代码位置处已给出**，根据自身环境修改以下即可。
    还有一个需要修改的地方就是连接数据库时采用的账号和密码。一般账号是root，密码是你自己设置的，需要在代码中修改，否则是连接不上的。

```
//filename :Test3.java  这个是主程序
//package SchoolProject.src;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test3 extends JFrame implements ActionListener {
	// 定义一些控件
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2, jb3, jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	// 定义连接数据库的变量
	Statement stat = null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		Test3 test3 = new Test3();
	}

	// 构造函数
	public Test3() {
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("查询");
		jb1.addActionListener(this);
		jl1 = new JLabel("请输入名字：");

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jb2 = new JButton("添加");
		jb2.addActionListener(this);
		jb3 = new JButton("修改");
		jb3.addActionListener(this);
		jb4 = new JButton("删除");
		jb4.addActionListener(this);

		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);

		// 创建模型对象
		sm = new StuModel();

		// 初始化
		jt = new JTable(sm);

		jsp = new JScrollPane(jt);

		// 将jsp放入到jframe中
		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		this.setSize(600, 400);
		// this.setLocation(300, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {
		// 判断是哪个按钮被点击
		if (arg0.getSource() == jb1) {
			System.out.println("用户希望被查询...");
			// 因为把对表的数据封装到StuModel中，可以比较简单的完成查询
			String name = this.jtf.getText().trim();
			// 写一个sql语句
			String sql = "select * from stu where stuName = '" + name + "' ";
			// 构建一个数据模型类，并更新
			sm = new StuModel(sql);
			// 更新jtable
			jt.setModel(sm);

		}

		// 一、弹出添加界面
		else if (arg0.getSource() == jb2) {
			System.out.println("添加...");
			StuAddDiag sa = new StuAddDiag(this, "添加学生", true);

			// 重新再获得新的数据模型,
			sm = new StuModel();
			jt.setModel(sm);
		} else if (arg0.getSource() == jb4) {
			// 二、删除记录
			// 1.得到学生的ID
			int rowNum = this.jt.getSelectedRow();// getSelectedRow会返回给用户点中的行
			// 如果该用户一行都没有选，就返回-1
			if (rowNum == -1) {
				// 提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return;
			}
			// 得到学术ID
			String stuId = (String) sm.getValueAt(rowNum, 0);
			System.out.println("Id： " + stuId);

			// 连接数据库,完成删除任务
			try {
				// 1.加载驱动
//				Class.forName("com.mysql.jdbc.Driver");
				Class.forName("com.mysql.cj.jdbc.Driver");
				// 2.连接数据库
				String url = "jdbc:mysql://localhost:3306/stuinfo?useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String user = "root";
				String passwd = "1234";

				ct = DriverManager.getConnection(url, user, passwd);
				System.out.println("连接成功");
				ps = ct.prepareStatement("delete from stu where stuId = ?");
				ps.setString(1, stuId);
				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;

					}
					if (ps != null) {
						ps.close();
						ps = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			sm = new StuModel();
			// 更新jtable
			jt.setModel(sm);
		} else if (arg0.getSource() == jb3) {
			System.out.println("11111");
			// 三、用户希望修改
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// 提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			// 显示对话框
			System.out.println("12435");
			StuUpDiag su = new StuUpDiag(this, "修改学号", true, sm, rowNum);
			sm = new StuModel();
			jt.setModel(sm);
		}
	}
}

```

```
//filename: StuUpDiag.java
//package SchoolProject.src;

/*
 * 修改学生
 */
import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class StuUpDiag extends JDialog implements ActionListener {
	// 定义我需要的swing组件
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jf1, jf2, jf3, jf4, jf5, jf6;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;

	// owner代笔父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
	public StuUpDiag(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		// 调用父类方法
		super(owner, title, modal);

		jl1 = new JLabel("学号");

		jl2 = new JLabel("名字");

		jl3 = new JLabel("性别");
		jl4 = new JLabel("年龄");
		jl5 = new JLabel("专业");

		jl6 = new JLabel("院系");

		jf1 = new JTextField(10);
		jf1.setText((sm.getValueAt(rowNum, 0)).toString());
		jf2 = new JTextField(10);
		jf2.setText((String) sm.getValueAt(rowNum, 1));
		jf3 = new JTextField(10);
		jf3.setText(sm.getValueAt(rowNum, 2).toString());
		jf4 = new JTextField(10);
		jf4.setText((sm.getValueAt(rowNum, 3)).toString());
		jf5 = new JTextField(10);
		jf5.setText((String) sm.getValueAt(rowNum, 4));
		jf6 = new JTextField(10);
		jf6.setText((String) sm.getValueAt(rowNum, 5));

		jb1 = new JButton("修改");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 设置布局
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		jp3.add(jb1);
		jp3.add(jb2);

		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		jp2.add(jf1);
		jp2.add(jf2);
		jp2.add(jf3);
		jp2.add(jf4);
		jp2.add(jf5);
		jp2.add(jf6);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		this.setSize(300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			Connection ct = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				// 1.加载驱动
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("加载成功");
				// 2.连接数据库
				// 定义几个常量
				String url = "jdbc:mysql://localhost:3306/stuinfo?useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String user = "root";
				String passwd = "1234";
				ct = DriverManager.getConnection(url, user, passwd);

				// 与编译语句对象

				String strsql = "insert into stu values(?,?,?,?,?,?)";
				pstmt = ct.prepareStatement(strsql);

				// 给对象赋值
				pstmt.setString(1, jf1.getText());
				pstmt.setString(2, jf2.getText());
				pstmt.setString(3, jf3.getText());
				pstmt.setString(4, jf4.getText());
				pstmt.setString(5, jf5.getText());
				pstmt.setString(6, jf6.getText());

				pstmt.executeUpdate();

				this.dispose();// 关闭学生对话框

			} catch (Exception arg1) {
				arg1.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception arg2) {
					arg2.printStackTrace();
				}
			}

		}

	}

}

```

```
//filename:StuModel.java
//package SchoolProject.src;

// 导入数据库相关包
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
// 导入向量包
import java.util.Vector;
// 导入Java界面组件
import javax.swing.table.*;

public class StuModel extends AbstractTableModel {
// 继承TableModel抽象类，实现学生模型
// 在rowData中存放行数据，在columnNames存放列名
	Vector rowData, columnNames;

	// 定义连接数据库的变量
	// 进行初始化，置为空
	Statement stat = null;
	Connection ct = null;
	ResultSet rs = null;

	// 初始化
	public void init(String sql) {
		if (sql.equals("")) {
			// 初始化时显示所有的存放在数据库中的数据
			sql = "select * from stu";
		}
		// 中间部分
		// 设置列名
		columnNames = new Vector();//创建向量
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("专业");
		columnNames.add("院系");

		// rowData存放多行
		rowData = new Vector();

		try {
			// 1.加载驱动
			// 8版本MySQL使用的是"com.mysql.cj.jdbc.Driver"，5版本使用的是"com.mysql.jdbc.Driver".
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("加载成功");//控制台提示
			// 2.连接数据库
			// 定义几个常量
			// 8版本需要加"?useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC"
			// 其中需要设置nullCatalogMeansCurrent=true，出现这种问题的原因是mysql版本问题，mysql8.xxx以上驱动会出现这个问题，下图是我原mysql配置，是8.0.16的。
			// 一开始因为使用的版本不一致导致总是连接不成功。后来驱动版本与数据库版本都统一之后连接成功。
			String url = "jdbc:mysql://localhost:3306/stuinfo?useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "root";  // 账号
			String passwd = "1234";  // 密码

			ct = DriverManager.getConnection(url, user, passwd);  // connection
			stat = ct.createStatement(); // 创建stat对象，prepare statement
			rs = stat.executeQuery(sql); // 查询结果

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				// 加入到rowData中
				rowData.add(hang);

			}

		} catch (Exception e) {
			// 捕获异常
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (stat != null) {
					stat.close();
					stat = null;
				}
				if (ct != null) {
					ct.close();
					ct = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 增加学生函数
	public void addStu(String sql) {
		// 根据用户输入的sql语句，完成添加任务

	}

	// 构造函数，传递的sql语句获取数据模型
	public StuModel(String sql) {
		this.init(sql);
	}

	// 构造学生模型，并进行初始化
	public StuModel() {
		this.init("");
	}

	// 获取行数
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// 获取列数
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// 获取目标行及目标列的数据
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector) (this.rowData.get(row))).get(column);
	}

	// 获取属性字段名字
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}
}

```

```
//filename:StuAddDiag.java
//package SchoolProject.src;
import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;

public class StuAddDiag extends JDialog implements ActionListener {
	// 定义我需要的swing组件
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jf1, jf2, jf3, jf4, jf5, jf6;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;

	// owner代笔父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
	public StuAddDiag(Frame owner, String title, boolean modal) {
		// 调用父类方法
		super(owner, title, modal);

		jl1 = new JLabel("学号");
		jl2 = new JLabel("名字");
		jl3 = new JLabel("性别");
		jl4 = new JLabel("年龄");
		jl5 = new JLabel("专业");
		jl6 = new JLabel("院系");

		jf1 = new JTextField(10);
		jf2 = new JTextField(10);
		jf3 = new JTextField(10);
		jf4 = new JTextField(10);
		jf5 = new JTextField(10);
		jf6 = new JTextField(10);

		jb1 = new JButton("添加");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 设置布局
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		jp3.add(jb1);
		jp3.add(jb2);

		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		jp2.add(jf1);
		jp2.add(jf2);
		jp2.add(jf3);
		jp2.add(jf4);
		jp2.add(jf5);
		jp2.add(jf6);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		this.setSize(300, 200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			Connection ct = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				// 1.加载驱动
				// 8版本MySQL使用的是"com.mysql.cj.jdbc.Driver"，5版本使用的是"com.mysql.jdbc.Driver".
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("加载成功");
				// 2.连接数据库
				// 定义几个常量
				// 8版本需要加"?useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC"
				// 其中需要设置nullCatalogMeansCurrent=true，出现这种问题的原因是mysql版本问题，mysql8.xxx以上驱动会出现这个问题，下图是我原mysql配置，是8.0.16的。
				// 一开始因为使用的版本不一致导致总是连接不成功。后来驱动版本与数据库版本都统一之后连接成功。
				String url = "jdbc:mysql://localhost:3306/stuinfo?useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
				String user = "root";
				String passwd = "1234";
				ct = DriverManager.getConnection(url, user, passwd);

				// 插入语句
				String strsql = "insert into stu values(?,?,?,?,?,?)";
				pstmt = ct.prepareStatement(strsql);

				// 给对象赋值
				pstmt.setString(1, jf1.getText());
				pstmt.setString(2, jf2.getText());
				pstmt.setString(3, jf3.getText());
				pstmt.setString(4, jf4.getText());
				pstmt.setString(5, jf5.getText());
				pstmt.setString(6, jf6.getText());

				pstmt.executeUpdate();

				this.dispose();// 关闭学生对话框

			} catch (Exception arg1) {
				arg1.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception arg2) {
					arg2.printStackTrace();
				}
			}

		}

	}

}

```

## 项目数据库文件信息

**注意要将数据库命名为 stu**

否则你需要改上面代码中数据库的名字。
下面是各个属性名称的设计以及字段的设计。

**这个名字也不能改**

，否则会造成无法正确读取库中的信息。
（当然也可以改库中的名字，不过你需要同时更改上述源程序中对应的名字。）

![https://img-blog.csdnimg.cn/20200922230041734.png#pic_center](https://img-blog.csdnimg.cn/20200922230041734.png#pic_center)

## 项目结构图设计

- 系统功能结构图：

![https://img-blog.csdnimg.cn/20200922230421196.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922230421196.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)

### 软件架构设计

**注意专业的应该用<font color="blue">Visio</font>画图**，当时电脑上没有成功装上Visio，便用了WPS的画图工具，导致上面留有水印。推荐使用Visio再画一遍。

- 逻辑架构

  ![https://img-blog.csdnimg.cn/20200922230521450.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922230521450.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)

- 开发架构

  ![https://img-blog.csdnimg.cn/20200922230543430.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922230543430.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)

- 数据架构

  ![https://img-blog.csdnimg.cn/20200922230601631.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922230601631.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)

- 运行架构

  ![https://img-blog.csdnimg.cn/20200922230617378.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922230617378.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)

- 物理架构

  ![https://img-blog.csdnimg.cn/20200922230630623.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center](https://img-blog.csdnimg.cn/20200922230630623.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80NDg5NTY2Ng==,size_16,color_FFFFFF,t_70#pic_center)