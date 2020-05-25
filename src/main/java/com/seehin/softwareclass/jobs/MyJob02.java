package com.seehin.softwareclass.jobs;

import com.seehin.softwareclass.service.impl.TestJobsService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author Seehin
 * @Date 2020/5/5 1:01
 */
public class MyJob02 implements Job {

    TestJobsService testJobsService;
    @Autowired
    public void setTestJobsService(TestJobsService testJobsService){
        this.testJobsService = testJobsService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        testJobsService.testSecond();
    }
}
