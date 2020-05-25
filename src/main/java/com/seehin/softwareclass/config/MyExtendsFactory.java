package com.seehin.softwareclass.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * @Description 定时器重写
 * @Author Seehin
 * @Date 2020/5/4 19:48
 */
@Component("myExtendsFactory")
public class MyExtendsFactory extends AdaptableJobFactory {

    private AutowireCapableBeanFactory autowireCapableBeanFactory;
    @Autowired
    public void setAutowireCapableBeanFactory(AutowireCapableBeanFactory autowireCapableBeanFactory){
        this.autowireCapableBeanFactory = autowireCapableBeanFactory;
    }

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object obj = super.createJobInstance(bundle);
        this.autowireCapableBeanFactory.autowireBean(obj);
        return obj;
    }
}
