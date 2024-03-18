package com.example.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class SimpleJobConfigration {

    @Bean(name = "hello1Job")
    public Job hello1Job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("hello1Job", jobRepository)
                .start(helloStep1(jobRepository, transactionManager))
                .incrementer(new RunIdIncrementer())
                .build();

    }

    @Bean(name = "helloStep1")
    public Step helloStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("hello1Step", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("================");
                    System.out.println(">> hello1Step ");
                    System.out.println("================");
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Hello1Step Ing.." + i);
                        Thread.sleep(1000);
                    }
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }


    @Bean(name = "hello2Job")
    public Job hello2Job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("hello2Job", jobRepository)
                .start(helloStep2(jobRepository, transactionManager))
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean(name = "helloStep2")
    public Step helloStep2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("hello2Step", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("================");
                    System.out.println(">> hello2Step ");
                    System.out.println("================");
                    for (int i = 0; i < 10; i++) {
                        System.out.println("Hello2Step Ing.." + i);
                        Thread.sleep(1000);
                    }
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();


    }
}
