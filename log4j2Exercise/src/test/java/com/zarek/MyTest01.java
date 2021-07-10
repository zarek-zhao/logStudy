package com.zarek;

import com.demo.MyTest02;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyTest01 {
    @Test
    public void test01(){

        /*
            使用配置文件
                log4j2说参考logback创作出来的，所以配置文件也是使用xml
                log4j2同样是默认加载类路径(resources)下的log4j2.xml文件中的配置

                跟标签，所有日志相关信息，都是在跟标签中进行配置
                <Configuration status="debug" monitorInterval="数值"><Configuration>
                在根标签中，可以加属性
                status="debug" 日志框架本身的日志输出级别
                monitorInterval="5" 自动加载配置文件的间隔时间，不低于5秒
         */
//        Logger logger = LogManager.getLogger(MyTest01.class);
//        logger.fatal("fatal信息");
//        logger.error("error信息");
//        logger.warn("warn信息");
//        logger.info("info信息");
//        logger.debug("debug信息");
//        logger.trace("trace信息");

    }

    @Test
    public void test02(){
        /*

            虽然log4j2也是日志门面，但是现在市场的主流趋势仍然时slf4j
            所以我们仍然需要使用slf4j作为日志门面，搭配log4j2强大的日志实现功能，进行日志的相关操作

            接下来配置的就是现在市场上最主流的日志使用搭配方式
            slf4j+log4j2

            1. 导入slf4j的日志门面
            2. 导入log4j2的适配器
            3. 导入log4j2的日志门面
            4. 导入log4j2的日志实现

            执行原理：
                slf4j门面调用的时log4j2的门面，再由log4j2的门面调用log4j2的实现

         */

        Logger logger = LoggerFactory.getLogger(MyTest01.class);
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");

    }

    @Test
    public void test03(){

        /*
                将日志输出到文件

         */

        Logger logger = LoggerFactory.getLogger(MyTest01.class);

        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");

    }

    @Test
    public void test04(){

        /*
                日志的拆分

         */

        Logger logger = LoggerFactory.getLogger(MyTest01.class);

        for (int i = 0; i < 1000; i++) {
            logger.error("error信息");
            logger.warn("warn信息");
            logger.info("info信息");
            logger.debug("debug信息");
            logger.trace("trace信息");
        }

    }

    @Test
    public void test05(){
        /*
            异步日志实现：
                方式1： 使用AsyncAppender的方式
                    1. 添加异步日志依赖
                    2. 在Appenders标签中，对异步进行配置
                        使用Async标签
                    3. rootLogger引用Async


         */

        Logger logger = LoggerFactory.getLogger(MyTest01.class);

        for (int i = 0; i < 1000; i++) {
            logger.error("error信息");
            logger.warn("warn信息");
            logger.info("info信息");
            logger.debug("debug信息");
            logger.trace("trace信息");
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println("i = " + i);
        }

    }

    @Test
    public void test06(){
        /*

            方式2： 使用AsyncLogger方式
                    1. 全局异步
                        所有的日志都是异步的日志记录，在配置文件上不用做任何的改动
                        只需要在类路径resources下添加一个properties属性文件，做一步配置即可
                        文件名要求是：log4j2.component.properties
                        Log4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector

         */

        Logger logger = LoggerFactory.getLogger(MyTest01.class);

        for (int i = 0; i < 1000; i++) {
            logger.error("error信息");
            logger.warn("warn信息");
            logger.info("info信息");
            logger.debug("debug信息");
            logger.trace("trace信息");
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println("i = " + i);
        }


    }

    @Test
    public void test07(){
        /*
            异步日志实现(单独分配线程做日志的记录)
                方式2： 使用AsyncLogger方式
                        2. 混合异步
                            可以在应用中同时使用同步日志和异步日志，这使得日志的配置及输出会更加的灵活
                需求：
                    假设我们现在有自定义的logger -com.zarek
                    让自定义的logger是异步的
                    让rootLogger是同步的

                注意：
                    测试之前，一定要将全局的异步配置 log4j2.component.properties 注释掉

                    对于当前的logger，MyTest01.class
                    MyTest01本身就是在自定义的logger路径下的

                注意：
                    如果使用异步日志
                        AsyncAppender、AsyncLogger不要同时出现，没有这个需求，效果也不会叠加
                        如果同时出现，那么效率会以AsyncAppender为主

                        AsyncLogger的全局异步和混合异步也不要同时出现，没有这个需求，效果也不会叠加

         */

        Logger logger = LoggerFactory.getLogger(MyTest01.class);

        for (int i = 0; i < 1000; i++) {
            logger.error("error信息");
            logger.warn("warn信息");
            logger.info("info信息");
            logger.debug("debug信息");
            logger.trace("trace信息");
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println("i = " + i);
        }


    }

    @Test
    public void test08(){
        /*
            异步日志实现(单独分配线程做日志的记录)
                方式2： 使用AsyncLogger方式
                        2. 混合异步
                            可以在应用中同时使用同步日志和异步日志，这使得日志的配置及输出会更加的灵活
                需求：
                    假设我们现在有自定义的logger -com.zarek
                    让自定义的logger是异步的
                    让rootLogger是同步的

                注意：
                    测试之前，一定要将全局的异步配置 log4j2.component.properties 注释掉

                    对于当前的logger，MyTest02.class
                    MyTest02本身不在在自定义的logger路径下的

         */

        Logger logger = LoggerFactory.getLogger(MyTest02.class);

        for (int i = 0; i < 1000; i++) {
            logger.error("error信息");
            logger.warn("warn信息");
            logger.info("info信息");
            logger.debug("debug信息");
            logger.trace("trace信息");
        }

        for (int i = 0; i < 1000; i++) {
            System.out.println("i = " + i);
        }


    }

}
