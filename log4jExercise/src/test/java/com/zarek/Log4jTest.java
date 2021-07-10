package com.zarek;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;

/**
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public class Log4jTest {
    @Test
    public void test01(){
        /*
            Log4j入门案例：
                加载初始化信息：BasicConfigurator.configure();

            日志级别说明：
                Log4j提供了8个级别的日志输出，分别为
                ALL 最低级别 用于打开所有级别的日志记录
                TRACE 程序推进下的追踪信息，这个追踪信息的日志级别非常低，一般情况下是不会使用的
                DEBUG 指出细粒度信息事件对调试应用程序是非常有帮助的，主要是配合开发，在开发过程中打印一些重要的运行信息
                INFO 消息的粗粒度级别运行信息
                WARN 表示警告，程序在运行过程中会出现的有可能会发生的隐形的错误
                        注意，有些信息不是错误，但是这个级别的输出目的就是为了给程序员提示，以发现潜藏的风险
                ERROR 系统的错误信息，发生的错误不影响系统的运行
                        一般情况下，如果不想输出太多的日志，使用该级别即可
                FATAL 标识严重错误（致命的），它是那种一旦发生系统就不可能继续运行的严重错误
                        如果这种级别的错误出现了，表示程序可以停止运行了
                OFF 最高等级的级别，用户关闭所有的日志记录
         */

//        加载初始化配置
        BasicConfigurator.configure();

        Logger logger = Logger.getLogger(Log4jTest.class);
        System.out.println("logger.getParent() = " + logger.getParent());
//        ConsoleAppender consoleAppender = new ConsoleAppender();
//        logger.addAppender(consoleAppender);
        logger.trace("trace信息");
        logger.debug("debug信息");
        logger.info("info信息");
        logger.warn("warn信息");
        logger.error("error信息");
        logger.fatal("fatal信息");
    }

    @Test
    public void test02(){
        /*
            配置文件的使用
                1. 观察源码 BasicConfigurator.configure();
                    可以得到两个信息：
                        * 创建根节点对象，Logger root = Logger.getRootLogger();
                        * 根节点添加了ConsoleAppender对象(表示默认打印到控制台，自定义的格式化输出)
                2. 不是用BasicConfigurator.configure();使用自定义的配置文件加载
                    * 配置文件需要提供Logger、Appender、Layout这3个组件信息(通过配置来代替以上的代码)

                    分析：
                    Logger logger = Logger.getLogger(Log4jTest.class);

                    进入到getLogger方法，会看到代码：
                    LogManager.getLogger(clazz.getName());
                    LogManager:日志管理器

                    点击LogManager,看看这个日志管理器中都实现了什么
                    看到很多常量信息，它们代表的就是不同形式(后缀名不同)的配置文件
                    最常使用的是log4j.properties属性文件(语法简单，使用方便)

                    问题：log4j.properties的加载时机
                    继续观察LogManager,找到其中的静态代码块static
                    在static代码块中，找到
                    Loader.getResource("log4j.properties");
                    这行代码给我们的最大的一个提示信息就是：
                    系统默认要从当前的类路径下找到log4j.properties
                    对于我们当前的项目是maven工程，那么理应在resources路径下去找

                    作为属性文件的加载，执行相应的properties配置对象：configurator=new PropertyConfigurator();

                    进入到PropertyConfigurator类中，观察到里面的常量信息
                    这些常量信息就是我们在properties属性文件中的各种属性配置项
                    其中，我们看到了如下两项信息，这两项信息是必须要进行配置的
                    static final String ROOT_LOGGER_PREFIX="log4j.rootLogger";
                    static final String APPENDER_PREFIX="log4j.appender.";

                    通过代码
                    String prefix = "log4j.appender."+appenderName;
                    我们需要自定义一个appendername，我们起名叫做console
                    log4j.appender.console
                    取值就是log4j中为我们提供的appender类
                    例如：
                        log4j.appender.console=org.apache.log4j.ConsoleAppender

                     在appender输出的过程中，还可以同时指定输出的格式
                     通过代码：
                     String layoutPrefix = prefix + ".layout";
                     配置：
                     log4j.appender.console.layout

         */

//        BasicConfigurator.configure();
        Logger logger = Logger.getLogger(Log4jTest.class);

        logger.trace("trace信息");
        logger.debug("debug信息");
        logger.info("info信息");
        logger.warn("warn信息");
        logger.error("error信息");
        logger.fatal("fatal信息");


    }

    @Test
    public void test03(){
        /*
            通过Logger中的开关
                打开日志输出的详细信息
                LogLog.debug(msg, ex);
                    public static void debug(String msg, Throwable t) {
                        if (debugEnabled && !quietMode) {
                            System.out.println("log4j: " + msg);
                            if (t != null) {
                                t.printStackTrace(System.out);
                            }
                        }

                    }
         */
        LogLog.setInternalDebugging(true);

        Logger logger = Logger.getLogger(Log4jTest.class);

        logger.trace("trace信息");
        logger.debug("debug信息");
        logger.info("info信息");
        logger.warn("warn信息");
        logger.error("error信息");
        logger.fatal("fatal信息");
    }

    @Test
    public void test04(){
        /*
            关于log4j.properties layout 属性的配置
                其中PatternLayout是日常使用最多的方式
                查看其源码
                setConversionPattern这个方法就是该PatternLayout的核心方法
                conversionPattern

                在log4j.properties中将layout设置为PatternLayout
                主要配置的是conversionPattern属性
         */

        LogLog.setInternalDebugging(true);

        Logger logger = Logger.getLogger(Log4jTest.class);

        logger.trace("trace信息");
        logger.debug("debug信息");
        logger.info("info信息");
        logger.warn("warn信息");
        logger.error("error信息");
        logger.fatal("fatal信息");


    }

    @Test
    public void test05(){
        /*
            将日志信息输出到文件中

            之前在配置文件中配置的是输出到控制台相关的配置
            那么可以直接将输出到控制台改编为输出到文件中
            一般情况下也可以做多方向的输出，所以控制台的配置可以保留，但是可以选择不用

            日志文件要保存到哪个磁盘 相关的配置
            查看FileAppender的源码
            看到属性信息：
            protected boolean fileAppend;表示是否追加信息，通过构造方法赋值为true
            protected int bufferSize;   缓冲区的大小，通过构造方法赋值为8192

            继续观察，找到setFile方法，这个方法就是用来指定文件位置的方法
            通过ognl,可以推断setFile方法操作的属性就是file

            如果有输出中文的需求？
            观察FileAppender的父类
            找到protected String encoding;属性


         */

        LogLog.setInternalDebugging(true);

        Logger logger = Logger.getLogger(Log4jTest.class);

        logger.trace("trace信息");
        logger.debug("debug信息");
        logger.info("info信息");
        logger.warn("warn信息");
        logger.error("error信息");
        logger.fatal("fatal信息");



    }

    @Test
    public void test06(){
        /*
            将日志以追加的方式输出到文件中会导致日志文件变得越来越大，难以维护
            解决方法：
                FileAppender提供了对于文件输出进一步处理的好用的子类
                RollingFileAppender
                DailyRollingFileAppender
            1. 学习RollingFileAppender
                按照文件的大小进行拆分
                配置文件进行RollingFileAppender相关配置
                protected long maxFileSize = 10485760L; 拆分文件的大小
                protected int maxBackupIndex = 1; 拆分文件的数量
                # 指定日志文件的大小
                log4j.appender.rollingFile.maxFileSize=1MB
                # 指定日志文件的数量
                log4j.appender.rollingFile.maxBackupIndex=5

                只要文件超过1MB，那么生成另一个文件，文件的数量最多是5个

                如果5个文件不够，作为日志管理来讲，也不可能让日志的继续增长下去
                所以，覆盖文件的策略，按照时间进行覆盖，原则就是保留信息，覆盖旧的
            2. 学习DailyRollingFileAppender
                按照时间来进行文件的拆分
                查看源码属性：
                private String datePattern = "'.'yyyy-MM-dd";默认是按照天来进行拆分的

                log4j.appender.dailyRollingFile.datePattern='.'yyyy-MM-dd HH-mm-ss

         */

        LogLog.setInternalDebugging(true);

        Logger logger = Logger.getLogger(Log4jTest.class);

        while(true){
            logger.trace("trace信息");
            logger.debug("debug信息");
            logger.info("info信息");
            logger.warn("warn信息");
            logger.error("error信息");
            logger.fatal("fatal信息");
        }

    }

    @Test
    public void test07(){
        /*
            将日志持久化到数据库表中
            创建表结构：(字段的指定可以根据需求进行调整)

            CREATE TABLE tbl_log(
                    id int(11) NOT NULL AUTO_INCREMENT,
                    name varchar(255) DEFAULT  NULL COMMENT '项目名称',
                    createTime varchar(255) DEFAULT  NULL COMMENT '创建时间',
                    level varchar(255) DEFAULT  NULL COMMENT '日志级别',
                    category varchar(255) DEFAULT  NULL COMMENT '所在类的全路径',
                    fileName varchar(255) DEFAULT  NULL COMMENT '文件名称',
                    message varchar(255) DEFAULT  NULL COMMENT '日志消息',
                    PRIMARY KEY(id)
            )

            对于数据库表的日志输出进行相应配置
            # 配置appender输出方式，输出到数据库表中
            log4j.appender.logDB=org.apache.log4j.jdbc.JDBCAppender
            log4j.appender.logDB.layout=org.apache.log4j.PatternLayout

            log4j.appender.logDB.Driver=com.mysql.cj.jdbc.Driver
            log4j.appender.logDB.URL=jdbc:mysql://localhost:3306/test
            log4j.appender.logDB.User=root
            log4j.appender.logDB.Password=123456
            log4j.appender.logDB.Sql=INSERT INTO tbl_log(name,createTime,level,category,fileName,message) values('project_log','%d{yyyy-MM-dd HH:mm:ss}','%p','%c','%F','%m')

         */

        LogLog.setInternalDebugging(true);

        Logger logger = Logger.getLogger(Log4jTest.class);

        while(true){
            logger.trace("trace信息");
            logger.debug("debug信息");
            logger.info("info信息");
            logger.warn("warn信息");
            logger.error("error信息");
            logger.fatal("fatal信息");
        }

    }

    @Test
    public void test08(){
        /*
            Log4j的自定义logger
                以前所创建出来的Logger对象，默认都是继承rootLogger的
                也可以自定义logger,让其他的logger来继承这个logger

                这种继承关系就是按照包结构的关系来进行指定的
                例如：
                Logger logger = Logger.getLogger(Log4jTest.class);
                路径就是com.zarek.Log4jTest
                它的父logger就是上层的路径或者更上层的路径
                例如
                    com.zarek
                    com

                参照logger是如何加载配置文件的
                查看PropertyConfigurator的源码
                得到信息log4j,logger
                这个属性值log4j.logger.就是在配置文件中对于自定义logger的配置属性

                #配置根节点logger
                log4j.rootLogger=TRACE, stdout,file

                # 配置自定义的logger
                log4j.logger.com.zarek=info,file

                观察结果：
                    从输出位置来看，控制台输出了信息，日志文件也输出了信息
                    所以可以得到结论，如果根节点的logger和自定义父logger配置的输出位置是不同的
                    则取二者的并集，配置的位置都会进行输出操作。

                    如果二者配置的日志级别不同，主要以自定义的父logger的级别输出为主
         */


        LogLog.setInternalDebugging(true);

        Logger logger = Logger.getLogger(Log4jTest.class);

        while(true){
            logger.trace("trace信息");
            logger.debug("debug信息");
            logger.info("info信息");
            logger.warn("warn信息");
            logger.error("error信息");
            logger.fatal("fatal信息");
        }

    }

    @Test
    public void test09(){
        /*
            自定义logger的应用场景
                之所以要自定义logger，就是为了针对不同系统信息做更加灵活的输出操作
                例如：
                    可以在原有案例的基础之上，加上一个apache的日志输出
                    log4j.logger.org.apache=error,stdout

                    在配置文件中配置的logger有如下三项
                    log4j.rootLogger=TRACE, stdout,file
                    log4j.logger.com.zarek=info,file
                        log4j.logger.org.apache=error,stdout

         */
    }
}
