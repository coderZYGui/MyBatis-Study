# MyBatis-Study

## <span id="j0">目录</span>

- [一、走进MyBatis](#j1)
	- [1.1、什么是框架?](#j2)
	- [1.2、 框架的形成](#j3)
	- [1.3、 ORM思想](#j4)
	- [1.4、 常见的ORM框架](#j5)
- [二、 MyBatis概述](#j6)
	- [2.1、MyBatis架构图](#j7)
	- [2.2、MyBatis核心组件](#j8)
- [三、MyBatis入门](#j9)
	- [3.1、如何获得MyBatis？](#j10)
	- [3.2、配置文件](#j11)
	- [3.3、查询代码](#j12)
- [四、日志框架](#j13)
	- [4.1、为什么要用日志?](#j14)
	- [4.2、常见的日志框架](#j15)
	- [4.3、日志级别](#j16)
	- [4.4、日志文件的组成](#j17)
	- [4.5、日志的使用](#j18)
- [五、OGNL](#j19)
- [六、作用域和生命周期](#j21)

### [MyBatis文档](https://mybatis.org/mybatis-3/zh/index.html)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221095323566.png)

# <span id="j1">一、 走进MyBatis</span>
[<font size = 1>跳转到目录</font>](#j0)
### <span id="j2">1.1、什么是框架?</span>
[<font size = 1>跳转到目录</font>](#j0)
什么是框架,框架从何而来,为什么使用框架?
框架( framework) :
1. 是一系列jar包,其本质是对JDK功能的拓展。
2. 框架是一组程序的集合,包含了-系列的最佳实践,作用是解决某-一个领域的问题。

### <span id="j3">1.2、 框架的形成</span>
[<font size = 1>跳转到目录</font>](#j0)
最佳实践( Best Practice) :
- 实际上是无数程序员经历过无数次尝试之后,总结出来的处理特定问题的特定方法。
- 如果把程序员的自由发挥看作是一条通往成功的途径 ,最佳实践就是其中的最短路径,能极大的解放生产力

![Web开发中的最佳实践:根据职责的纵向划分:**控制层、业务层、持久层:**
控制层: web/mvc: 负责处理与界面交互的相关操作(Struts2/Spring MVC)
业务层: service:负责复杂的业务逻辑计算和判断(Spring)
持久层: dao:负责将业务逻辑数据进行持久化存储(MyBatis/Hibernate)](https://img-blog.csdnimg.cn/20200221101457614.png)

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221101725491.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)
### <span id="j4">1.3、 ORM思想</span>
[<font size = 1>跳转到目录</font>](#j0)
- 对象关系映射(Object Relational Mapping, 简称ORM): 是一种为了解决面向对象与关系数据库存在的互不匹配的问题的技术. 简单的说,ORM是通过使用描述对象和数据库之间映射的元数据, 将Java程序中的对象自动持久化到关系数据库中.

- ORM 主要解决**对象-关系的映射**

|面向对象概念  | 面向关系概念 |
|--|--|
| 类 | 表 |
| 对象 | 表的行(记录) |
| 属性 | 表的列(字段) |

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221103702710.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)
### <span id="j5">1.4、 常见的ORM框架</span>
[<font size = 1>跳转到目录</font>](#j0)
把对象数据库的操作封装成一套API,具有操作数据库的增删改查等操作,而且具有独立性,<font color=blue>当持久层技术层发生改变时,不用修改任何业务层代码.</font>

- JPA : 本身是一种ORM规范,不是ORM框架. 由各大ORM框架提供实现.
- Hibernate : 以前最流行的ORM框架.
- MyBatis : 是目前最受欢迎的持久层解决方案.


# <span id="j6">二、 MyBatis概述</span>
[<font size = 1>跳转到目录</font>](#j0)
- MyBatis 是一款优秀的持久层框架
- 它支持定制化 SQL、存储过程以及高级映射。
- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
- MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

### <span id="j7">2.1、 MyBatis架构图</span>
[<font size = 1>跳转到目录</font>](#j0)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221105013490.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)

###  <span id="j8">2.2、 MyBatis核心组件</span>
[<font size = 1>跳转到目录</font>](#j0)
- `SqISessionFactoryBuilder (构建器) `: 根据配置信息或Java代码来构建- SqlSessionFactory对象。作用:创建SqlSessionFactory对象。

- `SqlSessionFactory (会话工厂)` :好比是DataSource ,线程安全的,在应用运行期间不要重复创建多次,建议使用单例模式。
**作用:** 创建SqlSession对象

- `SqISession (会话)` :好比是**Connection** ,线程不安全的,每次使用开启新的SqlSession对象,使用完毕正常关闭,默认使用DefaultSqlSession。<font color=red>提供操作数据库的增删改查方法</font>,可以调用操作方法,也可以操作Mapper组件。
- `Executor (执行器)` :SqlSession本身不能直接操作数据库,需要Executor来完成,该接口有两个实现:缓存执行器(缺省)、基本执行器。

- `MappedStatement` :映射语句封装执行语句时的信息如SQL、输入参数、输出结果

**原理图:**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221105625838.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)
更具体的底层原理图: 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221105727470.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)
涉及的对象:

- **SqlSession** :表示和数据库交互的会话,完成必要数据库增删改查功能。
- Executor :执行器,是MyBatis调度的核心,负责SQL语句的生成和查询缓存的维护。
- StatementHandler :语句处理器,封装了JDBC的DML、DQL 操作、参数设置。- 
- ParameterHandler :参数处理器,把用户传入参数转换为JDBC需要的参数值。- 
- ResultSetHandler :结果集处理器,把结果集中的数据封装到List集合。- 
- TypeHandler :类型转换器, Java类型和JDBC类型的相互转换。
- MappedStatement :映射语句对象,维护了一条< insertlupdateldelete|select>节点的封装。
- SqlSource : SQL源,根据用户传入的参数生成SQL语句,并封装到BoundSql中。
- BoundSql : SQL绑定,封装SQL语句和对应的参数信息。
- Configuration : MyBatis 全局配置对象,封装所有配置信息。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221110725191.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)

# <span id="j9">三、 MyBatis入门</span>
[<font size = 1>跳转到目录</font>](#j0)
### <span id="j10">3.1、 如何获得MyBatis？</span>
[<font size = 1>跳转到目录</font>](#j0)
通过Maven仓库
```xml
<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.5.3</version>
</dependency>
```

### <span id="j11">3.2、 配置文件</span>
[<font size = 1>跳转到目录</font>](#j0)
MyBatis 自身的配置文件有两种:

- <font color=blue>**MyBatis全局配置文件/主配置文件.**
	+ 起名: 一般规定 `mybatis-config.xml`
	+ 路径: 放在resources资源路径下
	+ 参照: [mybatis文档的 ---- XML配置章节](https://mybatis.org/mybatis-3/zh/configuration.html)
	+ 内容: 
		+ 全局的配置信息
		+ 属性配置信息
		+ 插件配置信息
		+ <font color=red>配置环境信息(连接池+事务)
		+ <font color=red>关联映射文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--1.配置数据库的环境-->
    <environments default="development">
        <!--开发环境:在以后事务管理器和连接池都是交给Spring框架来管理-->
        <environment id="development">
            <!--事务管理器-->
            <transactionManager type="JDBC"/>
            <!--连接池-->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="1111"/>
            </dataSource>
        </environment>
    </environments>
    <!--2.关联映射文件-->
    <!--
        注意这里的路径是mybatis-config.xml文件找到UserMapper.xml的路径
        如果在java的目录下,找不到UserMapper.xml
     -->
    <mappers>
        <mapper resource="com/sunny/dao/UserMapper.xml"/>
    </mappers>
</configuration>
```

- <font color=blue>**MyBatis映射文件/Mapper文件**
	+ 起名: 一般见名知意 `XxxMapper.xml`, Xxx表示模型对象 
	+ 路径: Mapper文件应该放到Mapper接口的路径.
	+ 参照: [mybatis文档的 --- XML映射文件章节](https://mybatis.org/mybatis-3/zh/sqlmap-xml.html#)
	+ 内容:
		+ 编写增删改查的SQL, 把SQL存放到`insert | update | delete | select`元素中去
		+ 结果集映射: 解决表中的<font color=red>列和对象中属性不匹配的问题.
		+ 缓存配置

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221113335182.png)
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--命名空间,类似包的概念: namespace:绑定一个对应的Dao/Mapper接口-->
<mapper namespace="com.sunny.dao.UserDao">
    <!--
        select元素: 专门用来做查询的SQL
            -id属性: 唯一标识,用来标识某一条SQL语句
            -parameterType属性: 表示执行该SQL语句需要的参数的类型(建议不写),MyBatis可以自行推断出来
            -resultType属性: 把结果集中每一行数据封装成什么类型的对象
    -->
    <select id="getUserList" resultType="com.sunny.domain.User">
        SELECT * FROM user;
    </select>
    <!-- #{id} 相当于在测试方法中selectOne传递过来的值 -->
    <select id="getUser" resultType="com.sunny.domain.User">
        SELECT * FROM user WHERE id = #{id};
    </select>
</mapper>
```
### <span id="j12">3.3、 查询操作</span>
[<font size = 1>跳转到目录</font>](#j0)
- UserDaoTest
```java
public class UserDaoTest {
    /**
     * 查询所有用户
     * @throws IOException
     */
    @Test
    public void queryUserListTest() throws IOException {

        //1. 获得sqlSession对象
        // SqlSession sqlSession = MybatisUtils.getSqlSession();

        //1. 从classpath路径去加载MyBatis全局配置文件:mybatis-config.xml
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        //2. 创建sqlSessionFactory对象,好比是DataSource
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3. 创建sqlSession对象,好比是Connection
        SqlSession sqlSession = factory.openSession();
        //4. 具体操作
        // 执行SQL(方式一)
         UserDao userDao = sqlSession.getMapper(UserDao.class);
         List<User> userList = userDao.getUserList();

        // 方式二
        // List<User> userList = sqlSession.selectList("com.sunny.dao.UserDao.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }

        // 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 查询id为1的用户
     * @throws IOException
     */
    @Test
    public void queryOneUserTest() throws IOException {
        // 加载全局配置文件
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        // 构建sqlSessionFactory工厂类对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 根据sqlSession类对象来创建SqlSession对象
        SqlSession sqlSession = factory.openSession();
        // sqlSession相当于Connection,来执行SQL语句
        User user = sqlSession.selectOne("com.sunny.dao.UserDao.getUser", 1L);
        System.out.println(user);
    }
}
```

- 抽取的MybatisUtils
```java
// sqlSessionFactory 生产 sqlSession
public class MybatisUtils {

    private static  SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 使用Mybatis的第一步: 获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。
    // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
```

# <span id="j13">四、 日志框架</span>
[<font size = 1>跳转到目录</font>](#j0)
参考mybatis文档: [日志](https://mybatis.org/mybatis-3/zh/logging.html)
### <span id="j14">4.1、 为什么要用日志?</span>
[<font size = 1>跳转到目录</font>](#j0)
- 比起`System.out.println`, 日志框架可以把日志的输出和代码分离;
- 日志框架可以方便定义日志的输出环境,如 控制台,文件,数据库;
- 日志框架可以方便的定义日志的输出格式和输出级别;

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221152900490.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)
### <span id="j15">4.2、 常见的日志框架</span>
[<font size = 1>跳转到目录</font>](#j0)
- JDK-Logging : JDK1.4版本以后开始提供的一一个自带的日志库实现,太简单, 不支持占位符显示,拓展性差,使用很少。

- Commons-logging : Apache提供的日志规范,需要用户可以选择第三方的日志组件作为具体实现,本身会通过动态查找的机制找出真正日志的实现库。
- <font color=red>**Log4j**</font> : Apache下功能非常丰富的日志库实现,功能强大,可以把日志输出到控制台、文件中,是出现比较早且最受欢迎日志组件。并且Log4j可以允许自定义日志格式和日志等级,帮助开发人员全方位的掌控日志信息。
- Log4j2 :是Log4j的升级,基本上把Log4j版本的核心全部重构,而且基于Log4j做了很多优化和升级。SLF4J :好比是Commons-logging是日志门面,本身并无日志的实现，制定了日志的规范,使用时得拷贝整合包。
- Logback :由Log4j创始人设计的另-个开源日志组件,也是作为Log4j的替代者出现的。
- 速度和效率都比LOG4J高,而且官方是建议和SLF4j -起使用 ，Logback. sIf4j、 Log4j 都是出自同-一个人，所以默认对SLF4J无缝结合。

### <span id="j16">4.3、 日志级别</span>
[<font size = 1>跳转到目录</font>](#j0)
- `ERROR > WARN> INFO > DEBUG > TRACE`

- <font color=blue>如果设置级别为INFO ,则优先级高于等于INFO级别(如: INFO、WARN、ERROR )的日志信息将可以被输出,小于该级别的如DEBUG和TRACE将不会被输出。总结:日志级别越低,输出的日志越详细.</font>

### <span id="j17">4.4、 日志文件的组成</span>
[<font size = 1>跳转到目录</font>](#j0)

三部分组成
- **root** :设置默认的日志输出级别和风格。
-  **logger** :设置自定义日志级别和风格。
- **appender** :可以把日志输出到控制台或文件中去。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221153301529.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)
### <span id="j18">4.5、 日志的使用</span>
[<font size = 1>跳转到目录</font>](#j0)
- 首先添加Log4j的坐标
```xml
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```
- 在Mybatis的全局配置文件中设置
```xml
 <!--日志技术-->
 <settings>
     <setting name="logImpl" value="LOG4J"/>
 </settings>
```
- 定义日志文件
```xml
# Global logging configuration
#设置全局的日志配置:输出Error级别,输出到控制台
log4j.rootLogger=ERROR, stdout
# MyBatis logging configuration...
#设置自定义的日志级别(测试类的包名com.sunny.dao)
log4j.logger.com.sunny.dao=DEBUG
# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```

- 自定义日志
```java
    // 自定义日志
    private static Logger logger = Logger.getLogger(UserDaoTest.class);

    @Test
    public void testLogger() throws Exception{
        // 如果日志输出级别为INFO,则输出
        if (logger.isInfoEnabled()){
            logger.info("银行转账操作");
        }
        if (logger.isDebugEnabled()){
            logger.debug("查询数据库");
        }
        if (logger.isTraceEnabled()){
            logger.trace("连接数据库");
        }
        if (logger.isTraceEnabled()){
            logger.trace("执行SQL");
        }
        if (logger.isDebugEnabled()){
            logger.debug("转账");
        }
        if (logger.isInfoEnabled()){
            logger.info("银行转账成功");
        }
    }
```
# <span id="j19">五、OGNL</span>
[<font size = 1>跳转到目录</font>](#j0)
**对象导航图语言**（Object Graph Navigation Language），简称OGNL，是应用于Java中的一个开源的表达式语言（Expression Language），它被集成在Struts2等框架中，作用是对数据进行访问，它拥有类型转换、访问对象方法、操作集合对象等功能。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200221160224788.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L20wXzM3OTg5OTgw,size_16,color_FFFFFF,t_70)

# <span id="j21">六、 作用域和生命周期</span>
[<font size = 1>跳转到目录</font>](#j0)
- SqISessionFactoryBuilder
这个类可以被实例化、使用和丢弃,一旦创建了SqlSessionFactory ,就不再需要它了。因此SqlSessionFactoryBuilder实例的最佳作用域是方法作用域(也就是局部方法变量)。你可以重用SqlSessionFactoryBuilder 来创建多个SqlSessionFactory 实例,但是最好还是不要让其一直存在以保证所有的XML解析资源开放给更重要的事情.

- SqISessionFactory
SqlSessionFactory 一旦被创建就应该在应用的运行期间一-直存在,没有任何理由对它进行清除或重建。使用SqISessionFactory 的最佳实践是在应用运行期间不要重复创建多次, 多次重建SqlSessionFactory被视为一种代码"坏味道( bad smell)”.因此SqISessionFactory 的最佳作用域是应用作用域。有很多方法可以做到,<font color = blue>最简单的就是使用单例模式或者静态单例模式。

- SqlSession
每个线程都应该有它自己的SqISession 实例。SqlSession的实例不是线程安全的,因此是不能被共享的,所以它的最佳的作用域是请求或方法作用域。绝对不能将SqlSession 实例的引用放在一个类的静态域,甚至-一个类的实例变量也不行。也绝不能将SqlSession 实例的引用放在任何类型的管理作用域中, 比如Servlet架构中的HttpSession. 如果你现在正在使用一种Web框架,要考虑SqISession放在-个和HTTP请求对象相似的作用域中。换句话说,每次收到的HTTP请求,就可以打开一个Sq|Session ,返回一个响应,就关闭它。这个关闭操作是很重要的,你应该把这个关闭操作放到finally 块中以确保每次都能执行关闭。下面的示例就是一-个确保 SqISession 关闭的标准模式:


---

[相关MyBatis的详细使用](https://blog.csdn.net/eson_15/category_9266032.html)

