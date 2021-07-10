package com.zarek.springbootlogexercise;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootlogExerciseApplicationTests {

    @Test
    void test01()
    {

        /*

            入门案例：
                springboot日志具体实现
                    级别测试
                    默认是info级别
                    logback的风格输出（默认使用的是logback的日志实现）
         */

        Logger logger = LoggerFactory.getLogger(SpringbootlogExerciseApplicationTests.class);
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");
    }

    @Test
    void test02()
    {

        /*

            使用log4j2的日志实现
            观察桥接器是否起作用
            结果：仍然slf4j和logback
            证明桥接器是起作用的
         */

//        org.apache.logging.log4j.Logger logger =  LogManager.getLogger(SpringbootlogExerciseApplicationTests.class);
//        logger.error("error信息");
//        logger.warn("warn信息");
//        logger.info("info信息");
//        logger.debug("debug信息");
//        logger.trace("trace信息");
    }

    @Test
    void test03(){

        /*

            application.yaml(yml) 是springboot的核心配置文件(用来简化开发使用)

            可以通过该配置文件，修改日志相关的配置

            将日志输出到文件中
                使用logging.file.path来配置文件路径下的文件夹(logging.file直接配置文件的形式已经过时，不再使用)
                在配置的文件夹下，日志文件生成的名字为spring.log


         */

        Logger logger = LoggerFactory.getLogger(SpringbootlogExerciseApplicationTests.class);
        logger.error("error信息");
        logger.warn("warn信息");
        logger.info("info信息");
        logger.debug("debug信息");
        logger.trace("trace信息");

    }

    @Test
    void test04(){

        /*

            如果是需要配置日志拆分等相对高级的功能
            那么application.properties就达不到要求了
            需要使用日志实现相应的配置文件

            例如使用logback日志实现
            就需要在类路径resources下，配置logback.xml
         */

        Logger logger = LoggerFactory.getLogger(SpringbootlogExerciseApplicationTests.class);
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
    void test05(){
        /*

            由于log4j2性能的强大
            当今市场上越来越多的项目选择使用slf4j+log4j2的组合
            springboot默认使用的是slf4j+logback的组合
            但是可以将默认的logback替换成log4j2

            1. 启动器依赖，间接的依赖logback
                 所以需要将之前的环境中的logback的依赖去除掉
                         <dependency>
                            <groupId>org.springframework.boot</groupId>
                            <artifactId>spring-boot-starter-web</artifactId>
                            <exclusions>
                <!--                排除掉原始日志依赖，以此去除logback引用-->
                                <exclusion>
                                    <artifactId>spring-boot-starter-logging</artifactId>
                                    <groupId>org.springframework.boot</groupId>
                                </exclusion>
                            </exclusions>
                        </dependency>

                        2. 添加log4j2依赖
                        3. 将log4j2的配置文件导入到类路径resources中

         */

        Logger logger = LoggerFactory.getLogger(SpringbootlogExerciseApplicationTests.class);
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
