package com.zarek;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyTest1 {
    @Test
    public void test1()
    {
//        入门案例
//        Logger对象的创建方式，不能直接new对象
//        取得对象的方法参数，需要引入当前类的全路径字符串(以后根据包结构由Logger父子关系)
        Logger logger = Logger.getLogger("com.zarek.MyTest1");
        /*
            对于日志的输出，由两种方式
            第一种方式：
                直接调用日志级别相关的方法，方法中传递日志输出信息
         */
        logger.info("输出info级别的信息");
        /*
            第二种方式：
                调用通用的log方法，然后在里面通过Level类型来定义日志的级别，以及搭配日志输出信息的参数
         */
        logger.log(Level.INFO, "第二种方式输出info级别的日志信息");

        String name = "zs";
        int age = 23;
        logger.info("学生的姓名为：" + name + ";年龄为：" + age);
    }

    @Test
    public void testDynamic()
    {
        Logger logger = Logger.getLogger("com.zarek.MyTest1");
        /*
            对于输出消息中，字符串的拼接弊端很多
            1.麻烦
            2.程序效率低
            3.可读性不强
            4.维护成本高

            我们应该使用动态生成数据的方式，生产日志
            我们使用的就是占位符的方式进行操作
         */
        String name = "zs";
        int age = 23;
        logger.log(Level.INFO, "学生的姓名为：{0};年龄为{1}", new Object[]{name, age});

    }

    @Test
    public void testLogLevel()
    {
        /*
            日志的级别
            如果仅仅通过设置日志级别
            那么不能够起到相应的效果
            需要搭配Handler共同设置才有效
         */
        Logger logger = Logger.getLogger("com.zarek.MyTest1");
        logger.severe("severe信息");
        logger.warning("warning信息");
        logger.info("info信息");
        logger.config("config信息");
        logger.fine("fine信息");
        logger.finer("finer信息");
        logger.finest("finest信息");

    }

    @Test
    public void 自定义日志的级别()
    {
        Logger logger = Logger.getLogger("com.zarek.MyTest1");

        // 需要将默认的日志打印方式关掉
        // 参数设置为false,我们打印日志的方式就不会按照父logger默认的方式进行操作
        logger.setUseParentHandlers(false);

        // 日志处理器
        // 使用的控制台日志处理器
        ConsoleHandler handler = new ConsoleHandler();
        // 创建日志格式化组件对象
        SimpleFormatter formatter = new SimpleFormatter();

        // 在处理器中设置输出格式
        handler.setFormatter(formatter);
        // 在记录器中添加处理器
        logger.addHandler(handler);

        // 设置日志的打印级别，此处必须将日志记录器和处理器的级别进行统一的设置，才会达到日志显示相应级别的效果
        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);

        logger.severe("severe信息");
        logger.warning("warning信息");
        logger.info("info信息");
        logger.config("config信息");
        logger.fine("fine信息");
        logger.finer("finer信息");
        logger.finest("finest信息");


    }

    @Test
    public void 日志信息输出到磁盘上() throws IOException
    {
        /*
                将日志输出到具体的磁盘文件中，
                这样做相当于是做了日志的持久化操作
         */
        Logger logger = Logger.getLogger("com.zarek.MyTest1");
        logger.setUseParentHandlers(false);
        FileHandler handler = new FileHandler("E:\\JULTest\\file\\myLogTest.log",true);
        SimpleFormatter formatter = new SimpleFormatter();
        handler.setFormatter(formatter);
        logger.addHandler(handler);

        // 也可以在控制台和文件中进行打印
        ConsoleHandler handler1 = new ConsoleHandler();
        handler1.setFormatter(formatter);
        logger.addHandler(handler1);

        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);
        handler1.setLevel(Level.ALL);

        logger.severe("severe信息");
        logger.warning("warning信息");
        logger.info("info信息");
        logger.config("config信息");
        logger.fine("fine信息");
        logger.finer("finer信息");
        logger.finest("finest信息");

        /*
            总结：
                用户使用Logger来进行日志的记录，Logger可以持有多个处理器Handler
                （日志的记录使用的是Logger，日志的输出使用的是Handler）
                添加了那些handler对象，就相当于需要根据所添加的handler
                将日志输出到指定的位置上，例如控制台、文件。。
         */

    }

    @Test
    public void logger之间的父子关系(){
        /*
            Logger之间的父子关系
                JUL中Logger之间存在"父子"关系
                这种父子关系不是普遍认为的类之间的继承关系
                关系是通过树状结构存储的

                JUL在进行初始化时会创建一个顶层的RootLogger作为所有Logger的父Logger
                查看源代码：
                    owner.rootLogger = owner.new RootLogger();
                    RootLogger是LogManager的内部类
                    java.util.logging.LogManager$RootLogger
                    默认的名称为 空串
                以上的RootLogger对象作为树状结构的根节点存在的，
                将来自定义的父子关系通过路径来进行关联
                父子关系，同时也是节点的挂载关系
                owner.addLogger(owner.rootLogger);
                LoggerContext cx = getUserContext(); // 一种用来保存节点的Map关系
         */

        /*
            从下面创建的两个logger对象看来
            我们可以认为logger1是logger2的父亲
         */
        // 父亲logger是RootLogger,名称默认是一个空的字符串
        // RootLogger可以被称之为所有logger对象的顶层logger
        Logger logger1 = Logger.getLogger("com.zarek");
        Logger logger2 = Logger.getLogger("com.zarek.MyTest1");

        //父亲做设置
        logger1.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        SimpleFormatter formatter = new SimpleFormatter();
        logger1.addHandler(handler);
        handler.setFormatter(formatter);

        logger1.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);

        /*
            父亲所作的设置，也能同时作用于儿子
            对logger1做日志打印的设置

         */
        //儿子做打印操作，继承了父亲的设置
        logger2.severe("severe信息");
        logger2.warning("warning信息");
        logger2.info("info信息");
        logger2.config("config信息");
        logger2.fine("fine信息");
        logger2.finer("finer信息");
        logger2.finest("finest信息");
    }

    @Test
    public void 日志配置文件() throws Exception
    {
        /*
            以上所有的配置的相关操作，都是以java硬编码的形式进行的
            可以使用更加清晰，更加专业的一种做法，就是使用配置文件
            如果没有添加自定义配置文件，则会使用系统默认的配置文件
                owner.readPrimordialConfiguration();
                readConfiguration();
                java.home --> 找到jre文件夹 --> lib --> logging.properties

         */
//        System.out.println("Class.forName(\"com.zarek.MyTest1\").getClassLoader().toString() = " + Class.forName("com.zarek.MyTest1").getClassLoader().toString());
        InputStream in = new FileInputStream("E:\\JULTest\\file\\logging.properties");
//        InputStream in = MyTest1.class.getResourceAsStream("logging.properties");
//        properties.load(in);
//        int line = 0;
//        while((line=in.read())!=-1){
//            System.out.println("line = " + (char)line);
//        }

        // 取得日志管理器的对象
        LogManager logManager = LogManager.getLogManager();

//      读取自定义的配置文件
        logManager.readConfiguration(in);

//
        Logger logger = Logger.getLogger("com.zarek.MyTest1");
        logger.severe("severe信息");
        logger.warning("warning信息");
        logger.info("info信息");
        logger.config("config信息");
        logger.fine("fine信息");
        logger.finer("finer信息");
        logger.finest("finest信息");


    }
}
