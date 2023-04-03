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
        return this.rowData.size();
    }

    // 获取列数
    public int getColumnCount() {
        return this.columnNames.size();
    }

    // 获取目标行及目标列的数据
    public Object getValueAt(int row, int column) {
        return ((Vector) (this.rowData.get(row))).get(column);
    }

    // 获取属性字段名字
    public String getColumnName(int column) {
        return (String) this.columnNames.get(column);
    }
}

