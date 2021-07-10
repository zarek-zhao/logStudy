package com.zarek;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public class MyTest01 {
    @Test
    public void test01(){
        /*
            在没有导入第三方日志框架（例如 log4j ）的情况下，会使用JUL日志框架做日志的记录操作
            JCL的使用原则：
                如果有log4j，优先使用log4j
                如果没有任何第三方日志框架的时候，使用的就是JUL
         */
        Log log = LogFactory.getLog(MyTest01.class);

        log.info("info信息");
    }

    @Test
    public void test02(){

        /*
            导入log4j依赖

                总结：
                    上一个测试使用的是JUL，
                    在集成log4j后，使用的是log4j,虽然日志框架发生了变化，但是代码完全没有改变

                    日志门面技术的好处：
                        门面技术是面向接口的开发，不再依赖具体的实现类，减少代码的耦合性
                        可以根据实际需求，灵活的切换日志框架
                        统一的API，方便开发者学习和使用
                        统一的配置管理便于项目日志的维护工作

         */

        Log log = LogFactory.getLog(MyTest01.class);

        log.info("info信息");


    }

}
