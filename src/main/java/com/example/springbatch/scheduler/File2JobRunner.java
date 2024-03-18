package com.example.springbatch.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Random;

@Component
public class File2JobRunner extends JobRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {

        String[] sourceArgs = args.getSourceArgs();
        JobDetail jobDetail = buildJobDetail(File2SchJob.class, "hello2Job", "batch", new HashMap());
        Trigger trigger = (Trigger) buildJobTrigger("0/10 * * * * ?");
        int result = new Random().nextInt();
        jobDetail.getJobDataMap().put("requestDate", String.valueOf(result));

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
