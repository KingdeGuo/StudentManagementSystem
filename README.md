# Java 学生管理系统 StudentManagementSystem
Student Management System Written by Java with MySQL

# 欢迎大家关注我的公众号【kingdeguo】
![https://github.com/KingdeGuo/myPictureBed/blob/main/img_upload2023/%E5%85%AC%E4%BC%97%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpeg](https://github.com/KingdeGuo/myPictureBed/blob/main/img_upload2023/%E5%85%AC%E4%BC%97%E5%8F%B7%E4%BA%8C%E7%BB%B4%E7%A0%81.jpeg)
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

# 赞赏作者
![https://github.com/KingdeGuo/myPictureBed/blob/main/img_upload2023/%E6%94%B6%E6%AC%BE%E7%A0%81.jpg](https://github.com/KingdeGuo/myPictureBed/blob/main/img_upload2023/%E6%94%B6%E6%AC%BE%E7%A0%81.jpg)