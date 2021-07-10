package com.zarek;

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
        for (int i = 0; i < 1000; i++) {
            Logger logger = LoggerFactory.getLogger(MyTest01.class);
            logger.trace("trace信息");
            logger.debug("debug信息");
            logger.info("info信息");
            logger.warn("warn信息");
            logger.error("error信息");
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("i = " + i);
        }
    }


}
