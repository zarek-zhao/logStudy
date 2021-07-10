package com.zarek;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyTest01 {
//    @Test
//    public void test01(){
//
//        /*
//            入门案例
//                SLF4J对日志级别的划分
//                trace、debug、info、warn、error五个级别
//                trace：日志追踪信息
//                debug：日志详细信息
//                info：日志关键信息
//                warn：日志警告信息
//                error：日志错误信息
//         */
//        Logger logger = LoggerFactory.getLogger(MyTest01.class);
//        logger.trace("trace信息");
//        logger.debug("debug信息");
//        logger.info("info信息");
//        logger.warn("warn信息");
//        logger.error("error信息");
//
//
//
//    }
//
//    @Test
//    public void 动态打印日志信息(){
//
//        Logger logger = LoggerFactory.getLogger(MyTest01.class);
//
//        // 动态打印日志信息
//        String name = "zs";
//        int age = 12;
//        logger.info("学生姓名：{},学生年龄：{}",name,age);
//    }
//
//    @Test
//    public void 打印异常信息(){
//
//        /*
//            日志对于异常信息的处理
//
//                一般情况下，在开发中的异常信息，都是记录在控制台上(开发环境的一种日志打印方式)
//                开发者根据异常信息提取出有用的线索，来调试bug
//
//                但是在真是生产环境中(项目上线)，对于服务器或者是系统相关的问题
//                在控制台上其实也会提供相应的异常或者错误信息的输出
//                但是这种错误输出方式(输出的时间，位置，格式。。)都是服务器系统默认的
//
//                可以通过日志技术，选择将异常以日志打印的方式，进行输出查看
//                输出的时间，位置(控制台，文件)，格式，完全由我们自己去进行定义
//         */
//
//        Logger logger = LoggerFactory.getLogger(MyTest01.class);
//
//        try {
//            Class.forName("aaa");
//        } catch (ClassNotFoundException e) {
//            // 打印栈的追踪信息
////            e.printStackTrace();
//            logger.info("XXX类中的XXX方法出现了异常，请及时关注信息");
//            logger.info("具体错误是：",e);
//        }
//    }
//
//    @Test
//    public void 集成其他的日志框架(){
//        /*
//
//            集成其他日志框架之前
//
//            测试一：
//                在原有slf4j-simple日志实现的基础上，又集成了logback
//                通过测试，日志是打印出来了,但是会和simple冲突，还是会使用simple日志实现
//                如果先导入logback依赖，后导入slf4j-simple依赖
//                那么默认使用的就是logback依赖
//                如果有多个日志实现的话，默认使用先导入的实现
//            测试二：
//                将slf4j-simple注释掉
//                只留下logback，那么slf4j门面使用的就是logback日志实现
//                在实际应用的时候，一般情况下，只集成一种日志实现框架即可
//
//                通过这个集成测试，发现虽然底层的日志实现改变了，但是源代码完全没有改变
//                这就是日志门面的最大有事
//                在底层真是记录日志的时候，不需要应用去做任何的了解
//                应用只需要记slf4j的API就可以了
//         */
//
//        Logger logger = LoggerFactory.getLogger(MyTest01.class);
//
//        logger.info("info信息");
//
//    }
//
//    @Test
//    public void testSlf4jNop(){
//        /*
//
//            使用slf4j-nop
//                表示不记录日志
//                在我们使用slf4j-nop的时候
//                首先还是需要导入实现依赖
//                这个实现依赖，根据我们之前所总结出来的日志实现种类的第二种
//                与logback和simple是属于一类的
//                通过集成依赖的顺序而定
//                如果想要nop发挥效果，禁止所有日志的打印
//                那么就必须要将slf4j-nop的依赖放在所有日志实现依赖的上方
//
//                或者只导入slf4j-nop的依赖，不导入其他日志框架的依赖
//         */
//        Logger logger = LoggerFactory.getLogger(MyTest01.class);
//        logger.info("info信息");
//    }
//
//    @Test
//    public void 绑定log4j(){
//
//        /*
//              绑定log4j
//              由于log4j实在slf4j之前发布的日志框架实现
//              所以并没有遵循slf4j的API规范
//
//              logback可以直接导入依赖使用，但是log4j需要绑定一个适配器
//
//              slf4j-log4j12
//         */
//
//        Logger logger = LoggerFactory.getLogger(MyTest01.class);
//        logger.trace("trace信息");
//        logger.debug("debug信息");
//        logger.info("info信息");
//        logger.warn("warn信息");
//        logger.error("error信息");
//
//    }
//
//    @Test
//    public void 集成JUL日志框架(){
//        Logger logger = LoggerFactory.getLogger(MyTest01.class);
//
//        System.out.println("logger.isDebugEnabled() = " + logger.isDebugEnabled());
//        logger.trace("trace信息");
//        logger.debug("debug信息");
//        logger.info("info信息");
//        logger.warn("warn信息");
//        logger.error("error信息");
//    }

    @Test
    public void 日志代码重构(){

        /*
            需求：
                假设项目一直以来使用的是 log4j 日志框架
                但是随着技术和需求的更新换代
                log4j 已然不能够满足我们系统的需求
                现在需要将系统中的日志实现重构为 slf4j + logback 的组合
                在不触碰java源代码的情况下，如何解决此问题?

                既然不使用log4j了，就将其依赖去除
                将slf4j日志门面和logback的日志实现依赖引入
                这样做，没有了log4j环境的支持，程序编译报错

                这个时候就需要使用桥接器来处理
                桥接器解决的是项目中日志的重构问题，当前系统中存在之前的日志API，可以通过桥接转换到slf4j的实现

                桥接器的使用步骤：
                    1. 去除之前旧的日志框架依赖
                        <dependency>
                            <groupId>log4j</groupId>
                            <artifactId>log4j</artifactId>
                            <version>1.2.17</version>
                        </dependency>

                    2. 添加slf4j提供的桥接组件
                        log4j相关的桥接器
                            <dependency>
                                <groupId>org.slf4j</groupId>
                                <artifactId>log4j-over-slf4j</artifactId>
                                <version>1.7.25</version>
                            </dependency>
                        桥接器加入后，代码编译就不报错了
                        测试：
                            日志信息输出
                            输出格式为logback
                            证明了现在使用的确实是slf4j门面+logback实现

                            在重构之后，就会为我们造成这样一种假象
                            使用的明明是log4j包下的日志组件资源
                            但是真正日志的实现，确实使用slf4j门面+logback实现
                            这就是桥接器给我们带来的效果

                            注意：
                                在桥接器加入之后，适配器就没有必要加入了
                                桥接器和适配器不能同时导入依赖
                                桥接器如果配置在适配器的上方，则运行报错，不能同时出现
                                桥接器如果配置在适配器的下方，则不会执行桥接器，没有任何的意义

         */

        Logger logger = Logger.getLogger(MyTest01.class);
        logger.info("info信息");
    }


}
