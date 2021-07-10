package com.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyTest02 {

    @Test
    public void test(){
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
