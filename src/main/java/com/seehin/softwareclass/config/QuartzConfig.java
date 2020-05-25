package com.seehin.softwareclass.config;

import com.seehin.softwareclass.jobs.DisposeAffairJob;
import com.seehin.softwareclass.jobs.MyJob02;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Description Qurtz任务调度
 * @Author Seehin
 * @Date 2020/5/4 19:28
 */
//@Configuration
public class QuartzConfig {

    @Bean(name = "firstJob")
    public JobDetailFactoryBean firstJobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(DisposeAffairJob.class);
        return jobDetailFactoryBean;
    }

    @Bean(name = "firstTrigger")
    public CronTriggerFactoryBean firstCronTriggerFactoryBean(@Qualifier("firstJob") JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        cronTriggerFactoryBean.setCronExpression("0/30 * * * * ?");
        return cronTriggerFactoryBean;
    }

    //有机会尝试抽取该处重复代码
    /*@Bean(name = "secondJob")
    public JobDetailFactoryBean secondJobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(MyJob02.class);
        return jobDetailFactoryBean;
    }

    @Bean(name = "secondTrigger")
    public CronTriggerFactoryBean secondCronTriggerFactoryBean(@Qualifier("secondJob") JobDetailFactoryBean jobDetailFactoryBean){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        cronTriggerFactoryBean.setCronExpression("0/4 * * * * ?");
        return cronTriggerFactoryBean;
    }*/

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(
            @Qualifier("firstTrigger") CronTriggerFactoryBean firstTrigger,
            MyExtendsFactory myExtendsFactory
            ){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(firstTrigger.getObject());
        schedulerFactoryBean.setJobFactory(myExtendsFactory);
        return schedulerFactoryBean;
    }
}
