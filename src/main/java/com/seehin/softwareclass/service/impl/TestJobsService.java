package com.seehin.softwareclass.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/5 0:29
 */
@Service
public class TestJobsService {

    /*private final Logger logger = LoggerFactory.getLogger(TestJobsService.class);*/

    public void testOne(){
        /*logger.info("service层打印："+new Date().toString().concat("service"));*/
        System.out.println("service层打印01："+new Date());
    }

    public void testSecond(){
        /*logger.info("service层打印："+new Date().toString().concat("service"));*/
        System.out.println("service层打印02："+new Date());
    }
}
