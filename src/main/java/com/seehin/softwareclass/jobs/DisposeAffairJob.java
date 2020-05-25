package com.seehin.softwareclass.jobs;

import com.seehin.softwareclass.service.impl.ListServiceImpl;
import com.seehin.softwareclass.utils.GetTimeNowUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description 定时调度
 * @Author Seehin
 * @Date 2020/5/4 19:28
 */
public class DisposeAffairJob implements Job {

    private final Logger logger = LoggerFactory.getLogger(DisposeAffairJob.class);

    ListServiceImpl listService;
    @Autowired
    public void setListService(ListServiceImpl listService){
        this.listService = listService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        GetTimeNowUtil getTimeNowUtil = new GetTimeNowUtil();
        logger.info("调度处理事务任务时间："+ getTimeNowUtil.getTimestampOfString());
        listService.disposeAffairs();
    }
}
